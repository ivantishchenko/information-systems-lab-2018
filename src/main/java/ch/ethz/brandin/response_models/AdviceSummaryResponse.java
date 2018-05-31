package ch.ethz.brandin.response_models;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.brandin.models.Comparison;

public class AdviceSummaryResponse implements IResponse {
	private List<String> summary;
	private Comparison data;
	
	public AdviceSummaryResponse(Comparison data) {
		this.data = data;
	}

	public List<String> getSummary() {
		return summary;
	}

	public void setSummary(List<String> summary) {
		this.summary = summary;
	}

	@Override
	public void generateResponse() {
		summary = new ArrayList<String>(data.getAdviceSummary());
	}
}
