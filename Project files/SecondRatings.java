
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        //this("ratedmovies_short.csv","ratings_short.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings fir=new FirstRatings();
        myMovies=fir.loadMovies(moviefile);
        myRaters=fir.loadRaters(ratingsfile);
        
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        HashMap<String,Integer> mp=new HashMap<String,Integer>();
        for(Movie itr:myMovies){
            String id=itr.getID();
            double avg=getAverageByID(id,minimalRaters);
            if(avg!=0.0){
                Rating temp=new Rating(id,avg);
                ans.add(temp);
            }
        }
        return ans;
    }
    
    public String getTitle(String id){
        for(Movie itr:myMovies){
            if(itr.getID().equals(id)){
                return itr.getTitle();
            }
        }
        return "ID not found";
    }
    
    public String getID(String title){
        for(Movie itr:myMovies){
            if(itr.getTitle().equals(title)){
                return itr.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
    
}