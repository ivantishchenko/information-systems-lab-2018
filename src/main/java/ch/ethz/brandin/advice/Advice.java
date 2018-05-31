package ch.ethz.brandin.advice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import ch.ethz.brandin.models.Comparison;

public class Advice {
	
	private static final AdviceResponse DEFAULT_ADVICE_RESPONSE = new AdviceResponse("You are doing great! Keep up your work :)");
	
	private List<PredicateInfo> predicateInfos;
	private Function<Comparison, String> defaultAdviceGenerator;
	
	public Advice() {
		predicateInfos = new ArrayList<PredicateInfo>();
	}
	
	public Advice addPredicateInfo(PredicateInfo predicateInfo) {
		predicateInfos.add(predicateInfo);
		return this;
	}

	public AdviceResponse evaluate(Comparison comparison) {
		for (PredicateInfo pi : predicateInfos) {
			boolean test = false;
			try {
				test = pi.getPredicate().test(comparison);
			}
			catch (Exception e) {
				test = false;
			}
			
			if (test) {
				AdviceResponse response = pi.generateAdviceResponse(comparison);
				comparison.getAdviceSummary().add(response.getAdvice());
				return response;
			}
		}
		
		if (defaultAdviceGenerator != null) {
			AdviceResponse response = new AdviceResponse(defaultAdviceGenerator.apply(comparison));
			comparison.getAdviceSummary().add(response.getAdvice());
			return response;
		}
		else {
			return DEFAULT_ADVICE_RESPONSE;
		}
	}	

	public Advice setDefaultAdviceGenerator(Function<Comparison, String> defaultAdviceGenerator) {
		this.defaultAdviceGenerator = defaultAdviceGenerator;
		return this;
	}
}
