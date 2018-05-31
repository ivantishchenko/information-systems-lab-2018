package ch.ethz.brandin.response_models;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Top3PostsResponse implements IResponse {
    private Comparison data;
    private final int NUM_POSTS = 3;

    private List<String> captions1;
    private List<String> captions2;
    private List<Integer> likes1;
    private List<Integer> likes2;
    private List<Integer> comments1;
    private List<Integer> comments2;
    private List<String> dates1;
    private List<String> dates2;
    private List<String> urls1;
    private List<String> urls2;
    private SimpleDateFormat formatter;

    public Top3PostsResponse(Comparison data) {
        this.data = data;
        captions1 = new ArrayList<>();
        captions2 = new ArrayList<>();
        likes1 = new ArrayList<>();
        likes2 = new ArrayList<>();
        comments1 = new ArrayList<>();
        comments2 = new ArrayList<>();
        dates1 = new ArrayList<String>();
        dates2 = new ArrayList<String>();
        formatter = new SimpleDateFormat("EE MMM d y H:m:s ZZZ");
        
        urls1 = new ArrayList<String>();
        urls2 = new ArrayList<String>();
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = new ArrayList<Post>(data.getUser1().getUnfilteredPosts());
        List<Post> posts2 = new ArrayList<Post>(data.getUser2().getUnfilteredPosts());
        
        posts1.sort((a, b) -> b.getLikesCount() - a.getLikesCount());
        posts2.sort((a, b) -> b.getLikesCount() - a.getLikesCount());


        posts1 = posts1.subList(0, Math.min(NUM_POSTS, posts1.size()));
        posts2 = posts2.subList(0, Math.min(NUM_POSTS, posts2.size()));

        posts1.forEach(p -> {
            captions1.add(p.getCaption());
            likes1.add(p.getLikesCount());
            comments1.add(p.getCommentsCount());
            dates1.add(createDateStr(p));
            urls1.add(p.getSrcUrl());
        });

        posts2.forEach(p -> {
            captions2.add(p.getCaption());
            likes2.add(p.getLikesCount());
            comments2.add(p.getCommentsCount());
            dates2.add(createDateStr(p));
            urls2.add(p.getSrcUrl());
        });

    }

    private String createDateStr(Post p) {
        String dateString = formatter.format(new Date(p.getTimestamp() * 1000));
        return dateString;
    }

    public List<String> getCaptions1() {
        return captions1;
    }

    public void setCaptions1(List<String> captions1) {
        this.captions1 = captions1;
    }

    public List<String> getCaptions2() {
        return captions2;
    }

    public void setCaptions2(List<String> captions2) {
        this.captions2 = captions2;
    }

    public List<Integer> getLikes1() {
        return likes1;
    }

    public void setLikes1(List<Integer> likes1) {
        this.likes1 = likes1;
    }

    public List<Integer> getLikes2() {
        return likes2;
    }

    public void setLikes2(List<Integer> likes2) {
        this.likes2 = likes2;
    }

    public List<Integer> getComments1() {
        return comments1;
    }

    public void setComments1(List<Integer> comments1) {
        this.comments1 = comments1;
    }

    public List<Integer> getComments2() {
        return comments2;
    }

    public void setComments2(List<Integer> comments2) {
        this.comments2 = comments2;
    }

    public List<String> getDates1() {
        return dates1;
    }

    public void setDates1(List<String> dates1) {
        this.dates1 = dates1;
    }

    public List<String> getDates2() {
        return dates2;
    }

    public void setDates2(List<String> dates2) {
        this.dates2 = dates2;
    }

	public List<String> getUrls1() {
		return urls1;
	}

	public void setUrls1(List<String> urls1) {
		this.urls1 = urls1;
	}

	public List<String> getUrls2() {
		return urls2;
	}

	public void setUrls2(List<String> urls2) {
		this.urls2 = urls2;
	}
}
