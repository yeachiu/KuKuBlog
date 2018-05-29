package blog.web;



import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import net.sf.json.JSONArray;

import blog.domain.Blog;
import blog.service.BlogService;
import tag.domain.Tag;
import tag.service.TagService;
import tagsmap.domain.Tagsmap;
import tagsmap.service.TagsmapService;
import tools.commons.CommonUtils;
import tools.commons.ShortUuid;
import tools.servlet.BaseServlet;
import user.domain.User;

public class MBlogServlet extends BaseServlet {
	private BlogService blogService = new BlogService();
	private TagService tagService = new TagService();
	private TagsmapService tagsmapService=new TagsmapService();
	/**
	 * 编辑文章内容
	 * [根据文章id查询修改]
	 */
	public String updateBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{ 
		Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 1.封装当前页面的信息
		 * * 1.1 加载文章内容
		 * * 1.2加载文章标签
		 * */
		 Blog newblog=CommonUtils.toBean(req.getParameterMap(),Blog.class);
		 String blogTitle=newblog.getBlogTitle();
		 
		 /*
		  * 2.校验文章标题，没有文章标题则返回对应的页面
		  */       
		if(blogTitle == null || blogTitle.trim().isEmpty()){
			errors.put("blogTitle", "请为文章添加一个标题！ "); 
			req.setAttribute("blogcon", newblog);	//返回到页面，设置value="${form.XXX }"获取
			req.setAttribute("errors", errors);//返回到页面错误信息栏目，用${errors.XXX}获取信息
			return "f:/jsps/manage/blog/editblog.jsp";
		}

		/*
		* 3.封装blogId和tagId到tagsmap中
		* * 清空文章原本标签(tagsmap)
		* * 添加新标签(tagsmap)
		* * 更新文章内容
		* “aaa,qqq,”--分割字符串
		* or 获取页面文章的所有标签，将他们与修改前文章标签作比较，做删除、添加处理？
		* */
		//分割字符串
		String sentence = (String)req.getParameter("tagNames"); 
		String subSentences[] = sentence.split(",");
	        
		/*
		* 4.把表单数据转发给service,要加一个事务管理
		*/
		boolean isUpdateSuccess = blogService.updateBlog(subSentences, newblog);
		if(isUpdateSuccess) {
			req.setAttribute("msg", "修改成功");
		}else {
			req.setAttribute("msg", "修改失败");
		}
		/*
		* 5.保存成功信息，转发到写字板msg.jsp显示成功信息
		*/
		req.setAttribute("msg", "添加成功");
		return "f:/jsps/manage/manage.jsp";
		
	}
	/**
	 * 载入文章内容，by文章ID
	 */
	public String loadblog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String blogId = req.getParameter("blogId");
		Blog blog=blogService.showblogbyid(blogId);
		List<Tag> tags= tagService.findByBlogId(blogId);
		blog.setTags(tags);
		
