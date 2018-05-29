package tagsmap.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import tagsmap.domain.Tagsmap;
import tools.jdbc.TxQueryRunner;

public class TagsmapDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 更新文章标签和文章id
	 * @throws SQLException 
	 * */
	public void updateTagsmap(Tagsmap tagsmap) throws SQLException {
		String sql="update t_tagsmap set tagId=?,blogId=?";
		Object[] params={tagsmap.getTagId(),tagsmap.getBlogId()};
		qr.update(sql,params);
		
	}
	/* 添加标签和文章id
	 * 1.插入文章的相关内容
	 * 2.更新数据库
	 * */
	public void addTagsmap(Tagsmap tagsmap) throws SQLException{
		String sql="insert into t_tagsmap(tagId,blogId) values(?,?)";
		Object[] params={tagsmap.getTagId(),tagsmap.getBlogId()};		
		qr.update(sql,params);
	}
}
