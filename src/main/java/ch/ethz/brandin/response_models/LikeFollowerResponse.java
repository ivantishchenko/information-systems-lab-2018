package ch.ethz.brandin.response_models;

import java.util.List;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class LikeFollowerResponse implements IResponse{
    private Comparison data;
    private double ratio1;
    private double ratio2;

    public LikeFollowerResponse(Comparison data) {
        this.data = data;
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = data.getUser1().getPosts();
        List<Post> posts2 = data.getUser2().getPosts();

        int postSize1 = posts1.size();
        int postSize2 = posts2.size();

        double avgLikes1 = 0;
        double avgLikes2 = 0;

        for (Post p: posts1) avgLikes1 += p.getLikesCount();
        for (Post p: posts2) avgLikes2 += p.getLikesCount();

        avgLikes1 /= postSize1;
        avgLikes2 /= postSize2;

        ratio1 = avgLikes1 / data.getUser1().getFollowedByCount();
        ratio2 = avgLikes2 / data.getUser2().getFollowedByCount();
    }

    public double getRatio1() {
        return ratio1;
    }

    public void setRatio1(double ratio) {
        this.ratio1 = ratio;
    }

    public double getRatio2() {
        return ratio2;
    }

    public void setRatio2(double ratio2) {
        this.ratio2 = ratio2;
    }
}
