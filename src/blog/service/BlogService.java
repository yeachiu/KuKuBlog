package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import blog.dao.BlogDao;
import blog.domain.Blog;
import tag.dao.TagDao;
import tag.domain.Tag;
import tag.service.TagService;
import tools.commons.ShortUuid;
import tools.jdbc.JdbcUtils;
import tools.jdbc.MyDataSourceUtils;



public class BlogService {
	private BlogDao blogDao = new BlogDao();
	private TagDao tagDao = new TagDao();
	private TagService tagService = new TagService();
	/**
	 * 更新文章
	 * */
	public boolean updateBlog(String[] subSentences,Blog blog){
//		Connection conn = null;
		boolean isUpdateSuccess = true; 
		try {
			//三个更新：删除替换文章原标签、更新文章原内容
			//开启事务
			MyDataSourceUtils.startTransaction();
			
			//更新文章标签
			tagDao.deltagfromMap(blog.getBlogId());
			for(String sub:subSentences) {
				if(!sub.trim().isEmpty()) {
					String tagId=null;
					boolean bool = tagDao.ajaxValidateTagName(sub);
					if(!bool){
						//不存在
						Tag tag = new Tag();
						tagId=ShortUuid.generateShortUuid();
						tag.setTagId(tagId);
						tag.setTagname(sub);
						tagDao.addTag(tag);
					}else {
						tagId=tagDao.findByTagname(sub);
					}
					tagDao.addtagsmap(blog.getBlogId(), tagId);
				}
			}
			
			//更新文章内容
			blogDao.updateBlog(blog);
			
		} catch (Exception e) {
			isUpdateSuccess = false;
			//回滚事务
			try {
				MyDataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				MyDataSourceUtils.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isUpdateSuccess;
	}
	/**
	 * 在t_tags,t_blog中进行模糊查询
	 * */
	public List<Blog> searchByKeywords(String search){
		try {
			return blogDao.searchByKeywords(search);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除文章
	 */
	public void deleteblog(String blogId){
		try {
			boolean bool = blogDao.findBlogNumByBlogId(blogId);
			if(bool){
				blogDao.delblogfromBlog(blogId);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 更改文章状态
	 * @return 
	 * @return 
	 */
	public boolean changeStatus(String blogId){
		try {
			Blog blog = blogDao.changeStatus(blogId);
			boolean status = blog.isStatus();
			System.out.println("0::"+status);
			if(status){
				blogDao.cStatustoBraft(blogId);
				return false;
			}else{
				blogDao.cStatustoPublished(blogId);
				return true;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	/**
	 * 添加文章
	 * */
	public boolean addBlog(String[] subSentences,Blog blog){
		boolean isAddSuccess = true; 
		try {
			//三个更新：删除替换文章原标签、更新文章原内容
			//开启事务
			MyDataSourceUtils.startTransaction();
			
			//添加文章标签
			for(String sub:subSentences) {
				if(!sub.trim().isEmpty()) {
					String tagId=null;
					boolean bool = tagDao.ajaxValidateTagName(sub);
					if(!bool){
						//不存在
						Tag tag = new Tag();
						tagId=ShortUuid.generateShortUuid();
						tag.setTagId(tagId);
						tag.setTagname(sub);
						tagDao.addTag(tag);
					}else {
						tagId=tagDao.findByTagname(sub);
					}
					tagDao.addtagsmap(blog.getBlogId(), tagId);
				}
			}
			
			//更新文章内容
			blogDao.addBlog(blog);
			
		} catch (Exception e) {
			isAddSuccess = false;
			//回滚事务
			try {
				MyDataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			try {
				MyDataSourceUtils.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return isAddSuccess;
		
	}
	/**
	 * 查找所有已发布文章
	 * @return
	 */
	public List<Blog> findPublishedBlog(){
		try{
			return blogDao.findPublishedBlog();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找[用户下]所有已发布文章
	 * @return
	 */
	public List<Blog> findPubBlog(String userName){
		try{
			return blogDao.findPubBlog(userName);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找[用户下]所有草稿
	 * @return
	 */
	public List<Blog> findDraftBlog(String userName){
		try{
			return blogDao.findDraftBlog(userName);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 *  查找[用户下]所有文章
	 * @param userName
	 * @return
	 */
	public List<Blog> findAllBlog(String userName){
		try{
			return blogDao.findAllBlog(userName);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找标签对应的所有的文章
	 * */
	public List<Blog> findByTagId(String tagId){
		try {
			return blogDao.findByTagId(tagId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据文章id找对应文章
	 * @return
	 */
	public Blog showblogbyid(String blogId){
		try{
			return blogDao.showblogbyid(blogId);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 显示标签对应的部分文章列表
	 * @return
	 */
	public List<Blog> findByTagIdLimit(String tagId){
		try{
			return blogDao.findByTagIdLimit(tagId);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
