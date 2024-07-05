
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    
    
    private double getAverageByID(String movid,int minimalRaters){
        int c=0; 
        double ans=0.0;
        for(Rater itr:RaterDatabase.getRaters()){
            if(itr.hasRating(movid)){
                c++;
                ans+=(itr.getRating(movid));
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
    
    private double dotProduct(Rater me,Rater r){
        double res=0.0;
        ArrayList<String> mov=me.getItemsRated();
        for(String ids:mov){
            if(r.hasRating(ids) && me.hasRating(ids)){
                double rat1=r.getRating(ids);
                double rat2=me.getRating(ids);
                if(rat1!=-1 && rat2!=-1){
                    res+=(rat1-5.0)*(rat2-5.0);
                }     
            }
        }
        return res; 
    }
    
    private ArrayList<Rating> getSimilarities(String ratid){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<Rater> temp=RaterDatabase.getRaters();
        Rater me=RaterDatabase.getRater(ratid);
        for(Rater itr:temp){
            if(!itr.getID().equals(ratid)){
                double rat=dotProduct(me,itr);
                if(rat>0)
                ans.add(new Rating(itr.getID(),rat));
            }
        }
        Collections.sort(ans,Collections.reverseOrder());
        return ans;
    }
    
    public ArrayList<Rating> getSimilarRatings(String ratId,int numSimilarRaters,int minimalRaters){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<Rating> tem=getSimilarities(ratId);
        ArrayList<String> movies=MovieDatabase.filterBy(new TrueFilter());
        int c;
        double weight;
        for(String mov:movies){
            c=0;
            weight=0.0;
            for(int k=0;k<numSimilarRaters;k++){
                String raterId=tem.get(k).getItem();
                double val=tem.get(k).getValue();
                if(RaterDatabase.getRater(raterId).hasRating(mov)){
                    weight+=(RaterDatabase.getRater(raterId).getRating(mov)*val);
                    c++;
                }
            }
            if(c>=minimalRaters){
                ans.add(new Rating(mov,weight/c));
            }
        }
        Collections.sort(ans,Collections.reverseOrder());
        return ans;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String ratId,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<Rating> tem=getSimilarities(ratId);
        ArrayList<String> movies=MovieDatabase.filterBy(filterCriteria);
        int c;
        double weight;
        for(String mov:movies){
            c=0;
            weight=0.0;
            for(int k=0;k<numSimilarRaters;k++){
                String raterId=tem.get(k).getItem();
                double val=tem.get(k).getValue();
                if(RaterDatabase.getRater(raterId).hasRating(mov)){
                    weight+=(RaterDatabase.getRater(raterId).getRating(mov)*val);
                    c++;
                }
            }
            if(c>=minimalRaters){
                ans.add(new Rating(mov,weight/c));
            }
        }
        Collections.sort(ans,Collections.reverseOrder());
        return ans;
    }

}
