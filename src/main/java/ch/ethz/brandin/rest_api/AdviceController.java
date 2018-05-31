package ch.ethz.brandin.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.brandin.advice.advices.CommentedHashtagAdvice;
import ch.ethz.brandin.advice.advices.FollowerAdvice;
import ch.ethz.brandin.advice.advices.HashtagAdvice;
import ch.ethz.brandin.advice.advices.HashtagsPerPostAdvice;
import ch.ethz.brandin.advice.advices.ImageVideoAdvice;
import ch.ethz.brandin.advice.advices.LikedHashtagAdvice;
import ch.ethz.brandin.advice.advices.PostPerWeekAdvice;
import ch.ethz.brandin.advice.advices.PostingTimeAdvice;
import ch.ethz.brandin.advice.advices.PostsPerDayAdvice;
import ch.ethz.brandin.advice.advices.WeekDistributionAdvice;
import ch.ethz.brandin.data_service.ComparisonManager;
import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.response_models.AdviceSummaryResponse;
import ch.ethz.brandin.response_models.IResponse;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

	@RequestMapping(value = "hashtag", method = RequestMethod.GET)
	public IResponse getHashtagAdvice(@RequestParam int id) {
		Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);

		return HashtagAdvice.INSTANCE.evaluate(comparison);
	}

	@RequestMapping(value = "week", method = RequestMethod.GET)
	public IResponse getWeekDayAdvice(@RequestParam int id) {
		Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
		return WeekDistributionAdvice.INSTANCE.evaluate(comparison);
	}

	@RequestMapping(value = "postingTime", method = RequestMethod.GET)
	public IResponse getPosttimeAdvice(@RequestParam int id) {
		Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);
		return PostingTimeAdvice.INSTANCE.evaluate(comparison);
	}

	@RequestMapping(value = "follower", method = RequestMethod.GET)
	public IResponse getFollowerAdvice(@RequestParam int id) {
		Comparison comparison = ComparisonManager.INSTANCE.getComparisonData(id);

		return FollowerAdvice.INSTANCE.evaluate(comparison);
	}

	@RequestMapping(value = "postsPerWeek", method = RequestMethod.GET)
	public IResponse getPostPerWeekAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return PostPerWeekAdvice.INSTANCE.evaluate(c);
	}
	
	@RequestMapping(value = "postsPerDay", method = RequestMethod.GET)
	public IResponse getPostsPerDayAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return PostsPerDayAdvice.INSTANCE.evaluate(c);
	}

	@RequestMapping(value = "hashtagsPerPost", method = RequestMethod.GET)
	public IResponse getHashtagPerPostAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return HashtagsPerPostAdvice.INSTANCE.evaluate(c);
	}
	
	@RequestMapping(value = "imageVideo", method = RequestMethod.GET)
	public IResponse getImaveVideoAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return ImageVideoAdvice.INSTANCE.evaluate(c);
	}
	
	@RequestMapping(value = "likesHashtags", method = RequestMethod.GET)
	public IResponse getLikeHashtagsAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return LikedHashtagAdvice.INSTANCE.evaluate(c);
	}
	
	@RequestMapping(value = "commentsHashtags", method = RequestMethod.GET) 
	public IResponse getCommentHashtagsAdvice(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		return CommentedHashtagAdvice.INSTANCe.evaluate(c);
	}
	
	@RequestMapping(value = "summary", method = RequestMethod.GET)
	public IResponse getAdviceSummary(@RequestParam int id) {
		Comparison c = ComparisonManager.INSTANCE.getComparisonData(id);
		AdviceSummaryResponse response = new AdviceSummaryResponse(c);
		response.generateResponse();
		return response;
	}
}