<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar>
					<v-toolbar-title>工作计划</v-toolbar-title>
					<v-spacer></v-spacer>
					<v-btn color="primary" dark class="mb-2" @click="addPlan()">添加计划</v-btn>
				</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="blue" small style="color: white;" @click="toView(item)">查看</v-btn>
			</template>
		</v-data-table>
		<!-- 查看详细计划 -->
		<v-dialog v-model="detailDialog" max-width="300">
			<v-card>
				<v-card-title>详细计划</v-card-title>
				<v-card-text>
					计划编号：{{viewPlanId}}
				</v-card-text>
				<v-card-text>
					员工编号：{{viewEmployeeId}}
				</v-card-text>
				<v-card-text>
					计划时间：{{viewPlanTime}}
				</v-card-text>
				<v-card-text>
					完成情况：{{viewStatus}}
				</v-card-text>
			</v-card>
		</v-dialog>
		<!-- 添加计划对话框 -->
		<v-dialog v-model="planDialog" max-width="400" persistent>
			<v-card>
				<v-card-title>添加计划</v-card-title>
				<v-card-text>
					<v-text-field label="计划名称" v-model="addPlanName"></v-text-field>
				</v-card-text>
				<v-card-text>
					<v-select :items="employee" v-model="selectEmployee" label="请选择员工">
					</v-select>
				</v-card-text>
				<v-card-text>
					<v-menu offset-y transition="scale-transition" ref="menu">
						<template v-slot:activator="{on,attrs}">
							<v-text-field v-model="addPlanTime" label="计划结束时间" v-bind="attrs" v-on="on">
							</v-text-field>
						</template>
						<v-date-picker v-model="addPlanTime" no-title scrollable>
							<v-spacer></v-spacer>
							<v-btn text color="red" @click="menu = false">取消</v-btn>
							<v-btn text color="primary" @click="$refs.menu.save(date)">确定</v-btn>
						</v-date-picker>
					</v-menu>
				</v-card-text>
				<v-card-text>
					<v-select :items="product" v-model="selectProduct" label="请选择产品"></v-select>
				</v-card-text>
				<v-card-text>
					<v-text-field label="数量" v-model="addNumber"></v-text-field>
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
				// 控制添加计划对话框
				planDialog: false,
				//控制查看详细计划对话框
				detailDialog: false,
				// 表头
				headers: [{
						text: "计划编号",
						value: "planId"
					},
					{
						text: "计划名称",
						value: "name"
					},
					{
						text: "员工编号",
						value: "employeeId"
					},
					{
						text: "计划时间",
						value: "planTime"
					},
					{
						text: "是否已完成",
						value: "status"
					},
					{
						text: "实施情况",
						value: "actions",
						sortable: false
					}
				],
				//表体
				desserts: [],
				// 存放查看详细计划对话框的数据
				viewPlanId: "",
				viewEmployeeId: "",
				viewPlanTime: "",
				viewStatus: "",
				//添加计划
				//存放所有雇员的id和姓名
				employee: [],
				// 存放所有的产品名
				product: [],
				// 存放添加的产品名
				addPlanName: "",
				// 存放产品生产日期
				addPlanTime: new Date().toISOString().substr(0, 10),
				// 存放所需卖的产品数量
				addNumber: "",
				//双向绑定选择的员工
				selectEmployee: "",
				//双向绑定选择的产品
				selectProduct: "",
				// 存放所有的产品id
				productId: [],
				// 存放所需卖的产品id
				addProductId: "",
			}
		},

		created() {
			// 判断权限
			if (localStorage.getItem("marker") == 0) {
				// 请求查询所有计划接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllPlan',
					data: {}
				}).then((res) => {
					// 将所有的计划对象数组存入desserts
					this.desserts = res.data.data
				})
			} else {
				// 权限不对,跳转404
				this.$router.push({
					name: '404'
				})
			}
		},

		methods: {
			// 添加计划方法
			addPlan() {
				// 请求查询所有员工接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllUser',
					data: {
						marker: 1
					}
				}).then((res) => {
					// 循环遍历回参员工对象数组,将员工的的id和姓名拼接好存入employee数组
					for (let i = 0; i < res.data.data.length; i++) {
						let str = res.data.data[i].id + "   " + res.data.data[i].name
						this.employee.push(str)
					}
				})
				// 请求查询所有产品接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryPlanAllProduct',
					data: {

					}
				}).then((res) => {
					// 循环遍历回参产品对象数组,将产品的名称存入product数组,产品id存入productId数组
					for (let i = 0; i < res.data.data.length; i++) {
						this.product.push(res.data.data[i].name)
						this.productId.push(res.data.data[i].id)
					}
				})
				// 打开添加计划对话框
				this.planDialog = true
			},

			// 取消添加计划方法
			close() {
				// 关闭添加计划对话框
				this.planDialog = false
				// 将默认数据设为空
				this.addNumber = ""
				this.addPlanName = ""
				this.addPlanTime = ""
				this.addProductId = ""
			},

			// 确认添加计划方法
			save() {
				// 将原来拼接的雇员分割成数组
				this.selectEmployee = this.selectEmployee.split(" ")
				// 循环遍历产品数组,找到选中的产品的id,存入addProductId
				for (let i = 0; i < this.product.length; i++) {
					if (this.product[i] == this.selectProduct) {
						this.addProductId = this.productId[i]
					}
				}
				// 请求增加计划接口
				this.$axios({
					method: 'post',
					url: this.$url + 'insertProductPlan',
					data: {
						employeeId: this.selectEmployee[0],
						list: [{
							number: this.addNumber,
							productId: this.addProductId
						}],
						name: this.addPlanName,
						planTime: this.addPlanTime
					}
				}).then((res) => {
					// 将回参的对象存入newList
					const newList = res.data.data
					// 构造了一个增加的计划模板
					var list = {
						planId: "",
						name: "",
						employeeId: "",
						planTime: "",
						status: "",
					}
					// 对这个模板赋值
					list.planId = newList.planId
					list.name = newList.name
					list.employeeId = newList.employeeId
					list.planTime = newList.planTime
					list.status = newList.status
					// 将模板添加到表体中
					this.desserts.push(list)
				})
				// 关闭添加计划对话框
				this.close()
			},

			// 查看详细计划对话框
			toView(item) {
				// 打开详细计划对话框
				this.detailDialog = true
				// 获取该条计划的索引
				const index = this.desserts.indexOf(item)
				// 计划id存入viewPlanId
				this.viewPlanId = this.desserts[index].planId
				// employeeId存入viewEmployeeId
				this.viewEmployeeId = this.desserts[index].employeeId
				// planTIme存入viewPlanTime
				this.viewPlanTime = this.desserts[index].planTime
				// status存入viewStatus
				this.viewStatus = this.desserts[index].status
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
