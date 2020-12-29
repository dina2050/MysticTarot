import org.json.simple.parser.ParseException;
import javax.swing.*;
import javax.swing.Action;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.HashMap;


public class UserInterface {

    public static void display() throws IOException, ParseException {
    JFrame window = new JFrame("Mystic Tarot");
    window.setSize(600,300);
    JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
       /* GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.fill = GridBagConstraints.BOTH;*/

        window.setContentPane(new JScrollPane(panel));
        String url ="images/cardimage.jpg";
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar MyMenu = new JMenuBar();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.gridheight=1;
        constraints.gridwidth=1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
    panel.add(MyMenu,constraints);
    MyMenu.setBounds(0,0,600,30);
    JMenu ActionMenu = new JMenu("Action");
    MyMenu.add(ActionMenu);
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


        Deck deck = new Deck();
        HashMap<Integer, Action> MenuItems = new HashMap<Integer, Action>();
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel();
                try {
                    deck.showCards(label, panel, constraints);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
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
                    deck.updateCard();
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
