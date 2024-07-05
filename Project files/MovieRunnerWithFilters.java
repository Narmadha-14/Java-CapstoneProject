
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        ArrayList<Rating> res=third.getAverageRatings(35);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getTitle(it.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new YearAfterFilter(2000);
        ArrayList<Rating> res=third.getAverageRatingsByFilter(20,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getYear(it.getItem())+" "+MovieDatabase.getTitle(it.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new GenreFilter("Comedy");
        ArrayList<Rating> res=third.getAverageRatingsByFilter(20,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getTitle(it.getItem())+"\n"+MovieDatabase.getGenres(it.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new MinutesFilter(105,135);
        ArrayList<Rating> res=third.getAverageRatingsByFilter(5,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getMinutes(it.getItem())+" "+MovieDatabase.getTitle(it.getItem()));
        }
    }
    
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> res=third.getAverageRatingsByFilter(4,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getTitle(it.getItem())+"\n"+MovieDatabase.getDirector(it.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f1=new YearAfterFilter(1990);
        Filter f2=new GenreFilter("Drama");
        AllFilters f=new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> res=third.getAverageRatingsByFilter(8,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getYear(it.getItem())+" "+MovieDatabase.getTitle(it.getItem())+"\n"
                              +MovieDatabase.getGenres(it.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings third=new ThirdRatings();
        System.out.println("Number of raters are:"+third.getRaterSize());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f1=new MinutesFilter(90,180);
        Filter f2=new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters f=new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> res=third.getAverageRatingsByFilter(3,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getMinutes(it.getItem())+" "+MovieDatabase.getTitle(it.getItem())+"\n"
                              +MovieDatabase.getDirector(it.getItem()));
        }
    }
    
    

}
