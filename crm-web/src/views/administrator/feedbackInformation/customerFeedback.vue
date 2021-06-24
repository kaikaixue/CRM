<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar>用户反馈</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="primary" small width="60px" dark @click="toView(item)">查看</v-btn>
			</template>
		</v-data-table>
		<!-- 系统反馈 -->
		<v-dialog v-model="isShow" max-width="300">
			<v-card>
				<v-card-title>详细反馈</v-card-title>
				<v-card-text>{{text}}</v-card-text>
			</v-card>
		</v-dialog>
		<!-- 产品反馈 -->
		<v-dialog v-model="productShow" max-width="300">
			<v-card>
				<v-card-title>产品反馈</v-card-title>
				<v-card-text>产品id：{{productId}}</v-card-text>
				<v-card-text>产品名称：{{productName}}</v-card-text>
				<v-card-text>反馈信息：{{text}}</v-card-text>
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
						text: "用户编号",
						value: "user_id"
					},
					{
						text: "用户名称",
						value: "name"
					},
					{
						text: "反馈类型",
						value: "type"
					},
					{
						text: "处理情况",
						value: "tradition"
					},
					{
						text: "操作",
						value: "actions",
						sortable: false
					}
				],
				//表体内容
				desserts: [],
				// 控制系统反馈对话框
				isShow: false,
				// 控制产品反馈对话框
				productShow: false,
				//反馈信息
				text: "",
				// 产品id
				productId: "",
				// 产品名称
				productName: "",
			}
		},

		created() {
			// 判断权限
			if (localStorage.getItem("marker") == 0) {
				// 查询用户所有反馈
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllFeedback',
					data: {
						marker: 2
					}
				}).then((res) => {
					// 将回参的反馈对象数组存入表体数组
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
			// 查看反馈
			toView(item) {
				// 得到该条数据的索引
				const index = this.desserts.indexOf(item)
				if (item.type == "系统反馈") {
					// 打开系统反馈的对话框
					this.isShow = true
					// 将反馈的信息存入text
					this.text = this.desserts[index].information
				} else {
					// 将产品id存入productId
					this.productId = this.desserts[index].productBean.id
					// 将产品名存入productName
					this.productName = this.desserts[index].productBean.name
					// 将反馈信息存入text
					this.text = this.desserts[index].information
					// 打开产品反馈对话框
					this.productShow = true
				}

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
