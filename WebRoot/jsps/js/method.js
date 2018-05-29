//window.onunload = initlistcolumn();

function loading(){
	findPublishedBlog1();
	findAllTag2();
}
function findPublishedBlog1(){
		$.ajax({
			cache: false,
			async: false,
			type: "post",
			dataType: "json",
			data: {method: "findPublishedBlog"},
			url: "/blog/BlogServlet",
			success: function(info) {
				var json = eval(info); //数组
				var _htmls='<ul class="list-group panel panel-default">';
				var w1=	'<li class="list-group-item">'+'<div class="panel-body">'+'<span class="blogTime">';
				var w2= '</span><h3 class="blogTitle text-primary">';
				var w3= '</h3><div class="blogContent">';
				var w5='</div></div></li>';
				var Idlist = new Array();/*保存文章集的ID*/
				var urllist = new Array();/*保存文章集的文章页url*/
				$("#bloglist1").empty();
				    
				$.each(json, function (index, item) {
					
	                 var blogTime = json[index].blogTime;//键值
	                 var blogTitle = json[index].blogTitle;//键值
	                 var blogContent = json[index].blogContent;//键值
	                 var blogId = json[index].blogId;

	                 var url='/blog/BlogServlet?method=showblogbyid&blogId='+blogId;
	                 Idlist[index]=blogId;
	                 urllist[index]=url;
	                 var link='</div><a class="blogtag setval'+blogId+'"><h6>【显示全文】</h6></a><hr>';
	                 var w4= '<div class="blogtags"><div class="blogtag'+blogId+'">';

	                 _htmls = _htmls+w1+blogTime+w2+ blogTitle +w3+ blogContent +link+w4+w5;
//	                 console.log(info);
	    		});
	    		_htmls+='</ul>';
	    		$("#bloglist1").append(_htmls);

	    		/***************************************/
	    		
	    		for(var a=0;a<Idlist.length;a++){
	    			var blogId=Idlist[a];
	    			var set="setval"+blogId;
	    			$("."+set+"").attr("href",urllist[a]);
	    			/*查询出文章下的标签*/
	    			$.ajax({
			 			cache: false,
			 			async: false,
			 			type: "post",
			 			dataType: "json",
			 			data: {method: "findByBlogId",blogId:blogId},
			 			url: "/blog/TagServlet",
			 			success: function(data) {
			 				var data = eval(data); //数组
			 				var blogtag="";	//定义变量要记得初始化！！！
			 				var id="blogtag"+blogId;
				 				$.each(data, function (ii, tt) {
				 	                 var tagId = data[ii].tagId;//键值
				 	                 var tagname = data[ii].tagname;//键值
				 	                 var url = '';
				 	                 //【占位】：点击该标签进入标签文章页
				 	                $("."+id+"").empty();//清除blogid对应#blogtag下的内容
				 	                var taghtml = '<a onclick="totag(&apos;'+tagId+'&apos;);" class="label label-success">'+tagname+'</a>';
//				 	               console.log(taghtml);
				 	                blogtag+=taghtml;//循环结束，为该#blogtag下要添加的内容
				 	              
				 	    		});
				 				
				 				$("."+id+"").append(blogtag);
			 				
			 			}
			 	});/*ajax2*/
			}
	    		
				
			}
		});
		
};//文件加载完执行

function totag(tagId){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		data: {method: "findByTagId",tagId:tagId},
		url: "/blog/BlogServlet",
		success: function() {
			return location.href="jsps/tag/tag.jsp";
		},
		error:function(){
			alert("返问失败！");
		}
	});
}

