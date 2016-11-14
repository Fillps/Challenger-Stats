package GUI.info_panel.champion_panel;

import GUI.Utils;
import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.data.OverallStats;
import model.database.stats_structure.data.Stats;
import model.database.stats_structure.entity.Champion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by filip on 06/11/2016.
 */
public class ChampionPanel extends JPanel{

    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel champInfoPanel;
    private JPanel firstPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;

    private LoadingImagePanel loadingImgPanel;
    private JLabel name;
    private JLabel championTitle;
    private StatsPanel statsPanel;
    private ItemPanel itemPanel;
    private SummonerSpellPanel summonerSpellPanel;
    private RunePanel runeWinRatePanel;
    private RunePanel runePlayRatePanel;
    private MasteryPanel masteryWinRatePanel;
    private MasteryPanel masteryPlayRatePanel;
    private MasteryTable masteryTable;
    private RuneTable runeTable;

    public ChampionPanel(int id, Controller controller){

        Champion champion = controller.getChampion(id);
        String path = champion.getChampionData().getImage().getFull();
        path =path.substring(0,path.length()-4)+ "_0.jpg";
        loadingImgPanel = new LoadingImagePanel( Path.CHAMPION_LOADING + path);

        name = new JLabel(champion.getChampionData().getName(), SwingConstants.LEFT);
        name.setFont(new Font("Arial", Font.PLAIN, 30));
        name.setAlignmentX( Component.LEFT_ALIGNMENT );

        championTitle = new JLabel(champion.getChampionData().getTitle(),SwingConstants.LEFT);
        championTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        championTitle.setAlignmentX( Component.LEFT_ALIGNMENT );

        OverallStats overallStats = controller.getOverallStats();
        Stats stats = champion.getStats();

        statsPanel = new StatsPanel(stats, overallStats);

        summonerSpellPanel = new SummonerSpellPanel(controller, champion.getID());

        itemPanel = new ItemPanel(controller, champion.getID());

        masteryTable = new MasteryTable(controller, champion.getID());

        runeTable = new RuneTable(controller, champion.getID());

        setLayout(new BorderLayout());

        champInfoPanel = new JPanel();
        champInfoPanel.setLayout(new BoxLayout(champInfoPanel,BoxLayout.Y_AXIS));
        champInfoPanel.add(name);
        champInfoPanel.add(championTitle);
        champInfoPanel.add(loadingImgPanel);

        name.setAlignmentX( Component.CENTER_ALIGNMENT );
        championTitle.setAlignmentX( Component.CENTER_ALIGNMENT );


        firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.add(champInfoPanel);
        firstPanel.add(statsPanel);


        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(summonerSpellPanel);
        rightPanel.add(itemPanel);
        rightPanel.add(masteryTable);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(firstPanel);
        leftPanel.add(runeTable);

        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(Path.CHAMPION_SPLASH + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rounded = Utils.makeRoundedCorner(myPicture, 20);

        JLabel picLabel = new JLabel(new ImageIcon(rounded));


        Border picinnerBorder = BorderFactory.createTitledBorder(" ");
        Border picouterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        picLabel.setBorder(BorderFactory.createCompoundBorder(picouterBorder, picinnerBorder));


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        //mainPanel.add(picLabel, BorderLayout.AFTER_LAST_LINE);

        JScrollPane scrollPane = new JScrollPane();

        champInfoPanel.setMaximumSize(new Dimension(300, 550));
        statsPanel.setMaximumSize(new Dimension(300, 565));

        itemPanel.setMaximumSize(new Dimension(1000,600));
        summonerSpellPanel.setMaximumSize(new Dimension(1000, 310));

        //leftPanel.setMaximumSize(dim);
        //rightPanel.setMaximumSize(dim);
        //mainPanel.setMaximumSize(dim);

        //runeTable.setMaximumSize(new Dimension(1000, 555));
        masteryTable.setMaximumSize(new Dimension(1000, 643));



        scrollPane.setViewportView(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);


        Border innerBorder = BorderFactory.createTitledBorder("Champion");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));



    }
}
