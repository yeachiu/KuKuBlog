package blog.web;



import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import blog.domain.Blog;
import blog.service.BlogService;

import tag.domain.Tag;
import tag.service.TagService;
import tools.commons.CommonUtils;
import tools.commons.ShortUuid;
import tools.servlet.BaseServlet;

public class BlogServlet extends BaseServlet {
	private BlogService blogService = new BlogService();
	private TagService tagService = new TagService();
	/**
	 * 
	 * 搜索框 模糊查询文章
	 * */
	public String searchByKeywords(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	
		/*
		 * 1.获取文本框输入的值
		 * */
		String search=req.getParameter("search");
		/*
		 * 2.调用service方法完成业务
		 * */
		//找出符合搜索关键字的文章id，得到一个文章对象结果集，此时“文章下标签”属性为空
		List<Blog> blogList=blogService.searchByKeywords(search);
		List<List<Tag>> tagsList = new ArrayList<List<Tag>>();
		for(Blog b:blogList){
			String blogId = (String) b.getBlogId();
			List<Tag> allTagsofBlog = tagService.findByBlogId(blogId);
			b.setTags(allTagsofBlog);
		}
		/*
		 * 3.保存到request中，返回页面
		 * */
		req.setAttribute("blogList", blogList);

		return "f:/jsps/search.jsp";
	}
	/**
	 * 查找所有已发布文章
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findPublishedBlog(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		/*
		 * 2.通过service得到所有已发布文章
		 */
		List<Blog> publishedBlogs = blogService.findPublishedBlog();
		JSONArray json = JSONArray.fromObject(publishedBlogs);
		String str = json.toString();
		out.write(str);
		/*
		 * 保存到request中，转发到文章列表页面list.jsp
		 */
//		req.setAttribute("publishedBlogs", publishedBlogs);
//		System.out.println("publishedBlogs:"+publishedBlogs);	
		System.out.println("publishedBlogs-JSON:"+str);		
		return null;
		
	}

	
	/**
	 * 浏览页面，显示所有已发布文章
	 */
	public String findbyBlogId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		/*
		 * 1.获取当前用户名
		 */
		String userName = (String) req.getSession().getAttribute("userName");
		/*
		 * 2.通过service得到所有已发布文章
		 */
		List<Blog> result = blogService.findAllBlog(userName);
		
		JSONArray findAllBlog = JSONArray.fromObject(result);
		/*
		 * 保存到request中，转发到文章列表页面list.jsp
		 */
		req.setAttribute("findAllBlog", findAllBlog);
		System.out.println("findAllBlog:"+findAllBlog);
		return "f:/index.jsp";
		
	}

	/**
	 * 获得某标签(tagid)下的文章全部信息
	 */
	public String findByTagId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取表单元素
		 * 2.添加文章内容，为文章添加标签(tagsmap:tagId,blogId);
		 * 		* 因为
		 */	
		/*
		 * 1.获取当前tagid
		 * */
		String tagId = req.getParameter("tagId");
		/*
		 * 2.调用service方法获得对应的文章列表
		 * */
		List<Blog> blogsOfTag = blogService.findByTagId(tagId);			//标签下面所有文章
		Tag thisTag = tagService.findTagnameByTagId(tagId);	//标签名、标签ID
		
		for(Blog b:blogsOfTag){
			String blogId = b.getBlogId();							//文章ID
			List<Tag> allTagsofBlog = tagService.findByBlogId(blogId);
			b.setTags(allTagsofBlog);
		}
		
				//根据文章ID找它的标签
		req.setAttribute("blogsOfTag", blogsOfTag);
		req.setAttribute("tag", thisTag);
		
			
		return "f:/jsps/tag/tag.jsp";
		
	}
	/**
	 * 获得某标签(tagid)下最新的五篇文章
	 */
	public String findByTagIdLimit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		/*
		 * 1.获取当前tagid
		 * */
		String tagId = req.getParameter("tagId");
		/*
		 * 2.调用service方法获得对应的文章列表
		 * */
		List<Blog> blogs=blogService.findByTagIdLimit(tagId);
		for(Blog b:blogs){
			String blogId = b.getBlogId();							//文章ID
			List<Tag> allTagsofBlog = tagService.findByBlogId(blogId);
			b.setTags(allTagsofBlog);
		}
		JSONArray json = JSONArray.fromObject(blogs);
		String str = json.toString();
		out.write(str);
	
		return null;
		
	}
	/**
	 * 获得文章内容，by文章ID
	 */
	public String showblogbyid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		String blogId = req.getParameter("blogId");
		
		Blog blog=blogService.showblogbyid(blogId);
		List<Tag> tags= tagService.findByBlogId(blogId);
		blog.setTags(tags);
		System.out.println("blog-Context:" +blog);
		req.setAttribute("blog", blog);
		System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");
		
		return "f:/jsps/blog/article.jsp";
		
	}
	
}
