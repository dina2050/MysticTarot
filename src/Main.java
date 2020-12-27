import org.json.simple.parser.ParseException;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Deck deck = new Deck();
        UserInterface carddeck = new UserInterface();
        carddeck.display();
        //deck.addCard();
        //deck.showCards();
         // deck.updateCard();
        //deck.deleteCard();
        //deck.createCards();
        //Card card = new Card("bla");
        //card.whichFile();


    }
}
