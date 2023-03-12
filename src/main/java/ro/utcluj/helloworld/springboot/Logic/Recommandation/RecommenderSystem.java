package ro.utcluj.helloworld.springboot.Logic.Recommandation;

import ro.utcluj.helloworld.springboot.Logic.ContentService;
import ro.utcluj.helloworld.springboot.Logic.UserService;
import ro.utcluj.helloworld.springboot.Model.Content;
import ro.utcluj.helloworld.springboot.Model.Tag;
import ro.utcluj.helloworld.springboot.Model.User;

import java.util.*;


public class RecommenderSystem {

    private UserService userService;
    private final ContentService contentService;

    public RecommenderSystem(UserService userService, ContentService contentService){
        this.contentService = contentService;
        this.userService = userService;
    }

    public ArrayList<Content> getRecommmandationList(User user) {
        ArrayList<Content> recommenderList = new ArrayList<>();
        List<Content> allContent = contentService.getAllContent();
        //compute frequency map
        HashMap<Tag,Integer> frequencyMap = computeFrequencyMap(user);
        int sum=0;
        for(Integer count : frequencyMap.values()){
            sum+=count;
        }
        HashMap<Integer,Double> similarityMap = new HashMap<>();
        for(Content content : allContent){
            ArrayList<Tag> tags = content.getTagsAsList();
            double nbTags = tags.size();
            double product = 0;
            for(Tag tag : Tag.values()){
                if(tags.contains(tag)){
                    product+=frequencyMap.getOrDefault(tag,0);
                }
            }
            similarityMap.put(content.id,product/(nbTags*sum));
        }
        //hashmap to array list
        ArrayList<Map.Entry<Integer, Double>> list = new ArrayList<>(similarityMap.entrySet());
        list.sort(Comparator.comparing(Map.Entry<Integer,Double>::getValue).reversed());
        for (Map.Entry<Integer,Double> item : list){
            recommenderList.add(contentService.getContentById(item.getKey()));
        }
        return recommenderList;
    }
    public HashMap<Tag,Integer> computeFrequencyMap(User user){
        HashMap<Tag,Integer> frequencyArray = new HashMap<>();
        for(Integer songId : user.getLikesAsList()){
            System.out.println(songId);
            Content song = contentService.getContentById(songId);
            for(Tag tag : song.getTagsAsList()){
                frequencyArray.putIfAbsent(tag, 0);
                frequencyArray.put(tag, frequencyArray.get(tag)+1);
            }
        }
        for(Integer songId : user.getDislikesAsList()){
            Content song = contentService.getContentById(songId);
            for(Tag tag : song.getTagsAsList()){
                frequencyArray.putIfAbsent(tag, 0);
                frequencyArray.put(tag, frequencyArray.get(tag)-1);
            }
        }
        return frequencyArray;
    }
}
