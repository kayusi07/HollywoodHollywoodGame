package hollywood.kayushi07.com.hollywoodhollywoodgame.Model;

public class Level {

    int id, movies, score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }



    public Level(int id, int movies, int score) {
        this.id = id;
        this.movies = movies;
        this.score = score;
    }


}
