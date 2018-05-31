package ch.ethz.brandin.response_models;

import ch.ethz.brandin.data_service.EVENTS;
import ch.ethz.brandin.models.Comparison;

public class ResponseCollection {

	private Comparison comparison;

	private FollowerResponse followerResponse;
	private HashtagPercentageResponse hashtagPercentageResponse;
	private PostTimeResponse postTimeResponse;
	private UserInfoResponse userInfoResponse;
	private WeekPostTimeResponse weekPostTimeResponse;
	private CommentFollowerResponse commentFollowerResponse;
	private LikeFollowerResponse likeFollowerResponse;
	private HashtagToPostResponse hashtagToPostResponse;
	private VideoImageAvgResponse videoImageAvgResponse;
	private Top3PostsResponse top3PostsResponse;
	private TagsLikesDistributionResponse tagsLikesDistributionResponse;
	private TagsCommentsDistributionResponse tagsCommentsDistributionResponse;
	private RegularityResponse regularityResponse;

	public ResponseCollection(Comparison comparison) {
		this.comparison = comparison;
	}

	public FollowerResponse getFollowerResponse() {
		if (followerResponse == null) {
			comparison.waitForEvent(EVENTS.USER_EVENT);
			followerResponse = new FollowerResponse(this.comparison);
			followerResponse.generateResponse();
		}
		return followerResponse;
	}

	public void setFollowerResponse(FollowerResponse followerResponse) {
		this.followerResponse = followerResponse;
	}

	public HashtagPercentageResponse getHashtagPercentageResponse() {
		if (hashtagPercentageResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			hashtagPercentageResponse = new HashtagPercentageResponse(this.comparison);
			hashtagPercentageResponse.generateResponse();
		}
		return hashtagPercentageResponse;
	}

	public void setHashtagPercentageResponse(HashtagPercentageResponse hashtagPercentageResponse) {
		this.hashtagPercentageResponse = hashtagPercentageResponse;
	}

	public PostTimeResponse getPostTimeResponse() {
		if (postTimeResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			postTimeResponse = new PostTimeResponse(this.comparison);
			postTimeResponse.generateResponse();
		}
		return postTimeResponse;
	}

	public void setPostTimeResponse(PostTimeResponse postTimeResponse) {
		this.postTimeResponse = postTimeResponse;
	}

	public UserInfoResponse getUserInfoResponse() {
		if (userInfoResponse == null) {
			comparison.waitForEvent(EVENTS.USER_EVENT);
			userInfoResponse = new UserInfoResponse(comparison);
			userInfoResponse.generateResponse();
		}
		return userInfoResponse;
	}

	public void setUserInfoResponse(UserInfoResponse userInfoResponse) {
		this.userInfoResponse = userInfoResponse;
	}

	public WeekPostTimeResponse getWeekPostTimeResponse() {
		if (weekPostTimeResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			weekPostTimeResponse = new WeekPostTimeResponse(comparison);
			weekPostTimeResponse.generateResponse();
		}
		return weekPostTimeResponse;
	}

	public void setWeekPostTimeResponse(WeekPostTimeResponse weekPostTimeResponse) {
		this.weekPostTimeResponse = weekPostTimeResponse;
	}

	public CommentFollowerResponse getCommentFollowerResponse() {
		if (commentFollowerResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			commentFollowerResponse = new CommentFollowerResponse(comparison);
			commentFollowerResponse.generateResponse();
		}
		return commentFollowerResponse;
	}

	public void setCommentFollowerResponse(CommentFollowerResponse commentFollowerResponse) {
		this.commentFollowerResponse = commentFollowerResponse;
	}

	public LikeFollowerResponse getLikeFollowerResponse() {
		if (likeFollowerResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			likeFollowerResponse = new LikeFollowerResponse(comparison);
			likeFollowerResponse.generateResponse();
		}
		return likeFollowerResponse;
	}

	public void setLikeFollowerResponse(LikeFollowerResponse likeFollowerResponse) {
		this.likeFollowerResponse = likeFollowerResponse;
	}

	public HashtagToPostResponse getHashtagToPostResponse() {
		if (hashtagToPostResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			hashtagToPostResponse = new HashtagToPostResponse(comparison);
			hashtagToPostResponse.generateResponse();
		}
		return hashtagToPostResponse;
	}

	public void setHashtagToPostResponse(HashtagToPostResponse hashtagToPostResponse) {
		this.hashtagToPostResponse = hashtagToPostResponse;
	}

	public VideoImageAvgResponse getVideoImageAvgResponse() {
		if (videoImageAvgResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			videoImageAvgResponse = new VideoImageAvgResponse(comparison);
			videoImageAvgResponse.generateResponse();
		}
		return videoImageAvgResponse;
	}

	public void setVideoImageAvgResponse(VideoImageAvgResponse videoImageAvgResponse) {
		this.videoImageAvgResponse = videoImageAvgResponse;
	}

	public Top3PostsResponse getTop3PostsResponse() {
		if (top3PostsResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			top3PostsResponse = new Top3PostsResponse(comparison);
			top3PostsResponse.generateResponse();
		}
		return top3PostsResponse;
	}

	public void setTop3PostsResponse(Top3PostsResponse top3PostsResponse) {
		this.top3PostsResponse = top3PostsResponse;
	}

	public TagsLikesDistributionResponse getTagsLikesDistributionResponse() {
		if (tagsLikesDistributionResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			tagsLikesDistributionResponse = new TagsLikesDistributionResponse(comparison);
			tagsLikesDistributionResponse.generateResponse();
		}
		return tagsLikesDistributionResponse;
	}

	public void setTagsLikesDistributionResponse(TagsLikesDistributionResponse tagsLikesDistributionResponse) {
		this.tagsLikesDistributionResponse = tagsLikesDistributionResponse;
	}

	public TagsCommentsDistributionResponse getTagsCommentsDistributionResponse() {
		if (tagsCommentsDistributionResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			tagsCommentsDistributionResponse = new TagsCommentsDistributionResponse(comparison);
			tagsCommentsDistributionResponse.generateResponse();
		}
		return tagsCommentsDistributionResponse;
	}

	public void setTagsCommentsDistributionResponse(TagsCommentsDistributionResponse tagsCommentsDistributionResponse) {
		this.tagsCommentsDistributionResponse = tagsCommentsDistributionResponse;
	}

	public RegularityResponse getRegularityResponse() {
		if (regularityResponse == null) {
	        comparison.waitForEvent(EVENTS.POSTS_USER1_EVENT);
	        comparison.waitForEvent(EVENTS.POSTS_USER2_EVENT);
			regularityResponse = new RegularityResponse(comparison);
			regularityResponse.generateResponse();
		}
		return regularityResponse;
	}

	public void setRegularityResponse(RegularityResponse regularityResponse) {
		this.regularityResponse = regularityResponse;
	}
}
