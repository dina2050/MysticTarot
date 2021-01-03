import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

class AddCardPanel extends JPanel {
    enum FieldTitle {
        TITLE("Title"), DESCRIPTION("Description"), IMAGE("Image");
        private String title;

        private FieldTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    };

    private Map<FieldTitle, JTextField> fieldMap = new HashMap<FieldTitle, JTextField>();
    UpdateCardPanel c = new UpdateCardPanel();
    public AddCardPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("New Card"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        GridBagConstraints gbc;
        for (int i = 0; i < FieldTitle.values().length; i++) {
            FieldTitle fieldTitle = FieldTitle.values()[i];
            gbc = c.createGbc(0, i);
            add(new JLabel(fieldTitle.getTitle() + ":", JLabel.LEFT), gbc);
            gbc = c.createGbc(1, i);
            JTextField textField = new JTextField(10);
            add(textField, gbc);

            fieldMap.put(fieldTitle, textField);
        }
    }



    public String getFieldText(FieldTitle fieldTitle) {
        return fieldMap.get(fieldTitle).getText();
    }

}