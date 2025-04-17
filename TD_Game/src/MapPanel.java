import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class MapPanel extends JPanel {
    private int [][] map ;
    private AssetManager assetmanager ;
    private EnemyManager enemyManager;
    private final int Tile_SiZE = 85 ;

    public MapPanel(int [][] map, AssetManager assetmanager,EnemyManager enemyManager){
        this.map = map;
        this.assetmanager = assetmanager;
        this.enemyManager = enemyManager;
        setPreferredSize(new Dimension(map[0].length *Tile_SiZE,map.length*Tile_SiZE));
    }
    @Override
    protected void paintComponent(Graphics g ){
        super.paintComponent(g);
        for (int y = 0 ; y < map.length ; y++){
            for (int x = 0 ; x < map[0].length;x++){
                int tileId = map[y][x] ;
                BufferedImage img = assetmanager.getTileAsset(tileId);
                g.drawImage(img, x*Tile_SiZE, y*Tile_SiZE,Tile_SiZE,Tile_SiZE,null);
            }
        }
        for (Enemy enemy : enemyManager.getEnemies()){
            int px = enemy.getX() * Tile_SiZE ;
            int py = enemy.getY() * Tile_SiZE ;

            g.drawImage(enemy.getSprite(), px,py,Tile_SiZE,Tile_SiZE,null);
            int bandwith = Tile_SiZE ;
            int barheight = 5 ;
            double healthPercent = (double) enemy.getHelth() / enemy.getMaxHealth();
            g.setColor(Color.RED);
            g.fillRect(px, py - 5 , bandwith, barheight);
            g.setColor(Color.GREEN);
            g.fillRect(px, py -5 , (int)(bandwith * healthPercent) , barheight);
        }
    }
}
