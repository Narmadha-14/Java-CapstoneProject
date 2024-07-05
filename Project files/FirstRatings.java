
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> ans=new ArrayList<Movie>();
        FileResource fr=new FileResource("data/"+filename);
        CSVParser parse=fr.getCSVParser();
        for(CSVRecord itr:parse){
            String id=itr.get("id");
            String title=itr.get("title");
            String year=itr.get("year");
            String country=itr.get("country");
            String genre= itr.get("genre");
            String dir=itr.get("director");
            String min=itr.get("minutes");
            String poster=itr.get("poster");
            Movie res=new Movie(id,title,year,genre,dir,country,poster,Integer.parseInt(min));
            ans.add(res);
        }
        return ans;
    }
    
    public int comedyMov(ArrayList<Movie> ans){
        int c=0;
        for(Movie itr:ans){
            if(itr.getGenres().indexOf("Comedy")!=-1){
                c++;
            }
        }
        return c;
    }
    
    public int findLen(ArrayList<Movie> ans,int len){
        int c=0;
        for(Movie itr:ans){
            if(itr.getMinutes()>150){
                c++;
            }
        }
        return c;
    }
    
    public void maxMovByDir(ArrayList<Movie> ans){
        HashMap<String,Integer> mp=new HashMap<String,Integer>();
        for(Movie it:ans){
            String dir=it.getDirector();
            String[] tmp=dir.split(",");
            for(String i:tmp){
                i=i.trim();
                if(mp.keySet().contains(i)){
                    mp.put(i,mp.get(i)+1);
                }
                else{
                    mp.put(i,1);
                }
            }
        }
        /*System.out.println("Hashsets are:"+"\n");
        for(String itr:mp.keySet()){
            System.out.println(itr+"-->"+mp.get(itr));
        }*/
        int maxi=-1,count=0;
        ArrayList<String> dirs=new ArrayList<String>();
        for(String itr:mp.keySet()){
            if(maxi<mp.get(itr)){
                maxi=mp.get(itr);
            }
        }
        for(String itr:mp.keySet()){
            if(maxi==mp.get(itr)){
                count++;
                dirs.add(itr);
            }
        }
        System.out.println("Maximum number of movies by any director is: "+maxi);
        System.out.println("Directors directed maximum movies are: "+count);
        System.out.println("Those Directors are: ");
        for(String it:dirs){
            System.out.println(it);
        }
    }
    
    public void testLoadMovies(){
        //ArrayList<Movie> ans=loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> ans=loadMovies("ratedmoviesfull.csv");
        System.out.println("Total number of movies:"+ans.size());
        //System.out.println("Movies are:"+"\n");
    
        //for(Movie itr:ans){
            //System.out.println(itr.getTitle());
        //}
        System.out.println("Movies include comedy Genre:"+comedyMov(ans));
        System.out.println("Movies greater than 150 min:"+findLen(ans,150));
        maxMovByDir(ans);
    }
    
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ans=new ArrayList<Rater>();
        FileResource fr=new FileResource("data/"+filename);
        CSVParser parse=fr.getCSVParser();
        for(CSVRecord itr:parse){
            String raterId=itr.get("rater_id");
            String movId=itr.get("movie_id");
            double ratings=Double.parseDouble(itr.get("rating"));
            int f=0;
            for(Rater it:ans){
                if(it.getID().equals(raterId)){
                    it.addRating(movId,ratings);
                    f=1;
                    break;
                }
            }
            if(f==0){
                //Rater raters=new Rater(raterId);
                //Rater raters=new PlainRater(raterId);
                Rater raters=new EfficientRater(raterId);
                raters.addRating(movId,ratings);
                ans.add(raters);
            }
        }
        return ans; 
    }
    
    public int numRaterID(ArrayList<Rater> ans,String ind){
        for(Rater it:ans){
            if(it.getID().equals(ind)){
                return it.numRatings();
            }
        }
        return -1;
    }
    
    public void maxRatings(ArrayList<Rater> ans){
        int maxi=-1;
        for(Rater it:ans){
            if(maxi<it.numRatings()){
                maxi=it.numRatings();
            }
        }
        int c=0;
        ArrayList<String> temp=new ArrayList<String>();
        for(Rater it:ans){
            if(maxi==it.numRatings()){
                c++;
                temp.add(it.getID());
            }
        }
        System.out.println("Maximum number of raters are: "+maxi);
        System.out.println("Raters have maximum ratings are: "+c);
        System.out.println("Those Raters are: ");
        for(String it:temp){
            System.out.println(it);
        }
        
    }
    
    public int numRatingforMov(ArrayList<Rater> ans,String tmp){
        int c=0;
        for(Rater it:ans){
            if(it.hasRating(tmp)){
                c++;
            }
        }
        return c;
    }
    
    public int diffMov(ArrayList<Rater> ans){
        HashSet<String> st=new HashSet<String>();
        for(Rater it:ans){
            ArrayList<String> mov=it.getItemsRated();
            for(String itr:mov){
                st.add(itr);
            }
        }
        return st.size();
    }
    
    public void testLoadRaters(){
        //ArrayList<Rater> ans=loadRaters("ratings_short.csv");
        ArrayList<Rater> ans=loadRaters("ratings.csv");
        System.out.println("Total number of raters are: "+ans.size());
        /*for(Rater itr:ans){
            System.out.println(itr.getID()+"->"+itr.numRatings());
            ArrayList<String> temp=itr.getItemsRated();
            for(String it:temp){
                if(itr.hasRating(it)){
                    System.out.println(it+"---"+itr.getRating(it));
                }
            }
        }*/
        System.out.println("Number of raters for ID is: "+numRaterID(ans,"193"));
        maxRatings(ans);
        System.out.println("Number of ratings for movie is: "+numRatingforMov(ans,"1798709"));
        System.out.println("Total movies by raters are: "+diffMov(ans));
    }

}
