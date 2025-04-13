
import javax.swing.*;


public class GameWindow {
    public static void main (String [] args){
        int [][] map = {
            {1,1,2,1,1,2},
            {1,0,2,0,1,2},
            {1,0,2,0,1,2},
            {1,0,2,0,1,2},
            {1,1,2,1,1,2}
        };
        AssetManager assetManager = new AssetManager();
        assetManager.loadAssets();
        JFrame frame = new JFrame("TOWER DEFENCE GAME ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MapPanel(map,assetManager));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
