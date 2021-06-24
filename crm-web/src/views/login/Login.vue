<template>
	<div class="total">
		<p class="p1">CRM</p>
		<p class="p2">客户管理系统</p>
		<!-- 登录面板 -->
		<div class="content">
			<div class="top">
				<h2>欢迎登录</h2>
			</div>
			<div class="mid">
				<v-form ref="form" class="v-form">
					<!-- 账号输入框 -->
					<v-col cols="12">
						<v-text-field prepend-icon="mdi-account" width="300px" v-model="account" label="账号" required
							color="white" :rules="[() => !!account || '请输入账号',checkAccount]"
							:error-messages="errorMessage1" @focus="change()">
						</v-text-field>
					</v-col>
					<!-- 密码输入框 -->
					<v-col cols="12">
						<v-text-field prepend-icon="mdi-lock" :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
							v-model="password" label="密码" required color="white" @click:append="show = !show"
							:type="show?'text':'password'" :rules="[() => !!password || '请输入密码',checkPassword]"
							:error-messages="errorMessage2" @focus="change()"></v-text-field>
					</v-col>
					<!-- 身份选择器 -->
					<v-col cols="12">
						<v-select :items="identity" v-model="select" label="请选择你的身份"
							prepend-icon="mdi-account-box-outline" @focus="change()">
						</v-select>
					</v-col>
				</v-form>
				<!-- 登录按钮 -->
				<v-btn width="70%" height="50rpx" color="#88B4DD" style="margin-top: 20px;" @click="login()">登录</v-btn>
				<!-- 点击注册按钮进入注册页面 -->
				<v-btn @click="register()" text color="white">注册</v-btn>
			</div>
			<div class="bottom">
				欢迎登录CRM管理系统
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				//密码框时显示 * 号还是text 
				show: false,
				//双向绑定账号密码
				account: "",
				password: "",
				//账号输入框错误提示
				errorMessage1: "",
				//密码输入框错误提示
				errorMessage2: "",
				//检测账号是否合法
				checkAccount: true,
				//检测密码是否合法
				checkPassword: true,
				//身份
				identity: ["管理员", "职员", "客户"],
				//双向绑定select选择器，默认选择客户
				select: "客户",
				//调用登录接口后返回的用户名
				name:"",
			}
		},

		computed: {
			form() {
				return {
					id:-1,
					account: this.account,
					password: this.password
				}
			}
		},

		methods: {
			//当输入框重新获得焦点时，将所有错误提示取消
			change() {
				this.checkAccount = true;
				this.checkPassword = true;
				this.errorMessage1 = ""
				this.errorMessage2 = ""
			},
			//登录接口
			login() {
				//检测表单内部所有元素的合法性
				let flag = this.$refs.form.validate();
				//判断选择器选中的用户身份
				if (this.select == "管理员") {
					this.marker = 0;
				} else if (this.select == "职员") {
					this.marker = 1;
				} else {
					this.marker = 2;
				}
				//如果表单内部元素全部合法，则调后端接口
				if (flag) {
					this.$axios({
						method: 'post',
						url: this.$url + 'login',
						data: {
							"account": this.account,
							"password": this.password,
							"marker": this.marker
						}
					}).then((res) => {
						//后端返回接口回参，根据回参判断登录是否通过
						if (res.data.code == "FAIL_CODE") {
							if (res.data.message == "用户不存在") {
								this.checkAccount = false
								this.errorMessage1 = res.data.message
							} else if (res.data.message == "账号或密码错误") {
								this.checkPassword = false
								this.errorMessage2 = res.data.message
							}
						} else if (res.data.code == "SUCCESS_CODE") {
							this.id = res.data.data.id
							this.name = res.data.data.name
							//将用户的所用信息转成对象存入浏览器数据库
							localStorage.setItem("userInfo",JSON.stringify(res.data.data))
							//进入子页面
							this.enter()
						}
					})
				} else {

				}
			},
			//跳转到注册页面
			register() {
				this.$router.push('/register')
			},

			//进入子页面
			enter() {
				this.$router.push({
					name: 'Index',
					params: {
						marker: this.marker,
						account: this.account,
						id: this.id,
						name: this.name,
					}
				})
			}
		}
	}
</script>

<style scoped>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}

	.btn-register {
		width: 60px;
		height: 30px;
		position: absolute;
		top: 3%;
		right: 3%;
		z-index: 2;
		border-radius: 5px;
		color: white;
	}

	.total {
		width: 100%;
		height: 100%;
		position: absolute;
		background-image: url(../../static/image/bc2.jpg);
		background-size: 100% 100%;
		background-repeat: no-repeat;
		z-index: 0;
	}

	.v-form {
		width: 80%;
		height: 240px;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
	}

	.p1 {
		margin-top: 12%;
		margin-left: 12%;
		font-size: 75px;
		color: white;
		letter-spacing: 20px;
	}

	.p2 {
		font-size: 60px;
		color: white;
		margin-left: 12%;
		letter-spacing: 10px;
	}

	.content {
		width: 400px;
		height: 450px;
		position: absolute;
		top: 20%;
		left: 50%;
		transform: translateX(75%);
		background-color: rgba(0, 0, 0, 0.1);
		z-index: 1;
		box-shadow: -8px 8px 10px 4px dimgray;
	}

	.top {
		width: 100%;
		height: 20%;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.top h2 {
		color: white;
		font-weight: 300;
	}

	.mid {
		width: 100%;
		height: auto;
		display: flex;
		flex-direction: column;
		align-items:center;
		color: white;
		justify-content: space-between;
	}

	.bottom {
		width: 100%;
		text-align: center;
		position: absolute;
		bottom: 10px;
		color: white;
		font-size: 5px;
	}
</style>
