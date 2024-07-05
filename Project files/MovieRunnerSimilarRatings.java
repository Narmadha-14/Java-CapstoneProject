
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        ArrayList<Rating> res=fourth.getAverageRatings(35);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getTitle(it.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f1=new YearAfterFilter(1990);
        Filter f2=new GenreFilter("Drama");
        AllFilters f=new AllFilters();
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> res=fourth.getAverageRatingsByFilter(8,f);
        System.out.println("Number of movies by average are:"+res.size());
        Collections.sort(res);
        for(Rating it:res){
            System.out.println(it.getValue()+" "+MovieDatabase.getYear(it.getItem())+" "+MovieDatabase.getTitle(it.getItem())+"\n"
                              +MovieDatabase.getGenres(it.getItem()));
        }
    }
    
    
    public void printSimilarRatings(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        ArrayList<Rating> ans=fourth.getSimilarRatings("71",20,5);
        for(Rating it:ans){
            System.out.println(MovieDatabase.getTitle(it.getItem())+" "+it.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new GenreFilter("Mystery");
        ArrayList<Rating> ans=fourth.getSimilarRatingsByFilter("964",20,5,f);
        for(Rating it:ans){
            System.out.println(MovieDatabase.getTitle(it.getItem())+" "+it.getValue()+"\n"+MovieDatabase.getGenres(it.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        Filter f=new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ans=fourth.getSimilarRatingsByFilter("120",10,2,f);
        for(Rating it:ans){
            System.out.println(MovieDatabase.getTitle(it.getItem())+" "+it.getValue()+"\n"+MovieDatabase.getDirector(it.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> ans=fourth.getSimilarRatingsByFilter("168",10,3,f);
        for(Rating it:ans){
            System.out.println(MovieDatabase.getTitle(it.getItem())+" "+MovieDatabase.getMinutes(it.getItem())+" "+
                               it.getValue()+"\n"+MovieDatabase.getGenres(it.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fourth=new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Number of raters are:"+RaterDatabase.size());
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are:"+MovieDatabase.size());
        AllFilters f=new AllFilters();
        Filter f1=new YearAfterFilter(1975);
        Filter f2=new MinutesFilter(70,200);
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> ans=fourth.getSimilarRatingsByFilter("314",10,5,f);
        for(Rating it:ans){
            System.out.println(MovieDatabase.getTitle(it.getItem())+" "+MovieDatabase.getYear(it.getItem())+" "+
                               MovieDatabase.getMinutes(it.getItem())+" "+it.getValue());
        }
    }
}
