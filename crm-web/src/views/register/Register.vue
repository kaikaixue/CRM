<template>
	<div class="total">
		<div class="content">
			<div class="top">
				<h2>欢迎注册</h2>
			</div>
			<div class="mid">
				<v-form ref="form" class="v-form">
					<!-- 用户名输入框 -->
					<v-col cols="12">
						<v-text-field prepend-icon="mdi-account" v-model="name" label="用户名" color="white"
							:rules="[() => !!name || '请输入用户名',nameCheck]" :error-messages="errorMessage1" required
							@blur="checkName()">
						</v-text-field>
					</v-col>
					<!-- 账号输入框 -->
					<v-col cols="12">
						<v-text-field prepend-icon="mdi-account-supervisor" v-model="account" label="账号" color="white"
							:rules="[() => !!account || '请输入账号',accountCheck]" :error-messages="errorMessage2" required
							@blur="checkAccount()">
						</v-text-field>
					</v-col>
					<!-- 密码输入框 -->
					<v-col cols="12">
						<v-text-field :type="showPassword?'text':'password'" prepend-icon="mdi-lock" v-model="password"
							label="请输入密码" color="white" :rules="[() => !!password || '请输入密码',passwordCheck]"
							@blur="checkPassword()" :error-messages="errorMessage3" required
							:append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
							@click:append="showPassword = !showPassword">
						</v-text-field>
					</v-col>
					<!-- 确认密码输入框 -->
					<v-col cols="12">
						<v-text-field :type="showPasswordAgain?'text':'password'" prepend-icon="mdi-lock-check"
							v-model="passwordAgain" label="请再次输入密码" color="white"
							:append-icon="showPasswordAgain ? 'mdi-eye' : 'mdi-eye-off'"
							:rules="[() => !!passwordAgain || '请再次输入密码',passwordAgainCheck]" required
							@click:append="showPasswordAgain = !showPasswordAgain" :error-messages="errorMessage4"
							@blur="checkPasswordAgain()">
						</v-text-field>
					</v-col>
					<!-- 身份选择器 -->
					<v-col cols="12">
						<v-select :items="identity" v-model="select" label="请选择你的身份"
							prepend-icon="mdi-account-box-outline">
						</v-select>
					</v-col>
				</v-form>
				<v-btn width="80%" @click="register()">注册</v-btn>
			</div>
			<div class="bottom">
				欢迎注册CRM客户管理系统
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				//四个input的value
				name: "",
				account: "",
				password: "",
				passwordAgain: "",
				//密码是否可见
				showPassword: false,
				showPasswordAgain: false,
				//校验四个input
				nameCheck: true,
				accountCheck: true,
				passwordCheck: true,
				passwordAgainCheck: true,
				//四个错误提示
				errorMessage1: "",
				errorMessage2: "",
				errorMessage3: "",
				errorMessage4: "",
				//身份
				identity: ["职员", "客户"],
				//双向绑定选择器
				select: "客户",
				//身份标识
				marker: 0,
			}
		},

		watch: {

		},

		methods: {
			//校验用户名
			checkName() {
				if (this.name.length < 4 || this.name.length > 16) {
					this.nameCheck = false;
					this.errorMessage1 = "用户名需为4~16位"
				} else {
					this.nameCheck = true;
					this.errorMessage1 = "";
				}
			},

			//校验账号
			checkAccount() {
				let reg = /^[0~9]*$/;
				if (this.account.length < 6 || this.account.length > 10) {
					this.accountCheck = false;
					this.errorMessage2 = "账号需为6~10位";
				} else if (isNaN(this.account)) {
					this.accountCheck = false;
					this.errorMessage2 = "请输入纯数字";
				} else {
					this.accountCheck = true;
					this.errorMessage2 = "";
				}
			},

			//校验密码为数字，字母
			checkPassword() {
				let reg = /^(?=.*[a-zA-Z])(?=.*[1-9]).{6,}$/;
				let flag = reg.test(this.password);
				if (flag) {
					this.passwordCheck = true;
					this.errorMessage3 = "";
				} else {
					this.passwordCheck = false;
					this.errorMessage3 = "密码需包含数字，字母";
				}
			},

			//检测两次密码是否一样
			checkPasswordAgain() {
				if (this.password != this.passwordAgain) {
					this.passwordAgainCheck = false;
					this.errorMessage4 = "两次密码输入不一样"
				} else {
					this.passwordAgainCheck = true;
					this.errorMessage4 = "";
				}
			},

			//注册接口
			register() {
				//检测表单内所有元素是否合法
				let flag = this.$refs.form.validate()
				if (this.select == "管理员") {
					this.marker = 0;
				} else if (this.select == "职员") {
					this.marker = 1;
				} else {
					this.marker = 2;
				}
				if (flag) {
					this.$axios({
						method: 'post',
						url: this.$url + 'registered',
						data: {
							"account": this.account,
							"password": this.password,
							"name": this.name,
							"marker": this.marker,
						}
					}).then((res) => {
						console.log(res)
						if (res.data.code == "FAIL_CODE") {
							this.accountCheck = false
							this.errorMessage2 = "该账号已存在，请重新输入"
						} else {
							window.alert("注册成功");
							//跳转到上一页面
							this.$router.go(-1);
						}
					})
				}
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

	.total {
		width: 100%;
		height: 100%;
		position: absolute;
		background-size: 100% 100%;
		background-image: url(../../static/image/bc2.jpg);
	}

	.content {
		width: 27%;
		height: 70%;
		position: absolute;
		top: 10%;
		left: 50%;
		transform: translateX(-50%);
		background-color: rgba(0, 0, 0, 0.1);
		box-shadow: -8px 8px 10px 4px dimgray;
	}

	.top {
		width: 100%;
		height: 60px;
		text-align: center;
		line-height: 60px;
		color: white;
		font-weight: 300;
	}

	.mid {
		width: 100%;
		height: 80%;
		display: flex;
		align-items: center;
		flex-direction: column;
	}

	.v-form {
		width: 80%;
		height: 350px;
		display: flex;
		flex-direction: column;
		padding: 10px 0 10px 0;
		justify-content: space-around;
		align-items: center;
	}

	.bottom {
		width: 100%;
		height: 40px;
		text-align: center;
		line-height: 40px;
		position: absolute;
		bottom: 10px;
		color: white;
		font-size: 5px;
	}
</style>
