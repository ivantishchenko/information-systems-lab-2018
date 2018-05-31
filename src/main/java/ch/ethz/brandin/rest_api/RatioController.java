package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/ratio")
public class RatioController {

    @RequestMapping(value = "like2follower", method = RequestMethod.GET)
    public IResponse getLikeFollowerRatio(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getLikeFollowerResponse();
    }

    @RequestMapping(value = "comment2follower", method = RequestMethod.GET)
    public IResponse getCommentFollowerRatio(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getCommentFollowerResponse();
    }

    @RequestMapping(value = "video-image-avg", method = RequestMethod.GET)
    public IResponse getVideoImageAvg(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getVideoImageAvgResponse();
    }


}