(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4a47e106"],{1074:function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"total"},[a("v-data-table",{staticClass:"elevation-1",attrs:{headers:t.headers,items:t.desserts},scopedSlots:t._u([{key:"top",fn:function(){return[a("v-toolbar",[a("v-toolbar-title",[t._v("工作计划")]),a("v-spacer"),a("v-btn",{staticClass:"mb-2",attrs:{color:"primary",dark:""},on:{click:function(e){return t.addPlan()}}},[t._v("添加计划")])],1)]},proxy:!0},{key:"item.actions",fn:function(e){var l=e.item;return[a("v-btn",{staticStyle:{color:"white"},attrs:{color:"blue",small:""},on:{click:function(e){return t.toView(l)}}},[t._v("查看")])]}}])}),a("v-dialog",{attrs:{"max-width":"300"},model:{value:t.detailDialog,callback:function(e){t.detailDialog=e},expression:"detailDialog"}},[a("v-card",[a("v-card-title",[t._v("详细计划")]),a("v-card-text",[t._v(" 计划编号："+t._s(t.viewPlanId)+" ")]),a("v-card-text",[t._v(" 员工编号："+t._s(t.viewEmployeeId)+" ")]),a("v-card-text",[t._v(" 计划时间："+t._s(t.viewPlanTime)+" ")]),a("v-card-text",[t._v(" 完成情况："+t._s(t.viewStatus)+" ")])],1)],1),a("v-dialog",{attrs:{"max-width":"400",persistent:""},model:{value:t.planDialog,callback:function(e){t.planDialog=e},expression:"planDialog"}},[a("v-card",[a("v-card-title",[t._v("添加计划")]),a("v-card-text",[a("v-text-field",{attrs:{label:"计划名称"},model:{value:t.addPlanName,callback:function(e){t.addPlanName=e},expression:"addPlanName"}})],1),a("v-card-text",[a("v-select",{attrs:{items:t.employee,label:"请选择员工"},model:{value:t.selectEmployee,callback:function(e){t.selectEmployee=e},expression:"selectEmployee"}})],1),a("v-card-text",[a("v-menu",{ref:"menu",attrs:{"offset-y":"",transition:"scale-transition"},scopedSlots:t._u([{key:"activator",fn:function(e){var l=e.on,s=e.attrs;return[a("v-text-field",t._g(t._b({attrs:{label:"计划结束时间"},model:{value:t.addPlanTime,callback:function(e){t.addPlanTime=e},expression:"addPlanTime"}},"v-text-field",s,!1),l))]}}])},[a("v-date-picker",{attrs:{"no-title":"",scrollable:""},model:{value:t.addPlanTime,callback:function(e){t.addPlanTime=e},expression:"addPlanTime"}},[a("v-spacer"),a("v-btn",{attrs:{text:"",color:"red"},on:{click:function(e){t.menu=!1}}},[t._v("取消")]),a("v-btn",{attrs:{text:"",color:"primary"},on:{click:function(e){return t.$refs.menu.save(t.date)}}},[t._v("确定")])],1)],1)],1),a("v-card-text",[a("v-select",{attrs:{items:t.product,label:"请选择产品"},model:{value:t.selectProduct,callback:function(e){t.selectProduct=e},expression:"selectProduct"}})],1),a("v-card-text",[a("v-text-field",{attrs:{label:"数量"},model:{value:t.addNumber,callback:function(e){t.addNumber=e},expression:"addNumber"}})],1),a("v-card-actions",[a("v-spacer"),a("v-btn",{attrs:{color:"red darken-1",text:""},on:{click:function(e){return t.close()}}},[t._v("取消")]),a("v-btn",{attrs:{color:"blue darken-1",text:""},on:{click:function(e){return t.save()}}},[t._v("确定")])],1)],1)],1)],1)},s=[],d=(a("b0c0"),a("ac1f"),a("1276"),{data:function(){return{planDialog:!1,detailDialog:!1,headers:[{text:"计划编号",value:"planId"},{text:"计划名称",value:"name"},{text:"员工编号",value:"employeeId"},{text:"计划时间",value:"planTime"},{text:"是否已完成",value:"status"},{text:"实施情况",value:"actions",sortable:!1}],desserts:[],viewPlanId:"",viewEmployeeId:"",viewPlanTime:"",viewStatus:"",employee:[],product:[],addPlanName:"",addPlanTime:(new Date).toISOString().substr(0,10),addNumber:"",selectEmployee:"",selectProduct:"",productId:[],addProductId:""}},created:function(){var t=this;0==localStorage.getItem("marker")?this.$axios({method:"post",url:this.$url+"queryAllPlan",data:{}}).then((function(e){t.desserts=e.data.data})):this.$router.push({name:"404"})},methods:{addPlan:function(){var t=this;this.$axios({method:"post",url:this.$url+"queryAllUser",data:{marker:1}}).then((function(e){for(var a=0;a<e.data.data.length;a++){var l=e.data.data[a].id+"   "+e.data.data[a].name;t.employee.push(l)}})),this.$axios({method:"post",url:this.$url+"queryPlanAllProduct",data:{}}).then((function(e){for(var a=0;a<e.data.data.length;a++)t.product.push(e.data.data[a].name),t.productId.push(e.data.data[a].id)})),this.planDialog=!0},close:function(){this.planDialog=!1,this.addNumber="",this.addPlanName="",this.addPlanTime="",this.addProductId=""},save:function(){var t=this;this.selectEmployee=this.selectEmployee.split(" ");for(var e=0;e<this.product.length;e++)this.product[e]==this.selectProduct&&(this.addProductId=this.productId[e]);this.$axios({method:"post",url:this.$url+"insertProductPlan",data:{employeeId:this.selectEmployee[0],list:[{number:this.addNumber,productId:this.addProductId}],name:this.addPlanName,planTime:this.addPlanTime}}).then((function(e){var a=e.data.data,l={planId:"",name:"",employeeId:"",planTime:"",status:""};l.planId=a.planId,l.name=a.name,l.employeeId=a.employeeId,l.planTime=a.planTime,l.status=a.status,t.desserts.push(l)})),this.close()},toView:function(t){this.detailDialog=!0;var e=this.desserts.indexOf(t);this.viewPlanId=this.desserts[e].planId,this.viewEmployeeId=this.desserts[e].employeeId,this.viewPlanTime=this.desserts[e].planTime,this.viewStatus=this.desserts[e].status}}}),i=d,n=(a("c410"),a("2877")),r=a("6544"),o=a.n(r),c=a("8336"),u=a("b0af"),v=a("99d9"),m=a("8fea"),p=a("2e4b"),h=a("169a"),f=a("e449"),b=a("b974"),x=a("2fa4"),P=a("8654"),y=a("71d9"),k=a("2a7f"),I=Object(n["a"])(i,l,s,!1,null,"5fa19c6a",null);e["default"]=I.exports;o()(I,{VBtn:c["a"],VCard:u["a"],VCardActions:v["a"],VCardText:v["b"],VCardTitle:v["c"],VDataTable:m["a"],VDatePicker:p["a"],VDialog:h["a"],VMenu:f["a"],VSelect:b["a"],VSpacer:x["a"],VTextField:P["a"],VToolbar:y["a"],VToolbarTitle:k["a"]})},c410:function(t,e,a){"use strict";a("ff33")},ff33:function(t,e,a){}}]);
//# sourceMappingURL=chunk-4a47e106.116dd8b0.js.map