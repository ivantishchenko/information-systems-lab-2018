package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/posttime")
public class PostTimeController {
    @RequestMapping(value = "histogram", method = RequestMethod.GET)
    public IResponse getPostTimeDistriubtion(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getPostTimeResponse();
    }

    @RequestMapping(value = "week", method = RequestMethod.GET)
    public IResponse getWeekDistribution(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getWeekPostTimeResponse();
    }
    
    @RequestMapping(value = "regularity", method = RequestMethod.GET)
    public IResponse getRegularity(@RequestParam int id) {
    	Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);    	
    	return comparison.getResponseCollection().getRegularityResponse();
    }
}
