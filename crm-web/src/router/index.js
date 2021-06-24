import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login/Login.vue'
import Register from '../views/register/Register.vue'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}


const routes = [
	{
		path:'/index',
		name:'Index',
		component:() => import('../views/index/Index.vue'),
		children:[
			{
				path:'/basicInformation',
				name:'BasicInformation',
				component: () => import('../views/customer/basicInformation/BasicInformation.vue')
			},
			{
				path:'/changePassword',
				name:'ChangePassword',
				component: () => import('../views/customer/changePassword/ChangePassword.vue'),
			},
			{
				path:'/myOrder',
				name:'MyOrder',
				component: () => import('../views/customer/myOrder/myOrder.vue')
			},
			{
				path:'/feedback',
				name:'Feedback1',
				component: () => import('../views/customer/feedback/feedback.vue')
			},
			{
				path:'/performance',
				name:'Performance',
				component: () => import('../views/employee/performance/performance.vue')
			},
			{
				path:'/workPlan',
				name:'WorkPlan',
				component: () => import('../views/employee/workPlan/workPlan.vue')
			},
			{
				path:'/order',
				name:'Order',
				component: () => import('../views/employee/order/order.vue')
			},
			{
				path:'/feedback',
				name:'Feedback2',
				component: () => import('../views/customer/feedback/feedback.vue')
			},
			{
				path:'/customerFeedback',
				name:'CustomerFeedback',
				component: () => import('../views/administrator/feedbackInformation/customerFeedback.vue')
			},
			{
				path:'/employeeFeedback',
				name:'EmployeeFeedback',
				component:() => import('../views/administrator/feedbackInformation/employeeFeedback.vue')
			},
			{
				path:'/planManagement',
				name:'PlanManagement',
				component:() => import('../views/administrator/salesManagement/planManagement.vue')
			},
			{
				path:'/productManagement',
				name:'ProductManagement',
				component:() => import('../views/administrator/salesManagement/productManagement.vue')
			},
			{
				path:'/customerManagement',
				name:'CustomerManagement',
				component:() => import('../views/administrator/userManagement/customerManagement.vue')
			},
			{
				path:'/employeeManagement',
				name:'EmployeeManagement',
				component:() => import('../views/administrator/userManagement/employeeManagement.vue')
			}
		]
	},
	{
		path:'/',
		name:'Login',
		component:Login
	},
	{
		path:'/register', 
		name:'Register',
		component:Register
	},
	{
		path:'/404',
		name:'404',
		component: () => import('../views/404/404.vue') 
	},
]


/*



*/


const router = new VueRouter({
  routes
})

export default router
