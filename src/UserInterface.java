import org.json.simple.parser.ParseException;
import javax.swing.*;
import javax.swing.Action;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserInterface {

    public static void display() throws IOException, ParseException {
    JFrame window = new JFrame("Mystic Tarot");
    window.setSize(600,300);
    JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        window.setContentPane(new JScrollPane(panel));
        String url ="images/cardimage.jpg";
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar MyMenu = new JMenuBar();
    Deck deck = new Deck();

        UpdateCardPanel c = new UpdateCardPanel();

    panel.add(MyMenu,c.createGbc2(0,0));

    List <String> titles = new ArrayList<String>();
    List <Card> cards = deck.showCards();
        for (Card card : cards) {
            titles.add(card.title);
        }
    StringSearchable searchable = new StringSearchable(titles);
    AutocompleteJComboBox combo = new AutocompleteJComboBox(searchable);
        JLabel label = new JLabel();

 combo.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Object value = combo.getSelectedItem();
                            String image = "";
                            String description = "";
                            for (Card card : cards) {
                                if (value == card.title) {
                                    image = card.image;
                                    description = card.description;
                                }

                            }

                            label.setIcon(new ImageIcon(image));
                            label.setText("<html><p style=\"width:500px\">"+description+"</p></html>");
                            panel.add(label, c.createGbc2(1,1));


                        }
                    });
    MyMenu.setBounds(0,0,600,30);
    JMenu ActionMenu = new JMenu("Action");
    MyMenu.add(ActionMenu);
    MyMenu.add(combo);
    JMenuItem ShowCards = new JMenuItem("Show cards");
    JMenuItem AddCard = new JMenuItem("Add a card");
    JMenuItem UpdateCard = new JMenuItem("Update a card");
    JMenuItem DeleteCard = new JMenuItem("Delete a card");
    ActionMenu.add(ShowCards);
    ActionMenu.add(AddCard);
    ActionMenu.add(UpdateCard);
    ActionMenu.add(DeleteCard);
    ShowCards.setMnemonic(1);
    AddCard.setMnemonic(2);
    UpdateCard.setMnemonic(3);
    DeleteCard.setMnemonic(4);


        GridBagConstraints constraints = new GridBagConstraints();
        HashMap<Integer, Action> MenuItems = new HashMap<Integer, Action>();
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0; i< cards.size(); i++) {
                    JLabel CardLabel = new JLabel();
                    CardLabel.setIcon(new ImageIcon(cards.get(i).image));
                    CardLabel.setText("<html><p style=\"width:500px\">"+cards.get(i).description+"</p></html>");
                    int j=1;
                    j+=i;
                    constraints.gridy = j;
                    constraints.gridx = 0;
                    constraints.weightx = 0.5;
                    constraints.weighty = 0.5;
                    constraints.gridheight=1;
                    constraints.gridwidth=1;
                    Border border = LineBorder.createGrayLineBorder();
                    CardLabel.setBorder(border);
                    constraints.anchor= GridBagConstraints.WEST;
                    panel.add(CardLabel, constraints);
                }

            }
        };
        Action action1 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            deck.addCard();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                });
            }
        };

        Action action2 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    deck.updateCard(cards);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

            }
        };

        Action action3 = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    deck.deleteCard();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

            }
        };
        MenuItems.put(1, action);
        MenuItems.put(2, action1);
        MenuItems.put(3, action2);
        MenuItems.put(4, action3);
        JMenuItem item = ActionMenu.getItem(0);
        item.addActionListener(MenuItems.get(item.getMnemonic()));
        JMenuItem item1 = ActionMenu.getItem(1);
        item1.addActionListener(MenuItems.get(item1.getMnemonic()));
        JMenuItem item2 = ActionMenu.getItem(2);
        item2.addActionListener(MenuItems.get(item2.getMnemonic()));
        JMenuItem item3 = ActionMenu.getItem(3);
        item3.addActionListener(MenuItems.get(item3.getMnemonic()));
        window.setVisible(true);
    }


}