function findPublishedBlog2(){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findPublishedBlog"},
		url: "/blog/BlogServlet",
		success: function(info) {
	
			var json = eval(info); //数组
			var _htmls='<h2 class="text-primary" id="tit">文章列表</h2><ul class="list-group panel panel-default">';
			var w1=	'<li class="list-group-item">'+'<div class="panel-body">'+'<span class="blogTime">';
			var w2= '</span><h3 class="blogTitle text-primary">';
			var w3= '</h3><div class="blogContent">';
			var w5='</div></div></li>';
			var Idlist = new Array();/*保存文章集的ID*/
			var urllist = new Array();/*保存文章集的文章页url*/
			$("#bloglist2").empty();
			    
			$.each(json, function (index, item) {
				
				var blogTime = json[index].blogTime;//键值
                var blogTitle = json[index].blogTitle;//键值
                var blogContent = json[index].blogContent;//键值
                var blogId = json[index].blogId;
                var url='/blog/BlogServlet?method=showblogbyid&blogId='+blogId;
                Idlist[index]=blogId;
                urllist[index]=url;
                var link='</div><a class="blogtag setval'+blogId+'"><h6>【显示全文】</h6></a><hr>';
                var w4= '<div class="blogtags"><div class="blogtag'+blogId+'">';

                _htmls = _htmls+w1+blogTime+w2+ blogTitle +w3+ blogContent +link+w4+w5;
//                 console.log(info);
    		});
    		_htmls+='</ul>';
    		$("#bloglist2").append(_htmls);

    		/***************************************/
    		
    		for(var a=0;a<Idlist.length;a++){
    			var blogId=Idlist[a];
    			var set="setval"+blogId;
    			$("."+set+"").attr("href",urllist[a]);
    			
    			$.ajax({
		 			cache: false,
		 			async: false,
		 			type: "post",
		 			dataType: "json",
		 			data: {method: "findByBlogId",blogId:blogId},
		 			url: "/blog/TagServlet",
		 			success: function(data) {
		 				
		 				var data = eval(data); //数组
		 				var blogtag="";	//定义变量要记得初始化！！！
		 				var id="blogtag"+blogId;
//		 				var Idlist = new Array();
//		 				var urllist = new Array();/*存储得到标签及的“查看全部链接”*/
			 				$.each(data, function (ii, tt) {
			 	                 var tagId = data[ii].tagId;//键值
			 	                 var tagname = data[ii].tagname;//键值
//			 	                 var url='/blog/BlogServlet?method=findByTagId&tagId='+tagId;
//			 	                 Idlist[ii]=tagId;
//			 	                 urllist[ii]=url;
			 	                 //【占位】：点击该标签进入标签文章页
			 	                $("."+id+"").empty();//清除blogid对应#blogtag下的内容
			 	                var w1 = '<a href="#" class="label label-success btaglink'
			 	                var taghtml =w1+tagId+'">'+tagname+'</a>';
//			 	               console.log(taghtml);
			 	                blogtag+=taghtml;//循环结束，为该#blogtag下要添加的内容
			 	              
			 	    		});
			 				
			 				$("."+id+"").append(blogtag);
			 				
//			 				for(var a=0;a<Idlist.length;a++){
//			 	    			var tagId=Idlist[a];
//			 	    			var set="btaglink"+tagId;
//			 	    			$("."+set+"").attr("href",urllist[a]);
//			 	    		}/*end.for*/
		 			}/*end.each*/
		 	});/*ajax2*/
		}
    		
			
		}
	});/*ajax1*/
	
};//文件加载完执行

