<template>
	<div class="total">
		<!-- topbar -->
		<topbar style="height: 10%;" :userName="userName"></topbar>
		<div class="mid">
			<!-- sidebar -->
			<!-- 左部列表 -->
			<div class="sidebar">
				<v-list expand>
					<v-list-group v-for="item in items[marker]" :prepend-icon="item.icon" color="white">
						<template v-slot:activator>
							<v-list-item-title>{{item.text}}</v-list-item-title>
						</template>
						<div v-for="(list, index) in item.list" :key="index" @click="enter(list)" tag="div">
							<v-list-item link>
								<v-list-item-title style="color: white;">{{list.text}}</v-list-item-title>
							</v-list-item>
						</div>
					</v-list-group>
				</v-list>
			</div>
			<!-- blank -->
			<!-- 右侧开辟了一块区域，使用router-view来切换 -->
			<div class="blank">
				<router-view>
				</router-view>
			</div>
		</div>
	</div>
</template>

<script>
	import topbar from '../../components/TopBar.vue'
	export default {
		data() {
			return {
				//登录进来的用户id
				id: -1,
				//登录进来的用户标识
				marker: -1,
				//用户账号
				account: "",
				//用户名
				userName: "",
				//左侧列表数组，根据用户标识来选择展示哪一个
				items: [
					[{
							id: 0,
							text: "个人信息",
							list: [{
								text: "基本信息",
								router: "BasicInformation"
							}],
							icon: "mdi-account-circle"
						},
						{
							id: 1,
							text: "用户管理",
							list: [{
								text: "客户管理",
								router: "CustomerManagement"
							}, {
								text: "员工管理",
								router: "EmployeeManagement"
							}],
							icon: "mdi-account-switch"
						},
						{
							id: 2,
							text: "营销管理",
							list: [{
									text: "产品管理",
									router: "ProductManagement"
								},
								{
									text: "发布任务计划",
									router: "PlanManagement"
								}
							],
							icon: "mdi-google-analytics"
						},
						{
							id: 3,
							text: "查看反馈",
							list: [{
								text: "用户反馈",
								router: "CustomerFeedback"
							}, {
								text: "员工反馈",
								router: "EmployeeFeedback"
							}],
							icon: "mdi-comment-text-multiple-outline"
						}
					],
					[{
							id: 0,
							text: "个人信息",
							list: [{
								text: "基本信息",
								router: "BasicInformation"
							}],
							icon: "mdi-account-circle"
						},
						{
							id: 1,
							text: "工作情况",
							list: [{
								text: "业绩情况",
								router: "Performance"
							}, {
								text: "工作计划",
								router: "WorkPlan"
							},{
								text: "订单处理",
								router: "Order"
							}],
							icon: "mdi-clipboard-check"
						},
						{
							id: 2,
							text: "反馈信息",
							list: [{
								text: "我要反馈",
								router: "Feedback2"
							}],
							icon: "mdi-comment-text-multiple-outline"
						}
					],
					[{
							id: 0,
							text: "个人信息",
							list: [{
								text: "基本信息",
								router: "BasicInformation"
							}, {
								text: "修改密码",
								router: "ChangePassword"
							}],
							icon: "mdi-account-circle"
						},
						{
							id: 1,
							text: "消费情况",
							list: [{
								text: "我的订单",
								router: "MyOrder"
							}],
							icon: "mdi-alarm-panel-outline"
						},
						{
							id: 2,
							text: "反馈信息",
							list: [{
								text: "我要反馈",
								router: "Feedback1"
							}],
							icon: "mdi-comment-text-multiple-outline"
						}
					]
				]
			}
		},

		methods: {
			enter(item) {
				this.$router.push({
					name: item.router,
					params: {
						id: this.id,
						marker:this.marker,
						account:this.account
					}
				})
			}
		},
		
		watch:{
			userName:function(value){
				value = JSON.parse(localStorage.getItem("userInfo")).name
			}
		},

		created() {
			//判断是第一次进入页面还是手动刷新
			if (window.name == "") {
				//接收登录页面通过路由传参的值
				this.marker = this.$route.params.marker
				this.account = this.$route.params.account
				this.id = this.$route.params.id
				this.userName = this.$route.params.name
				//将用户的信息存入浏览器数据库
				localStorage.setItem("account", this.account)
				localStorage.setItem("id", this.id)
				localStorage.setItem("marker", this.marker)
				localStorage.setItem("userName",this.userName)
				//对window.name设置值，以便下一次判断标识为手动刷新
				window.name = "isReload"
			}
			//从浏览器数据库获取值
			this.account = localStorage.getItem("account")
			this.marker = localStorage.getItem("marker")
			this.userName = JSON.parse(localStorage.getItem("userInfo")).name
			this.id = localStorage.getItem("id")
			//根据登录标识，拿到对应的数组
			let newList = this.items[localStorage.getItem("marker")]
			this.enter(newList[0].list[0])
		},
		
		//引用组件
		components: {
			topbar
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

	.mid {
		width: 100%;
		height: 90%;
		display: flex;
	}

	.sidebar {
		width: 15%;
		height: 100%;
		background-color: #393D49;
	}

	.blank {
		width: 85%;
		height: 100%;
	}

	.v-list-group {
		text-align: center;
		background-color: #393D49;
	}

	.v-list-item {
		text-align: center;
	}
	
	.act{
		color: white;
	}
</style>
