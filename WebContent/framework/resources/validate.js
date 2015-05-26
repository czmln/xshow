
(function ($) {
	$.isTel = function (s) {
		var reg = /^(([0\+]\d{2,3}[-_]?)?(0\d{2,3})[-_]?)?(\d{7,8})([-_]?(\d{3,}))?$/;
		return reg.test(s);
	};
	$.isMobile = function (s) {
		var reg = /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/;
		return reg.test(s);
	};
	$.isEmail = function (s) {
		var reg = /^\b(^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b$/;
		return reg.test(s);
	};
	$.isNmber = function (s) {
		return !isNaN(s);
	};
	$.isIDcard=function(CardNo){
		CardNo = CardNo.replace(" ", "");
		var strCardNo;
		if (CardNo.length == 18) {
			pattern = /^\d{17}(\d|x|X)$/;
			if (pattern.exec(CardNo) == null) {
				return;
			}
			strCardNo = CardNo.toUpperCase();
		} else {
			pattern = /^\d{15}$/;
			if (pattern.exec(CardNo) == null) {
				return;
			}
			strCardNo = CardNo.substr(0, 6) + "19" + CardNo.substr(6, 9);
			strCardNo += $.GetVCode(strCardNo);
		}
		return  $.CheckValid(strCardNo);
	}


	$.GetVCode = function (CardNo17) {
		var Wi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
		var Ai = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		var cardNoSum = 0;
		for (var i = 0; i < CardNo17.length; i++) {
			cardNoSum += CardNo17.charAt(i) * Wi[i];
		}
		var seq = cardNoSum % 11;
		return Ai[seq];
	};
	$.CheckValid = function (CardNo18) {
		if ($.GetVCode(CardNo18.substr(0, 17)) != CardNo18.charAt(17)) {
			return false;
		}
		if (!$.IsDate(CardNo18.substr(6, 8))) {
			return false;
		}
		var aCity = {11:"\u5317\u4eac", 12:"\u5929\u6d25", 13:"\u6cb3\u5317", 14:"\u5c71\u897f", 15:"\u5185\u8499\u53e4", 21:"\u8fbd\u5b81", 22:"\u5409\u6797", 23:"\u9ed1\u9f99\u6c5f ", 31:"\u4e0a\u6d77", 32:"\u6c5f\u82cf", 33:"\u6d59\u6c5f", 34:"\u5b89\u5fbd", 35:"\u798f\u5efa", 36:"\u6c5f\u897f", 37:"\u5c71\u4e1c", 41:"\u6cb3\u5357", 42:"\u6e56\u5317 ", 43:"\u6e56\u5357", 44:"\u5e7f\u4e1c", 45:"\u5e7f\u897f", 46:"\u6d77\u5357", 50:"\u91cd\u5e86", 51:"\u56db\u5ddd", 52:"\u8d35\u5dde", 53:"\u4e91\u5357", 54:"\u897f\u85cf ", 61:"\u9655\u897f", 62:"\u7518\u8083", 63:"\u9752\u6d77", 64:"\u5b81\u590f", 65:"\u65b0\u7586", 71:"\u53f0\u6e7e", 81:"\u9999\u6e2f", 82:"\u6fb3\u95e8", 91:"\u56fd\u5916"};
		if (aCity[parseInt(CardNo18.substr(0, 2))] == null) {
			return false;
		}
		//this.ID18 = CardNo18;
		//this.ID15 = CardNo18.substr(0, 6) + CardNo18.substr(8, 9);
		//this.Local = aCity[parseInt(CardNo18.substr(0, 2))];
		return true;
	};
	$.IsDate = function (strDate) {
		var r = strDate.match(/^(\d{1,4})(\d{1,2})(\d{1,2})$/);
		if (r == null) {
			return false;
		}
		var d = new Date(r[1], r[2] - 1, r[3]);
		return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[2] && d.getDate() == r[3]);
	};
	
	
	/**
	 * @deprecated 验证表单     input select元素加入以下属性进行验证
	 * isnull="y",不能为空
	 * validate="isnumber",值是数字
	 * validate="isemaile",值是邮箱
	 * validate="ismobile",值是手机
	 * validate="istel",值是电话
	 * minlength="6",值的最小长度6位
	 * maxlength="15",值的最大长度15位
	 * format="自己定义的全局函数的名称" 接收当前input元素jquery对象, 返回如{rs:false,msg:"密码输入不一致"}
	 * @date 2014-10-30
	 * @param  form, 表单的jquery对象
	 * @returns  验证通过  返回true,否则返回false
	 * 
	 */
	
	$.formvalidate=function(form,position){
		  
		  var messagehtml=$("<div id='form_validate_messsage' style='border-radius:4px;background: #fdd;color:#000;display: none;padding: 5px 5px 5px 5px;' ></div>");
		  inputs=form.find("input,select"),
		  startinput=null;
		  if(position){
			  messagehtml.css("position","absolute");
		  }else{
			  messagehtml.css("position","fixed");
		  }
		  form.append(messagehtml);
		  var msgDiv=$("#form_validate_messsage"),
		  msg="",
		  isNullRs=true,
		  result=true,
		  minLenRs=true,
		  maxLenRs=true,
		  formatRs=true;
		  
		  
		  
		  this.bindEvent=function(obj){
			  
			  obj.bind("mouseover",function(e){ 
					/* var xx = e.pageX; 
					 var yy = e.pageY+10; 	*/
					 var postion=obj.position();
					 msgDiv.css({top: obj.offset().top+obj.outerHeight(), left: obj.offset().left});
					 msgDiv.text(obj.attr("tip"));
					 msgDiv.show();
			 })
			 
			  obj.bind("mouseout",function(e){ 
					 msgDiv.hide();
			 }) 
			 
			  obj.bind("input change",function(e){ 
				  var jInput=$(this);
		    	  var type=jInput.attr("validate");
		    	  var isnull=jInput.attr("isnull");
		    	  var minLength=jInput.attr("minlength");
				  var maxLength=jInput.attr("maxlength");
				  var format=jInput.attr("format");
		    	  var res=false; 
				  if(type||isnull){
					 if(isnull=='y'&&!jInput.val()||jInput.val()=='请选择省'||jInput.val()=='请选择市') {
						 jInput.attr("tip","该输入项不能为空");
						 res=false;
						 msgDiv.text(jInput.attr("tip"));
						 msgDiv.show();
					 }else{
						 res=true;	 
					  if(minLength){
							 if(jInput.val().length<parseInt(minLength)){
								 jInput.attr("tip","该输入项长度不能小于"+minLength);
								 msgDiv.text(jInput.attr("tip"));
								 msgDiv.show();
								 res=false;
								
							 }else{
								 res=true; 
							 }
					   }
						 
					   if(maxLength){
							 if(jInput.val().length>parseInt(maxLength)){
								 jInput.attr("tip","该输入项长度不能大于"+minLength);
								 msgDiv.text(jInput.attr("tip"));
								 msgDiv.show();
								 res=false;
								
							 }else{
								 res=true;
							 }
					  }
					  if(format){
					     var formatRsO=eval(format)(jInput);
						 
						 if(!formatRsO.rs){ 
							 jInput.attr("tip",formatRsO.msg);
							 msgDiv.text(jInput.attr("tip"));
							 msgDiv.show();
							 res=false;
						 }else{
							 res=true;
						 }
					  }
					  if(type=='isnumber'){
						
						 if($.isNmber(jInput.val())||(isnull!='y'&&!jInput.val())){
						    res=true;
						 }else{
							jInput.attr("tip","该输入项不是数字");
							msgDiv.text(jInput.attr("tip"));
							msgDiv.show();
							res=false;
						 }
					 }else if(type=='isemaile'){
						if($.isEmail(jInput.val())||(isnull!='y'&&!jInput.val())){
							 res=true;
						}else{
							jInput.attr("tip","该输入项不是邮箱");
							msgDiv.text(jInput.attr("tip"));
							msgDiv.show();
							res=false;
						}
					 }else if(type=='ismobile'){
						 if($.isMobile(jInput.val())||(!isnull&&!jInput.val())){
							 res=true;
						  }else{
							 jInput.attr("tip","该输入项不是手机号");
							 msgDiv.text(jInput.attr("tip"));
							 msgDiv.show();
							 res=false;
						 }
						 
						 
					 }else if(type=='istel'){
						 if($.isTel(jInput.val())||(isnull!='y'&&!jInput.val())){
							 res=true;
						  }else{
						     res=false;
						     jInput.attr("tip","该输入项不是电话号码");
						     msgDiv.text(jInput.attr("tip"));
							 msgDiv.show();
						  }
					   }
					 
				     }
				  }
		    	 if(res) {
		    		 jInput.css("border-color","#ccc");
		    		 jInput.unbind("mouseover");
		    		 jInput.unbind("mouseout");
		    		 msgDiv.hide();
		    	 }else{
		    		 obj.bind("mouseover",function(e){ 
						 var xx = e.pageX; 
						 var yy = e.pageY; 	
						 msgDiv.css({top: obj.offset().top, left: obj.offset().left+obj.outerWidth()+5});
						 msgDiv.text(obj.attr("tip"));
						 msgDiv.show();
				     })
				 
				     obj.bind("mouseout",function(e){ 
				    	 msgDiv.hide();
				     })
				     
				     jInput.css("border-color","#f00");
		    	 }
			  })
		  }
		  
		  
		  for(i=0;i<inputs.length;i++){
			  jInput=$(inputs[i]);
			  var type=jInput.attr("validate");
			  var isnull=jInput.attr("isnull");
			  var minLength=jInput.attr("minlength");
			  var maxLength=jInput.attr("maxlength");
			  var format=jInput.attr("format");
			  if(type||isnull){
				 if(isnull=='y'&&!jInput.val()||jInput.val()=='请选择省'||jInput.val()=='请选择市') {
					 jInput.attr("tip","该输入项不能为空");
					 jInput.css("border-color","#f00");
					 this.bindEvent(jInput);
					 isNullRs=false;
					 continue;
				 }else if(isnull!='y'&&!jInput.val()||jInput.val()=='请选择省'||jInput.val()=='请选择市'){
					 
					 //this.bindEvent(jInput);
					 if(!isNullRs){
						continue;
					 }else if(!isNullRs){
						 result=true;
						 continue;
					 }
					
					 isNullRs=true;
					
				 }
				 
				 if(minLength){
					 if(jInput.val().length<parseInt(minLength)){
						
						 jInput.attr("tip","该输入项长度不能小于"+minLength);
						 this.bindEvent(jInput);
						 jInput.css("border-color","#f00");
						 minLenRs=false;
						 continue;
					 }
				 }
				 
				 if(maxLength){
					 if(jInput.val().length>parseInt(maxLength)){
						 jInput.attr("tip","该输入项长度不能大于"+maxLength);
						 this.bindEvent(jInput);
						 jInput.css("border-color","#f00");
						 maxLenRs=false;
						 continue;
					 }
				 }
				 
				 if(format){
					 var formatRsO=eval(format)(jInput);
					 
					 if(!formatRsO.rs){ 
						 jInput.attr("tip",formatRsO.msg);
						 this.bindEvent(jInput);
						 jInput.css("border-color","#f00");
						 formatRs=false;
						 continue;
					 }
				 }
				 
				 if(type=='isnumber'&&!$.isNmber(jInput.val())&&jInput.val()){
					 jInput.attr("tip","该输入项不是数字");
					 this.bindEvent(jInput);
					 jInput.css("border-color","#f00");
					 result=false;
					 continue;
					 
				 }else if(type=='isemaile'&&!$.isEmail(jInput.val())&&jInput.val()){
					 jInput.attr("tip","该输入项不是邮箱");
					 this.bindEvent(jInput);
					 jInput.css("border-color","#f00");
					 result=false;
					 continue;
					
				 }else if(type=='ismobile'&&!$.isMobile(jInput.val())&&jInput.val()){
					 jInput.attr("tip","该输入项不是手机号");
					 this.bindEvent(jInput);
					 jInput.css("border-color","#f00");
					 result=false;
					 continue;
				 }else if(type=='istel'&&!$.isTel(jInput.val())&&jInput.val()){
					 jInput.attr("tip","该输入项不是电话号码");
					 this.bindEvent(jInput);
					 jInput.css("border-color","#f00");
					 result=false;
					 continue;
				 }
			  }
		  }
		if(result&&isNullRs&&maxLenRs&&minLenRs&&formatRs){
			return true;
		}else{
			return false;
		}  
		return result;
	  }
	
})(jQuery);


