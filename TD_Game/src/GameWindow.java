import java.util.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class GameWindow {

    public static void main(String[] args) {
        int[][] map = {
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
            {0,1,0,1,2,2,2,1,0,1,0,1,0,1,0,1,0},
            {2,2,2,0,2,0,2,0,1,0,1,0,1,8,1,0,1},
            {0,1,2,1,2,1,2,1,0,1,0,1,0,1,0,1,0},
            {1,0,2,0,2,0,2,0,1,0,1,8,1,0,1,0,1},
            {0,1,2,1,2,1,2,1,8,1,0,1,0,1,0,1,0},
            {1,0,2,2,2,8,2,2,2,0,1,0,1,8,1,0,1},
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
        };
        List<Point> path = new ArrayList<>();
        path.add(new Point(0, 3));
        path.add(new Point(1, 3));
        path.add(new Point(2, 3));
        path.add(new Point(3, 3));
        
        List<Tower> towers = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        AssetManager assetManager = new AssetManager();
        assetManager.loadAssets();
        assetManager.LoadEnemyAssets();
        assetManager.LoadTowerAssets();
        assetManager.LoadUI();

        ScoreTracker scoreTracker = new ScoreTracker();
        EnemyManager enemyManager = new EnemyManager(assetManager);

        MapPanel mapPanel = new MapPanel(map, assetManager, enemyManager, towers, projectiles, scoreTracker);

        JFrame frame = new JFrame("TOWER DEFENCE GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(mapPanel); // ✅ Only add once
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add some test towers
        towers.add(new BasicTower(3, 2, assetManager.getTowerSprite(1)));
        towers.add(new BasicTower(3, 3, assetManager.getTowerSprite(0)));
        towers.add(new BasicTower(3, 4, assetManager.getTowerSprite(2)));
        towers.add(new BasicTower(3, 5, assetManager.getTowerSprite(3)));

        // Game loop
        new Timer(16, e -> {
            enemyManager.update();

            // Update towers (they shoot)
            for (Tower t : towers)
                t.update(enemyManager.getEnemies(), projectiles);

            // Update projectiles and remove inactive ones
            projectiles.removeIf(p -> {
                p.update();
                return !p.isActive();
            });

            // Remove dead enemies & add score
            Iterator<Enemy> it = enemyManager.getEnemies().iterator();
            while (it.hasNext()) {
                Enemy enemy = it.next();
                if (enemy.reachedEnd()){
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null,"GAME OVER ! ");
                    System.exit(0);
                }
                if (enemy.isDead()) {
                    scoreTracker.add(enemy.getScoreValue());
                    it.remove();
                }
            }

            // Repaint UI
            mapPanel.repaint();
        }).start();
    }
}
