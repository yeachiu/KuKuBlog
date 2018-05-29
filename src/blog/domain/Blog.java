package blog.domain;

import java.util.ArrayList;
import java.util.List;

import tag.domain.Tag;

/**
 * 文章模块实体类
 * @author DF
 *
 */
public class Blog {
	private String blogId;	//文章ID
	private String author;	//作者
	private String blogContent;	//文章内容
	private String blogTitle;	//文章标题
	private String blogTime;	//创建时间
	private boolean status;	//是否发布
	private String commentsNum;	//评论数
	private List<Tag> tags; 	//文章标签
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogTime() {
		return blogTime;
	}
	public void setBlogTime(String blogTime) {
		this.blogTime = blogTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCommentsNum() {
		return commentsNum;
	}
	public void setCommentsNum(String commentsNum) {
		this.commentsNum = commentsNum;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", author=" + author + ", blogContent=" + blogContent + ", blogTitle="
				+ blogTitle + ", blogTime=" + blogTime + ", status=" + status + ", commentsNum=" + commentsNum
				+ ", tags=" + tags + "]";
	}
	
	
}
