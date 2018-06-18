package hollywood.kayushi07.com.hollywoodhollywoodgame.Model;


public class Model {

    public static final int CLOSED_LEVEL=0;
    public static final int OPEN_LEVEL=1;

    public int type;
    public int id, movies, score;
    public String text;



    public Model(int type, String text, int id, int movies, int score)
    {
        this.type=type;
        this.id = id;
        this.movies = movies;
        this.score = score;
        this.text=text;

    }

}
