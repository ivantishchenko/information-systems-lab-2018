package ch.ethz.brandin.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
	@JsonProperty("id")
	private long id;
	@JsonProperty("taken_at_timestamp")
	private long timestamp;
	@JsonProperty("edge_media_to_caption")
	private CaptionObject captionObject;
	@JsonProperty("edge_media_to_comment")
	private CommentEdge commentEdge;
	@JsonProperty("edge_media_preview_like")
	private LikesEdge likesEdge;
	@JsonProperty("is_video")
	private boolean isVideo;
	@JsonProperty("thumbnail_src")
	private String srcUrl;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public CaptionObject getCaptionObject() {
		return captionObject;
	}

	public void setCaptionObject(CaptionObject captionObject) {
		this.captionObject = captionObject;
	}

	public CommentEdge getCommentEdge() {
		return commentEdge;
	}

	public void setCommentEdge(CommentEdge commentEdge) {
		this.commentEdge = commentEdge;
	}
	
	public LikesEdge getLikesEdge() {
		return likesEdge;
	}

	public void setLikesEdge(LikesEdge likesEdge) {
		this.likesEdge = likesEdge;
	}

	public boolean isVideo() {
		return isVideo;
	}

	public void setVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	public String getCaption() {
		if (captionObject != null) {
			return captionObject.getCaption();
		} else {
			return "";
		}
	}
	
	public int getCommentsCount() {
		if (commentEdge != null) {
			return commentEdge.getCount();
		} else {
			return 0;
		}
	}
	
	public int getLikesCount() {
		if (likesEdge != null) {
			return likesEdge.getCount();
		} else {
			return 0;
		}
	}

	public static class PostObject {
		@JsonProperty("data")
		private DataObject dataObject;

		public DataObject getDataObject() {
			return dataObject;
		}

		public void setDataObject(DataObject dataObject) {
			this.dataObject = dataObject;
		}

		public List<Post> getPosts() {
			List<Post> posts = new ArrayList<Post>();
			if (dataObject != null && dataObject.getUserObject() != null
					&& dataObject.getUserObject().getEdgeMedia() != null
					&& dataObject.getUserObject().getEdgeMedia().getNodes() != null) {
				for (PostNode p : dataObject.getUserObject().getEdgeMedia().getNodes()) {
					if (p.getPost() != null) {
						posts.add(p.getPost());
					}
				}
			}

			return posts;
		}

		public PageInfo getPageInfo() {
			if (dataObject != null && dataObject.getUserObject() != null
					&& dataObject.getUserObject().getEdgeMedia() != null) {
				return dataObject.getUserObject().getEdgeMedia().getPageInfo();
			}
			return null;
		}
	}

	public static class DataObject {
		@JsonProperty("user")
		private UserObject userObject;

		public UserObject getUserObject() {
			return userObject;
		}

		public void setUserObject(UserObject userObject) {
			this.userObject = userObject;
		}
	}

	public static class UserObject {
		@JsonProperty("edge_owner_to_timeline_media")
		private EdgeMedia edgeMedia;

		public EdgeMedia getEdgeMedia() {
			return edgeMedia;
		}

		public void setEdgeMedia(EdgeMedia edgeMedia) {
			this.edgeMedia = edgeMedia;
		}
	}

	public static class EdgeMedia {
		@JsonProperty("count")
		private int count;
		@JsonProperty("page_info")
		PageInfo pageInfo;
		@JsonProperty("edges")
		List<PostNode> nodes;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public PageInfo getPageInfo() {
			return pageInfo;
		}

		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}

		public List<PostNode> getNodes() {
			return nodes;
		}

		public void setNodes(List<PostNode> nodes) {
			this.nodes = nodes;
		}
	}

	public static class PostNode {
		@JsonProperty("node")
		private Post post;

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}
	}

	public static class CaptionObject {
		@JsonProperty("edges")
		List<EdgeCaption> edges;

		public List<EdgeCaption> getEdges() {
			return edges;
		}

		public void setEdges(List<EdgeCaption> edges) {
			this.edges = edges;
		}

		public String getCaption() {
			if (edges != null && edges.size() > 0) {
				return edges.get(0).getCaption();
			} else {
				return "";
			}
		}
	}

	public static class EdgeCaption {
		@JsonProperty("node")
		private CaptionNode captionNode;

		public CaptionNode getCaptionNode() {
			return captionNode;
		}

		public void setCaptionNode(CaptionNode captionNode) {
			this.captionNode = captionNode;
		}

		public String getCaption() {
			if (captionNode != null) {
				return captionNode.getText();
			} else {
				return "";
			}
		}
	}

	public static class CaptionNode {
		@JsonProperty("text")
		private String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
	
	public static class CommentEdge {
		@JsonProperty("count")
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
	
	public static class LikesEdge {
		@JsonProperty("count")
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
}
