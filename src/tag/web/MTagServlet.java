package tag.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import tag.domain.Tag;
import tag.service.TagService;
import tools.commons.ShortUuid;
import tools.servlet.BaseServlet;

/**
 * Servlet implementation class MTagServlet
 */
public class MTagServlet extends BaseServlet {
	
	private TagService tagService=new TagService();
	
	
	/**
	 * 删除标签
	 */
	public String deletetag(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//获取当前tagId
		String tagId=req.getParameter("tagId");
		tagService.deletetag(tagId);
			return null;
	}
//	/**
//	 * 删除映射
//	 * @param req
//	 * @param resp
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	public String deletemap(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		
//		//获取当前tagId
//		String tagId=req.getParameter("tagId");
//		String blogId=req.getParameter("blogId");
//		tagService.deletemap(tagId,blogId);
//		return null;	
//	}
	/**
	 * 添加标签
	 * 
	 * */
	public String addTagname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
			
			String tagname=req.getParameter("tagname");
			
			Tag tag = new Tag();
			String tagId=ShortUuid.generateShortUuid();
			tag.setTagId(tagId);
			tag.setTagname(tagname);
			tagService.addTag(tag);
			JSONArray json = JSONArray.fromObject(tag);
			String str = json.toString();
			
			out.write(str);
			System.out.println("addTagname："+tag.getTagname());
			return null;
	}
	/*
	 * 显示标签的文章数
	 * 1.获取当前标签的 tagid
	 * 2.通过service得到文章数
	 * 3.保存到request中，转发到manage.jsp
	 * */

	public String findBlogNumByTagId(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获取当前tagId
		String tagid=req.getParameter("tagId");
		//得到标签数,保存到request中
		int blognum=tagService.findBlogNumByTagId(tagid);
		req.setAttribute("blognum", blognum);
		//返回manage.jsp页面的相关模块
		return "f:/jsps/manage/tag/mantag.jsp";		
	}
}
