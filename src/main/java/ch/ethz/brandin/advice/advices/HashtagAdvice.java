package ch.ethz.brandin.advice.advices;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.HashtagPercentageResponse;

public class HashtagAdvice {

	private static final double MAX = 10;

	private static final String InconsistentHashtagsAdvice = "They seem to vary a lot with regard to their use of hashtags. There is not a single preferred one...";

	private static final Predicate<Comparison> InconsistendHashtagsPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			HashtagPercentageResponse r = t.getResponseCollection().getHashtagPercentageResponse();

			float max2 = Collections.max(r.getHashtags1().values());

			if (max2 < MAX) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(InconsistendHashtagsPredicate, c -> InconsistentHashtagsAdvice))
			.setDefaultAdviceGenerator(c -> CreateAdvice(c));

	private static String CreateAdvice(Comparison c) {
		HashtagPercentageResponse r = c.getResponseCollection().getHashtagPercentageResponse();

		String hashtag = null;

		if (r.getHashtags2() != null && !r.getHashtags2().isEmpty()) {
			Map<String, Float> m = new LinkedHashMap<String, Float>(r.getHashtags2());
			float max = Collections.max(m.values());
			for (Entry<String, Float> e : m.entrySet()) {
				if (e.getValue() == max) {
					hashtag = e.getKey();
				}
			}
		}

		if (hashtag != null) {
			return "We suggest you use the following hashtag more often: " + hashtag;
		} else {
			return "We cannot provide any advice about hashtags at the moment.";
		}
	}
}
