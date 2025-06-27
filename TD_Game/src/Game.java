import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Game {
    private List<Tower> towers = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private AssetManager assetManager = new AssetManager();
    private ScoreTracker scoreTracker = new ScoreTracker();
    private EnemyManager enemyManager;
    private MapPanel mapPanel;

    private final int[][] map = {
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
        {0,1,0,1,2,2,2,1,2,2,2,3,2,2,2,5,0},
        {2,2,2,4,2,0,2,5,2,0,2,0,2,8,2,5,1},
        {0,1,2,1,2,1,2,5,2,1,2,1,2,1,2,1,4},
        {1,0,2,0,2,0,2,4,2,3,2,8,2,0,2,5,1},
        {0,1,2,1,2,1,2,1,2,5,2,5,2,1,2,5,0},
        {1,3,2,2,2,8,2,2,2,5,2,2,2,8,2,2,2},
        {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
    };

    public void start() {
        // Load assets
        assetManager.loadAssets();
        assetManager.LoadEnemyAssets();
        assetManager.LoadTowerAssets();
        assetManager.LoadUI();
        assetManager.LoadPrAssets();

        // Setup game components
        enemyManager = new EnemyManager(assetManager);
        mapPanel = new MapPanel(map, assetManager, enemyManager, towers, projectiles, scoreTracker);

        JFrame frame = new JFrame("TOWER DEFENSE GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(mapPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Game loop
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
