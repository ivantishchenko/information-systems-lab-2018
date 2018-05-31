package ch.ethz.brandin.rest_api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.ComparisonParameter;

@RestController
@RequestMapping("/api")
public class RootController {

	private final static Logger log = LogManager.getLogger(RootController.class);

	@RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
	public int createComparison(@RequestBody ComparisonParameter comparisonParameter) {

		int id = ComparisonManager.INSTANCE.createComparison(comparisonParameter);


		log.debug(String.format("Created new comparison: {%s, %s}[%d]",
				comparisonParameter.getProfile1(), comparisonParameter.getProfile2(), id));

		return id;

	}

}
