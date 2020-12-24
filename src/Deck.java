import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
public class Deck {
    List<Card> cards = new ArrayList<Card>();

    public Deck() throws IOException, ParseException {


    }

    public void addCard() throws IOException {
        String title = "";
        String description = "";
        System.out.println("Add the title of the new card");
        Scanner scan = new Scanner(System.in);
        String cardTitle = scan.nextLine();
        System.out.println("Add the description of the new card");
        String cardDescription = scan.nextLine();
        title += cardTitle;
        description += cardDescription;
        System.out.println("title: " + title);
        System.out.println("description: " + description);
        Card newcard = new Card(title, description);
        cards.add(newcard);
        Writer output;
        File file = new File("cards/" + cardTitle + ".txt");
        output = new BufferedWriter(new FileWriter(file));


            var mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(newcard);
            System.out.println("Using a custom serializer (in this case for a String): {}" + json);


        output.write(json);
        output.close();
        System.out.println("addedcards: " + cards);

    }

    public void showCards() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        File dir = new File("cards/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null && dir.isDirectory()) {
                for (File child : directoryListing) {
                    String str = FileUtils.readFileToString(child, "UTF-8");
                    Object obj = parser.parse(str);
                    JSONObject jsonObject = (JSONObject) obj;
                    String title = String.valueOf(jsonObject.get("title"));
                    String description = String.valueOf(jsonObject.get("description"));
                    Card newcard = new Card(title, description);
                    cards.add(newcard);
                }
        }
            else {
                System.out.println("There are no cards!!!");

                }



        for (Card card : cards) {
            System.out.println("title: " + card.title);
            System.out.println("description: " + card.description);
            System.out.println("card: " + card);
        }
    }

    public void deleteCard() throws IOException, ParseException {
        showCards();
        System.out.println("Choose the card you want to remove.");
        Scanner scan = new Scanner(System.in);
        String CardToRemove = scan.nextLine();
        cards.removeIf(card -> card.title.equals(CardToRemove));
        File file = new File("cards/" + CardToRemove + ".txt");
        file.delete();

    }


    public void updateCard() throws IOException, ParseException {
        showCards();
        String newTitle, newDescription;
        System.out.println("Choose the card you want to update");
        Scanner scan = new Scanner(System.in);
        String CardToUpdate = scan.nextLine();
        for (Card card : cards) {
            if (card.title.equals(CardToUpdate)){
                System.out.println(card.title);
                System.out.println(card.description);
                newTitle = scan.nextLine();
                newDescription = scan.nextLine();
                card.title = newTitle;
                card.description = newDescription;
                var mapper = new ObjectMapper();
                var json = mapper.writeValueAsString(card);
                List<String> newLines = new ArrayList<>();
                for (String line : Files.readAllLines(Paths.get("cards/" + CardToUpdate + ".txt"), StandardCharsets.UTF_8)) {
                      newLines.add(line.replace(line,json));


                }
                Files.write(Paths.get("cards/" + CardToUpdate + ".txt"), newLines, StandardCharsets.UTF_8);
                Path source = Paths.get("cards/" + CardToUpdate + ".txt");
                Files.move(source, source.resolveSibling(newTitle + ".txt"));


            }
            System.out.println("title: " + card.title);
            System.out.println("description: " + card.description);
            System.out.println("card: " + card);
        }
    }
}

