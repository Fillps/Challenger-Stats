package GUI.menu;

import GUI.ButtonIntegerListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by filip on 06/11/2016.
 */
public class MenuPanel extends JPanel {
    private MenuButton championSearchButton = new MenuButton("Search Champion");
    private MenuButton masterysButton = new MenuButton("Masterys");
    private MenuButton championsButton = new MenuButton("Champions");
    private MenuButton runesButton = new MenuButton("Runes");
    private MenuButton itemsButton = new MenuButton("Items");

    private ButtonIntegerListener buttonIntegerListener;

    public MenuPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 170;
        setPreferredSize(dim);
        setMinimumSize(dim);

        championSearchButton.setMnemonic(KeyEvent.VK_S);
        championsButton.setMnemonic(KeyEvent.VK_C);
        runesButton.setMnemonic(KeyEvent.VK_R);
        masterysButton.setMnemonic(KeyEvent.VK_M);
        itemsButton.setMnemonic(KeyEvent.VK_I);

        add(championSearchButton, BorderLayout.CENTER, 0);
        add(championsButton, BorderLayout.CENTER, 1);
        add(runesButton, BorderLayout.CENTER, 2);
        add(masterysButton, BorderLayout.CENTER, 3);
        add(itemsButton, BorderLayout.CENTER, 4);


        championSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(0);
            }
        });
        championsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(1);
            }
        });

        runesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(2);
            }
        });

        masterysButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(3);
            }
        });

        itemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(4);
            }
        });
        Border innerBorder = BorderFactory.createTitledBorder("menu");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    public void setButtonIntegerListener(ButtonIntegerListener buttonIntegerListener){
        this.buttonIntegerListener = buttonIntegerListener;
    }
}


