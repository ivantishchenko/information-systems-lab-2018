package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.advice.Util;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.RegularityResponse;

public class PostPerWeekAdvice {

	private static final double MAX_THRESHOLD = 1.1;

	private static final String MaxNumberPerWeekAdvice = "They have weeks where they post more than you maximally do. Maybe an intensive week could be something?";

	private static final Predicate<Comparison> MaxNumberPerWeekPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			RegularityResponse r = t.getResponseCollection().getRegularityResponse();

			int max1 = Util.getMax2(r.getPostsPerWeek1());
			int max2 = Util.getMax2(r.getPostsPerWeek1());

			if (max2 > MAX_THRESHOLD * max1) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(MaxNumberPerWeekPredicate, c -> MaxNumberPerWeekAdvice))
			.setDefaultAdviceGenerator(
					c -> "Your behaviour regarding number of posts per weeks is already similar to theirs.");
}
