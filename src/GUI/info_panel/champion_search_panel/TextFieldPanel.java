package GUI.info_panel.champion_search_panel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by filip on 06/11/2016.
 */
public class TextFieldPanel extends JPanel {
    private JTextField textField = new JTextField("Search Champion...", 20);

    private TextFieldListener textFieldListener;

    public TextFieldPanel(){
        add(textField, BorderLayout.CENTER);

        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                textFieldListener.insertUpdate(textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                textFieldListener.removeUpdate(textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Plain text components don't fire these events.
            }
        });

        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Search Champion..."))
                    textField.setText(""); // Empty the text field when it receives focus
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty())
                    textField.setText("Search Champion...");
            }
        });
    }

    public void setTextFieldListener(TextFieldListener textFieldListener) {
        this.textFieldListener = textFieldListener;
    }

    public String getText(){
        return textField.getText();
    }
}
