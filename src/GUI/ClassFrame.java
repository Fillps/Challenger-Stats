package GUI;

import model.database.data_structure.TrieExtended;
import model.database.files.WriterAndReader;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;


import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ClassFrame extends JFrame {


    private static final long serialVersionUID = 2451829341034438685L;

    //public static JButton inputButton = new JButton("Send");
    public static JTextField editTextArea = new JTextField("Type Here!");
    public static JTextArea uneditTextArea = new JTextArea();

    // Your String, defined here and usable throughout the class


    public ClassFrame(String title) {
        super(title);
        // Learn to indent your code properly so that it's more readable to both you
        // and others

        //SET LAYOUT MANAGER (How it arranges components)
        setLayout(new BorderLayout());
        //////CREATE SWING COMPONENTS////////////
        //OUTPUT TEXT AREA
        uneditTextArea.setEditable(false);
        uneditTextArea.setBackground(Color.LIGHT_GRAY);
        //INPUT TEXT AREA
        editTextArea.setBackground(Color.WHITE);
        editTextArea.setForeground(Color.BLACK);

        //SET CONTENT PANE
        Container c = getContentPane();

        //ADD COMPONENTS TO CONTENT PANE
        c.add(uneditTextArea, BorderLayout.CENTER);
        c.add(editTextArea, BorderLayout.NORTH);
        //c.add(inputButton, BorderLayout.WEST);

        TrieExtended trie = WriterAndReader.read("arquivos/TrieChampionsNames.ser");


        ClassFrame.editTextArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                uneditTextArea.setText("");
                List<String> list = handleChampionsNames(trie, editTextArea.getText());
                for (String name : list)
                    uneditTextArea.append(name + "\n");
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                uneditTextArea.setText("");
                if (!editTextArea.getText().isEmpty()) {
                    List<String> list = handleChampionsNames(trie, editTextArea.getText());
                    for (String name : list)
                        uneditTextArea.append(name + "\n");

                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Plain text components don't fire these events.
            }
        });

        ClassFrame.editTextArea.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if (editTextArea.getText().equals("Type Here!"))
                    editTextArea.setText(""); // Empty the text field when it receives focus
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (editTextArea.getText().isEmpty())
                    editTextArea.setText("Type Here!");
            }



        });



    }

    private List<String> handleChampionsNames(TrieExtended trie, String text){
        text = text.toLowerCase();
        int index = text.indexOf(' ');
        int index2 = text.indexOf('\'');
        char[] chars = text.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        if (index > 0 && index+1 < text.length())
            chars[index+1] = Character.toUpperCase(chars[index+1]);
        if (index2 > 0 && index2+1 < text.length())
            chars[index2+1] = Character.toUpperCase(chars[index2+1]);
        text = String.valueOf(chars);
        List<String> list = trie.getListSubTree(text);
        Collections.sort(list);
        return list;
    }

}

