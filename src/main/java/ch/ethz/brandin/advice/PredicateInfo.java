package ch.ethz.brandin.advice;

import java.util.function.Function;
import java.util.function.Predicate;

import ch.ethz.brandin.models.Comparison;

public class PredicateInfo {
	private Predicate<Comparison> predicate;
	private Function<Comparison, String> adviceGenerator;

	public PredicateInfo(Predicate<Comparison> predicate, Function<Comparison, String> advice) {
		this.predicate = predicate;
		this.adviceGenerator = advice;
	}
	
	public Predicate<Comparison> getPredicate() {
		return this.predicate;
	}

	public AdviceResponse generateAdviceResponse(Comparison comparison) {
		return new AdviceResponse(adviceGenerator.apply(comparison));
	}
}
