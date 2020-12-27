import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInterface {
    public static void display() throws IOException, ParseException {
    JFrame window = new JFrame("Mystic Tarot");
    window.setSize(600,300);
    JPanel panel = new JPanel();
    window.setContentPane(panel);
        String url ="cards/images/cardimage.jpg";

    //panel.setLayout(new GridLayout(1,1));
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar MyMenu = new JMenuBar();

    panel.add(MyMenu);
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
        JTextArea textArea = new JTextArea(50, 10);
        panel.add(textArea);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);
        PrintStream standardOut = System.out;
        PrintStream standardErr = System.err;
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;



        constraints.gridx = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        panel.add(new JScrollPane(textArea), constraints);
        HashMap<Integer, Action> MenuItems = new HashMap<Integer, Action>();
        Action action = new Deck();
        MenuItems.put(1, action);
        MenuItems.put(2, action);
        MenuItems.put(3, action);
        MenuItems.put(4, action);
      for (Map.Entry<Integer, Action> entry : MenuItems.entrySet()) {
            for (int i = 0 ; i <  ActionMenu.getItemCount(); i++) {
                JMenuItem item = ActionMenu.getItem(i);
                Integer key = entry.getKey();
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ev) {
                            if (item.getMnemonic() == key){
                               /* MenuItems.get(item.getMnemonic());
                                System.out.println(MenuItems.get(item.getMnemonic()));*/
                                switch (key){
                                    case 1:
                                        try {
                                            action.showCards();
                                        } catch (IOException | ParseException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 2:
                                        try {
                                            action.addCard();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 3:
                                        try {
                                            action.updateCard();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 4:
                                        try {
                                            action.deleteCard();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                }
                            }

                        }
                    });

            }
        }

        window.setVisible(true);
    }

}

 /*for(int i=0; i<22; i++) {
        JLabel label = new JLabel();
        panel.add(label);
        label.setIcon(new ImageIcon(url));
        }*/