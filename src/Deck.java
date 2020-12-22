import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

public class Deck {
    List<Card> cards = new ArrayList<Card>();
    ArrayList<String> descriptions = new ArrayList<String>();
    ArrayList<String> titles = new ArrayList<String>();

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
        Writer output = null;
        File file = new File("descriptions/" + cardTitle + ".txt");
        output = new BufferedWriter(new FileWriter(file));


            var mapper = new ObjectMapper();
            var json = mapper.writeValueAsString(newcard);
            System.out.println("Using a custom serializer (in this case for a String): {}" + json);


        //ouverture d'un flux de sortie vers le fichier "personne.serial"
        //FileOutputStream fos = new FileOutputStream("descriptions/" + cardTitle + ".txt");

        // création d'un "flux objet" avec le flux fichier
       // ObjectOutputStream oos= new ObjectOutputStream(fos);
        output.write(json);
        output.close();
        //output.writeObject(json);

      /*  try {
            // sérialisation : écriture de l'objet dans le flux de sortie
            oos.writeObject(json);
            // on vide le tampon
            oos.flush();
            System.out.println(json + " a ete serialise");
        } finally {
            //fermeture des flux
            try {
                oos.close();
            } finally {
                fos.close();
            }
        }*/

        System.out.println("addedcards: " + cards);

    }

    public void showCards() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        File dir = new File("descriptions/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String str = FileUtils.readFileToString(child, "UTF-8");
                Object obj = parser.parse(str);
                JSONObject jsonObject = (JSONObject) obj;
                String title = String.valueOf(jsonObject.get("title"));
                String description = String.valueOf(jsonObject.get("description"));
                Card newcard = new Card(title, description);
                cards.add(newcard);

            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }

        for (Card card : cards) {
            System.out.println("title: " + card.title);
            System.out.println("description: " + card.description);
            System.out.println("card: " + card);
        }
    }

}

