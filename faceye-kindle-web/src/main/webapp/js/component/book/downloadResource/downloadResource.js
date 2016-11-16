/**
*说明:电子书->book -> 下载资源 DownloadResource  js 脚本
*作者:haipenge
*/
var DownloadResource={
  init:function(){
	  /**
	   * 点击显示下载地址
	   */
	   $('.click-show-download-url').click(function(){
		   DownloadResource.onShowDownlaodUrlClick();
		   return false;
	   });
       /**
       *全选、全不选
       */
       $('input[name="check-all"]').click(function(){
	     Check.onCheck($('input[name="check-all"]'),$('input[name="check-single"]'));
	    });
	    /**
	    *执行批量删除
	    */
	    $('.multi-remove').click(function(){
	        var modal = new Modal({
	           id:'multi-remove-confirm-modal',
			   title : '确认删除',
			   body : '您确认要删除本条数据吗?',
			   footer : true
		    });
		    modal.show();
		    $('#multi-remove-confirm-modal .modal-footer').delegate("#default-window-btn-yes", "click", function() {
			   DownloadResource.multiRemove();
	     	});
	       
	    });
	    /**
	    *单条记录删除
	    */
	    $('.record-remove').click(function(){
	       var id=$(this).parent().parent().attr('id');
	        var modal = new Modal({
	           id:'remove-confirm-modal',
			   title : '确认删除',
			   body : '您确认要删除本条数据吗?',
			   footer : true
		    });
		    modal.show();
		    $('#remove-confirm-modal .modal-footer').delegate("#default-window-btn-yes", "click", function() {
			  DownloadResource.recordRemove(id);
	     	});
	     });
  },
  /**
   * 批量删除
   */
  multiRemove:function(){
	  var checkedIds=Check.getCheckedIds($('input[name="check-single"]'));
	  if(checkedIds!=''){
		  $.ajax({
			  url:'/book/downloadResource/multiRemove',
			  type:'post',
			  dataType:'json',
			  data:{
				  ids:checkedIds
			  },
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:'数据删除成功'});
				  var idArray=checkedIds.split(',');
				  for(var i=0;i<idArray.length;i++){
				     var id=idArray[i];
				     $('#'+id).remove();
				  }
				  $('#multi-remove-confirm-modal').modal('hide');
				  msg.show();
			  }
		  });
	  }else{
		  var msg=new Msg({msg:'请选择要删除的数据',type:'warning'});
		  msg.show();
	   }
    },
    /**
    *删除一条数据
    */
    recordRemove:function(id){
       $.ajax({
			  url:'/book/downloadResource/remove/'+id,
			  type:'post',
			  dataType:'json',
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:data.msg});
				  $('#'+id).remove();
				  $('#remove-confirm-modal').modal('hide');
				  msg.show();
			  }
		  });
    },
    /**
     * 响应显示下载地址点击事件
     */
    onShowDownlaodUrlClick:function(){
    	var id=$('input[name="id"]').val();
    	$.ajax({
    		url:'/book/downloadResource/showDownloadUrl',
    		type:'post',
    		dataType:'json',
    		data:{
    			id:id
    		},
    		success:function(data,textStatus,xhr){
    			var url=data.url;
    			var html='<a href="'+url+'">点击下载</a>'
    			$('#show-download-url').empty().append(html);
    		}
    	});
    }
};

$(document).ready(function(){
	DownloadResource.init();
});
