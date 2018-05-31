package ch.ethz.brandin.response_models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class WeekPostTimeResponse implements IResponse {
    private int[] distribution1;
    private int[] distribution2;
    private Comparison data;
    private Calendar calendar;

    public WeekPostTimeResponse(Comparison data) {
        this.data = data;
        distribution1 = new int[7];
        distribution2 = new int[7];
        calendar = Calendar.getInstance();
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = data.getUser1().getPosts();
        List<Post> posts2 = data.getUser2().getPosts();

        List<Date> dates1 = new ArrayList<>();
        List<Date> dates2 = new ArrayList<>();

        posts1.forEach(x -> dates1.add(new Date(x.getTimestamp() * 1000 )));
        posts2.forEach(x -> dates2.add(new Date(x.getTimestamp() * 1000 )));

        dates1.forEach(x -> {
            calendar.setTime(x);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            distribution1[day - 1]++;
        });

        dates2.forEach(x -> {
            calendar.setTime(x);
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            distribution2[day - 1]++;
        });

//        if ( posts1.size() == IntStream.of(distribution1).sum() && posts2.size() == IntStream.of(distribution2).sum()) {
//            System.out.println("Diagrams are ok");
//        }

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
