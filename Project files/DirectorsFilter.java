
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String directors;
    public DirectorsFilter(String dir){
        directors=dir;
    }
    
    public boolean satisfies(String id){
        String[] dirs=directors.split(",");
        for(String itr:dirs){
            itr=itr.trim();
            if(MovieDatabase.getDirector(id).indexOf(itr)!=-1)
                return true;
        }
        return false;
    }

}
