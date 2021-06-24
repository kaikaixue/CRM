<template>
	<div class="total">
		<div class="content">
			<div class="content-title">提交反馈</div>
			<div class="content-mid">
				<v-textarea filled label="我要反馈" v-model="text" color="lightgray">
				</v-textarea>
			</div>
			<div class="content-bottom">
				<v-btn color="#009688" @click="summit()">提交反馈</v-btn>
			</div>
		</div>
		<v-dialog v-model="isShow" max-width="250">
			<v-card>
				<v-card-title>
					<span class="headline">提示</span>
				</v-card-title>
				<v-card-text>您已成功提交反馈</v-card-text>
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
				text: "",
				isShow: false
			}
		},
		
		created(){
			if(localStorage.getItem("marker") != 2 && localStorage.getItem("marker") != 1){
				this.$router.push({
					name: '404'
				})
			}
		},
		
		methods:{
			summit(){
				this.$axios({
					method:'post',
					url:this.$url + 'addFeedback',
					data:{
						"information": this.text,
						"userId": localStorage.getItem("id")
					}
				}).then((res) => {
					console.log(res)
					if(res.data.code == "SUCCESS_CODE"){
						this.isShow = true
						this.text = ""
					}
				})
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
		width: 40%;
		height: 60%;
	}

	.content-title {
		width: 100%;
		height: 5%;
		text-align: center;
		font-size: 20px;
	}

	.content-mid {
		margin-top: 2%;
		width: 100%;
		height: 45%;
	}

	.content-bottom {
		width: 100%;
		height: 10%;
		display: flex;
		justify-content: flex-end;
		align-items: center;
	}
</style>
