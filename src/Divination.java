import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



public class Divination {
    CardPanel c = new CardPanel();
    public void FortuneTeller(List<Card> cards, JPanel panel, List<Icon> icons) {
        for (int i = 0; i < cards.size(); i++) {
            int j = 1;
            j += i;
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(cards.get(i).image));
            panel.add(label, c.createGbc3(j, 0));
            int finalI = i;
            final Boolean[] isClicked = {false};
            int finalJ = j;
            List<JLabel> result = new ArrayList<>();
                label.addMouseListener(
                        new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                Icon icon = label.getIcon();
                                icons.add(icon);
                                JLabel TextLabel = new JLabel();
                                TextLabel.setText("<html><p style=\"width:200px\">" + cards.get(finalI).description + "</p></html>");
                                panel.remove(label);
                                panel.add(TextLabel);

                            }

                        }
                );


        }

    }

}