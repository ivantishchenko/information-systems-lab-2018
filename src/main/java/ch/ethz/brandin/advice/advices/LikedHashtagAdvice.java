package ch.ethz.brandin.advice.advices;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.TagsLikesDistributionResponse;

public class LikedHashtagAdvice {

	public static Advice INSTANCE = new Advice().setDefaultAdviceGenerator(c -> CreateAdvice(c));

	private static String CreateAdvice(Comparison c) {
		TagsLikesDistributionResponse r = c.getResponseCollection().getTagsLikesDistributionResponse();

		String hashtag = null;

		if (r.getDistribution2() != null && !r.getDistribution2().isEmpty()) {
			Map<String, Integer> m = new HashMap<String, Integer>(r.getDistribution2());
			int max = Collections.max(m.values());

			for (Entry<String, Integer> e : m.entrySet()) {
				if (e.getValue() == max) {
					hashtag = e.getKey();
					break;
				}
			}
		}

		if (hashtag != null) {
			return String.format("%s seems to generate the most likes. Maybe this is something for you?", hashtag);
		} else {
			return "We cannot provide any advice about liked hashtags at the moment.";
		}
	}
}
