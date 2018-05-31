package ch.ethz.brandin.response_models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;

public class TagsLikesDistributionResponse implements IResponse {
    private final int TARGET_LEN = 10;
    private Comparison data;
    private Pattern MY_PATTERN;
    private HashMap<String, Integer> distribution1;
    private HashMap<String, Integer> distribution2;

    public TagsLikesDistributionResponse(Comparison data) {
        this.data = data;
        MY_PATTERN = Pattern.compile("#(\\S+)");
        distribution1 = new HashMap<>();
        distribution2 = new HashMap<>();
    }

    @Override
    public void generateResponse() {
        List<Post> posts1 = data.getUser1().getPosts();
        List<Post> posts2 = data.getUser2().getPosts();

        // new part
        Map<String, Integer> tagLike1 = new HashMap<>();
        Map<String, Integer> tagAppearance1 = new HashMap<>();

        posts1.forEach(post -> {
            Set<String> distinctTags = getDistinctTags(post);
            distinctTags.forEach(tag -> {
                if (!tagLike1.containsKey(tag) && !tagAppearance1.containsKey(tag)) {
                    tagLike1.put(tag, post.getLikesCount());
                    tagAppearance1.put(tag, 1);
                }
                else {
                    tagLike1.put(tag, tagLike1.get(tag) + post.getLikesCount());
                    tagAppearance1.put(tag, tagAppearance1.get(tag) + 1);
                }
            });
        });
//        tagLike1.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .limit(TARGET_LEN)
//                .forEach(x -> {
//                    String key = x.getKey();
//                    float avgVal = x.getValue() / (float) tagAppearance1.get(key);
//                    distribution1.put(key, avgVal);
//                });
        tagLike1.forEach((key, value1)-> {
            int value2 = tagAppearance1.get(key);
            distribution1.put(key, value1 / value2);
        });

        distribution1 = (HashMap<String, Integer>) distribution1.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(TARGET_LEN).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        tagLike1.forEach((k,v)->System.out.println("Item : " + k + " Likes : " + v));
//        tagAppearance1.forEach((k,v)->System.out.println("Item : " + k + " Counts : " + v));
//        System.out.println("Distribution 1");
//        distribution1.forEach((k,v)->System.out.println("Item : " + k + " Counts : " + v));

        // distribution 2

        Map<String, Integer> tagLike2 = new HashMap<>();
        Map<String, Integer> tagAppearance2 = new HashMap<>();

        posts2.forEach(post -> {
            Set<String> distinctTags = getDistinctTags(post);
            distinctTags.forEach(tag -> {
                if (!tagLike2.containsKey(tag) && !tagAppearance2.containsKey(tag)) {
                    tagLike2.put(tag, post.getLikesCount());
                    tagAppearance2.put(tag, 1);
                }
                else {
                    tagLike2.put(tag, tagLike2.get(tag) + post.getLikesCount());
                    tagAppearance2.put(tag, tagAppearance2.get(tag) + 1);
                }
            });
        });

        tagLike2.forEach((key, value1)-> {
            int value2 = tagAppearance2.get(key);
            distribution2.put(key, value1 / value2);
        });

        distribution2 = (HashMap<String, Integer>) distribution2.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(TARGET_LEN).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Set<String> getDistinctTags(Post post) {
        Matcher mat = MY_PATTERN.matcher(post.getCaption());
        Set<String> distinctTags = new HashSet<>();

        while (mat.find()) {
            String tag = mat.group(1);
            distinctTags.add(tag);
        }
        return distinctTags;
    }

    public HashMap<String, Integer> getDistribution1() {
        return distribution1;
    }

    public void setDistribution1(HashMap<String, Integer> distribution1) {
        this.distribution1 = distribution1;
    }

    public HashMap<String, Integer> getDistribution2() {
        return distribution2;
    }

    public void setDistribution2(HashMap<String, Integer> distribution2) {
        this.distribution2 = distribution2;
    }

}
