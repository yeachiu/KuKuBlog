package tagsmap.service;

import java.sql.SQLException;

import tagsmap.dao.TagsmapDao;
import tagsmap.domain.Tagsmap;

public class TagsmapService {
	private TagsmapDao tagsmapDao = new TagsmapDao();
	/**
	 *更新文章id和 标签id
	 * */
	public void updateTagsmap(Tagsmap tagsmap){
		try {
			tagsmapDao.updateTagsmap(tagsmap);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加标签id和文章id
	 * */
	public void addTagsmap(Tagsmap tagsmap){
		try {
			tagsmapDao.addTagsmap(tagsmap);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
