package GUI.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by filip on 06/11/2016.
 */
public class MenuButton extends JButton {
    public MenuButton(String title){
        super(title);
        Dimension dim = getPreferredSize();
        dim.width = 140;
        setPreferredSize(dim);
        setMinimumSize(dim);

    }
}