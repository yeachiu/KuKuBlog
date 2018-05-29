package tag.domain;

import java.util.ArrayList;
import java.util.List;

import blog.domain.Blog;

/*
 * 实体类
 * */
public class Tag {
	private String tagId;//唯一的标签id
	private String tagname;//标签名字
	private int blognum;//标签下文章数
	private List<Blog> blogs;	//标签下文章
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public int getBlognum() {
		return blognum;
	}
	public void setBlognum(int blognum) {
		this.blognum = blognum;
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	
	
	
	
}
