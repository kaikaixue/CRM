<template>
	<div class="d-flex" style="width: 100%; height: 70px;background-color: #23262E;">
		<div class="left">
			<p style="font-size: 30px; letter-spacing: 10px;">CRM</p>
			<p style="font-size: 15px; margin-top: -15px;">客户管理系统</p>
		</div>
		<div class="mid">
			<p style="margin-left: 20%;">{{nowDateTime}}</p>
		</div>
		<div class="right">
			<v-menu bottom right transition="scale-transition" origin="top left" offset-y>
				<template v-slot:activator="{ on }">
					<v-chip pill v-on="on">
						{{userName}}
					</v-chip>
				</template>
				<v-card>
					<v-btn @click="exit()">退出登录</v-btn>
				</v-card>
			</v-menu>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'topBar',
		data() {
			return {
				nowDateTime: "",
				name: "",
				isShowDialog: false,
			}
		},

		props: {
			userName: String
		},

		methods: {
			getTime() {
				var _this = this;
				let yy = new Date().getFullYear();
				let mm = new Date().getMonth() + 1;
				let dd = new Date().getDate();
				let hh = new Date().getHours();
				let mf = new Date().getMinutes() < 10 ? '0' + new Date().getMinutes() : new Date().getMinutes();
				let ss = new Date().getSeconds() < 10 ? '0' + new Date().getSeconds() : new Date().getSeconds();
				_this.nowDateTime = yy + '年 ' + mm + '月' + dd + '日 ' + hh + ':' + mf + ':' + ss;
			},

			currentTime() {
				setInterval(this.getTime, 500)
			},
			
			exit(){
				window.name=""
				localStorage.removeItem("userName")
				localStorage.removeItem("account")
				localStorage.removeItem("marker")
				localStorage.removeItem("id")
				localStorage.removeItem("userInfo")
				this.$router.push({
					name: "Login"
				})
			},
		},

		created() {
			console.log(this.userName)
			this.currentTime();
			// this.$axios({
			// 	method: 'post',
			// 	url: this.$url + 'queryUser',
			// 	data: {
			// 		"account": this.account
			// 	}
			// }).then((res) => {
			// 	console.log(res)
			// 	this.userName = res.data.data.user.name;
			// })
		}
	}
</script>

<style scoped>
	.left {
		margin-left: 5%;
		width: 15%;
		height: 100%;
		color: white;
	}

	.mid {
		width: 60%;
		height: 100%;
		color: white;
		line-height: 70px;
		text-align: center;
		font-size: 20px;
	}

	.right {
		margin-left: 10%;
		width: 10%;
		height: 100%;
		display: flex;
		align-items: center;
	}

	.right-div {
		width: 60%;
		height: 60%;
		display: flex;
		align-items: center;
		color: white;
		cursor: pointer;
	}

	.content {
		width: 300px;
		height: 300px;
		background-color: aqua
	}

	.dialog {
		width: 80px;
		height: 40px;
		position: absolute;
		top: 7%;
		right: 2%;
	}
</style>
