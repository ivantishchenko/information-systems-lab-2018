package ch.ethz.brandin.response_models;

import java.util.List;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class CommentFollowerResponse implements IResponse {
	private Comparison data;
	private double ratio1;
	private double ratio2;

	public CommentFollowerResponse(Comparison data) {
		this.data = data;
	}

	@Override
	public void generateResponse() {
		List<Post> posts1 = data.getUser1().getPosts();
		List<Post> posts2 = data.getUser2().getPosts();

		int postSize1 = posts1.size();
		int postSize2 = posts2.size();

		double avgComments1 = 0;
		double avgComments2 = 0;

		for (Post p : posts1)
			avgComments1 += p.getCommentsCount();
		for (Post p : posts2)
			avgComments2 += p.getCommentsCount();

		avgComments1 /= postSize1;
		avgComments2 /= postSize2;

		ratio1 = avgComments1 / data.getUser1().getFollowedByCount();
		ratio2 = avgComments2 / data.getUser2().getFollowedByCount();
	}

	public double getRatio1() {
		return ratio1;
	}

	public void setRatio1(double ratio) {
		this.ratio1 = ratio;
	}

	public double getRatio2() {
		return ratio2;
	}

	public void setRatio2(double ratio2) {
		this.ratio2 = ratio2;
	}
}
