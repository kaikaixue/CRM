<template>
	<div class="total">
		<img class="img" src="../../static/image/spaceman.svg" />
		<h1 style="color: red; text-align: center;">非常抱歉，你没有权限访问此页面</h1>
		<h2 @click="toLogin()" style="cursor: pointer; text-decoration: underline; color: royalblue; text-align: center;">点击，返回至登录页面</h2>
	</div>
</template>

<script>
	export default {
		data() {
			return {

			}
		},

		methods: {
			toLogin() {
				this.$router.push({
					name: 'Login'
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

	.total {
		width: 100%;
		height: 100%;
	}

	.img {
		position: absolute;
		left: 50%;
		top: 50%;
		margin-left: -256px;
		margin-top: -256px;
		}
</style>
