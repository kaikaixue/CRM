<template>
	<div class="total">
		<v-data-table :headers="headers" :items="desserts">
			<template v-slot:top>
				<v-toolbar>
					<v-toolbar-title>订单处理</v-toolbar-title>
				</v-toolbar>
			</template>
			<template v-slot:item.actions="{ item }">
				<h4 style="font-weight: 400;" v-show="item.orderStatus">已处理</h4>
				<v-btn color="blue" small dark @click="agree(item)" width="20px" v-show="!item.orderStatus">同意</v-btn>
				<v-btn color="red" small dark style="margin-left: 10px;" width="20px" @click="disagree(item)" v-show="!item.orderStatus">不同意</v-btn>
			</template>
		</v-data-table>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				headers:[
					{text:"订单编号",value:"orderId"},
					{text:"产品名称",value:"productName"},
					{text:"数量",value:"number"},
					{text:"单价",value:"unitPrice"},
					{text:"总价",value:"totalPrice"},
					{text:"下单时间",value:"createTime"},
					{text:"操作",value:"actions",sortable:false}
				],
				desserts:[],
			}
		},
		
		created(){
			if(localStorage.getItem("marker") == 1){
				this.$axios({
					method:'post',
					url: this.$url + 'queryAllEmployeeOrder',
					data: {
						userId: localStorage.getItem("id")
					}
				}).then((res) => {
					this.desserts = res.data.data
				})
			}
		},
		
		methods:{
			agree(item){
				this.$axios({
					method: 'post',
					url: this.$url + 'updateOrderPhase',
					data:{
						orderId: item.orderId,
						phase: 1
					}
				})
				item.orderStatus = true
			},
			
			disagree(item){
				this.$axios({
					method: 'post',
					url: this.$url + 'updateOrderPhase',
					data:{
						orderId: item.orderId,
						phase: 3
					}
				})
				item.orderStatus = true
			},
		},
		
		
	}
</script>

<style scoped>
	*{
		padding: 0;
		margin: 0;
		box-sizing: border-box;
	}
	
	.total{
		width: 100%;
		height: 100%;
	}
</style>
