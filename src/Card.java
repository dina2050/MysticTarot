import java.io.File;
import java.io.Serializable;

public class Card implements Serializable {
    public String title;
    public String description;

    public Card(String title, String description){
        this.title = title;
        this.description = description;

    }

    //filereader read = new filereader();
    //read.ReadFile(description);
}
