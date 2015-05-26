$(function() {
	$.extend($.fn.validatebox.defaults.rules, {    
	    equals: {    
	        validator: function(value,param){    
	            return value == $(param[0]).val();    
	        },    
	        message: '密码不一致!'   
	    },
	    
	    length:{validator:function(value,param){ 
	    	var len=$.trim(value).length; 
	    	return len>=param[0]&&len<=param[1]; 
	    }, 
	    
	    	message:"输入内容长度必须介于{0}和{1}之间." 
	    },
	    
	    maxLength: {     
            validator: function(value, param){     
                return param[0] >= value.length;     
            },     
            message: '只能输入最大{0}位字符.'    
        },

	    username : {// 验证用户名 
	    	validator : function(value) { 
	    		return /^[a-zA-Z][a-zA-Z0-9_]{3,15}$/i.test(value); 
	    	}, 
	    	message : '登陆名不合法（字母开头，允许4-16字节，允许字母数字下划线）' 
	    },
	    name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名' 
	    },
	    
	    date : {// 验证姓名，可以是中文或英文 
	        validator : function(value) { 
	         //格式yyyy-MM-dd或yyyy-M-d
	            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
	        },
	        message : '清输入合适的日期格式'
	    },
	    loginName:{//检查登陆名
	        validator: function (value) {
	        	var oldName = $(this).attr("oldVaule");
	        	//修改时
	        	if((oldName!=null || oldName!="") && oldName == value) {
	        		return true;
	        	} else {
	        		var validate = $.ajax({
	        			url:baseJS+'/userinfo/checkLoginName.do',
	        			async : false,    
	        			cache : false,
	        			type: "POST",
	        			data: {'loginName': value},
	        			dataType: "json"
	        		}).responseText;
	        		return validate==="true";   
	        	}
	        },  
	        message: '该登陆名已存在,请重新输入'  
	    }  
	    
	}); 
});