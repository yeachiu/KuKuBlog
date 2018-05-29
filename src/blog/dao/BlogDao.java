package blog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import blog.domain.Blog;

import tag.domain.Tag;
import tools.commons.CommonUtils;
import tools.jdbc.TxQueryRunner;
import user.domain.User;

public class BlogDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 编辑文章
	 * @throws SQLException 
	 * */
	public void updateBlog(Blog blog) throws SQLException{
		String sql="UPDATE t_blog SET blogTitle=?,blogContent=? where blogId=?";
		Object[] params={blog.getBlogTitle(),blog.getBlogContent(),blog.getBlogId()};
		qr.update(sql,params);
		
	}
	/**
	 * 在t_tags,t_blog中进行模糊查询
	 * @throws SQLException 
	 * */
	public  List<Blog> searchByKeywords(String search) throws SQLException{
		String sql1="select b.author,b.blogContent,b.blogId,b.blogTime,b.blogTitle from t_blog b LEFT OUTER JOIN t_tagsmap m on  m.blogId=b.blogId LEFT OUTER JOIN t_tags t on m.tagId=t.tagId where b.blogTitle like '%"+search+"%' or t.tagname like '%"+search+"%'";
		List<Blog> blogList = qr.query(sql1, new BeanListHandler<Blog>(Blog.class));
		System.out.println("sql结果："+blogList);
		return blogList;
	}
	/**
	 * 查找文章是否存在
	 */
	public boolean findBlogNumByBlogId(String blogId) throws SQLException{
		
		String sql="select count(*) from t_blog where blogId=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), blogId);
		
		return cnt.intValue()!=0;//数据库存在，则返回true;不存在，则返回false;
	}
	/**
	 * 删除标签表t_blog下指定文章
	 */
	public void delblogfromBlog(String blogId) throws SQLException {
		String sql = "delete from t_blog where blogId=?;";
		qr.update(sql, blogId);
	}
	/**
	 * 删除tagsmap下指定文章
	 */
	public void delblogfromMap(String blogId) throws SQLException {
		String sql = "delete from t_tagsmap where blogId=?;";
		qr.update(sql, blogId);
	}
	/**
	 * 查询文章发布状态
	 * @return 
	 */
	public Blog changeStatus(String blogId) throws SQLException{
		String sql = "select status,blogId from t_blog where blogId=? order by blogTime desc";
		return qr.query(sql, new BeanHandler<Blog>(Blog.class),blogId);
	}
	/**
	 * 更改为发布状态
	 */
	public void cStatustoPublished(String blogId) throws SQLException{
		String sql = "UPDATE t_blog SET status='1' WHERE blogId=?;";
		qr.update(sql,blogId);
	}
	/**
	 * 更改为草稿状态
	 */
	public void cStatustoBraft(String blogId) throws SQLException{
		String sql = "UPDATE t_blog SET status='0' WHERE blogId=?;";
		qr.update(sql,blogId);
	}
	 /** 添加文章
	 * 1.插入文章的相关内容
	 * 2.更新数据库
	 * */
	public void addBlog(Blog blog) throws SQLException{
		String sql="insert into t_blog(blogId,author,blogTitle,blogTime,"
				+ "blogContent,status) "+ "values(?,?,?,?,?,?)";
		Object[] params={blog.getBlogId(),blog.getAuthor(),blog.getBlogTitle(),blog.getBlogTime(),
				blog.getBlogContent(),blog.isStatus()};		
		qr.update(sql,params);
	}
	/**
	 * 查找已发布文章
	 * []
	 * @throws SQLException 
	 */
	public List<Blog> findPublishedBlog() throws SQLException{
		String sql = "select * from t_blog where status='1' order by blogTime desc";
		return qr.query(sql, new BeanListHandler<Blog>(Blog.class));
	}
	/**
	 * 查找[用户下]已发布文章
	 * []
	 * @throws SQLException 
	 */
	public List<Blog> findPubBlog(String userName) throws SQLException{
		String sql = "select * from t_blog where status='1' and author=? order by blogTime desc";
		return qr.query(sql, new BeanListHandler<Blog>(Blog.class),userName);
	}
	/**
	 * 查找[用户下]所有草稿
	 * []
	 * @throws SQLException 
	 */
	public List<Blog> findDraftBlog(String userName) throws SQLException{
		String sql = "select * from t_blog where status='0' and author=? order by blogTime desc";
		return qr.query(sql, new BeanListHandler<Blog>(Blog.class),userName);
	}
	/**
	 * 查找[用户下]所有文章
	 * []
	 * @throws SQLException 
	 */
	public List<Blog> findAllBlog(String userName) throws SQLException{
		String sql = "select * from t_blog where author=? order by blogTime desc";
		return qr.query(sql, new BeanListHandler<Blog>(Blog.class),userName);
	}
	/**
	 * 显示标签名对应的全部文章列表
	 * */
	
	public List<Blog> findByTagId(String tagId) throws SQLException{
		String sql="select distinct b.* from t_blog b,t_tagsmap m where b.blogId=m.blogId and m.tagId=? order by blogTime desc";
		List<Blog> blogsOfTag=qr.query(sql, new BeanListHandler<Blog>(Blog.class),tagId);
		return blogsOfTag;
	}
	
	/**
	 * 显示标签名对应的部分文章列表
	 * */
	
	public List<Blog> findByTagIdLimit(String tagId) throws SQLException{
		String sql="select distinct b.* from t_blog b,t_tagsmap m where b.blogId=m.blogId and m.tagId=?"+
" order by blogtime desc limit 5";
		List<Blog> tagf=qr.query(sql, new BeanListHandler<Blog>(Blog.class),tagId);
		return tagf;
	}
	/**
	 * 根据文章id找对应文章
	 */
	public Blog showblogbyid(String blogId) throws SQLException{
		String sql="select * from t_blog where blogId=?";
		Blog blog=qr.query(sql, new BeanHandler<Blog>(Blog.class),blogId);
		return blog;
	}
//	public Blog showblogbyid(String blogId) throws SQLException{
//		String sql="select * from t_blog where blogId=? order by blogTime desc";
//		Blog blog=qr.query(sql, new BeanHandler<Blog>(Blog.class),blogId);
//		return blog;
//	}
	
}
