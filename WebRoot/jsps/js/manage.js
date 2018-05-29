function loading(){
	mantag();
	// 标签管理页---添加标签
	$(document).on("click","#addtag",function () {  
	       var tagname = $('#InputName0').val();
	       tagname=tagname.replace(/\s+/g,"");
	       if(tagname!=""){
	    	  
	    	   $.ajax({
	    		   cache: false,
	    		   async: false,
	    		   type: "post",
	    		   dataType:"json",
	    		   data:{method:"booleanTagName",tagname:tagname},
	    		   url:"/blog/MTagServlet",
	    		   success: function (msg) { 
	    			   
	    			   if(!msg){
	    				   $.ajax({ 
	    					   cache: false,
	    					   async: false, 
	    			           type: "post",  
	    			           url: "/blog/MTagServlet",  
	    			           data: { method:"addTagname",tagname:tagname},  
	    			           success: function () {
	    			        	  
	    			        	   	$("#msg").empty(); 
	    			   				$("#msg").append('<p class="msgtext">添加成功！</p>')
	    			   				setTimeout('$("#msg").show("slow")',1000);
	    			   				setTimeout('$("#msg").hide("slow")',6000);
	    			        	   mantag();
	    			           }, 
	    			           error:function(){
	    			        	   console.log("添加标签出错！");
	    		    			}
	    			       }); 
	    				}else{
	    					alert("该标签已存在！");
	    				}
	    				
	    			},
	    			error:function(msg){
	    				alert("操作失败！");
	    			}
	    	   });
	    	   
	    	   
	       }else{
	    	   alert("标签名不能为空！");
	       }/*end.if*/       
	   });
}
/* 标签管理页---删除标签 */
function delt(tagId){
	 var bool=confirm ("确定删除此标签吗？");
	 if(bool){
		 $.ajax({
				cache: false,
				async: false,
				type: "post",
				data: {method: "deletetag",tagId:tagId},
				url: "/blog/MTagServlet",
				success: function() {
					$("#msg").empty(); 
					$("#msg").append('<p class="msgtext">删除成功！</p>')
					setTimeout('$("#msg").show("slow")',1000);
					setTimeout('$("#msg").hide("slow")',6000);
					
				},
				error:function(){
					$("#msg").empty(); 
					$("#msg").append('<p class="msgtext">删除失败！</p>')
					setTimeout('$("#msg").show("slow")',1000);
					setTimeout('$("#msg").hide("slow")',6000);
				}
			});
			mantag();
	 }
	
}

function delb(blogId){
	var bool=confirm ("确定删除此该文章吗？");
	 if(bool){
		$.ajax({
			cache: false,
			async: false,
			type: "post",
			data: {method: "deleteblog",blogId:blogId},
			url: "/blog/MBlogServlet",
			success: function() {
				$("#msg").empty(); 
				$("#msg").append('<p class="msgtext">删除成功！</p>')
				setTimeout('$("#msg").show("slow")',1000);
				setTimeout('$("#msg").hide("slow")',6000);
				
			},
			error:function(){
				$("#msg").empty(); 
				$("#msg").append('<p class="msgtext">删除失败！</p>')
				setTimeout('$("#msg").show("slow")',1000);
				setTimeout('$("#msg").hide("slow")',6000);
			}
		});
		loading1();
	 }
}
function mantag(){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findAllTag"},
		url: "/blog/TagServlet",
		success: function(info) {
			var json = eval(info); //标签集，数组
			
			var _htmls='<tbody><tr><th>排序</th><th>标签名</th><th>操作</th><th>文章数</th></tr>';
			var tagtag='';
			/*循环生成部分*/
			var w1= '<tr><td>';
			
            var w3= '</a></td>';
            
            var w6= '</td></tr>';
            
            var Idlist = new Array();
			var urllist = new Array();/*存储得到标签及的“查看全部链接”*/
			$("#mtagtable").empty();    
			$.each(json, function (index, item) {
                 var tagId = json[index].tagId;//键值
                 var tagname = json[index].tagname;//键值
                 var blognum = json[index].blognum;
                 var url='/blog/BlogServlet?method=findByTagId&tagId='+tagId;
                 Idlist[index]=tagId;
                 urllist[index]=url;
                 var w2= '</td><td class="tagName"><a href="" id="rtaglink'+tagId+'">';
                 var w5= '<span onclick="delt(&apos;'+tagId+'&apos;);">删除</span></td><td class="blogNum">';
                 var w4= '<td><a data-toggle="modal" data-target="#myModal" onclick="edittag('+tagId+','+tagname+');">编辑</a>&nbsp;&nbsp;';
                 tagtag = tagtag+w1+(index+1)+w2+tagname+w3+w4+w5+blognum+w6;
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		_htmls+='</tbody></div>';
    		$("#mtagtable").append(_htmls);
    		/*****************************/
    		for(var a=0;a<Idlist.length;a++){
    			var tagId=Idlist[a];
    			var set="rtaglink"+tagId;   			
    			$("#"+set+"").attr("href",urllist[a]);
    			
    		}/*end.for*/
		}/*end.success*/
	});/*end.ajax1*/
};/* end.function */

