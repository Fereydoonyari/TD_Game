import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.CardLayout;

public class Game {
    private List<Tower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private AssetManager assetManager = new AssetManager();
    private ScoreTracker scoreTracker = new ScoreTracker();
    private EnemyManager enemyManager;
    private MapPanel mapPanel;
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MenuPanel menuPanel;
    private int[][] selectedMap;

    private final int[][] map1 = {
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
        {0,1,0,1,2,2,2,1,2,2,2,3,2,2,2,5,0},
        {2,2,2,4,2,0,2,5,2,0,2,0,2,8,2,5,1},
        {0,1,2,1,2,1,2,5,2,1,2,1,2,1,2,1,4},
        {1,0,2,0,2,0,2,4,2,3,2,8,2,0,2,5,1},
        {0,1,2,1,2,1,2,1,2,5,2,5,2,1,2,5,0},
        {1,3,2,2,2,8,2,2,2,5,2,2,2,8,2,2,2},
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
    };
    private final int[][] map2 = {
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
        {0,1,0,2,2,2,2,2,2,2,2,2,2,2,2,5,0},
        {2,2,2,2,1,0,1,5,1,0,1,5,1,0,2,0,1},
        {0,1,2,2,5,2,2,2,2,2,2,2,2,1,2,5,4},
        {1,0,2,2,1,2,1,4,5,2,2,2,2,5,2,5,1},
        {0,1,2,2,5,2,0,1,0,1,5,1,0,1,2,1,0},
        {1,3,2,2,1,2,2,2,2,2,2,2,2,2,2,5,1},
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
    };
    public void start() {
        assetManager.loadAssets();
        assetManager.LoadEnemyAssets();
        assetManager.LoadTowerAssets();
        assetManager.LoadUI();
        assetManager.LoadPrAssets();

        frame = new JFrame("TOWER DEFENSE GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        menuPanel = new MenuPanel(this);
        mainPanel.add(menuPanel, "Menu");

        frame.add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        cardLayout.show(mainPanel, "Menu");
    }

    public void startGameWithMap(int mapIndex) {
        if (mapIndex == 1) {
            selectedMap = map1;
        } else {
            selectedMap = map2;
        }
        towers = new ArrayList<>();
        projectiles = new ArrayList<>();
        scoreTracker = new ScoreTracker();
        enemyManager = new EnemyManager(mapIndex,assetManager);
        mapPanel = new MapPanel(selectedMap, assetManager, enemyManager, towers, projectiles, scoreTracker);
        mainPanel.add(mapPanel, "Game");
        frame.pack();
        cardLayout.show(mainPanel, "Game");
        startGameLoop();
    }

    private void startGameLoop() {
        new Timer(18, e -> {
            enemyManager.update();
            for (Tower t : towers)
                t.update(enemyManager.getEnemies(), projectiles);
            projectiles.removeIf(p -> {
                p.update();
                return !p.isActive();
            });
            Iterator<Enemy> it = enemyManager.getEnemies().iterator();
            while (it.hasNext()) {
                Enemy enemy = it.next();
                if (enemy.reachedEnd()) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "GAME OVER!");
                    System.exit(0);
                }
                if (enemy.isDead()) {
                    scoreTracker.add(enemy.getScoreValue());
                    it.remove();
                }
            }
            mapPanel.repaint();
        }).start();
    }
}
