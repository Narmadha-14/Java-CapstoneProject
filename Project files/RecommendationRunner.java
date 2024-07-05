
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate (){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Horror"));
        f.addFilter(new YearAfterFilter(2014));
        ArrayList<String> list=MovieDatabase.filterBy(f);
        return list;
    }
    public void printRecommendationsFor (String webRaterID){
        FourthRatings four=new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> lst=four.getSimilarRatings(webRaterID,10,2);
        if(lst.size()==0){
            System.out.println("<p>");
            System.out.println("No Movies Found");
            System.out.println("<p>");
        }
        else{
            System.out.println("<table class=\"tab\">");
                System.out.println("<style>");
                System.out.println("<.tab table{border: 1px solid black; padding: 8px;}>");
                System.out.println("<.tab th{border: 1px solid black; padding: 8px;}>");
                System.out.println("<.tab td{border: 1px solid black; padding: 8px;}>");
                System.out.println("</style>");
                
                System.out.println("<tr>");
                System.out.println("<th>"+"Poster"+"</th>");
                System.out.println("<th>"+"Title"+"</th>");
                System.out.println("<th>"+"year"+"</th>");
                System.out.println("<th>"+"Directors"+"</th>");
                System.out.println("<th>"+"Genre"+"</th>");
                System.out.println("<th>"+"Length"+"</th>");
                System.out.println("<th>"+"Rating"+"</th>");
                System.out.println("</tr>");
                
                for(Rating rat:lst){
                    System.out.println("<tr>");
                    System.out.println("<td>"+"<img src="+MovieDatabase.getPoster(rat.getItem())+">"+"</td>");
                    System.out.println("<td>"+MovieDatabase.getTitle(rat.getItem())+"</td>");
                    System.out.println("<td>"+MovieDatabase.getYear(rat.getItem())+"</td>");
                    System.out.println("<td>"+MovieDatabase.getDirector(rat.getItem())+"</td>");
                    System.out.println("<td>"+MovieDatabase.getGenres(rat.getItem())+"</td>");
                    System.out.println("<td>"+MovieDatabase.getMinutes(rat.getItem())+"</td>");
                    System.out.println("<td>"+rat.getValue()+"</td>");
                    System.out.println("</tr>");
                }
            System.out.println("</table>");
        }
        
    }
}