function edittag(tagId,tagname){
	var a=1;
	var b=tagId;
	alert(a);
	alert(tagId);
	alert(tagname);
//	$("#newtag").attr("value","tagname");
//	$("#theId").attr("value","tagId");
	
}

function loading1(){
	
	manblog_all();
	manblog_pub();
	manblog_dra();
}
function manblog_all(){
		$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findAllBlog"},
		url: "/blog/MBlogServlet",
		success: function(info) {
			var json = eval(info); //标签集，数组
			var _htmls='<tbody><tr><th>排序</th><th>标题</th><th>创建时间</th><th>发布状态</th><th>操作</th></tr>';
			var tagtag='';
			/*循环生成部分*/
			var w1= '<tr><td>';			
            var w3= '</a></td>';
            var w4= '<td class="blogTime">';
            var w5='</td><td class="status">';
            var Idlist = new Array();/*保存文章集的ID*/
            var urllist1 = new Array();/*保存文章集的文章页url*/
			var urllist2 = new Array();/*保存文章集的文章页url*/
            
			$("#allblist").empty();    
			$.each(json, function (index, item) {
				var blogTime = json[index].blogTime;//键值
                var blogTitle = json[index].blogTitle;//键值
                var blogContent = json[index].blogContent;//键值
                var blogId = json[index].blogId;
                var status = json[index].status;
                var url1='/blog/BlogServlet?method=showblogbyid&blogId='+blogId;
                var url2='/blog/MBlogServlet?method=loadblog&blogId='+blogId;
                Idlist[index]=blogId;
                urllist1[index]=url1;
                urllist2[index]=url2;
                var w2= '</td><td class="blogTitle"><a class="blogtag setall'+blogId+'">';
                var w6= '</td><td><a href="" class="blogtag editall'+blogId+'">编辑</a>&nbsp;&nbsp;';
                var w7= '<span onclick="delb(&apos;'+blogId+'&apos;);">删除</span>&nbsp;&nbsp;';
                var w8= '<span onclick="cstatus(&apos;'+blogId+'&apos;);">更改状态</span></td></tr>';
                if(status){
                	var st="已发布";
                }else{
                	var st="草稿";
                }
                 tagtag = tagtag+w1+(index+1)+w2+blogTitle+w3+w4+blogTime+w5+st+w6+w7+w8;
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		_htmls+='</tbody></div>';
    		$("#allblist").append(_htmls);
    		/***************************************/
    		
    		for(var a=0;a<Idlist.length;a++){
    			var blogId=Idlist[a];
    			var set="setall"+blogId;
    			var edit="editall"+blogId;
    			$("."+set+"").attr("href",urllist1[a]);
    			$("."+edit+"").attr("href",urllist2[a]);
    		}
		}/*end.success*/
	});/*end.ajax1*/
}/*end.function*/
function manblog_pub(){
	
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findPubBlog"},
		url: "/blog/MBlogServlet",
		success: function(info) {
			var json = eval(info); //标签集，数组
			var _htmls='<tbody><tr><th>排序</th><th>标题</th><th>创建时间</th><th>发布状态</th><th>操作</th></tr>';
			var tagtag='';
			/*循环生成部分*/
			var w1= '<tr><td>';
			
            var w3= '</a></td>';
            var w4= '<td class="blogTime">';
            var w5='</td><td class="status">';
            var Idlist = new Array();/*保存文章集的ID*/
            var urllist1 = new Array();/*保存文章集的文章页url*/
			var urllist2 = new Array();/*保存文章集的文章页url*/
			$("#pubblist").empty();    
			$.each(json, function (index, item) {
				var blogTime = json[index].blogTime;//键值
                var blogTitle = json[index].blogTitle;//键值
                var blogContent = json[index].blogContent;//键值
                var blogId = json[index].blogId;
                var status = json[index].status;
                var url1='/blog/BlogServlet?method=showblogbyid&blogId='+blogId;
                var url2='/blog/MBlogServlet?method=loadblog&blogId='+blogId;
                Idlist[index]=blogId;
                urllist1[index]=url1;
                urllist2[index]=url2;
                var w2= '</td><td class="blogTitle"><a class="blogtag setpub'+blogId+'">';
                var w6= '</td><td><a href="" class="blogtag editpub'+blogId+'">编辑</a>&nbsp;&nbsp;';
                var w7= '<span onclick="delb(&apos;'+blogId+'&apos;);">删除</span>&nbsp;&nbsp;';
                var w8= '<span onclick="cstatus(&apos;'+blogId+'&apos;);">更改状态</span></td></tr>';
                if(status){
                	var st="已发布";
                }else{
                	var st="草稿";
                }
                 tagtag = tagtag+w1+(index+1)+w2+blogTitle+w3+w4+blogTime+w5+st+w6+w7+w8;
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		_htmls+='</tbody></div>';
    		$("#pubblist").append(_htmls);
    		/***************************************/
    		
    		for(var a=0;a<Idlist.length;a++){
    			var blogId=Idlist[a];
    			var set="setpub"+blogId;
    			var edit="editpub"+blogId;
    			$("."+set+"").attr("href",urllist1[a]);
    			$("."+edit+"").attr("href",urllist2[a]);
    		}
		}/*end.success*/
	});/*end.ajax1*/
}/*end.function*/
function manblog_dra(){
	
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findDraftBlog"},
		url: "/blog/MBlogServlet",
		success: function(info) {
			
			var json = eval(info); //标签集，数组
			var _htmls='<tbody><tr><th>排序</th><th>标题</th><th>创建时间</th><th>发布状态</th><th>操作</th></tr>';
			var tagtag='';
			/*循环生成部分*/
			var w1= '<tr><td>';
			
            var w3= '</a></td>';
            var w4= '<td class="blogTime">';
            var w5='</td><td class="status">';   
            var Idlist = new Array();/*保存文章集的ID*/
			var urllist1 = new Array();/*保存文章集的文章页url*/
			var urllist2 = new Array();/*保存文章集的文章页url*/
			$("#drablist").empty();    
			$.each(json, function (index, item) {
				var blogTime = json[index].blogTime;//键值
                var blogTitle = json[index].blogTitle;//键值
                var blogContent = json[index].blogContent;//键值
                var blogId = json[index].blogId;
                var status = json[index].status;
                var url1='/blog/BlogServlet?method=showblogbyid&blogId='+blogId;
                var url2='/blog/MBlogServlet?method=loadblog&blogId='+blogId;
                Idlist[index]=blogId;
                urllist1[index]=url1;
                urllist2[index]=url2;
                var w2= '</td><td class="blogTitle"><a class="blogtag setdra'+blogId+'">';
                var w6= '</td><td><a href="" class="blogtag editdra'+blogId+'">编辑</a>&nbsp;&nbsp;';
                var w7= '<span onclick="delb(&apos;'+blogId+'&apos;);">删除</span>&nbsp;&nbsp;';
                var w8= '<span onclick="cstatus(&apos;'+blogId+'&apos;);">更改状态</span></td></tr>';
                if(status){
                	var st="已发布";
                }else{
                	var st="草稿";
                }
                 tagtag = tagtag+w1+(index+1)+w2+blogTitle+w3+w4+blogTime+w5+st+w6+w7+w8;
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		_htmls+='</tbody></div>';
    		$("#drablist").append(_htmls);
    		/***************************************/
    		
    		for(var a=0;a<Idlist.length;a++){
    			var blogId=Idlist[a];
    			var set="setdra"+blogId;
    			var edit="editdra"+blogId;
    			$("."+set+"").attr("href",urllist1[a]);
    			$("."+edit+"").attr("href",urllist2[a]);
    		}
		},/*end.success*/
		error:function(){
			console.log("Draft manage error!");
		}
	});/*end.ajax1*/
}/*end.function*/

function cstatus(blogId){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType:"json",
		data: {method: "changStatus",blogId:blogId},
		url: "/blog/MBlogServlet",
		success: function(status) {
			if(status){
				$("#msg").empty(); 
   				$("#msg").append('<p class="msgtext">文章发布成功！</p>')
   				setTimeout('$("#msg").show("slow")',1000);
   				setTimeout('$("#msg").hide("slow")',6000);
				
			}else{
				$("#msg").empty(); 
   				$("#msg").append('<p class="msgtext">已保存为草稿！</p>')
   				setTimeout('$("#msg").show("slow")',1000);
   				setTimeout('$("#msg").hide("slow")',6000);
				
			}
			
		},
		error:function(status){
			alert("操作失败！");
		}
	});
	loading1();
}