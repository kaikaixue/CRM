<!-- <template>
	<div class="total">
		<div class="content">
			<div class="content-title">
				个人信息
			</div>
			<div class="content-mid">
				<div class="item" v-for="(item,index) in items">
					<div class="item-left">{{item.title}}</div>
					<v-text-field :placeholder="item.placeholder" :readonly="item.readonly" v-model="item.value" outlined dense :label="item.title" style="margin-left: 5%;" color="lightgray"></v-text-field>
				</div>
			</div>
			<div class="content-bottom">
				<v-btn color="#009688" @click="summit()">立即提交</v-btn>
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				items:[
					{title:"账号",value:"2780739361",placeholder:"",readonly:true},
					{title:"姓名",value:"",placeholder:"请填写姓名",readonly:false},
					{title:"联系方式",value:"",placeholder:"请填写手机号",readonly:false},
					{title:"工资",value:"",placeholder:"",readonly:true},
					{title:"所属部门",value:"",placeholder:"",readonly:true},
					{title:"类型",value:"",placeholder:"",readonly:true},
				]
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
		background-color: white;
	}
	
	.content {
		margin-top: 1%;
		width: 40%;
		height: 70%;
	}
	
	.content-title {
		width: 100%;
		height: 10%;
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
	}
	
	.item {
		width: 80%;
		height: 40px;
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
	
	.content-bottom {
		margin-top: 5%;
		width: 100%;
		height: 10%;
		display: flex;
		justify-content: space-around;
		align-items: center;
	}
</style>
 -->