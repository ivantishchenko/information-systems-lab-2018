package ch.ethz.brandin.models;

public class ComparisonParameter {
	private String profile1;
	private String profile2;
	private String filter;
	private int numvalue;

	public String getProfile1() {
		return profile1;
	}
	public void setProfile1(String profile1) {
		this.profile1 = profile1;
	}
	public String getProfile2() {
		return profile2;
	}
	public void setProfile2(String profile2) {
		this.profile2 = profile2;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public int getNumvalue() {
		return numvalue;
	}
	public void setNumvalue(int numvalue) {
		this.numvalue = numvalue;
	}
}