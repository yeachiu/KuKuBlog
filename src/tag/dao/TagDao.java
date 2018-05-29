package tag.dao;

import java.sql.Connection;
/*
 * @author
 * 业务持久层，依赖于service层
 * */
import java.sql.SQLException;
import java.util.List;




import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;


import com.mchange.v2.c3p0.impl.NewPooledConnection;

import tag.domain.Tag;
import tools.jdbc.MyDataSourceUtils;
import tools.jdbc.TxQueryRunner;

public class TagDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 添加文章-标签映射tagsmap
	 */
	public void addtagsmap(String blogId,String tagId) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql="insert into t_tagsmap(blogId,tagId) values(?,?) ";
		runner.update(conn,sql, blogId,tagId);
	}
	
	/**
	 * 删除标签表t_tags下指定标签
	 */
	public void deltagfromTag(String tagId) throws SQLException {
		String sql = "delete from t_tags where tagId=?;";
		qr.update(sql, tagId);
	}
	/**
	 * 删除tagsmap下指定标签
	 */
	public void deltagfromMap(String blogId) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql = "delete from t_tagsmap where blogId=?;";
		runner.update(conn,sql,blogId);
	}
	/**
	 * 查询标签名对应ID
	 */
	public String findByTagname(String tagname) throws SQLException {
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql = "select tagId from t_tags where tagname=?";
		return (String)runner.query(conn,sql, new ScalarHandler(),tagname);
	}
	/**
	 * 校验标签名是否存在
	 * */
	public boolean ajaxValidateTagName(String tagname) throws SQLException {
		String sql = "select count(1) from t_tags where tagname=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(),tagname);
		
//		System.out.println(number.intValue()!=0);
		return number.intValue()!=0;//数据库存在，则返回true;不存在，则返回false;
	}
	
	/**
	 * 添加文章标签
	 * @throws SQLException 
	 * 
	 * */
	public void addTag(Tag tag) throws SQLException{
		QueryRunner runner = new QueryRunner();
		Connection conn = MyDataSourceUtils.getCurrentConnection();
		String sql="insert into t_tags(tagId,tagname) values(?,?)";
		Object[] params={tag.getTagId(),tag.getTagname()};
		runner.update(conn,sql,params);
	}
	/**
	 * 查询是否存在映射
	 * @param tagId
	 * @param blogId
	 * @return
	 * @throws SQLException
	 */
	public int findmap(String tagId,String blogId) throws SQLException{
		String sql="select count(blogId) from t_tagsmap where tagId=? and blogId=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), tagId,blogId);
		return cnt == null ? 0 : cnt.intValue();
	}
	/**
	 * 返回所有的标签
	 * */	
	/**
	 * 1.查询所有的标签
	 * */
	public List<Tag> findAllTag() throws SQLException{
		String sql="select * from t_tags";
		List<Tag> tag=qr.query(sql,new BeanListHandler<Tag>(Tag.class));
		return tag;
	}
	/**
	 * 查找标签下文章数
	 * @param tagname
	 * @return
	 * @throws SQLException
	 */
	public int findNumber(String tagId) throws SQLException{
		String sql = "select count(*) from t_tagsmap where tagId=?;";
		Number num = (Number)qr.query(sql, new ScalarHandler(),tagId);
		return num.intValue();
	}
	
	/**
	 * 2.查询指定文章的所有标签
	 * */
	public List<Tag> findByBlogId(String blogId) throws SQLException{
		String sql="select distinct t.* from t_tags t,t_tagsmap m where t.tagId=m.tagId and M.blogId=?";
		List<Tag> tags=qr.query(sql,  new BeanListHandler<Tag>(Tag.class),blogId);
		return tags;
	}
	
	
	/**
	 * 3.查询指定标签的所有文章数
	 * */
	public int findBlogNumByTagId(String tagId) throws SQLException {
		String sql="select count(blogId) from t_tagsmap where tagId=?";
		Number cnt = (Number)qr.query(sql, new ScalarHandler(), tagId);
		return cnt == null ? 0 : cnt.intValue();
	}
	
	
	/**
	 * 查询标签名
	 * findTagnameByTagId
	 */
	public Tag findTagnameByTagId(String tagId) throws SQLException{
		String sql="select * from t_tags where tagId=?";
		/*
		 * BeanHandler --> 它是ResultSetHandle的实现类，它的作用是把结果集封装到实体类对象中
		 * 结果是单行单列
		 */
		return qr.query(sql, new BeanHandler<Tag>(Tag.class),tagId);
	}

	
	
}
