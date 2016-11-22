package GUI.info_panel.champion_panel;

import GUI.info_panel.BackgroundPanel;
import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.data.MasteryID;
import model.database.stats_structure.entity.MasteryPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * Created by filip on 08/11/2016.
 */
public class MasteryPanel extends JPanel {
    private JPanel ferocity = new JPanel(new GridLayout(6,3));
    private JPanel cunning = new JPanel(new GridLayout(6,3));
    private JPanel resolve = new JPanel(new GridLayout(6,3));

    public MasteryPanel(MasteryPage masteryPage, Controller controller, ActionListener listener){

        JButton button = new JButton("X");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
        List<MasteryID> listID = masteryPage.getMasteries();
        int j = 0;
        for(int i = 0; i < 41; i++){
            if (i == 1 || i == 6 || i==11){
                ferocity.add(new JLabel());
            }
            else if(i == 15 || i == 17 ||i == 20 || i == 25)
                resolve.add(new JLabel());
            else if(i == 28 || i == 33 || i == 38)
                cunning.add(new JLabel());


            if (listID.size() > j && i == listID.get(j).getID()){

                MasteryID masteryID = listID.get(j);
                j++;
                Image image = new ImageIcon(Path.MASTERY_ICONS + controller.getMastery(i).getMasteryData().getImage().getFull()).getImage();
                ImageIcon icon = new ImageIcon(image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH ));
                JLabel iconLabel = new JLabel(String.valueOf(masteryID.getRank()), icon, JLabel.CENTER);
                iconLabel.setHorizontalTextPosition(JLabel.CENTER);
                iconLabel.setVerticalTextPosition(JLabel.BOTTOM);
                iconLabel.setBorder(BorderFactory.createEmptyBorder(0,5,1,5));
                //iconLabel.setMinimumSize(new Dimension(55,55));
                iconLabel.setForeground(Color.YELLOW);
                if (i>26)
                    cunning.add(iconLabel);
                else if (i>13)
                    resolve.add(iconLabel);
                else
                    ferocity.add(iconLabel);
            }
            else {
                Image image = new ImageIcon(Path.MASTERY_ICONS + "gray_" + controller.getMastery(i).getMasteryData().getImage().getFull()).getImage();
                ImageIcon icon = new ImageIcon(image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH ));
                JLabel iconLabel = new JLabel(" ", icon, JLabel.CENTER);
                iconLabel.setHorizontalTextPosition(JLabel.CENTER);
                iconLabel.setVerticalTextPosition(JLabel.BOTTOM);
                iconLabel.setMinimumSize(new Dimension(55,55));
                if (i>26)
                    cunning.add(iconLabel);
                else if (i>13)
                    resolve.add(iconLabel);
                else
                    ferocity.add(iconLabel);
            }

        }
        Image image = new ImageIcon(Path.MASTERY_BACK_2).getImage();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(460, 300,  java.awt.Image.SCALE_SMOOTH ));
        BackgroundPanel b = new BackgroundPanel(icon.getImage());
        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));
        b.add(ferocity);
        b.add(cunning);
        b.add(resolve);
        setLayout(new BorderLayout());
        add(button, BorderLayout.NORTH);
        add(b, BorderLayout.CENTER);

    }


}
