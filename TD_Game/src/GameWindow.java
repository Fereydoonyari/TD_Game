
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;


public class GameWindow {
    public static void main (String [] args){
        int [][] map = {

            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,8,1,0,1},
            {0,1,0,1,0,1,8,1,0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,3,1,0,1,8,1,0,1,0,1},
            {0,1,0,1,0,1,0,1,8,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,8,1,0,1,0,1,0,1,8,1,0,1},
            {9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}
        };
        List<Tower> towers = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        AssetManager assetManager = new AssetManager();
        assetManager.loadAssets();
        assetManager.LoadEnemyAssets();
        assetManager.LoadTowerAssets();
        EnemyManager enemyManager = new EnemyManager(assetManager);
        MapPanel mapPanel = new MapPanel(map,assetManager,enemyManager);
        JFrame frame = new JFrame("TOWER DEFENCE GAME ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        towers.add(new BasicTower(3, 1, assetManager.getTowerSprite(0)));

        new Timer(16,e ->{
            frame.add(mapPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            enemyManager.update();
            for (Tower t : towers) t.update(enemyManager.getEnemies(),projectiles);
            projectiles.removeIf(p -> {
                p.update();
                return !p.isActive();
            });
            mapPanel.repaint();
        }).start();


    }

}
