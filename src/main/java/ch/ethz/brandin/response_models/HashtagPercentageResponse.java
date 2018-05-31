package ch.ethz.brandin.response_models;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class HashtagPercentageResponse implements IResponse {

	private final int TARGET_LEN = 5;
	private Comparison data;
	private LinkedHashMap<String, Float> hashtags1;
	private LinkedHashMap<String, Float> hashtags2;
	private Pattern MY_PATTERN;

	public HashtagPercentageResponse(Comparison data) {
		MY_PATTERN = Pattern.compile("#(\\S+)");
		this.data = data;
		hashtags1 = new LinkedHashMap<>();
		hashtags2 = new LinkedHashMap<>();
	}

	@Override
	public void generateResponse() {
		List<Post> posts1 = data.getUser1().getPosts();
		List<Post> posts2 = data.getUser2().getPosts();

		ArrayList<String> allTags1 = new ArrayList<>();
		ArrayList<String> allTags2 = new ArrayList<>();

		posts1.forEach(x -> {
			Matcher mat = MY_PATTERN.matcher(x.getCaption());
			while (mat.find())
				allTags1.add(mat.group(1));
		});

		posts2.forEach(x -> {
			Matcher mat = MY_PATTERN.matcher(x.getCaption());
			while (mat.find())
				allTags2.add(mat.group(1));
		});

		// group by identity x -> x, val will contain counting
		Map<String, Long> tagCount1 = allTags1.stream().collect(groupingBy(x -> x, counting()));
		Map<String, Long> tagCount2 = allTags2.stream().collect(groupingBy(x -> x, counting()));

		float postsSize1 = posts1.size();
		float postsSize2 = posts2.size();
		tagCount1.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(TARGET_LEN)
				.forEach(x -> hashtags1.put(x.getKey(), x.getValue() * 100 / postsSize1));

		tagCount2.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(TARGET_LEN)
				.forEach(x -> hashtags2.put(x.getKey(), x.getValue() * 100 / postsSize2));

	}

	public LinkedHashMap<String, Float> getHashtags1() {
		return hashtags1;
	}

	public void setHashtags1(LinkedHashMap<String, Float> hashtags1) {
		this.hashtags1 = hashtags1;
	}

	public LinkedHashMap<String, Float> getHashtags2() {
		return hashtags2;
	}

	public void setHashtags2(LinkedHashMap<String, Float> hashtags2) {
		this.hashtags2 = hashtags2;
	}
}
