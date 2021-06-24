<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts" class="elevation-1">
			<template v-slot:top>
				<v-toolbar flat color="white">
					<v-toolbar-title>产品管理</v-toolbar-title>
					<v-spacer></v-spacer>
					<v-btn color="primary" dark class="mb-2" @click="isShow = true">添加产品</v-btn>
				</v-toolbar>
			</template>
			<!-- <template v-slot:item.actions="{ item }">
				<v-icon @click="deleteItem(item)" small>mdi-delete</v-icon>
			</template> -->
		</v-data-table>
		<!-- 删除对话框 -->
		<!-- <v-dialog v-model="isDelete" persistent max-width="290">
			<v-card>
				<v-card-title>
					<span>确认删除</span>
				</v-card-title>
				<v-card-text>是否确定删除此产品？</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="green" text @click="close()" text>取消</v-btn>
					<v-btn color="red" text @click.stop="ensure()" text>确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog> -->

		<!-- 添加产品对话框 -->
		<v-dialog v-model="isShow" max-width="350">
			<v-card>
				<v-card-title>
					<span>添加产品</span>
				</v-card-title>
				<v-card-text>
					<v-col cols="12">
						<v-text-field v-model="name" label="名称"></v-text-field>
					</v-col>
					<v-col cols="12">
						<v-select v-model="type" label="类型" :items="productType"></v-select>
					</v-col>
					<v-col cols="12">
						<v-text-field v-model="price" label="价格"></v-text-field>
					</v-col>
					<!-- <v-col cols="12">
						<v-text-field v-model="number" label="数量"></v-text-field>
					</v-col> -->
					<v-col cols="12">
						<v-menu offset-y transition="scale-transition" ref="menu">
							<template v-slot:activator="{on,attrs}">
								<v-text-field v-model="product_time" label="生产日期" v-bind="attrs" v-on="on">
								</v-text-field>
							</template>
							<v-date-picker v-model="product_time" no-title scrollable>
								<v-spacer></v-spacer>
								<v-btn text color="red" @click="menu = false">取消</v-btn>
								<v-btn text color="primary" @click="$refs.menu.save(date)">确定</v-btn>
							</v-date-picker>
						</v-menu>
					</v-col>
				</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn color="green" @click="cancel()" text>取消</v-btn>
					<v-btn color="red" @click="sure()" text>确定</v-btn>
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
						text: "产品编号",
						value: "id"
					},
					{
						text: "产品名称",
						value: "name"
					},
					{
						text: "生产日期",
						value: "product_time"
					},
					{
						text: "产品类型",
						value: "type"
					},
					{
						text: "价格",
						value: "price"
					},
				],
				// 表体
				desserts: [],
				// 控制添加产品对话框
				isShow: false,
				// 控制删除对话框
				// isDelete: false,
				// 双向绑定输入框
				name: "",
				type: "",
				price: "",
				number: "",
				product_time: new Date().toISOString().substr(0, 10),
				//删除产品是用的中间商
				item: {},
				// 存放所有产品类型的数组
				productType: [],
			}
		},

		created() {
			//权限
			if (localStorage.getItem("marker") == 0) {
				// 请求获取所有产品接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllProduct'
				}).then((res) => {
					this.desserts = res.data.data
				})
				// 请求查询所有产品类型接口
				this.$axios({
					method: 'post',
					url: this.$url + 'queryAllProductType'
				}).then((res) => {
					// 遍历回参的对象数组,将type添加到productType数组
					for (let i = 0; i < res.data.data.length; i++) {
						this.productType.push(res.data.data[i].type)
					}
				})
			} else {
				// 权限不对,跳转404
				this.$router.push({
					name: '404'
				})
			}
		},

		methods: {
			// deleteItem(item){
			// 	this.isDelete = true
			// 	this.item = item
			// },

			// close(){
			// 	this.isDelete = false
			// },

			// ensure(){
			// 	const index = this.desserts.indexOf(this.item)
			// 	this.desserts.splice(index, 1)
			// 	this.$axios({
			// 		method: 'post',
			// 		url: this.$url + 'deleteUserOrder',
			// 		data: {
			// 			orderId: this.item.orderId
			// 		}
			// 	})
			// 	this.isDelete = false
			// },

			//关闭添加产品对话框
			cancel() {
				this.isShow = false
			},

			// 确认添加产品方法
			sure() {
				// 请求增加产品接口
				this.$axios({
					method: 'post',
					url: this.$url + 'insertProduct',
					data: {
						image: "",
						name: this.name,
						price: this.price,
						type: this.type,
						productTime: this.product_time,
					}
				}).then((res) => {
					//将增加的产品的回参存入newList
					const newList = res.data.data
					// 构造一个添加的产品的模板
					var addItem = {
						id: "",
						name: "",
						product_time: "",
						type: "",
						price: "",
					}
					// 将添加的产品的值存入模板
					addItem.id = newList.id
					addItem.name = newList.name
					addItem.type = newList.type
					addItem.price = newList.price
					// addItem.number = newList.number
					addItem.product_time = newList.product_time
					// 将模板存入表格中
					this.desserts.push(addItem)
					// 初始化模板
					this.name = ""
					this.type = ""
					this.price = ""
					// this.number = ""
					this.product_time = ""
				})
				// 关闭添加产品对话框
				this.cancel()
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
