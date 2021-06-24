<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar>
					<v-toolbar-title>我的业绩</v-toolbar-title>
				</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="blue" small width="60px" dark @click="toView(item)">查看</v-btn>
				<!-- 查看详细计划对话框 -->
				<v-dialog v-model="isShow" max-width="300px">
					<v-card>
						<v-card-title>查看计划</v-card-title>
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
							计划业绩：{{targetPerformance}}
						</v-card-text>
						<v-card-text>
							产品：{{product}}
						</v-card-text>
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
				planId: -1,
				planName: "",
				targetPerformance: "",
				startTime: "",
				endTime: "",
				product: [],
				// 控制查看计划对话框
				isShow: false,
				employeeId: -1,
				// 控制表头信息
				headers: [{
						text: "计划编号",
						value: "planId"
					},
					{
						text: "计划名称",
						value: "planName"
					},
					{
						text: "实际业绩",
						value: "actualPerformance"
					},
					{
						text: "目标业绩",
						value: "targetPerformance"
					},
					{
						text: "差距",
						value: "gap"
					},
					{
						text: "操作",
						value: "actions",
						sortable: false
					},
				],
				// 存放表体内容
				desserts: [],


			}
		},

		watch: {
			// 监听查看计划对话框是否关闭，如关闭就将product数组清空
			isShow: function(value) {
				if (value == false) {
					this.product.splice(0, this.product.length)
				}
			}
		},

		methods: {
			// 查看详细计划方法
			toView(item) {
				// 打开详细计划对话框
				this.isShow = true
				// 获取该条数据在表体数组中的索引
				const index = this.desserts.indexOf(item)
				// 得到计划id存入planId
				this.planId = this.desserts[index].planId
				// 得到计划名称存入planName
				this.planName = this.desserts[index].planName
				//得到计划业绩存入targetPerformance
				this.targetPerformance = this.desserts[index].targetPerformance
				//遍历需卖产品的对象数组
				for (let i = 0; i < this.desserts[index].productBeanList.length; i++) {
					// 将每个对象元素中的产品名称存入product数组
					this.product.splice(i, 0, this.desserts[index].productBeanList[i].name)
				}
				// 得到计划开始时间
				this.startTime = this.desserts[index].startTime
				//得到计划结束时间
				this.endTime = this.desserts[index].endTime
			}
		},

		created() {
			//权限
			if (localStorage.getItem("marker") == 1) {
				//得到用户id
				this.id = localStorage.getItem("id")
				// 请求查询员工计划接口
				this.$axios({
					method: 'post',
					url: this.$url + '/queryEmployeePlan',
					data: {
						employeeId: this.id
					}
				}).then((res) => {
					// 将工作计划存入表体
					this.desserts = res.data.data
				})
			} else {
				// 权限不够跳转404
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
		background-color: white;
	}
</style>
