
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String genre;
    public GenreFilter(String gen){
        genre=gen;
    }
    
    public boolean satisfies(String id){
        if(MovieDatabase.getGenres(id).indexOf(genre)==-1)
        return false;
        return true;
    }
}
