<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar>员工管理</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="blue" small dark @click="editItem(item)">编辑</v-btn>
				<v-btn color="red" small dark style="margin-left: 10px;" @click="deleteEmployee(item)">删除</v-btn>
			</template>
		</v-data-table>
		<!-- 删除员工 -->
		<v-dialog v-model="isDelete" max-width="300">
			<v-card>
				<v-card-title>
					<span class="headline">确认删除</span>
				</v-card-title>
				<v-card-text>是否确定删除该员工？</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="red darken-1" text @click="cancel()">取消</v-btn>
					<v-btn color="blue darken-1" text @click="sure()">确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
		<!-- 编辑员工个人信息 -->
		<v-dialog v-model="dialog" max-width="450px">
			<v-card>
				<v-card-title>
					<span class="headline">编辑</span>
				</v-card-title>
				<v-card-text>
					<v-text-field v-model="editedItem.id" readonly label="员工编号"></v-text-field>
				</v-card-text>
				<v-card-text>
					<v-text-field v-model="editedItem.name" readonly label="员工姓名"></v-text-field>
				</v-card-text>
				<v-card-text>
					<v-text-field v-model="editedItem.phone" readonly label="手机号"></v-text-field>
				</v-card-text>
				<v-card-text>
					<v-text-field v-model="editedItem.wages" label="工资"></v-text-field>
				</v-card-text>
				<v-card-text>
					<v-select v-model="editedItem.department" label="部门" :items="departmentList"></v-select>
				</v-card-text>
				<v-card-text>
					<v-select v-model="editedItem.type" label="类型" :items="typeList"></v-select>
				</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="red darken-1" text @click="close()">取消</v-btn>
					<v-btn color="blue darken-1" text @click="save()">确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				//表头
				headers: [{
						text: "员工编号",
						value: "id"
					},
					{
						text: "姓名",
						value: "name"
					},
					{
						text: "账号",
						value: "account"
					},
					{
						text: "手机号",
						value: "phone"
					},
					{
						text: "工资",
						value: "wages"
					},
					{
						text: "所属部门",
						value: "department"
					},
					{
						text: "类型",
						value: "type"
					},
					{
						text: "操作",
						value: "actions",
						sortable: false
					}
				],
				//表体
				desserts: [],
				//控制删除员工对话框
				isDelete: false,
				// 编辑员工信息模板
				editedItem: {
					id: "",
					name: "",
					account: "",
					phone: "",
					wages: "",
					department: "",
					type: "",
				},
				// 修改的某条数据在表体中的索引
				editedIndex: -1,
				// 控制编辑员工信息对话框
				dialog: false,
				// 存放该员工的基本信息
				information: {},
				// 中间商
				item: {},
				// 存放所有部门
				departmentList: [],
				// 存放所有员工类型
				typeList: [],
			}
		},

		created() {
			// 判断权限
			if (localStorage.getItem("marker") == 0) {
				// 请求查询所有雇员接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllUser',
					data: {
						marker: 1
					}
				}).then((res) => {
					// 将查询到的员工对象数组存入desserts
					this.desserts = res.data.data
				})
			} else {
				//权限不对,跳转到404
				this.$router.push({
					name: '404'
				})
			}
		},

		methods: {
			// 删除员工方法
			deleteEmployee(item) {
				// 将该条数据存入中间商
				this.item = item
				// 打开确认删除对话框
				this.isDelete = true
			},

			//取消删除员工
			cancel() {
				// 关闭删除员工对话框
				this.isDelete = false
			},

			// 确认删除员工方法
			sure() {
				// 利用中间商来获取在desserts中的索引
				const index = this.desserts.indexOf(this.item)
				// 将该条数据在desserts中删除
				this.desserts.splice(index, 1)
				// 请求删除用户接口
				this.$axios({
					method: 'post',
					url: this.$url + 'deleteUser',
					data: {
						marker: 1,
						userId: this.item.id
					}
				})
				// 关闭删除对话框
				this.cancel()
			},

			// 修改员工信息方法
			editItem(item) {
				// 获取该条数据在desserts中的索引
				this.editedIndex = this.desserts.indexOf(item)
				// 将更新数据拷贝到原数据
				this.editedItem = Object.assign({}, item)
				// 请求查询所有部门接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllDepartment',
					data: {

					}
				}).then((res) => {
					// 循环遍历将部门全部存入departmentList数组
					for (let i = 0; i < res.data.data.length; i++) {
						this.departmentList.push(res.data.data[i].department)
					}
				})
				// 请求获取所有产品类型接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllEmployeeType',
					data: {

					}
				}).then((res) => {
					// 将所有产品类型存入typeList
					for (let i = 0; i < res.data.data.length; i++) {
						this.typeList.push(res.data.data[i].type)
					}
				})
				// 打开修改对话框
				this.dialog = true
			},

			//取消修改员工信息方法
			close() {
				// 关闭修改对话框
				this.dialog = false
			},

			// 确认修改员工信息方法
			save() {
				if (this.editedIndex > -1) {
					// 将修改后的对象拷贝原数组中的位置
					Object.assign(this.desserts[this.editedIndex], this.editedItem)
					// 请求查询查询用户信息接口
					this.$axios({
						method: 'post',
						url: this.$url + 'queryUserId',
						data: {
							userId: this.editedItem.id
						}
					}).then((res) => {
						// 将该用户的信息存入information
						this.information = res.data.data
					})
					// 请求更新用户信息接口
					this.$axios({
						method: 'post',
						url: this.$url + 'updateUser',
						data: {
							"account": this.editedItem.account,
							"address": this.information.address,
							"avatar": this.information.address,
							"cred": this.information.cred,
							"department": this.editedItem.department,
							"marker": 1,
							"name": this.editedItem.name,
							"password": this.information.password,
							"phone": this.editedItem.phone,
							"salt": this.information.salt,
							"sex": this.information.sex,
							"speed_time": "",
							"type": this.editedItem.type,
							"userId": this.editedItem.id,
							"wages": this.editedItem.wages
						}
					})
				}
				// 关闭修改对话框
				this.close()
			}
		}
	}
</script>

<style scoped>
	* {
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}

	.total {
		width: 100%;
		height: 100%;
	}
</style>
