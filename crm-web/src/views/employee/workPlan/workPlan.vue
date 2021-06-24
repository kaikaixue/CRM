<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1" sort-by="actions">
			<template v-slot:top>
				<v-toolbar>
					<v-toolbar-title>工作计划</v-toolbar-title>
				</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="blue" small width="60px" dark @click="toView(item)" v-if="!item.status">提交</v-btn>
				<h4 v-else style="font-weight: 400;">已提交</h4>
				<v-dialog v-model="isShow" max-width="300px">
					<v-card>
						<v-card-title>提交计划</v-card-title>
						<v-card-text>
							计划编号：{{planId}}
						</v-card-text>
						<v-card-text>
							计划名称：{{planName}}
						</v-card-text>
						<v-card-text>
							计划时间：{{startTime}}~{{endTime}}
						</v-card-text>
						<v-card-text>
							实际业绩：{{actualPerformance}}
						</v-card-text>
						<v-card-text>
							计划业绩：{{targetPerformance}}
						</v-card-text>
						<v-card-text>
							产品：{{product}}
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="red" @click="cancel()" text>取消</v-btn>
							<v-btn color="blue" @click="summit(item)" text>提交</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</template>
		</v-data-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				// 控制提交对话框
				isShow: false,
				//存放计划id
				planId: "",
				// 存放计划名称
				planName: "",
				// 计划开始时间
				startTime: "",
				//计划结束时间
				endTime: "",
				// 实际业绩
				actualPerformance: "",
				//目标业绩
				targetPerformance: "",
				// 表头
				headers: [{
						text: "计划编号",
						value: "planId"
					},
					{
						text: "计划名称",
						value: "planName"
					},
					{
						text: "实际利润",
						value: "actualPerformance"
					},
					{
						text: "计划利润",
						value: "targetPerformance"
					},
					{
						text: "计划结束时间",
						value: "endTime"
					},
					{
						text: "操作",
						value: "actions",
					}
				],
				//存放产品名
				product: [],
				//表体
				desserts: [],
			}
		},

		watch: {
			// 监听对话框是否关闭,如关闭清空产品数组
			isShow: function(value) {
				if (value == false) {
					this.product.splice(0, this.product.length)
				}
			}
		},

		methods: {
			// 提交对话框方法
			toView(item) {
				// 打开提交对话框
				this.isShow = true
				// 得到该条数据的索引
				const index = this.desserts.indexOf(item)
				// 计划id存入planId
				this.planId = this.desserts[index].planId
				// 计划名称存入planName
				this.planName = this.desserts[index].planName
				// 目标业绩存入targetPerformance
				this.targetPerformance = this.desserts[index].targetPerformance
				// 实际业绩存入actualPerformance
				this.actualPerformance = this.desserts[index].actualPerformance
				// 遍历产品表,将产品名存入product数组
				for (let i = 0; i < this.desserts[index].productBeanList.length; i++) {
					this.product.splice(i, 0, this.desserts[index].productBeanList[i].name)
				}
				// 开始时间存入startTime
				this.startTime = this.desserts[index].startTime
				// 结束时间存入endTime
				this.endTime = this.desserts[index].endTime
			},

			// 关闭提交对话框
			cancel() {
				this.isShow = false
			},

			// 确认提交
			summit(item) {
				// 得到该条数据的索引
				const index = this.desserts.indexOf(item)
				// 将该条数据的状态设为已提交
				this.desserts[index].status = true
				// 请求更新员工计划接口
				this.$axios({
					method: 'post',
					url: this.$url + 'updateEmployeePlan',
					data: {
						planId: this.planId
					}
				})
				// 关闭确认提交对话框
				this.cancel()
			}
		},

		created() {
			// 判断权限
			if (localStorage.getItem("marker") == 1) {
				// 得到用户id
				this.id = localStorage.getItem("id")
				// 请求查询雇员计划接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryEmployeePlan',
					data: {
						employeeId: this.id
					}
				}).then((res) => {
					// 将回参存入表体
					this.desserts = res.data.data
				})
			} else {
				// 权限不对,跳转404
				this.$router.push({
					name: '404'
				})
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
