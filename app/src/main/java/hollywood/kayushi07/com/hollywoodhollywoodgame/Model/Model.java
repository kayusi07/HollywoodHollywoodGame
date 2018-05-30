package hollywood.kayushi07.com.hollywoodhollywoodgame.Model;


public class Model {


    public static final int TEXT_TYPE=0;
    public static final int CLOSED_LEVEL=1;
    public static final int OPEN_LEVEL=2;

    public int type;
    public int data;
    public String text;



    public Model(int type, String text, int data)
    {
        this.type=type;
        this.data=data;
        this.text=text;

    }

}
