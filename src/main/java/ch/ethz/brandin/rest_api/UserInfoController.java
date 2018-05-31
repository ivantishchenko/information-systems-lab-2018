package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public IResponse checkPrivacy(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getUserInfoResponse();
    }

    @RequestMapping(value = "top3-posts", method = RequestMethod.GET)
    public IResponse getTop3Posts(@RequestParam int id) {
        Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
        return comparison.getResponseCollection().getTop3PostsResponse();
    }
}
