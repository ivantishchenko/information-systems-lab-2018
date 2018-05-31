 package ch.ethz.brandin.response_models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class RegularityResponse implements IResponse {

	private static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
	private static final long MILLISECONDS_PER_WEEK = 1000 * 60 * 60 * 24 * 7;
	private Comparison data;

	private int[][] postsPerWeek1;
	private int[][] postsPerWeek2;
	private int[][] postsPerDay1;
	private int[][] postsPerDay2;

	public RegularityResponse(Comparison data) {
		this.data = data;
	}

	@Override
	public void generateResponse() {
		List<Post> posts1 = data.getUser1().getPosts();
		List<Post> posts2 = data.getUser2().getPosts();

		List<Date> dates1 = new ArrayList<>();
		List<Date> dates2 = new ArrayList<>();

		posts1.forEach(x -> dates1.add(new Date(x.getTimestamp() * 1000)));
		posts2.forEach(x -> dates2.add(new Date(x.getTimestamp() * 1000)));

		postsPerWeek1 = createDistribution(dates1, MILLISECONDS_PER_WEEK);
		postsPerWeek2 = createDistribution(dates2, MILLISECONDS_PER_WEEK);

		postsPerDay1 = createDistribution(dates1, MILLISECONDS_PER_DAY);
		postsPerDay2 = createDistribution(dates2, MILLISECONDS_PER_DAY);
	}

	private int[][] createDistribution(List<Date> dates, long granularity) {
		List<Long> times = new ArrayList<Long>();
		dates.forEach(x -> times.add(x.getTime() / granularity));

		Map<Long, Integer> postToBucketMap = new HashMap<Long, Integer>();

		for (Long l : times) {
			if (!postToBucketMap.containsKey(l)) {
				postToBucketMap.put(l, 1);
			} else {
				postToBucketMap.put(l, postToBucketMap.get(l) + 1);
			}
		}

		Map<Integer, Integer> bucketToSizeMap = new HashMap<Integer, Integer>();

		for (Integer count : postToBucketMap.values()) {
			if (!bucketToSizeMap.containsKey(count)) {
				bucketToSizeMap.put(count, 1);
			} else {
				bucketToSizeMap.put(count, bucketToSizeMap.get(count) + 1);
			}
		}

		int[][] res = new int[bucketToSizeMap.size()][];

		int i = 0;
		for (Entry<Integer, Integer> e : bucketToSizeMap.entrySet()) {
			res[i] = new int[] { e.getKey().intValue(), e.getValue() };
			i += 1;
		}

		return res;
	}

	public int[][] getPostsPerWeek1() {
		return postsPerWeek1;
	}

	public void setPostsPerWeek1(int[][] postsPerWeek1) {
		this.postsPerWeek1 = postsPerWeek1;
	}

	public int[][] getPostsPerWeek2() {
		return postsPerWeek2;
	}

	public void setPostsPerWeek2(int[][] postsPerWeek2) {
		this.postsPerWeek2 = postsPerWeek2;
	}

	public int[][] getPostsPerDay1() {
		return postsPerDay1;
	}

	public void setPostsPerDay1(int[][] postsPerDay1) {
		this.postsPerDay1 = postsPerDay1;
	}

	public int[][] getPostsPerDay2() {
		return postsPerDay2;
	}

	public void setPostsPerDay2(int[][] postsPerDay2) {
		this.postsPerDay2 = postsPerDay2;
	}
}
