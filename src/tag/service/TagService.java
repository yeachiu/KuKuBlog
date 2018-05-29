package tag.service;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import tag.dao.TagDao;
import tag.domain.Tag;
import tools.commons.ShortUuid;

/*
 * 标签分类模块业务层
 *  依赖于Dao层
 */
public class TagService {
	private TagDao tagDao=new TagDao();
	/**
	 * 添加文章-标签映射
	 */
	public void addTagsmap(String blogId,String tagId) {
		try {
			tagDao.addtagsmap(blogId, tagId);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 删除标签
	 */
	public void deletetag(String tagId){
		try {
			tagDao.deltagfromTag(tagId);	
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 标签名校验
	 * @param tagname
	 * @return
	 */
	public Tag ajaxValidateTagName(String tagname) {		
		try {
			/*
			 * 访问Dao,查询标签名是否存在；
			 * 若存在，查询ID(findByTagname)
			 * 若不存在，创建新ID(uuid)
			 * 返回servlet一个tagId 
			 */
			String tagId=null;
			Tag tag = new Tag();
			boolean bool = tagDao.ajaxValidateTagName(tagname);
			
			if(bool){
				//存在
				tagId= tagDao.findByTagname(tagname);
				tag.setTagId(tagId);
				tag.setTagname(tagname);
				
			}
			if(!bool){
				tagId=ShortUuid.generateShortUuid();
				tag.setTagId(tagId);
				tag.setTagname(tagname);
				tagDao.addTag(tag);
				
			}
			
			
		return tag;	
			
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加文章标签
	 * */
	public void addTag(Tag tag){
		try {
			tagDao.addTag(tag);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 1.查找所有标签
	 * */
	public List<Tag> findAllTag(){
		try {
			return tagDao.findAllTag();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找标签下文章数
	 */
	public int findNumber(String tagId){
		try {
			return tagDao.findBlogNumByTagId(tagId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 2.查找文章的所有标签
	 * */
	public List<Tag> findByBlogId(String blogId){
		try {
			return tagDao.findByBlogId(blogId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	 * 3.显示标签的文章数
	 * */
	public int findBlogNumByTagId(String tagId){
		try {
			return tagDao.findBlogNumByTagId(tagId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 查询标签名
	 * findTagnameByTagId
	 */
	public Tag findTagnameByTagId(String tagId){
		try {
			return tagDao.findTagnameByTagId(tagId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询‘标签名’是否存在
	 */
	public boolean booleanTagName(String tagname) {
		try {
			return tagDao.ajaxValidateTagName(tagname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
