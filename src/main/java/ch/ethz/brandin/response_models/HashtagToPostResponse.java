package ch.ethz.brandin.response_models;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class HashtagToPostResponse implements IResponse {
    private Comparison data;
    private Pattern MY_PATTERN;
    private int[] distribution1;
    private int[] distribution2;
    private final int HASHTAGS_NUM = 31;

    public HashtagToPostResponse(Comparison data) {
        this.data = data;
        MY_PATTERN = Pattern.compile("#(\\S+)");
        distribution1 = new int[HASHTAGS_NUM];
        distribution2 = new int[HASHTAGS_NUM];
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = data.getUser1().getPosts();
        List<Post> posts2 = data.getUser2().getPosts();

        for (Post p: posts1)
            distribution1[getTagsCount(p)]++;

        for (Post p: posts2) {
            distribution2[getTagsCount(p)]++;
        }

    }

    private int getTagsCount(Post p) {
        Matcher mat = MY_PATTERN.matcher(p.getCaption());
        int countTags = 0;
        while(mat.find())
            countTags++;

        return Math.min(countTags, HASHTAGS_NUM - 1);
    }

    public int[] getDistribution1() {
        return distribution1;
    }

    public void setDistribution1(int[] distribution1) {
        this.distribution1 = distribution1;
    }

    public int[] getDistribution2() {
        return distribution2;
    }

    public void setDistribution2(int[] distribution2) {
        this.distribution2 = distribution2;
    }
}
