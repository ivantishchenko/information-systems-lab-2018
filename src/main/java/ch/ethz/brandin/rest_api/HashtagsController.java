package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/hashtags")
public class HashtagsController {

    @RequestMapping(value = "percentage", method = RequestMethod.GET)
    public IResponse getUsersHashtags(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getHashtagPercentageResponse();
    }

    @RequestMapping(value = "distribution", method = RequestMethod.GET)
    public IResponse getHashtagPostDistribution(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getHashtagToPostResponse();
    }

    @RequestMapping(value = "tags-likes-distribution", method = RequestMethod.GET)
    public IResponse getTagsLikesDistribution(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getTagsLikesDistributionResponse();
    }

    @RequestMapping(value = "tags-comments-distribution", method = RequestMethod.GET)
    public IResponse getTagsCommentsDistribution(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getTagsCommentsDistributionResponse();
    }

}
