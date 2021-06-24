<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts">
			<template v-slot:top>
				<v-toolbar>客户管理</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn color="blue" small dark @click="toView(item)">查看</v-btn>
				<v-btn color="red" small dark @click="deleteCustomer(item)" style="margin-left: 10px;">删除</v-btn>
			</template>
		</v-data-table>
		<v-dialog v-model="isShow" max-width="1000px">
			<v-card>
				<v-card-title>查看订单</v-card-title>
				<v-card-text>
					<v-data-table :headers="top" :items="orders">
					</v-data-table>
				</v-card-text>
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
						text: "客户编号",
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
						text: "信用度",
						value: "cred"
					},
					{
						text: "地址",
						value: "address"
					},
					{
						text: "操作",
						value: "actions",
						sortable: false
					}
				],
				// 表体
				desserts: [],
				// 控制查看订单对话框
				isShow: false,
				// 查看某个用户所有订单的表头
				top: [{
						text: "订单编号",
						value: "orderId"
					},
					{
						text: "商品名",
						value: "productName"
					},
					{
						text: "数量",
						value: "number"
					},
					{
						text: "单价",
						value: "unitPrice"
					},
					{
						text: "总价",
						value: "totalPrice"
					},
					{
						text: "雇员编号",
						value: "employeeId"
					},
					{
						text: "订单状态",
						value: "orderStatus"
					}
				],
				// 某个用户所有订单的表体
				orders: []
			}
		},

		created() {
			// 权限
			if (localStorage.getItem("marker") == 0) {
				// 请求查询所有客户接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllUser',
					data: {
						marker: 2
					}
				}).then((res) => {
					//将所有客户的对象数组传入desserts
					this.desserts = res.data.data
				})
			} else {
				// 权限不对,跳转404
				this.$router.push({
					name: '404'
				})
			}
		},

		watch:{
			//监测查看用户订单是否关闭,如果关闭就清空用户订单orders这个数组
			isShow:function(value){
				if(value == false){
					this.orders.splice(0,this.orders.length)
				}
			}
		},

		methods: {
			// 查看用户订单方法
			toView(item) {
				// 获取该条数据的索引
				const index = this.desserts.indexOf(item)
				// 打开查看订单对话框
				this.isShow = true
				// 请求查询用户所有订单接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllOrder',
					data: {
						userId: this.desserts[index].id
					}
				}).then((res) => {
					// 将用户的所有订单对象数组存入orders
					this.orders = res.data.data
				})
			},

			// 删除用户方法
			deleteCustomer(item) {
				// 获取该条数据的索引
				const index = this.desserts.indexOf(item)
				// 删除用户
				this.$axios({
					method: 'post',
					url: this.$url + 'deleteUser',
					data: {
						userId: this.desserts[index].id,
						marker: 2
					}
				})
				// 删除该条数据
				this.desserts.splice(index, 1)
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