/*左侧sidebar标签栏目*/
function findAllTag1(){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findAllTag"},
		url: "/blog/TagServlet",
		success: function(info) {
			var json = eval(info); //数组
			var _htmls='<div class="row" id="tagsllist">';
			var tagtag='';
			var w1= '<div class="col-xs-8 col-lg-8"><h3 class="tagName text-primary">';
			var w2= '</h3><div class="table-responsive">';
            var Idlist = new Array();
			var urllist = new Array();/*存储得到标签及的“查看全部链接”*/
			$("#tagslist").empty();    
			$.each(json, function (index, item) {
                 var tagId = json[index].tagId;//键值
                 var tagname = json[index].tagname;//键值
         
                 var url='/blog/BlogServlet?method=findByTagId&tagId='+tagId;
                 var w4= '<p><a  href="tag.html" role="button" class="btn btn-default settag'+tagId+'">查看全部 »</a></p></div>';
                 Idlist[index]=tagId;
                 urllist[index]=url;
                 var w3= '<table class="table" id="tagblog'+tagId+'"></table></div>';

                tagtag = tagtag+w1+tagname+w2+w3+w4;
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		_htmls+='</div>';
    		$("#tagslist").append(_htmls);
    		/*******************************************************/
    		for(var a=0;a<Idlist.length;a++){
    			var tagId=Idlist[a];
    			var set="settag"+tagId;
    			$("."+set+"").attr("href",urllist[a]);
    			$.ajax({
		 			cache: false,
		 			async: false,
		 			type: "post",
		 			dataType: "json",
		 			data: {method: "findByTagIdLimit",tagId:tagId},
		 			url: "/blog/BlogServlet",
		 			success: function(data) {

		 				var data = eval(data); //数组
		 				var tagblog="";	//定义变量要记得初始化！！！
		 				var id="tagblog"+tagId;
			 				$.each(data, function (ii, tt) {
			 					var blogTime = data[ii].blogTime;//键值
			 	                var blogTitle = data[ii].blogTitle;//键值
			 	                var blogContent = data[ii].blogContent;//键值
			 	                var blogId = data[ii].blogId;
			 	                
			 	   
			 	                 //【占位】：点击该标签进入标签文章页
			 	                $("#"+id+"").empty();//清除blogid对应#blogtag下的内容

			 	                var bloghtml = '<tbody><tr><td >'+(ii+1)+'</td><td class="blogTitle"><a href="/blog/BlogServlet?method=showblogbyid&blogId='+blogId+'">'+blogTitle+'</a></td><td class="blogTime">'+blogTime+'</td></tr></tbody>';
//			 	               console.log(taghtml);
			 	               tagblog+=bloghtml;//循环结束，为该#blogtag下要添加的内容
			 	              
			 	    		});
			 				
			 				$("#"+id+"").append(tagblog);
		 				
		 			}
		 	});/*ajax2*/
			}/* end.for */
		}/*end.success*/
	});/*end.ajax1*/
};/* end.function */

/*右侧标签栏目*/
function findAllTag2(){
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "json",
		data: {method: "findAllTag"},
		url: "/blog/TagServlet",
		success: function(info) {
			/*描述：方法访问成功，但是无法显示:解决【输出div的id名多了一个空格。。。】*/
			var json = eval(info); //数组
			var _htmls='';
			var tagtag ="";
			var Idlist = new Array();
			var urllist = new Array();/*存储得到标签及的“查看全部链接”*/
			$("#righttags").empty();    
			$.each(json, function (index, item) {
                 var tagId = json[index].tagId;//键值
                 var tagname = json[index].tagname;//键值
                 var url='/blog/BlogServlet?method=findByTagId&tagId='+tagId;
                 Idlist[index]=tagId;
                 urllist[index]=url;
                 var w= '<a href="" id="rtaglink'+tagId+'">'
                 tagtag = tagtag + w + tagname+'</a>';
//                 console.log(info);
    		});/*end.each*/
			
			_htmls+=tagtag;
    		$("#righttags").append(_htmls);
    		
    		for(var a=0;a<Idlist.length;a++){
    			var tagId=Idlist[a];
    			var set="rtaglink"+tagId;
    			
    			$("#"+set+"").attr("href",urllist[a]);
    			$("#"+set+"").attr("class",classname());
    			
    		}/*end.for*/
		}/*end.success*/
	});/*end.ajax1*/
};/*end.fun*/

function getRandom(){
        //x上限，y下限     
         var x = 6;     
         var y = 0;     
         var rand = parseInt(Math.random() * (x - y + 1) + y);     
         return rand;   
         alert(rand);
}/*end.fun*/
/*生成标签名*/
function classname(){
	var color=getRandom();
	switch(color){
	case 1:
		var classname="label label-primary";break;
	case 2:
		var classname="label label-success";break;
	case 3:
		var classname="label label-info";break;
	case 4:
		var classname="label label-danger";break;
	case 5:
		var classname="label label-default";break;
	default:
		var classname="label label-warning";break;
	}
	return classname;
}

function aboutme(){
	
	$.ajax({
		cache: false,
		async: false,
		type: "post",
		dataType: "text",
		data: {method: "showAboutme"},
		url: "/blog/UserServlet",
		success: function(info) {
			var _htmls=info;
			$("#amCon").append(_htmls);
		},
		error:function(){
			alert("访问失败！");
		}
	});
}