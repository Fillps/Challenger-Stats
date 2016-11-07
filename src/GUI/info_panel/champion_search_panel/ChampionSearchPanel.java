package GUI.info_panel.champion_search_panel;

import controller.Controller;
import model.database.data_structure.TrieMapExtended;
import model.database.stats_structure.entity.Champion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

/**
 * Created by filip on 06/11/2016.
 */
public class ChampionSearchPanel extends JPanel{

    private ChampionButtonsPanel championButtonsPanel = new ChampionButtonsPanel();
    private JScrollPane championButtonsScrollPanel = new JScrollPane();
    private TextFieldPanel textFieldPanel = new TextFieldPanel();


    private Controller controller;

    public ChampionSearchPanel(){

        setLayout(new BorderLayout());

        add(textFieldPanel, BorderLayout.NORTH);

        championButtonsScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        championButtonsScrollPanel.setViewportView(championButtonsPanel);

        add(championButtonsScrollPanel, BorderLayout.CENTER);

        championButtonsPanel.addAllButtons();
        championButtonsScrollPanel.validate();
        championButtonsScrollPanel.repaint();
        validate();
        repaint();


        championButtonsPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                championButtonsPanel.removeAllButtons();
                handleChampionsNames(textFieldPanel.getText());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        textFieldPanel.setTextFieldListener(new TextFieldListener() {
            @Override
            public void removeUpdate(String text) {
                if (text.isEmpty() || text.equals("Search Champion...")) {
                    championButtonsPanel.addAllButtons();
                    championButtonsScrollPanel.validate();
                    championButtonsScrollPanel.repaint();
                    validate();
                    repaint();
                }
                else {
                    championButtonsPanel.removeAllButtons();
                    handleChampionsNames(text);
                }
            }
            @Override
            public void insertUpdate(String text) {
                if (text.equals("Search Champion...")){
                    championButtonsPanel.addAllButtons();
                    championButtonsScrollPanel.validate();
                    championButtonsScrollPanel.repaint();
                    validate();
                    repaint();
                }
                else {
                    championButtonsPanel.removeAllButtons();
                    handleChampionsNames(text);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Search Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));






    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    private void handleChampionsNames(String text){
        List<Integer> list = controller.getTrieChampionsNames().getListValuesOnSubTree(text.toLowerCase());
        for (Integer i : list) {
            championButtonsPanel.addButton(i);
        }
        championButtonsPanel.validate();
        championButtonsPanel.repaint();
        championButtonsScrollPanel.validate();
        championButtonsScrollPanel.repaint();
        validate();
        repaint();
    }

    /*public void setTrieChampionsNames(TrieMapExtended trieChampionsNames) {
        this.trieChampionsNames = trieChampionsNames;
    }*/
}
