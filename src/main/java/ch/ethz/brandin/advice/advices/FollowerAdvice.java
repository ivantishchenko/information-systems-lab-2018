package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.FollowerResponse;

public class FollowerAdvice {

	private static final double WayTooExclusiveFactor = 5;
	private static final double TooExclusiveFactor = 2;
	private static final double TooPublicFactor = 0.75;
	private static final double WayTooPublicFactor = 0.5;

	private static final String WayTooExclusiveAdvice = "Your ratio is way higher. You could try to follow a few more people!";
	private static final String TooExclusiveAdvice = "Your ratio is higher. You could try to follow more people in order to gain followers yourself!";
	private static final String TooPublicAdvice = "Your ratio is lower. So try to be more exclusive in who you follow!";
	private static final String WayTooPublicAdvice = "Your ratio is significantly lower. Try to be more exclusive in who you follow";

	private static final Predicate<Comparison> WayTooExclusivePredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			FollowerResponse r = t.getResponseCollection().getFollowerResponse();
			if (r.getRatio1() > WayTooExclusiveFactor * r.getRatio2()) {
				return true;
			}
			return false;
		}
	};

	private static final Predicate<Comparison> TooExclusivePredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			FollowerResponse r = t.getResponseCollection().getFollowerResponse();
			if (r.getRatio1() > TooExclusiveFactor * r.getRatio2()) {
				return true;
			}
			return false;
		}
	};

	private static final Predicate<Comparison> WayTooPublicPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			FollowerResponse r = t.getResponseCollection().getFollowerResponse();

			if (r.getRatio1() < WayTooPublicFactor * r.getRatio2()) {
				return true;
			}
			return false;
		}
	};

	private static final Predicate<Comparison> TooPublicPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			FollowerResponse r = t.getResponseCollection().getFollowerResponse();
			if (r.getRatio1() < TooPublicFactor * r.getRatio2()) {
				return true;
			}
			return false;
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(WayTooExclusivePredicate, c -> WayTooExclusiveAdvice))
			.addPredicateInfo(new PredicateInfo(TooExclusivePredicate, c -> TooExclusiveAdvice))
			.addPredicateInfo(new PredicateInfo(WayTooPublicPredicate, c -> WayTooPublicAdvice))
			.addPredicateInfo(new PredicateInfo(TooPublicPredicate, c -> TooPublicAdvice))
			.setDefaultAdviceGenerator(c -> "Your ratio is in the same range.");
}
