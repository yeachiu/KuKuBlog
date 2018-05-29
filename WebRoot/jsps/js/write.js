/*回显标签名*/ 
$(function(){
   $(document).on("click","#addtag",function () {
	   
       var tagname = $('#InputName0').val(); 
       var div=window.document.getElementById("blogtags");  //显示标签内容的div
       var w1='<span class="label label-info"><span class="ttag">';
       var w2='</span><span class="glyphicon glyphicon-remove text-danger remove"></span></span>';
      
       //var w3='</div>';
       var _html=w1+tagname+w2;
       $("#blogtags").append(_html); 
//       $.ajax({ 
//      		cache: false,
//			async: false, 
//           type: "post",  
//           dataType: "json",  
//           url: "/blog/TagServlet",  
//           data: { method:"addTagname",tagname:tagname},  
//           success: function (msg) { 
//        	   alert("插入成功！");
//        	   var json = eval(msg); //数组
//        	   var _html= ''; 
//                   var tagId = json[0].tagId;
//                   var tagname = json[0].tagname;  
//                   //var w0= '<div id="bt'+tagId+'">';
//                   var w1='<span class="label label-info"><span class="ttag">';
//                   var w2='</span><span class="glyphicon glyphicon-remove text-danger remove"></span></span>';
//                  
//                   //var w3='</div>';
//                   _html=w1+tagname+w2;
//                   $("#blogtags").append(_html); 
//           },  
//       });  
   });
   
   /* 删除 */
   $(document).on("click",".remove",function () {  
       this.parentNode.parentNode.removeChild(this.parentNode);
   });
   
   
});/*endfunction()*/