		req.setAttribute("blog", blog);
		
		
		return "/jsps/manage/blog/editblog.jsp";
		
	}
	/**
	 * 删除文章
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String deleteblog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String blogId=req.getParameter("blogId");
		blogService.deleteblog(blogId);
		return null;		
	}
	/**
	 * 更改文章状态
	 */
	public String changStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		
		/*获取参数*/
		String blogId = req.getParameter("blogId");
		/*提交到service进行处理*/
		boolean result=blogService.changeStatus(blogId);
		resp.getWriter().print(result);
		
		return null;
	}
	/**
	 *保存草稿
	 * */
	public String saveDrafts(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		Map<String,String> errors = new HashMap<String,String>();
		User user = (User)req.getSession().getAttribute("sessionUser");
		/*
		 * 1.封装表单数据
		 */
		//获取文章内容
		Blog blog=CommonUtils.toBean(req.getParameterMap(),Blog.class);	
		//获取标签
		String sentence = (String)req.getParameter("tagNames"); 
		String subSentences[] = sentence.split(",");
		
		/*
		 * 2.校验文章标题，没有文章标题则返回对应的页面
		 */       
		String blogTitle = blog.getBlogTitle();
		if(blogTitle == null || blogTitle.trim().isEmpty()){
			errors.put("blogTitle", "请为文章添加一个标题！ "); 
			req.setAttribute("blogcon", blog);	//返回到页面，设置value="${form.XXX }"获取
			req.setAttribute("errors", errors);//返回到页面错误信息栏目，用${errors.XXX}获取信息
			return "r:/jsps/manage/blog/write.jsp";
		}
		/*
		 * 3.补全文章信息，为blog设置各项属性值
		 * */
		String blogid=ShortUuid.generateShortUuid();//随机产生一个8位数的blogid；		
		blog.setBlogId(blogid);		
		blog.setBlogTitle(blogTitle);//添加文章标题
		//添加作者
		String author = user.getUserName();
		blog.setAuthor(author);
		//添加创建时间
		Date nowTime = new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dd = myFmt.format(nowTime);
		blog.setBlogTime(dd);
		//设置发布状态
		blog.setStatus(false);
		
		/*
		 * 4.把表单数据转发给service
		 */
		boolean isAddSuccess = blogService.addBlog(subSentences,blog);

		/*
		 * 5.保存成功信息，转发到写字板msg.jsp显示成功信息
		 */
		if(isAddSuccess) {
			req.setAttribute("msg", "发布成功！");
		}else {
			req.setAttribute("msg", "发布失败！");
		}
		
		return "f:/jsps/manage/manage.jsp";
	}
	
	/**
	 *添加文章
	 * */
	public String addBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		Map<String,String> errors = new HashMap<String,String>();
		User user = (User)req.getSession().getAttribute("sessionUser");
		
		/*
		 * 1.封装表单数据
		 */
		//获取文章内容
		Blog blog=CommonUtils.toBean(req.getParameterMap(),Blog.class);	
		//获取标签
		String sentence = (String)req.getParameter("tagNames"); 
		String subSentences[] = sentence.split(",");
		
		/*
		 * 2.校验文章标题，没有文章标题则返回对应的页面
		 */       
		String blogTitle = blog.getBlogTitle();
		if(blogTitle == null || blogTitle.trim().isEmpty()){
			errors.put("blogTitle", "请为文章添加一个标题！ "); 
			req.setAttribute("blogcon", blog);	//返回到页面，设置value="${form.XXX }"获取
			req.setAttribute("errors", errors);//返回到页面错误信息栏目，用${errors.XXX}获取信息
			return "r:/jsps/manage/blog/write.jsp";
		}
		/*
		 * 3.补全文章信息，为blog设置各项属性值
		 * */
		String blogid=ShortUuid.generateShortUuid();//随机产生一个8位数的blogid；		
		blog.setBlogId(blogid);		
		blog.setBlogTitle(blogTitle);//添加文章标题
		//添加作者
		String author = user.getUserName();
		blog.setAuthor(author);
		//添加创建时间
		Date nowTime = new Date();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String dd = myFmt.format(nowTime);
		blog.setBlogTime(dd);
		//设置发布状态
		blog.setStatus(true);
		
		/*
		 * 4.把表单数据转发给service
		 */
		boolean isAddSuccess = blogService.addBlog(subSentences,blog);

		/*
		 * 5.保存成功信息，转发到写字板msg.jsp显示成功信息
		 */
		if(isAddSuccess) {
			req.setAttribute("msg", "发布成功！");
		}else {
			req.setAttribute("msg", "发布失败！");
		}
		
		return "f:/jsps/manage/manage.jsp";
	}
	
	
	/**
	 * 博客管理页面，显示所有文章
	 */
	public String findAllBlogs(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		/*
		 * 1.获取当前用户名
		 */
		String userName = (String) req.getSession().getAttribute("userName");
		/*
		 * 2.通过service得到所有已发布文章
		 */
		List<Blog> findAllBlog = blogService.findAllBlog(userName);
		List<Blog> findPubBlog = blogService.findPubBlog(userName);
		List<Blog> findDraftBlog = blogService.findDraftBlog(userName);
		/*                        
		 * 保存到request中，转发到文章列表页面list.jsp
		 */
		req.setAttribute("findAllBlog", findAllBlog);
		req.setAttribute("findPubBlog", findPubBlog);
		req.setAttribute("findDraftBlog", findDraftBlog);
		return "f:/jsps/manage/manage.jsp";
		
	}
	/**
	 * 查询所有文章
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		/*
		 * 1.获取当前用户名
		 */
		String userName = (String) req.getSession().getAttribute("userName");
		/*
		 * 2.通过service得到所有草稿
		 */
		List<Blog> findAllBlog = blogService.findAllBlog(userName);
		JSONArray json = JSONArray.fromObject(findAllBlog);
		String str = json.toString();
		out.write(str);
	
		return str;
		
	}
	/**
	 * 查询已发布文章
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findPubBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		/*
		 * 1.获取当前用户名
		 */
		String userName = (String) req.getSession().getAttribute("userName");
		/*
		 * 2.通过service得到所有草稿
		 */
		List<Blog> findPubBlog = blogService.findPubBlog(userName);
		JSONArray json = JSONArray.fromObject(findPubBlog);
		String str = json.toString();
		out.write(str);
	
		return str;
		
	}
	/**
	 * 查询草稿
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findDraftBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		/*
		 * 1.获取当前用户名
		 */
		String userName = (String) req.getSession().getAttribute("userName");
		/*
		 * 2.通过service得到所有草稿
		 */

		List<Blog> findDraftBlog = blogService.findDraftBlog(userName);
		JSONArray json = JSONArray.fromObject(findDraftBlog);
		String str = json.toString();
		out.write(str);
		
		return str;
		
	}
}
