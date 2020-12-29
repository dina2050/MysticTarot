import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

abstract public class Action {


    public void addCard() throws IOException {

    }

    public List<Card> showCards() throws IOException, ParseException {
        List<Card> cards = new ArrayList<Card>();
        return cards;
    }

    public void updateCard() throws IOException, ParseException {

    }

    public void deleteCard() throws IOException, ParseException {

    }





}

