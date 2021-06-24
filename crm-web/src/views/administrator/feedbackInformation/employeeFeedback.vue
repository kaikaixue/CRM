<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar>员工反馈</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="primary" small width="60px" dark @click="toView(item)">查看</v-btn>
			</template>
		</v-data-table>
		<!-- 查看反馈对话框 -->
		<v-dialog v-model="isShow" max-width="300">
			<v-card>
				<v-card-title>详细反馈</v-card-title>
				<v-card-text>{{text}}</v-card-text>
			</v-card>
		</v-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				// 表头
				headers: [{
						text: "员工编号",
						value: "user_id"
					},
					{
						text: "员工名称",
						value: "name"
					},
					{
						text: "员工部门",
						value: "department"
					},
					{
						text: "员工类型",
						value: "type"
					},
					{
						text: "操作",
						value: "actions",
						sortable: false
					}
				],
				// 表体
				desserts: [],
				// 控制查看反馈对话框
				isShow: false,
				// 反馈信息
				text: "",
			}
		},

		created() {
			// 权限
			if (localStorage.getItem("marker") == 0) {
				// 请求查询员工反馈
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllFeedback',
					data: {
						marker: 1
					}
				}).then((res) => {
					// 将员工反馈的对象数组存入desserts
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
			// 查看反馈方法
			toView(item) {
				// 打开查看反馈对话框
				this.isShow = true
				// 得到该条反馈的索引
				const index = this.desserts.indexOf(item)
				// 将反馈信息存入text
				this.text = this.desserts[index].information
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
