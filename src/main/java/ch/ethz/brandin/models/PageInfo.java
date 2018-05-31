package ch.ethz.brandin.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageInfo {
	@JsonProperty("has_next_page")
	private boolean hasNextPage;
	@JsonProperty("end_cursor")
	private String endCursor;
	
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public String getEndCursor() {
		return endCursor;
	}
	public void setEndCursor(String endCursor) {
		this.endCursor = endCursor;
	}
}
