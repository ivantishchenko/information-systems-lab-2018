package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/follower")
public class FollowerController {

	@RequestMapping(value = "ratio", method = RequestMethod.GET)
	public IResponse getFollowerData(@RequestParam int id) {
		Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
		return comparison.getResponseCollection().getFollowerResponse();
	}
}
