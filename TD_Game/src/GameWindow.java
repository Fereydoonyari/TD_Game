
import javax.swing.*;


public class GameWindow {
    public static void main (String [] args){
        int [][] map = {
            {1,0,1,0,1,0,1,8,1,4,1,0,1,0,1,3,6},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,3,1,0,1,0,1,0,1,0,1},
            {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
            {0,1,0,1,0,1,3,1,2,1,0,9,9,9,2,7,3}
        };
        AssetManager assetManager = new AssetManager();
        assetManager.loadAssets();
        assetManager.LoadEnemyAssets();
        EnemyManager enemyManager = new EnemyManager(assetManager);
        MapPanel mapPanel = new MapPanel(map,assetManager,enemyManager);
        JFrame frame = new JFrame("TOWER DEFENCE GAME ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.add(new MapPanel(map,assetManager));


        new Timer(16,e ->{
            frame.add(mapPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            enemyManager.update();
            mapPanel.repaint();
        }).start();


    }

}
