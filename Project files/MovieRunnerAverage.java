
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sec=new SecondRatings();
        System.out.println("Number of movies are:"+sec.getMovieSize());
        System.out.println("Number of raters are:"+sec.getRaterSize());
        ArrayList<Rating> res=sec.getAverageRatings(20);
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+sec.getTitle(it.getItem()));
        }
        System.out.println("Number of movies have more than 50 ratings are:"+res.size());
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sec=new SecondRatings();
        String id=sec.getID("Vacation");
        ArrayList<Rating> res=sec.getAverageRatings(3);
        for(Rating it:res){
            if(it.getItem().equals(id)){
                System.out.println("Rating is:"+it.getValue());
            }
        }
    }

}
