
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRating;

    public EfficientRater(String id) {
        myID = id;
        myRating = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        if(myRating.keySet().contains(item)==false)
        myRating.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if(myRating.keySet().contains(item)){
            return true;      
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String it:myRating.keySet()){
            if(it.equals(item)){
                return myRating.get(it).getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRating.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String it:myRating.keySet()){
            list.add(myRating.get(it).getItem());
        }
        return list;
    }
}
