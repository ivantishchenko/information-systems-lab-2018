package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.advice.Util;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.WeekPostTimeResponse;

public class WeekDistributionAdvice {

	private static final double weekendThreshold = 0.5;
	
	private static final String WeekendAdvice = "They seem to post more during the weekend compared to you!";
	private static final String EndOfWeekAdvice = "They seem to have a larger focus on the end of the week compared to you";
	private static final String NoZeroDayAdvice = "They seem not to have a day when they have not posted anything, whereas you do.";

	private static final Predicate<Comparison> WeekendPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			WeekPostTimeResponse r = t.getResponseCollection().getWeekPostTimeResponse();
			
			int[] indices = new int[] { 5, 6};

			double weekend1 = Util.proportion(r.getDistribution1(), indices);
			double weekend2 = Util.proportion(r.getDistribution2(), indices);

			if (weekend1 < weekendThreshold && weekend2 > weekendThreshold) {
				return true;
			} else {
				return false;
			}
		}
	};
	
	private static final Predicate<Comparison> EndOfWeekPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			WeekPostTimeResponse r = t.getResponseCollection().getWeekPostTimeResponse();
			int[] indices = new int[] {4, 5, 6};
			double prop1 = Util.proportion(r.getDistribution1(), indices);
			double prop2 = Util.proportion(r.getDistribution2(), indices);
			
			if (prop1 < prop2) {
				return true;
			} else {
				return false;
			}
		}
	};

	private static final Predicate<Comparison> NoZeroDayPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			WeekPostTimeResponse r = t.getResponseCollection().getWeekPostTimeResponse();
			boolean hasZero1 = Util.hasZeros(r.getDistribution1());
			boolean hasZero2 = Util.hasZeros(r.getDistribution2());
			
			return hasZero1 && !hasZero2;
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(WeekendPredicate, c -> WeekendAdvice))
			.addPredicateInfo(new PredicateInfo(EndOfWeekPredicate, c -> EndOfWeekAdvice))
			.addPredicateInfo(new PredicateInfo(NoZeroDayPredicate, c -> NoZeroDayAdvice))
			.setDefaultAdviceGenerator(c -> "You seem to already post during the same days as they do.");
}
