package ch.ethz.brandin.response_models;

import java.util.List;
import java.util.stream.Collectors;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class VideoImageAvgResponse implements IResponse{

    private Comparison data;
    private int imagesSize1;
    private int imagesSize2;
    private double avgLikesImage1;
    private double avgLikesImage2;
    private double avgCommentsImage1;
    private double avgCommentsImage2;
    private double videosSize1;
    private double videosSize2;
    private double avgLikesVideo1;
    private double avgLikesVideo2;
    private double avgCommentsVideo1;
    private double avgCommentsVideo2;

    public VideoImageAvgResponse(Comparison data) {
        this.data = data;
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = data.getUser1().getPosts();
        List<Post> posts2 = data.getUser2().getPosts();

        List<Post> videos1 = posts1.stream().filter(x -> x.isVideo()).collect(Collectors.toList());
        List<Post> images1 = posts1.stream().filter(x -> !x.isVideo()).collect(Collectors.toList());
//        System.out.println("Video content");
//        videos1.forEach(x -> System.out.println(x.getCaption()));
//        System.out.println("Image content");
//        images1.forEach(x -> System.out.println(x.getCaption()));
        List<Post> videos2 = posts2.stream().filter(x -> x.isVideo()).collect(Collectors.toList());
        List<Post> images2 = posts2.stream().filter(x -> !x.isVideo()).collect(Collectors.toList());

        // filtering above
        // AVGs
        videosSize1 = videos1.size();
        videosSize2 = videos2.size();
        // comments
        for (Post p: videos1) {
            avgCommentsVideo1 += p.getCommentsCount();
            avgLikesVideo1 += p.getLikesCount();
        }
        if (videosSize1 != 0) {
            avgCommentsVideo1 /= videosSize1;
            avgLikesVideo1 /= videosSize1;
        }

        for (Post p: videos2) {
            avgCommentsVideo2 += p.getCommentsCount();
            avgLikesVideo2 += p.getLikesCount();
        }
        if (videosSize2 != 0) {
            avgCommentsVideo2 /= videosSize2;
            avgLikesVideo2 /= videosSize2;
        }

        // AVGs
        imagesSize1 = images1.size();
        imagesSize2 = images2.size();
        // comments
        for (Post p: images1) {
            avgCommentsImage1 += p.getCommentsCount();
            avgLikesImage1 += p.getLikesCount();
        }
        if (imagesSize1 != 0) {
            avgCommentsImage1 /= imagesSize1;
            avgLikesImage1 /= imagesSize1;
        }

        for (Post p: images2) {
            avgCommentsImage2 += p.getCommentsCount();
            avgLikesImage2 += p.getLikesCount();
        }
        if (imagesSize2 != 0) {
            avgCommentsImage2 /= imagesSize2;
            avgLikesImage2 /= imagesSize2;
        }
    }


    public double getAvgCommentsVideo1() {
        return avgCommentsVideo1;
    }

    public void setAvgCommentsVideo1(double avgCommentsVideo1) {
        this.avgCommentsVideo1 = avgCommentsVideo1;
    }

    public double getAvgCommentsVideo2() {
        return avgCommentsVideo2;
    }

    public void setAvgCommentsVideo2(double avgCommentsVideo2) {
        this.avgCommentsVideo2 = avgCommentsVideo2;
    }

    public double getAvgCommentsImage1() {
        return avgCommentsImage1;
    }

    public void setAvgCommentsImage1(double avgCommentsImage1) {
        this.avgCommentsImage1 = avgCommentsImage1;
    }

    public double getAvgCommentsImage2() {
        return avgCommentsImage2;
    }

    public void setAvgCommentsImage2(double avgCommentsImage2) {
        this.avgCommentsImage2 = avgCommentsImage2;
    }

    public double getVideosSize1() {
        return videosSize1;
    }

    public void setVideosSize1(double videosSize1) {
        this.videosSize1 = videosSize1;
    }

    public double getVideosSize2() {
        return videosSize2;
    }

    public void setVideosSize2(double videosSize2) {
        this.videosSize2 = videosSize2;
    }

    public double getAvgLikesVideo1() {
        return avgLikesVideo1;
    }

    public void setAvgLikesVideo1(double avgLikesVideo1) {
        this.avgLikesVideo1 = avgLikesVideo1;
    }

    public double getAvgLikesVideo2() {
        return avgLikesVideo2;
    }

    public void setAvgLikesVideo2(double avgLikesVideo2) {
        this.avgLikesVideo2 = avgLikesVideo2;
    }

    public int getImagesSize1() {
        return imagesSize1;
    }

    public void setImagesSize1(int imagesSize1) {
        this.imagesSize1 = imagesSize1;
    }

    public int getImagesSize2() {
        return imagesSize2;
    }

    public void setImagesSize2(int imagesSize2) {
        this.imagesSize2 = imagesSize2;
    }

    public double getAvgLikesImage1() {
        return avgLikesImage1;
    }

    public void setAvgLikesImage1(double avgLikesImage1) {
        this.avgLikesImage1 = avgLikesImage1;
    }

    public double getAvgLikesImage2() {
        return avgLikesImage2;
    }

    public void setAvgLikesImage2(double avgLikesImage2) {
        this.avgLikesImage2 = avgLikesImage2;
    }
}
