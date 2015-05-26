$(function(){
	//保存数据
	$("#save_btn").click(function(){
		var me=$(this);
		var form=$("#edit_form");
		var check=form.form('validate');
		if(!check)  return;
		var json =form.serialize();		
		me.attr('disabled','disabled');
		$.messager.progress();		
		$.ajax({
		     type: 'POST',
			 url:form.attr("action"),
			 dataType:'json',
			 data:json,
			 success: function(data) {
				 $.messager.progress('close');				  
				  if(data.msg){
						$.messager.alert("提示消息",data.msg,"info");
				  }
                 
			 },
			 error : function(XMLHttpRequest, strError, strObject) {
					$('#deploy-add-btn').removeAttr('disabled');
					$.alert('请求失败！');
			 }		
	   });
		
	});
	
	var uploader = WebUploader.create({

	    // 选完文件后，是否自动上传。
	    auto: true,
	    //允许重复上传
	    duplicate:true,
	    // swf文件路径
	    swf: baseJS + '/webuploader0.1.5/Uploader.swf',

	    // 文件接收服务端。
	    server: baseJS+"/framework/ueditor1_4_3/jsp/controller.jsp?action=uploadimage&encode=utf-8",

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
	    	
	    	id:"#filePicker",
	    	multiple :false
	    },
	    	           
	    //多选
	   
	    // 只允许选择图片文件。
	    accept: {
	        title: '封面上传',
	        extensions: 'gif,jpg,jpeg,bmp,png',
	        mimeTypes: 'image/*'
	    }
	});
 
 
 //图片预览
 uploader.on( 'fileQueued', function( file ) {
	    var $li = $(
	            '<div id="' + file.id + '" class="file-item thumbnail">' +		                
	                '<img>' +
	            '</div>'
	            ),
	        $img = $li.find('img');


	    // $list为容器jQuery实例
	    $("#fileList").children().remove();
	    $("#fileList").append($li);

	    // 创建缩略图
	    // 如果为非图片文件，可以不用调用此方法。
	    
	    // thumbnailWidth x thumbnailHeight 为 100 x 100
	    //缩略图大小
	    var thumbnailWidth=100;
	    var thumbnailHeight=100;
	    uploader.makeThumb( file, function( error, src ) {
	        if ( error ) {
	            $img.replaceWith('<span>不能预览</span>');
	            return;
	        }
	        $img.css("cursor","pointer");
	        $img.attr( 'src', src );
	        $img.attr("title","双击删除");
	        //添加双击事件,移除包含图片的div
	        $img.dblclick(function(){
	        	var me=$(this);
	        	me.parent().remove();
	        });
	    }, thumbnailWidth, thumbnailHeight );
	});
 
// 文件上传过程中创建进度条实时显示。
 uploader.on( 'uploadProgress', function( file, percentage ) {
     var $li = $( '#'+file.id ),
         $percent = $li.find('.progress span');

     // 避免重复创建
     if ( !$percent.length ) {
         $percent = $('<p class="progress"><span></span></p>')
                 .appendTo( $li )
                 .find('span');
     }

     $percent.css( 'width', percentage * 100 + '%' );
 });

 // 文件上传成功，给item添加成功class, 用样式标记上传成功。
 uploader.on( 'uploadSuccess', function( file ,response) {		
     $( '#'+file.id ).addClass('upload-state-done');
     
     //添加提示信息
     var $li = $( '#'+file.id );
     $info = $li.find('div.correct');

     // 避免重复创建
     if ( !$info.length ) {
	    $info = $('<div class="correct"></div>').appendTo( $li );
     }
     var $li = $( '#'+file.id ),
     $info = $li.find('div.error');

    // 避免重复创建
    if ( !$info.length ) {
	     $info = $('<div class="correct"></div>').appendTo( $li );
    }
    
    $info.text('上传成功,双击删除');
    var $imgUrl=$('<input name="logo" type="hidden" >');
    
    $imgUrl.val(response["url"]);
     
    $imgUrl.appendTo( $li );
     
     
     
     
 });

 // 文件上传失败，显示上传出错。
 uploader.on( 'uploadError', function( file ) {
     var $li = $( '#'+file.id ),
         $info = $li.find('div.error');

     // 避免重复创建
     if ( !$info.length ) {
    	 $info = $('<div class="error"></div>').appendTo( $li );
     }
     var $li = $( '#'+file.id ),
     $info = $li.find('div.error');

     // 避免重复创建
     if ( !$info.length ) {
    	 $info = $('<div class="error"></div>').appendTo( $li );
     }
     $info.text('上传失败,双击删除');
     
 });
 


 // 完成上传完了，成功或者失败，先删除进度条。
 uploader.on( 'uploadComplete', function( file ) {		 
	 //移除进度条
     $( '#'+file.id ).find('.progress').remove();
 });
	

})

