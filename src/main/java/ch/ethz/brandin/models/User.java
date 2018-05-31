package ch.ethz.brandin.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class User {
	
	@JsonProperty("id")
	private long id;
	@JsonProperty("full_name")
    private String fullName;
	@JsonProperty("profile_pic_url")
    private String profilePicUrl;
	@JsonProperty("edge_followed_by")
	private EdgeCount followedByEdge;
	@JsonProperty("edge_follow")
	private EdgeCount followEdge;
	@JsonProperty("is_private")
	private boolean isPrivate;
    
    private List<Post> posts;
    private List<Post> unfilteredPosts;
    
    public User() {
    	unfilteredPosts = new ArrayList<Post>();
    }
    
    public void setId(long id) {
    	this.id = id;
    }
    
    public long getId() {
		return id;
	}
    
    public void setFullName(String fullName) {
    	this.fullName = fullName;
    }

	public String getFullName() {
		return fullName;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public EdgeCount getFollowedByEdge() {
		return followedByEdge;
	}

	public void setFollowedByEdge(EdgeCount followedByEdge) {
		this.followedByEdge = followedByEdge;
	}

	public EdgeCount getFollowEdge() {
		return followEdge;
	}

	public void setFollowEdge(EdgeCount followEdge) {
		this.followEdge = followEdge;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public int getFollowedByCount() {
		return followedByEdge.getCount();
	}
	
	public int getFollowCount() {
		return followEdge.getCount();
	}
	
	public List<Post> getPosts() {
		return posts;
	}
	
	public List<Post> getUnfilteredPosts() {
		return unfilteredPosts;
	}
	
	public void filterPosts(String filter, int numPosts) {
		if (filter == null || filter.isEmpty()) {
			return;
		}
		
		posts = new ArrayList<Post>(unfilteredPosts);
		
        // sort descending
        if (filter.equals("popular")) {
        	posts.sort((a, b) -> b.getLikesCount() - a.getLikesCount());
        }

        if (numPosts != -1) {
        	posts = posts.subList(0, Math.min(numPosts, posts.size()));
        }
	}

	public static class UserObject {
		private static ObjectMapper objectMapper;
		
		static {
			objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}

		@JsonProperty("entry_data")
		private EntryDataObject entryDataObject;
		
		public static UserObject createUserObject(String html) throws IOException {
			String[] s = html.split("window._sharedData = ");
			String[] ss = s[1].split(";</script>");
			String data = ss[0];
			
			UserObject userObject = objectMapper.readValue(data, User.UserObject.class);
			return userObject;
		}
		
		public User getUser() {
			return entryDataObject.getProfilePageObject().get(0).getGraphQlObject().getUser();
		}
		

		public EntryDataObject getEntryDataObject() {
			return entryDataObject;
		}

		public void setEntryDataObject(EntryDataObject entryDataObject) {
			this.entryDataObject = entryDataObject;
		}
	}
	
	public static class EntryDataObject {
		@JsonProperty("ProfilePage")
		private List<ProfilePageObject> profilePageObject;

		public List<ProfilePageObject> getProfilePageObject() {
			return profilePageObject;
		}

		public void setProfilePageObject(List<ProfilePageObject> profilePageObject) {
			this.profilePageObject = profilePageObject;
		}
	}
	
	public static class ProfilePageObject {
		@JsonProperty("graphql")
		private GraphQlObject graphQlObject;

		public GraphQlObject getGraphQlObject() {
			return graphQlObject;
		}

		public void setGraphQlObject(GraphQlObject graphQlObject) {
			this.graphQlObject = graphQlObject;
		}
	}
	
	public static class GraphQlObject {
		@JsonProperty("user")
		private User user;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}
	
	public static class EdgeCount {
		
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
