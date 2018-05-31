package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.advice.Util;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.RegularityResponse;

public class PostsPerDayAdvice {

	private static final double MAX_THRESHOLD = 1;

	private static final String MaxNumberPerDayAdvice = "They have days where they make more posts... you could give it a shot to have a few days with a lot of posts?";

	private static final Predicate<Comparison> MaxNumberPerDayPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			RegularityResponse r = t.getResponseCollection().getRegularityResponse();

			int max1 = Util.getMax2(r.getPostsPerDay1());
			int max2 = Util.getMax2(r.getPostsPerDay1());

			if (max2 > MAX_THRESHOLD * max1) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(MaxNumberPerDayPredicate, c -> MaxNumberPerDayAdvice))
			.setDefaultAdviceGenerator(
					c -> "Your behaviour with regard to number of posts per day is already similar to theirs.");
}
