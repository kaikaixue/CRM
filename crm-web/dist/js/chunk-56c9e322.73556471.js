(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-56c9e322"],{"006f":function(t,e,a){"use strict";a("b9ce")},"0a0d":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"total"},[a("topbar",{staticStyle:{height:"10%"},attrs:{userName:t.userName}}),a("div",{staticClass:"mid"},[a("div",{staticClass:"sidebar"},[a("v-list",{attrs:{expand:""}},t._l(t.items[t.marker],(function(e){return a("v-list-group",{attrs:{"prepend-icon":e.icon,color:"white"},scopedSlots:t._u([{key:"activator",fn:function(){return[a("v-list-item-title",[t._v(t._s(e.text))])]},proxy:!0}],null,!0)},t._l(e.list,(function(e,i){return a("div",{key:i,attrs:{tag:"div"},on:{click:function(a){return t.enter(e)}}},[a("v-list-item",{attrs:{link:""}},[a("v-list-item-title",{staticStyle:{color:"white"}},[t._v(t._s(e.text))])],1)],1)})),0)})),1)],1),a("div",{staticClass:"blank"},[a("router-view")],1)])],1)},r=[],o=(a("b0c0"),function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"d-flex",staticStyle:{width:"100%",height:"70px","background-color":"#23262E"}},[t._m(0),a("div",{staticClass:"mid"},[a("p",{staticStyle:{"margin-left":"20%"}},[t._v(t._s(t.nowDateTime))])]),a("div",{staticClass:"right"},[a("v-menu",{attrs:{bottom:"",right:"",transition:"scale-transition",origin:"top left","offset-y":""},scopedSlots:t._u([{key:"activator",fn:function(e){var i=e.on;return[a("v-chip",t._g({attrs:{pill:""}},i),[t._v(" "+t._s(t.userName)+" ")])]}}])},[a("v-card",[a("v-btn",{on:{click:function(e){return t.exit()}}},[t._v("退出登录")])],1)],1)],1)])}),s=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"left"},[a("p",{staticStyle:{"font-size":"30px","letter-spacing":"10px"}},[t._v("CRM")]),a("p",{staticStyle:{"font-size":"15px","margin-top":"-15px"}},[t._v("客户管理系统")])])}],n={name:"topBar",data:function(){return{nowDateTime:"",name:"",isShowDialog:!1}},props:{userName:String},methods:{getTime:function(){var t=this,e=(new Date).getFullYear(),a=(new Date).getMonth()+1,i=(new Date).getDate(),r=(new Date).getHours(),o=(new Date).getMinutes()<10?"0"+(new Date).getMinutes():(new Date).getMinutes(),s=(new Date).getSeconds()<10?"0"+(new Date).getSeconds():(new Date).getSeconds();t.nowDateTime=e+"年 "+a+"月"+i+"日 "+r+":"+o+":"+s},currentTime:function(){setInterval(this.getTime,500)},exit:function(){window.name="",localStorage.removeItem("userName"),localStorage.removeItem("account"),localStorage.removeItem("marker"),localStorage.removeItem("id"),localStorage.removeItem("userInfo"),this.$router.push({name:"Login"})}},created:function(){console.log(this.userName),this.currentTime()}},c=n,l=(a("006f"),a("2877")),u=a("6544"),m=a.n(u),d=a("8336"),h=a("b0af"),g=a("cc20"),p=a("e449"),f=Object(l["a"])(c,o,s,!1,null,"370482b4",null),v=f.exports;m()(f,{VBtn:d["a"],VCard:h["a"],VChip:g["a"],VMenu:p["a"]});var x={data:function(){return{id:-1,marker:-1,account:"",userName:"",items:[[{id:0,text:"个人信息",list:[{text:"基本信息",router:"BasicInformation"}],icon:"mdi-account-circle"},{id:1,text:"用户管理",list:[{text:"客户管理",router:"CustomerManagement"},{text:"员工管理",router:"EmployeeManagement"}],icon:"mdi-account-switch"},{id:2,text:"营销管理",list:[{text:"产品管理",router:"ProductManagement"},{text:"发布任务计划",router:"PlanManagement"}],icon:"mdi-google-analytics"},{id:3,text:"查看反馈",list:[{text:"用户反馈",router:"CustomerFeedback"},{text:"员工反馈",router:"EmployeeFeedback"}],icon:"mdi-comment-text-multiple-outline"}],[{id:0,text:"个人信息",list:[{text:"基本信息",router:"BasicInformation"}],icon:"mdi-account-circle"},{id:1,text:"工作情况",list:[{text:"业绩情况",router:"Performance"},{text:"工作计划",router:"WorkPlan"},{text:"订单处理",router:"Order"}],icon:"mdi-clipboard-check"},{id:2,text:"反馈信息",list:[{text:"我要反馈",router:"Feedback2"}],icon:"mdi-comment-text-multiple-outline"}],[{id:0,text:"个人信息",list:[{text:"基本信息",router:"BasicInformation"},{text:"修改密码",router:"ChangePassword"}],icon:"mdi-account-circle"},{id:1,text:"消费情况",list:[{text:"我的订单",router:"MyOrder"}],icon:"mdi-alarm-panel-outline"},{id:2,text:"反馈信息",list:[{text:"我要反馈",router:"Feedback1"}],icon:"mdi-comment-text-multiple-outline"}]]}},methods:{enter:function(t){this.$router.push({name:t.router,params:{id:this.id,marker:this.marker,account:this.account}})}},watch:{userName:function(t){JSON.parse(localStorage.getItem("userInfo")).name}},created:function(){""==window.name&&(this.marker=this.$route.params.marker,this.account=this.$route.params.account,this.id=this.$route.params.id,this.userName=this.$route.params.name,localStorage.setItem("account",this.account),localStorage.setItem("id",this.id),localStorage.setItem("marker",this.marker),localStorage.setItem("userName",this.userName),window.name="isReload"),this.account=localStorage.getItem("account"),this.marker=localStorage.getItem("marker"),this.userName=JSON.parse(localStorage.getItem("userInfo")).name,this.id=localStorage.getItem("id");var t=this.items[localStorage.getItem("marker")];this.enter(t[0].list[0])},components:{topbar:v}},b=x,k=(a("8f8a"),a("8860")),w=a("56b0"),S=a("da13"),_=a("5d23"),I=Object(l["a"])(b,i,r,!1,null,"38803d45",null);e["default"]=I.exports;m()(I,{VList:k["a"],VListGroup:w["a"],VListItem:S["a"],VListItemTitle:_["b"]})},"2e2e":function(t,e,a){},"615b":function(t,e,a){},"8f8a":function(t,e,a){"use strict";a("2e2e")},b0af:function(t,e,a){"use strict";var i=a("5530"),r=(a("a9e3"),a("0481"),a("615b"),a("10d2")),o=a("297c"),s=a("1c87"),n=a("58df");e["a"]=Object(n["a"])(o["a"],s["a"],r["a"]).extend({name:"v-card",props:{flat:Boolean,hover:Boolean,img:String,link:Boolean,loaderHeight:{type:[Number,String],default:4},raised:Boolean},computed:{classes:function(){return Object(i["a"])(Object(i["a"])({"v-card":!0},s["a"].options.computed.classes.call(this)),{},{"v-card--flat":this.flat,"v-card--hover":this.hover,"v-card--link":this.isClickable,"v-card--loading":this.loading,"v-card--disabled":this.disabled,"v-card--raised":this.raised},r["a"].options.computed.classes.call(this))},styles:function(){var t=Object(i["a"])({},r["a"].options.computed.styles.call(this));return this.img&&(t.background='url("'.concat(this.img,'") center center / cover no-repeat')),t}},methods:{genProgress:function(){var t=o["a"].options.methods.genProgress.call(this);return t?this.$createElement("div",{staticClass:"v-card__progress",key:"progress"},[t]):null}},render:function(t){var e=this.generateRouteLink(),a=e.tag,i=e.data;return i.style=this.styles,this.isClickable&&(i.attrs=i.attrs||{},i.attrs.tabindex=0),t(a,this.setBackgroundColor(this.color,i),[this.genProgress(),this.$slots.default])}})},b9ce:function(t,e,a){}}]);
//# sourceMappingURL=chunk-56c9e322.73556471.js.map