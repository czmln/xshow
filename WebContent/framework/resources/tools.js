(function(){

	String.prototype.format = function(args) {
	    var result = this;
	    if (arguments.length > 0) {    
	        if (arguments.length == 1 && typeof (args) == "object") {
	            for (var key in args) {
	                if(args[key]!=undefined){
	                    var reg = new RegExp("({" + key + "})", "g");
	                    result = result.replace(reg, args[key]);
	                }
	            }
	        }
	        else {
	            for (var i = 0; i < arguments.length; i++) {
	                if (arguments[i] != undefined) {
	                    var reg = new RegExp("({[" + i + "]})", "g");
	                    result = result.replace(reg, arguments[i]);
	                }
	            }
	        }
	    }
	    return result;
	};
	
	/**
	 * 根据业务应用ID和字段自动生成编码
	 */
	$.fn.getAutoCode=function(appId,ruleCodeName,resultInput){
		var btn=$(this).attr('id');
		
		if(appId&&ruleCodeName&&resultInput){
			$.ajax({
				url:baseJS+"/coderuleconfig/generateCode.do",
				type:"POST",
				data:{"applicationId":appId,"ruleCodeName":ruleCodeName},
				dataType:"json",
				success:function(backObject){
					if(backObject.success){
						if(backObject.data == null || backObject.data==""){
							$.messager.alert("提示","该业务应用下的该字段没有配置相应规则","info");
						}else{
							$(resultInput).val(backObject.data);
							$('#'+btn).attr("disabled","disabled"); 
						}
					}else{
						$.messager.alert("提示",backObject.message,"info");
					}
				}
			});
		}
	};
	
	
	
	
	$.fn.addTab=function(options){//添加一个tab
		var setting= $.extend({},options);
		if(this.tabs('exists',setting.title)){
			this.tabs('close',setting.title);
		}
			this.tabs('add',{
				title:setting.title,
				content:'<iframe src="'+setting.url+'" frameborder=0 style="width:100%;height:100%;border:none"></iframe>',
				closable:setting.closeable||true
				/*
				tools:[{
					iconCls:'icon-mini-refresh',
					handler:function(){
						alert('refresh');
					}
				}]
				*
				*/
			});
	};

	$.fn.closeTab=function(title){
		if(this.tabs('exists',title)){
		this.tabs('close',title);
		}
	};
	$.fn.existsTab=function(title){
		return this.tabs('exists',title);
	};
	$.editorInit=function(obj,readonly){//多个id
		if(typeof obj==='string'){
			_initEditor('#'+obj,readonly);
		}else if(obj instanceof Array){
			var ids='';
			if(obj.length>0){
				for(var _t in obj){
					ids+=(",#"+obj[_t]);
				}
				_initEditor(ids.substr(1),readonly);
			}
			
			
			
		}
	};
	function _initEditor(ids,readonly){
		KindEditor.ready(function(K) {
			K.create(ids, {
			resizeType : 2,//2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
			readonlyMode:readonly||false,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			filterMode:false,
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist'],
				afterCreate : function() { 
			         this.sync(); 
			        }, 
			        afterBlur:function(){ 
			            this.sync(); 
			        }         
		});
		});
	}

	$.fn.editorInit=function(){
		_initEditor("#"+$(this).attr('id'),($(this).attr("readonly")?true:false));
		
	}
	//弹出住处提示框
	$.getCurrentDate=function(){
		 var d=new Date();
		 var m=d.getMonth()+1;
		 var _m=(m<10?("0"+m):m);
		 return (d.getFullYear()+"-"+_m+"-"+d.getDate());
		
	}
	
	//弹出住处提示框
	$.alert=function(content,fn){
		if(fn){
			$.messager.alert('提示消息',content,'info',fn);	
		}else{
			$.messager.alert('提示消息',content,'info');	
			
		}
	}
	
	$.confirm=function(content,fn){
		 $.messager.confirm('提示消息',content,fn);	
	}
	
	//gird 列头右键菜单 by chen ze
	
	$.showColumnMenu=function(e,field){
	var gridId=this.id;
			 e.preventDefault();
                    if (!_$cmenu$_20121121bycz){  
                        _$cmenu$_20121121bycz=$('<div style="width:100px"/>').appendTo('body'); 
                        _$cmenu$_20121121bycz.menu({  
				                onClick: function(item){  
				                    if (item.iconCls == 'icon-ok'){  
				                        $('#'+gridId).datagrid('hideColumn', item.name);  
				                        _$cmenu$_20121121bycz.menu('setIcon', {  
				                            target: item.target,  
				                            iconCls: 'icon-empty'  
				                        });  
				                    } else {  
				                        $('#'+gridId).datagrid('showColumn', item.name);  
				                        _$cmenu$_20121121bycz.menu('setIcon', {  
				                            target: item.target,  
				                            iconCls: 'icon-ok'  
				                        });  
				                    }  
				                }  
				            });  
				            var fields = $('#'+gridId).datagrid('getColumnFields');  
				            for(var i=0; i<fields.length; i++){  
				                var field = fields[i];  
				                var col = $('#'+gridId).datagrid('getColumnOption', field);
				                if(col.title===undefined) continue;
				                _$cmenu$_20121121bycz.menu('appendItem', {  
				                    text: col.title,  
				                    name: field,  
				                    iconCls: (col.hidden===undefined||col.hidden===false)?'icon-ok':'icon-empty'
				                });  
				            } 
                    }  
                    _$cmenu$_20121121bycz.menu('show', {  
                        left:e.pageX,  
                        top:e.pageY  
                    }); 
        }
	
	$.fn.extend({
		/**
		 * 将form表单序列号为JSON对象
		 * */
		serializeObject : function() {
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [o[this.name]];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		        }
		    });
		    return o;
		},
		/**
		 * 在table中。将选择的某行向上移动一行
		 * @param {tr} 选中的一行
		 * @param {opts} 
		 */
		upRow : function(tr, opts) {
			var defaultOpts = {
					column:0,
					orderField:undefined
				};
			var nowOpts = $.extend({}, defaultOpts, opts);
			
			if(tr.prev().size() > 0){
				tr.attr("update", true);
				tr.prev().attr("update", true);
				
				var t1 = tr.find("td:eq(" + nowOpts.column + ")");
				var t2 = tr.prev().find("td:eq(" + nowOpts.column + ")");
				
				var text = t1.text();
				var text1 = t2.text();
				
				t1.text(parseInt(text1));
				t2.text(parseInt(text1) + 1);
				
				if(nowOpts.orderField != undefined){
					var nom = tr.prev().attr(nowOpts.orderField);
					tr.attr(nowOpts.orderField, nom);
					tr.prev().attr(nowOpts.orderField, parseInt(nom) + 1);
				}
				
				tr.after(tr.prev());
			}
		},
		/**
		 * 在table中。将选择的某行向下移动一行
		 * @param {tr} 选中的一行
		 * @param {opts} 
		 */
		downRow : function(tr, opts) {
			var defaultOpts = {
					column:0,
					orderField:undefined
				};
			var nowOpts = $.extend({}, defaultOpts, opts);
			
			if(tr.next().size() > 0){
				tr.attr("update", true);
				tr.next().attr("update", true);
				
				var t1 = tr.find("td:eq(" + nowOpts.column + ")");
				var t2 = tr.next().find("td:eq(" + nowOpts.column + ")");
				var text = t1.text();
				var text1 = t2.text();
				t1.text(parseInt(text1));
				t2.text(parseInt(text1) - 1);
				
				if(nowOpts.orderField != undefined){
					var nom = tr.next().attr(nowOpts.orderField);
					tr.attr(nowOpts.orderField, nom);
					tr.next().attr(nowOpts.orderField, parseInt(nom) - 1);
				}
				tr.before(tr.next());
			}
			
		},
		/**
		 * 获得被选中的某行
		 * @return {tr} 选中的一行
		 */
		getSelectRow : function(){
			return $(this).find('tbody tr.datagrid-row-selected');
		},
		/**
		 * 获得被移动改变的行
		 * @return {tr} 多行或一行
		 */
		getChangeRows: function(opts){
			
			var defaultOpts = {
					primaryId : "primaryId",
					orderField : undefined
			};
			var nowOpts = $.extend({}, defaultOpts, opts);
			
			var utrs = $(this).find("tbody tr[update='true']");
			var changeRows = new Array();
			if(nowOpts.orderField != undefined){
				utrs.each(function(){
					var data = {};
					data.id = $(this).attr(nowOpts.primaryId);
					data[nowOpts.orderField] = $(this).attr(nowOpts.orderField);
					changeRows.push(data);
				});
			}
			return changeRows;
		},
		getRows: function(opts){
			
			var defaultOpts = {
					primaryId : "primaryId",
					orderField : undefined
			};
			var nowOpts = $.extend({}, defaultOpts, opts);
			
			var utrs = $(this).find('tbody tr');
			var changeRows = new Array();
			if(nowOpts.orderField != undefined){
				utrs.each(function(){
					var data = {};
					data.id = $(this).attr(nowOpts.primaryId);
					data[nowOpts.orderField] = $(this).attr(nowOpts.orderField);
					changeRows.push(data);
				});
			}
			return changeRows;
		}
	});
	
	
	/***
	 * 为时间对象添加格式化方法
	 * 如 new Date().format("yyyy-MM-dd hh:mm:ss");  
	 *   new Date().format("yyyy-MM-dd");
	 * @return {}  string
	 */
	Date.prototype.Format = function(fmt)   
	{ 
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} 	
	
	
	//扩展easyui的tree方法,添加获取节点级别的方法
	$.extend($.fn.tree.methods, {
		getLevel:function(jq,target){
			var l = $(target).parentsUntil("ul.tree","ul");
			return l.length+1;
		}
	});
	//获取验证表单
	$.extend($.fn.validatebox.defaults.rules, {
		
		phoneOrMobile  :{			
			validator : function(value) {
			  if(!value){
				  return true;
			  }
			   var phoneRs=/^0\d{2,3}-?\d{7,8}$/.test(value);
			   var mobileRs=/^0?(13[0-9]|15[012356789]|18[02356789]|14[57])[0-9]{8}$/.test(value);
			   if(phoneRs||mobileRs){
				   return true
			   }else{ 
				   return false;
			   }
			},
		    message : "请输入正确的联系电话"

		}
		
	});
	
})();

