package tagsmap.web;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tagsmap.domain.Tagsmap;
import tagsmap.service.TagsmapService;
import tools.servlet.BaseServlet;

public class TagsmapServlet extends BaseServlet {
	private TagsmapService tagsmapService = new TagsmapService();
	
	/**
	 * 把文章和标签结合在一起
	 * */
		public String addtagIdandblogId(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			/*
			 * 1.封装tagId和blogId到tagsmap
			 * */
			Tagsmap tagsmap=new Tagsmap();
			tagsmap.setTagId(req.getParameter("tagId"));
			tagsmap.setBlogId(req.getParameter("blogId"));
			/*
			 *2. 提交到service中完成业务
			 * */
			tagsmapService.addTagsmap(tagsmap);
			/*
			 * 3.
			 * */
	        return null;
		}
}
