
import GUI.MainFrame;
import model.database.data_structure.TrieExtended;
import model.database.data_structure.TrieMapExtended;
import model.database.files.WriterAndReader;
import model.riot_api.StaticData;
import tests.GUITests;
import tests.StaticDataTests;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by filip on 04/11/2016.
 */
public class ChallengerStats {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame("Challenger Stats");

            }
        });
    }
}
