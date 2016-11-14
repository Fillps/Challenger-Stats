package GUI;

import GUI.info_panel.InfoPanel;
import GUI.menu.MenuPanel;
import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by filip on 06/11/2016.
 */
public class MainFrame extends JFrame {

    private Controller controller = new Controller();
    private MenuPanel menuPanel = new MenuPanel();
    private InfoPanel infoPanel = new InfoPanel(controller);

    public MainFrame(String title){
        super(title);

        setLayout(new BorderLayout());

        add(menuPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);


        menuPanel.setButtonIntegerListener(new ButtonIntegerListener() {
            @Override
            public void ButtonIntegerEvent(int buttonIndex) {
                infoPanel.removeAll();
                infoPanel.addComponent(buttonIndex);
                infoPanel.validate();
                infoPanel.repaint();

            }
        });



        setMinimumSize(new Dimension(500, 400));
        setSize(1335, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
