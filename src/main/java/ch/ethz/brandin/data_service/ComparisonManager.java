package ch.ethz.brandin.data_service;

import java.util.HashMap;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.ComparisonParameter;

public class ComparisonManager {
	public static ComparisonManager INSTANCE;

	private static int ID = 1;

	private HashMap<Integer, Comparison> map;

	public ComparisonManager() {
		map = new HashMap<>();
	}

	public int createComparison(ComparisonParameter comparisonParameter) {
		int id = ID++;

		Comparison c = new Comparison(id, comparisonParameter.getProfile1(),
				comparisonParameter.getProfile2(), comparisonParameter.getNumvalue(), comparisonParameter.getFilter());
		map.put(id, c);

		c.init();
		return id;
	}
	
	public Comparison getComparisonData(int id) {
		return map.get(id);
	}
}
