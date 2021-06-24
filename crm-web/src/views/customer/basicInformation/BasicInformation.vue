<template>
	<div class="total">
		<div class="content">
			<div class="content-title">
				个人信息
			</div>
			<div class="content-mid">
				<div class="item" v-for="(item,index) in items[marker]">
					<div class="item-left">{{item.title}}</div>
					<v-text-field :placeholder="item.placeholder" :readonly="item.readonly" v-model="item.value"
						outlined dense :label="item.title" style="margin-left: 5%;" color="lightgray"></v-text-field>
				</div>
			</div>
			<div class="content-bottom">
				<v-btn color="#009688" @click="summit()">立即提交</v-btn>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				lists:[],
				marker: -1,
				items: [
					[{
							title: "账号",
							value: JSON.parse(localStorage.getItem("userInfo")).account,
							placeholder: "",
							readonly: true
						},
						{
							title: "姓名",
							value: JSON.parse(localStorage.getItem("userInfo")).name,
							placeholder: "请填写姓名",
							readonly: false
						},
						{
							title: "联系方式",
							value: JSON.parse(localStorage.getItem("userInfo")).phone,
							placeholder: "请填写手机号",
							readonly: false
						},
						{
							title: "联系地址",
							value: JSON.parse(localStorage.getItem("userInfo")).address,
							placeholder: "请填写联系地址",
							readonly: false
						},
						{
							title: "职务",
							value: "经理",
							placeholder: "请填写职务",
							readonly: true
						},
					],
					[{
							title: "账号",
							value: JSON.parse(localStorage.getItem("userInfo")).account,
							placeholder: "",
							readonly: true
						},
						{
							title: "姓名",
							value: JSON.parse(localStorage.getItem("userInfo")).name,
							placeholder: "请填写姓名",
							readonly: false
						},
						{
							title: "联系方式",
							value: JSON.parse(localStorage.getItem("userInfo")).phone,
							placeholder: "请填写手机号",
							readonly: false
						},
						{
							title: "工资",
							value: JSON.parse(localStorage.getItem("userInfo")).wages,
							placeholder: "",
							readonly: true
						},
						{
							title: "所属部门",
							value: JSON.parse(localStorage.getItem("userInfo")).department,
							placeholder: "",
							readonly: true
						},
						{
							title: "类型",
							value: JSON.parse(localStorage.getItem("userInfo")).type,
							placeholder: "",
							readonly: true
						},
					],
					[{
							title: "账号",
							value: JSON.parse(localStorage.getItem("userInfo")).account,
							placeholder: "",
							readonly: true
						},
						{
							title: "姓名",
							value: JSON.parse(localStorage.getItem("userInfo")).name,
							placeholder: "请填写姓名",
							readonly: false
						},
						{
							title: "联系方式",
							value: JSON.parse(localStorage.getItem("userInfo")).phone,
							placeholder: "请填写联系方式",
							readonly: false
						},
						{
							title: "联系地址",
							value: JSON.parse(localStorage.getItem("userInfo")).address,
							placeholder: "请填写联系地址",
							readonly: false
						},
						{
							title: "信用度",
							value: JSON.parse(localStorage.getItem("userInfo")).cred,
							placeholder: "",
							readonly: true
						}
					],
				]
			}
		},

		created() {
			this.marker = localStorage.getItem("marker")
			console.log(localStorage.getItem("id"))
			this.$axios({
				method:'post',
				url:this.$url + 'queryUserId',
				data:{
					userId: localStorage.getItem("id")
				}
			}).then((res) => {
				console.log(res)
				localStorage.setItem("userInfo",JSON.stringify(res.data.data))
			})
		},
		

		methods: {
			summit() {
				console.log(this.items[this.marker])
				let newList = this.items[this.marker]
				console.log(newList)
				for(let i = 0;i<newList.length;i++){
					this.lists.splice(i,0,newList[i].value)
				}
				console.log(this.lists)
				
				this.$axios({
					method: 'post',
					url: this.$url + 'updateUser',
					data: {
						"account": this.lists[0],
						"address": this.lists[3],
						"avatar": JSON.parse(localStorage.getItem("userInfo")).avatar,
						"cred": JSON.parse(localStorage.getItem("userInfo")).cred,
						"department": this.lists[4],
						"marker": JSON.parse(localStorage.getItem("userInfo")).marker,
						"name": this.lists[1],
						"password": JSON.parse(localStorage.getItem("userInfo")).password,
						"phone": this.lists[2],
						"salt": JSON.parse(localStorage.getItem("userInfo")).salt,
						"sex": JSON.parse(localStorage.getItem("userInfo")).sex,
						"speed_time": JSON.parse(localStorage.getItem("userInfo")).speed_time,
						"type": this.lists[5],
						"userId": JSON.parse(localStorage.getItem("userInfo")).userId,
						"wages": JSON.parse(localStorage.getItem("userInfo")).wages
					}
				})
			}
		}
	}
</script>

<style scoped>
	.total {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
	}

	.content {
		margin-top: 1%;
		width: 40%;
		height: 70%;
	}

	.content-title {
		width: 100%;
		height: 10%;
		/* background-color: #DEB887; */
		text-align: center;
		font-size: 30px;
	}

	.content-mid {
		margin-left: -10%;
		padding-top: 20px;
		width: 100%;
		height: 80%;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: space-between;
		/* background-color: aquamarine; */
	}

	.item {
		width: 80%;
		height: 40px;
		/* background-color: #DEB887; */
		display: flex;
		justify-content: space-between;
		font-weight: 500;
	}

	.item-left {
		width: 22%;
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
		margin-top: 5%;
		/* padding: 0 20% 0 10%; */
		width: 100%;
		height: 10%;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
</style>
