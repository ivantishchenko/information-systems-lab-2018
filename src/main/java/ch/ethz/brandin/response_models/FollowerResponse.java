package ch.ethz.brandin.response_models;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.User;

public class FollowerResponse implements IResponse{
	private int followCount1;
	private int followCount2;
	private int followedByCount1;
	private int followedByCount2;
	private float ratio1;
	private float ratio2;
	private Comparison data;

	public FollowerResponse(Comparison data) {
		this.data = data;
	}

	@Override
	public void generateResponse() {
		User u1 = data.getUser1();
		User u2 = data.getUser2();
		
		setFollowCount1(u1.getFollowCount());
		setFollowCount2(u2.getFollowCount());
		setFollowedByCount1(u1.getFollowedByCount());
		setFollowedByCount2(u2.getFollowedByCount());
		setRatio1((float)u1.getFollowedByCount() / u1.getFollowCount());
		setRatio2((float)u2.getFollowedByCount() / u2.getFollowCount());
	}

	public int getFollowCount1() {
		return followCount1;
	}

	public void setFollowCount1(int followCount1) {
		this.followCount1 = followCount1;
	}

	public int getFollowCount2() {
		return followCount2;
	}

	public void setFollowCount2(int followCount2) {
		this.followCount2 = followCount2;
	}

	public int getFollowedByCount1() {
		return followedByCount1;
	}

	public void setFollowedByCount1(int followedByCount1) {
		this.followedByCount1 = followedByCount1;
	}

	public int getFollowedByCount2() {
		return followedByCount2;
	}

	public void setFollowedByCount2(int followedByCount2) {
		this.followedByCount2 = followedByCount2;
	}

	public float getRatio1() {
		return ratio1;
	}
	public void setRatio1(float ratio1) {
		this.ratio1 = ratio1;
	}
	public float getRatio2() {
		return ratio2;
	}
	public void setRatio2(float ratio2) {
		this.ratio2 = ratio2;
	}
}
