<template>
	<div class="total">
		<div class="content">
			<div class="content-title">修改密码</div>
			<div class="content-mid">
				<div class="item" v-for="item in items">
					<div class="item-left">{{item.title}}</div>
					<v-text-field :label="item.title" outlined dense v-model="item.value" style="margin-left: 5%;"
						color="lightgray" :rules="item.rules" :error-messages="item.errorMessage"></v-text-field>
				</div>
			</div>
			<div class="content-bottom">
				<v-btn color="#009688" @click="summit()">立即提交</v-btn>
			</div>
		</div>
		<v-dialog v-model="isShow" max-width="250">
			<v-card>
				<v-card-title>
					<span class="headline">错误提示</span>
				</v-card-title>
				<v-card-text>{{errMessage}}</v-card-text>
				<v-card-actions>
					<v-spacer></v-spacer>
					<v-btn class="blue darken-1" text @click="isShow = false">确定</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				account: "",
				passwordCheck: true,
				isShow: false,
				errMessage: "",
				items: [{
						title: "原密码",
						value: "",
						rules: [value => !!value || '请输入原密码'],
						errorMessage: "",
					},
					{
						title: "新密码",
						value: "",
						rules: [value => !!value || '请输入新密码'],
						errorMessage: "",
					},
					{
						title: "确认密码",
						value: "",
						rules: [value => !!value || '请输入确认密码'],
						errorMessage: ""
					},
				]
			}
		},
		created() {
			if (localStorage.getItem("marker") == 2) {
				this.account = this.$route.params.account
			} else {
				this.$router.push({
					name: '404'
				})
			}
			// localStorage.setItem("userInfo", JSON.stringify(this.items))
			// console.log(JSON.parse(localStorage.getItem("userInfo")));
		},
		methods: {
			check() {
				if (this.items[1].value != this.items[2].value) {
					this.passwordCheck = false
					// this.errorMessage = "两次密码输入不一样"
					return false
				} else {
					this.passwordCheck = true
					return true
				}
			},

			summit() {
				let flag = this.check()
				console.log(flag)
				if (flag) {
					this.$axios({
						method: 'post',
						url: this.$url + 'updatePassword',
						data: {
							"lastPassword": this.items[0].value,
							"account": this.account,
							"newPassword": this.items[2].value
						}
					}).then((res) => {
						console.log(res)
						if (res.data.code == "FAIL_CODE") {
							this.isShow = true
							this.errMessage = res.data.message
						} else {
							window.alert("密码修改成功，点击确定跳转登录页面")
							this.$router.push({
								name: "Login"
							})
						}
					})
				} else {
					this.isShow = true
					this.errMessage = "请确认输入信息是否正确"
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
		display: flex;
		justify-content: center;
	}

	.content {
		margin-top: 2%;
		width: 35%;
		height: 65%;
	}

	.content-title {
		width: 100%;
		height: 10%;
		text-align: center;
		font-size: 30px;
	}

	.content-mid {
		margin-left: -8%;
		padding: 20px 0 0 0;
		width: 100%;
		height: 55%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;
	}

	.item {
		width: 80%;
		height: 40px;
		display: flex;
		justify-content: space-between;
		font-weight: 500;
	}

	.item-left {
		width: 20%;
		height: 100%;
		line-height: 40px;
		text-align: right;
	}

	.item-right {
		width: 70%;
		height: 100%;
	}

	.item-right-input {
		width: 100%;
		height: 100%;
		border: 1px solid lightgrey;
		outline: none;
		border-radius: 3px;
	}

	.content-bottom {
		margin-top: 7%;
		width: 100%;
		height: 10%;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
</style>
