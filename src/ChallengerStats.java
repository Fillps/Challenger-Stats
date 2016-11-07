
import GUI.MainFrame;
import controller.Controller;
import model.database.data_structure.TrieExtended;
import model.database.data_structure.TrieMapExtended;
import model.database.files.WriterAndReader;
import model.riot_api.StaticData;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import tests.GUITests;
import tests.StaticDataTests;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by filip on 04/11/2016.
 */
public class ChallengerStats {

    public static void main(String[] args) {


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
