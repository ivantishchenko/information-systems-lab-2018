package ch.ethz.brandin.advice.advices;

import java.util.function.Predicate;

import ch.ethz.brandin.advice.Advice;
import ch.ethz.brandin.advice.PredicateInfo;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.VideoImageAvgResponse;

public class ImageVideoAdvice {

	private static final String NotEnoughVideoAdvice = "You could try to post more videos?";
	private static final String TooMuchVideoAdvice = "You seem to post more videos compared to the other profile";

	private static final Predicate<Comparison> NotEnoughVideoPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			VideoImageAvgResponse r = t.getResponseCollection().getVideoImageAvgResponse();

			if (r.getImagesSize1() / r.getVideosSize1() > r.getImagesSize2() / r.getVideosSize2()) {
				return true;
			} else {
				return false;
			}
		}
	};

	private static final Predicate<Comparison> TooMuchVideoPredicate = new Predicate<Comparison>() {
		@Override
		public boolean test(Comparison t) {
			VideoImageAvgResponse r = t.getResponseCollection().getVideoImageAvgResponse();

			if (r.getImagesSize1() / r.getVideosSize1() < r.getImagesSize2() / r.getVideosSize2()) {
				return true;
			} else {
				return false;
			}
		}
	};

	public static final Advice INSTANCE = new Advice()
			.addPredicateInfo(new PredicateInfo(NotEnoughVideoPredicate, c -> NotEnoughVideoAdvice))
			.addPredicateInfo(new PredicateInfo(TooMuchVideoPredicate, c -> TooMuchVideoAdvice))
			.setDefaultAdviceGenerator(c -> "Your ratio of video to image seems to be about the same.");
}
