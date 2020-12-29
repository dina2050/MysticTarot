import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Deck extends Action {
    List<Card> cards = new ArrayList<Card>();
    public Deck() throws IOException, ParseException   {


    }
    public void addCard() throws IOException {
        String title = "";
        String description = "";
        String image = "";
        AddCardPanel cardEditorPane = new AddCardPanel();
        String cardTitle = "";
        String cardDescription = "";
        String cardImage = "";
        int result = JOptionPane.showConfirmDialog(null, cardEditorPane,
                "Add a card", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // TODO: do something with info
            for (AddCardPanel.FieldTitle fieldTitle :
                    AddCardPanel.FieldTitle.values()) {
               if (fieldTitle.getTitle() == "Title"){
                    cardTitle = cardEditorPane.getFieldText(fieldTitle);
               }
               else if (fieldTitle.getTitle() == "Description"){
                    cardDescription = cardEditorPane.getFieldText(fieldTitle);
               }

               else {
                    cardImage = cardEditorPane.getFieldText(fieldTitle);
               }

            }
        }

        title += cardTitle;
        description += cardDescription;
        image += cardImage;
        Card newcard = new Card(title, description, image);
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

    public List<Card> showCards(JLabel label, JPanel panel, GridBagConstraints constraints) throws IOException, ParseException {
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
                    String image = String.valueOf(jsonObject.get("image"));
                    Card newcard = new Card(title, description, image);
                    cards.add(newcard);
                }
        }
            else {
                System.out.println("There are no cards!!!");

                }

        for (int i=0; i< cards.size(); i++) {
            System.out.println("title: " + cards.get(i).title);
            System.out.println("description: " + cards.get(i).description);
            System.out.println("image: " + cards.get(i).image);
            label = new JLabel();
            label.setIcon(new ImageIcon(cards.get(i).image));
            //label.setText(cards.get(i).description);
            label.setText("<html><p style=\"width:500px\">"+cards.get(i).description+"</p></html>");
            constraints.gridy = i;
            Border border = LineBorder.createGrayLineBorder();
            label.setBorder(border);
            constraints.anchor= GridBagConstraints.WEST;
            panel.add(label, constraints);
        }
        return cards;
    }

    JLabel label = new JLabel();
   JPanel panel = new JPanel();
   GridBagConstraints constraints = new GridBagConstraints();
   public void updateCard() throws IOException, ParseException {
         ChooseCardPanel chooseCardPane = new ChooseCardPanel();
           String CardToUpdate = "";
           String EnteredTitle = "";
                        int result = JOptionPane.showConfirmDialog(null, chooseCardPane,
                                "Choose the card you want to update", JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if (result == JOptionPane.OK_OPTION) {
                            // TODO: do something with info

                            for (ChooseCardPanel.FieldTitle fieldTitle :
                                    ChooseCardPanel.FieldTitle.values()) {
                                EnteredTitle = chooseCardPane.getFieldText(fieldTitle);

                            }

                            if (!EnteredTitle.equals("")) {
                                CardToUpdate += EnteredTitle;
                                UpdateCardPanel cardUpdatePane = new UpdateCardPanel();
                                String newTitle = "";
                                String newDescription = "";
                                String newImage = "";
                                UserInterface call = new UserInterface();
                                cards = showCards(label,panel,constraints);
                                for (Card card : cards) {
                                    if (card.title.equals(CardToUpdate)) {
                                        System.out.println(CardToUpdate);
                                        int UpdateResult = JOptionPane.showConfirmDialog(null, cardUpdatePane,
                                                "Update a card", JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.PLAIN_MESSAGE);
                                        if (UpdateResult == JOptionPane.OK_OPTION) {
                                            // TODO: do something with info
                                            for (UpdateCardPanel.FieldTitle fieldTitle :
                                                    UpdateCardPanel.FieldTitle.values()) {
                                                if (fieldTitle.getTitle() == "Title") {
                                                    newTitle = cardUpdatePane.getFieldText(fieldTitle);
                                                } else if (fieldTitle.getTitle() == "Description") {
                                                    newDescription = cardUpdatePane.getFieldText(fieldTitle);
                                                } else {
                                                    newImage = cardUpdatePane.getFieldText(fieldTitle);
                                                }


                                        }

                                        card.title = newTitle;
                                        card.description = newDescription;
                                        card.image = newImage;
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
                                }

                            }


            }

        }

    }

      public void deleteCard() throws IOException, ParseException {
          ChooseCardPanel chooseCardPane = new ChooseCardPanel();
          String CardToRemove = "";
          String EnteredTitle = "";
          int result = JOptionPane.showConfirmDialog(null, chooseCardPane,
                  "Choose the card you want to delete", JOptionPane.OK_CANCEL_OPTION,
                  JOptionPane.PLAIN_MESSAGE);
          if (result == JOptionPane.OK_OPTION) {
              // TODO: do something with info

              for (ChooseCardPanel.FieldTitle fieldTitle :
                      ChooseCardPanel.FieldTitle.values()) {
                  EnteredTitle = chooseCardPane.getFieldText(fieldTitle);

              }
              CardToRemove += EnteredTitle;
              cards = showCards(label,panel,constraints);
              if (!EnteredTitle.equals("")) {
                  for (Card card : cards) {
                      if(card.title.equals(CardToRemove)){
                          File file = new File("cards/" + CardToRemove + ".txt");
                          file.delete();
                      }

                      else {
                          System.out.println("There is no such card!");
                      }


              }
          }


    }
}
}

