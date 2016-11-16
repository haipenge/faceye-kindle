/**
*说明:电子书->book -> 标签 BookTag  js 脚本
*作者:haipenge
*/
var BookTag={
  init:function(){
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
			   BookTag.multiRemove();
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
			  BookTag.recordRemove(id);
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
			  url:'/book/bookTag/multiRemove',
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
			  url:'/book/bookTag/remove/'+id,
			  type:'post',
			  dataType:'json',
			  success:function(data,textStatux,xhr){
				  var msg=new Msg({msg:data.msg});
				  $('#'+id).remove();
				  $('#remove-confirm-modal').modal('hide');
				  msg.show();
			  }
		  });
    }
};

$(document).ready(function(){
	BookTag.init();
});
