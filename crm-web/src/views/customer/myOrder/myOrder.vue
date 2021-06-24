<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar flat color="white">
					<v-toolbar-title>我的订单</v-toolbar-title>
					<v-divider class="mx-4" inset vertical></v-divider>
					<v-spacer></v-spacer>
					<v-btn color="primary" dark class="mb-2" @click="dialog = true">添加订单</v-btn>
				</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<v-btn v-show="item.feedbackStatus" text small width="20px">已反馈</v-btn>
				<v-btn color="blue" small dark @click="feedback(item)" v-show="!item.feedbackStatus" width="20px">反馈</v-btn>
				<v-btn color="red" small dark style="margin-left: 10px;" @click="deleteItem(item)" width="20px">取消</v-btn>
			</template>
		</v-data-table>
		<!-- 订单已完成不可反馈对话框 -->
		<v-dialog v-model="isCancel" max-width="300">
			<v-card>
				<v-card-title>
					<span class="headline">提示</span>
				</v-card-title>
				<v-card-text>此订单已完成，无法取消订单</v-card-text>
			</v-card>
		</v-dialog>
		<!-- 订单未完成不可反馈对话框 -->
		<v-dialog v-model="isFeedback" max-width="300">
			<v-card>
				<v-card-title>提示</v-card-title>
				<v-card-text>此订单未完成，无法反馈</v-card-text>
			</v-card>
		</v-dialog>
		<!-- 反馈对话框 -->
		<v-dialog v-model="summitFeedback" max-width="290" persistent>
			<v-card>
				<v-card-title>
					<span class="headline">提交反馈</span>
				</v-card-title>
				<v-card-text>
					<v-textarea v-model="feedbackText" label="请输入反馈信息"></v-textarea>
				</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="green" text @click="cancelFeedback()" text>取消</v-btn>
					<v-btn color="red" text @click.stop="ensureFeedback()" text>确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
		<!-- 删除对话框 -->
		<v-dialog v-model="isDelete" persistent max-width="290">
			<v-card>
				<v-card-title>
					<span>确认删除</span>
				</v-card-title>
				<v-card-text>是否确定删除此订单？</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="green" text @click="cancel()" text>取消</v-btn>
					<v-btn color="red" text @click.stop="ensure()" text>确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
		<!-- 添加订单对话框 -->
		<v-dialog v-model="dialog" max-width="300px">
			<v-card>
				<v-card-title>
					<span class="headline">添加订单</span>
				</v-card-title>
				<v-card-text>
					<v-col cols="12">
						<v-select menu-props="auto,overflowY" :items="product" v-model="productName" outlined
							label="选择产品">
						</v-select>
					</v-col>
					<v-col cols="12">
						<v-text-field v-model="productNumber" label="数量"></v-text-field>
					</v-col>
					<v-col cols="12">
						<v-text-field v-model="productPrice" label="单价" readonly></v-text-field>
					</v-col>
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
				item: {},
				feedbackItem: {},
				feedbackText: "",
				id: -1,
				dialog: false,
				items: [{
						text: "水果",
						list: ["苹果", "香蕉"]
					},
					{
						text: "电器",
						list: ["苹果", "香蕉"]
					},
					{
						text: "化妆品",
						list: ["苹果", "香蕉"]
					},
				],
				headers: [{
						text: '订单编号',
						value: 'orderId'
					},
					{
						text: '商品名',
						value: 'productName'
					},
					{
						text: '数量',
						value: 'number'
					},
					{
						text: '单价',
						value: 'unitPrice'
					},
					{
						text: '总价',
						value: 'totalPrice'
					},
					{
						text: '雇员编号',
						value: 'employeeId'
					},
					{
						text: '订单状态',
						value: 'orderStatus'
					},
					{
						text: '操作',
						value: 'actions',
						sortable: false
					},
				],
				desserts: [],
				addIndex: -1,
				isDelete: false,
				flag: false,
				newItem: [],
				product: [],
				// types:[],
				// values:[],
				productNumber: 1,
				productName: "",
				productPrice: "",
				productId: "",
				isFeedback: false,
				summitFeedback: false,
				isCancel: false,
			}
		},

		watch: {
			productName: function(value) {
				for (let i = 0; i < this.newItem.length; i++) {
					if (value == this.newItem[i].name) {
						this.productPrice = this.newItem[i].price
						this.productId = this.newItem[i].id
					}
				}
			}
		},

		created() {
			if (localStorage.getItem("marker") == 2) {
				console.log(this.$route.params)
				this.id = localStorage.getItem("id");
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllOrder',
					data: {
						userId: this.id
					}
				}).then((res) => {
					console.log(res)
					this.desserts = res.data.data
					console.log(this.desserts)
					this.number = this.desserts.length
				})
				this.$axios({
					method: 'post',
					url: this.$url + 'queryBuyAllProduct',
					data: {

					}
				}).then((res) => {
					console.log(res)
					this.newItem = res.data.data
					// console.log(this.newItem)
					for (let i = 0; i < this.newItem.length; i++) {
						this.product.push(this.newItem[i].name)
					}
					// console.log(this.product)
					// for(let i in this.product){
					// 	this.types.push(i) 
					// 	this.values.push(this.product[i])
					// }
					// console.log(this.types)
					// console.log(this.values)
				})
			} else {
				this.$router.push({
					name: '404'
				})
			}


		},

		methods: {
			//打开提交反馈对话框
			feedback(item) {
				if (item.orderStatus == "已完成") {
					this.feedbackItem = item
					this.summitFeedback = true
				} else {
					this.isFeedback = true
				}
			},

			//取消提交反馈
			cancelFeedback() {
				this.summitFeedback = false
			},

			//确认提交反馈
			ensureFeedback() {
				const index = this.desserts.indexOf(this.feedbackItem)
				this.desserts[index].feedbackStatus = true
				this.$axios({
					method: 'post',
					url: this.$url + 'addFeedback',
					data: {
						information: this.feedbackText,
						userId: localStorage.getItem("id"),
						orderId: this.feedbackItem.orderId
					}
				})
				this.cancelFeedback()
			},

			editItem(item) {
				this.editedIndex = this.desserts.indexOf(item)
				this.editedItem = Object.assign({}, item)
				// this.dialog = true
			},

			close() {
				this.dialog = false;
			},

			save() {
				this.$axios({
					method: 'post',
					url: this.$url + 'addOrder',
					data: {
						number: this.productNumber,
						productId: this.productId,
						userId: this.id
					}
				}).then((res) => {
					console.log(res)
					let newList = res.data.data
					var addItem = {
						orderId: "",
						productName: "",
						number: "",
						unitPrice: "",
						totalPrice: "",
						employeeId: "",
						orderStatus: "",
					}
					addItem.orderId = newList.orderId
					addItem.productName = newList.productName
					addItem.number = newList.number
					addItem.unitPrice = newList.unitPrice
					addItem.totalPrice = newList.totalPrice
					addItem.employeeId = newList.employeeId
					addItem.orderStatus = newList.orderStatus
					this.desserts.push(addItem)
					this.productNumber = 1
					this.productPrice = ""
				})
				this.close()
			},

			deleteItem(item) {
				if (item.orderStatus == "已完成") {
					this.isCancel = true
				} else {
					this.isDelete = true
					this.item = item
				}
			},

			ensure() {
				const index = this.desserts.indexOf(this.item)
				this.desserts.splice(index, 1)
				this.$axios({
					method: 'post',
					url: this.$url + 'deleteUserOrder',
					data: {
						orderId: this.item.orderId
					}
				})
				this.isDelete = false
			},

			cancel() {
				this.flag = false
				this.isDelete = false
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
