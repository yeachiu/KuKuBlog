package tag.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import tag.domain.Tag;
import tag.service.TagService;
import tools.commons.ShortUuid;
import tools.servlet.BaseServlet;

public class TagServlet extends BaseServlet {
	private TagService tagService=new TagService();
	
	
	/**
	 * 检验标签名是否存在
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String booleanTagName(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
	
			/*获取参数*/
			String tagname=req.getParameter("tagname");
			/*提交到service进行处理*/
			Boolean bool = tagService.booleanTagName(tagname);
			resp.getWriter().print(bool);
			System.out.println("booleanTagName:"+bool);
			return null;
	}
	/**点击页面，显示所有的标签
	 * 1.通过service得到所有标签
	 * 2.保存到request中，转发到tag.jsp页面
	 * */
	public String findAllTag(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			PrintWriter out = resp.getWriter();
			
			resp.setContentType("text/text");
			resp.setCharacterEncoding("UTF-8");
			List<Tag> tags=tagService.findAllTag();	
			for(Tag t:tags){
				String tagId=t.getTagId();
				int blognum = tagService.findBlogNumByTagId(tagId);
				t.setBlognum(blognum);
			}
			
			JSONArray json = JSONArray.fromObject(tags);
			String str = json.toString();
			
			out.write(str);
			
			return null;
	}
	
	
	/*
	 * 显示文章对应的所有标签
	 * 1.获取当前blogid;
	 * 2.通过service得到所有标签
	 * 3.保存到request中，转发到listcolumn.jsp页面里面的标签模块
	 * */
	public String findByBlogId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		String blogid = req.getParameter("blogId");
		
		List<Tag> allTagsofBlog=tagService.findByBlogId(blogid);
		JSONArray json = JSONArray.fromObject(allTagsofBlog);
		String str = json.toString();
		out.write(str);
		
		return null;
	}
	
	
}
