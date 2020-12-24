import org.json.simple.parser.ParseException;
import java.io.IOException;
import javax.swing.*;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Deck deck = new Deck();
        //deck.addCard();
        //deck.showCards();
         // deck.updateCard();
        //deck.deleteCard();
        //deck.createCards();
        //Card card = new Card("bla");
        //card.whichFile();

JFrame window = new JFrame("Mystic Tarot");
window.setSize(600,300);
window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
window.setVisible(true);


    }
}
