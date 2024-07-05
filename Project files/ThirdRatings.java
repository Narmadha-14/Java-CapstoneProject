
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
        //this("ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fir=new FirstRatings();
        myRaters=fir.loadRaters(ratingsfile);
        
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id,int minimalRaters){
        int c=0; 
        double ans=0.0;
        for(Rater itr:myRaters){
            if(itr.hasRating(id)){
                c++;
                ans+=(itr.getRating(id));
            }
        }
        if(c<minimalRaters){
            return 0.0;
        }
        return ans/c;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<String> myMovies=MovieDatabase.filterBy(new TrueFilter());
        for(String itr:myMovies){
            double avg=getAverageByID(itr,minimalRaters);
            if(avg!=0.0){
                Rating temp=new Rating(itr,avg);
                ans.add(temp);
            }
        }
        return ans;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<String> list=MovieDatabase.filterBy(filterCriteria);
        for(String it:list){
            double avg=getAverageByID(it,minimalRaters);
            if(avg!=0.0){
                Rating temp=new Rating(it,avg);
                ans.add(temp);
            }
        }
        return ans;
    }

}
