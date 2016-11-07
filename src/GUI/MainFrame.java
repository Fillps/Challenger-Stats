package GUI;

import GUI.info_panel.InfoPanel;
import GUI.menu.MenuListener;
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
    private InfoPanel infoPanel = new InfoPanel();

    public MainFrame(String title){
        super(title);

        setLayout(new BorderLayout());
        infoPanel.setController(controller);

        add(menuPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);


        menuPanel.setMenuListener(new MenuListener() {
            @Override
            public void menuEventButton(int buttonIndex) {
                infoPanel.removeAll();
                infoPanel.addComponent(buttonIndex);
                infoPanel.setVisible(false);
                infoPanel.setVisible(true);
            }
        });



        setMinimumSize(new Dimension(500, 400));
        setSize(1240, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
