import GUI.MainFrame;

import javax.swing.*;



/**
 * Created by filip on 04/11/2016.
 */
public class ChallengerStats {

    public static void main(String[] args){

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame("Challenger Stats");

            }
        });
    }

}

