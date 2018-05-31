package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.advice.Util;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.HashtagToPostResponse;

public class HashtagsPerPostAdvice {

	private static final String HashtagsPerPostGeneralAdvice = "They seem to be using more hashtags in general. Maybe try to increase your count as well?";
	private static final String HashtagsPerPostMaxPredicateAdvice = "Your maximum use of hashtags seems to me higher. Maybe you want to cut down on the maximum number you use?";

	private static final Predicate<Comparison> HashtagsPerPostGeneralPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			HashtagToPostResponse r = t.getResponseCollection().getHashtagToPostResponse();
			int[] indices = new int[] { 0 };
			double p1 = Util.proportion(r.getDistribution1(), indices);
			double p2 = Util.proportion(r.getDistribution2(), indices);

			if (p1 < p2) {
				return true;
			} else {
				return false;
			}
		}
	};

	private static final Predicate<Comparison> HashtagsPerPostMaxPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			HashtagToPostResponse r = t.getResponseCollection().getHashtagToPostResponse();

			int max = Util.getMax(r.getDistribution1());
			int max2 = Util.getMax(r.getDistribution2());

			if (max > max2) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(HashtagsPerPostGeneralPredicate, c -> HashtagsPerPostGeneralAdvice))
			.addPredicateInfo(new PredicateInfo(HashtagsPerPostMaxPredicate, c -> HashtagsPerPostMaxPredicateAdvice))
			.setDefaultAdviceGenerator(c -> "Your number of hashtags seems to be close enough to theirs.");
}
