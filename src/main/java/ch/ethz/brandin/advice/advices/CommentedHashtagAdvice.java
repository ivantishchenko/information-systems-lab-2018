package ch.ethz.brandin.advice.advices;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.TagsCommentsDistributionResponse;

public class CommentedHashtagAdvice {

	public static final Advice INSTANCe = new Advice().setDefaultAdviceGenerator(c -> createAdvice(c));

	private static String createAdvice(Comparison c) {
		TagsCommentsDistributionResponse r = c.getResponseCollection().getTagsCommentsDistributionResponse();

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
			return String.format(
					"The hashtag %s seems to generate the most engagement in terms of comments. Give it a shot?",
					hashtag);
		} else {
			return "";
		}
	}
}
