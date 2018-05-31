package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.advice.Util;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.PostTimeResponse;

public class PostingTimeAdvice {

	private static final double EveningThreshold = 0.4;

	private static final String EveningAdvice = "A larger portion of their posts occur during the evening. Maybe give this a shot?";

	private static final Predicate<Comparison> EveningPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			PostTimeResponse r = t.getResponseCollection().getPostTimeResponse();

			int[] indices = new int[] { 17, 18, 19, 20, 21, 22, 23 };
			double evening1 = Util.proportion(r.getDistribution1(), indices);
			double evening2 = Util.proportion(r.getDistribution2(), indices);

			if (evening1 < EveningThreshold && evening2 > EveningThreshold) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(EveningPredicate, c -> EveningAdvice))
			.setDefaultAdviceGenerator(c -> "You seem to post at the same times as they do. So nothing to see here :)");

}
