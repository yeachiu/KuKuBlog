package tagsmap.domain;
/**
 * 文章标签映射表实体类
 * 
 * */
public class Tagsmap {
		private int mapId;
		private String tagId;
		private String blogId;
		public int getMapId() {
			return mapId;
		}
		public void setMapId(int mapId) {
			this.mapId = mapId;
		}
		public String getTagId() {
			return tagId;
		}
		public void setTagId(String tagId) {
			this.tagId = tagId;
		}
		public String getBlogId() {
			return blogId;
		}
		public void setBlogId(String blogId) {
			this.blogId = blogId;
		}
		@Override
		public String toString() {
			return "Tagsmap [mapId=" + mapId + ", tagId=" + tagId + ", blogId="
					+ blogId + "]";
		}
}
