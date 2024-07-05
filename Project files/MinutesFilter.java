
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int minim;
    private int maxim;
    public MinutesFilter(int mini,int maxi){
        minim=mini;
        maxim=maxi;
    }
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id)>=minim && MovieDatabase.getMinutes(id)<=maxim;
    }
}
