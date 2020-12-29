import java.io.Serializable;

public class Card implements Serializable {
    public String title;
    public String description;
    public String image;

    public Card(String title, String description, String image){
        this.title = title;
        this.description = description;
        this.image = image;

    }

}
