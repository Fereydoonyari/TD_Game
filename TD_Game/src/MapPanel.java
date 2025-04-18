import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class MapPanel extends JPanel {
    private int [][] map ;
    private AssetManager assetmanager ;
    private EnemyManager enemyManager;
    private List<Tower> towers;
    private List<Projectile> projectiles;
    private final int Tile_SiZE = 85 ;

    public MapPanel(int [][] map, AssetManager assetmanager,EnemyManager enemyManager,List<Tower> towers, List<Projectile>projectiles){
        this.map = map;
        this.assetmanager = assetmanager;
        this.enemyManager = enemyManager;
        this.towers = towers;
        this.projectiles = projectiles;
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
        for (Tower tower : towers){
            g.drawImage(tower.getSprite(), tower.getx()*Tile_SiZE,tower.gety()*Tile_SiZE, Tile_SiZE,Tile_SiZE,null);
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
        for (Projectile p : projectiles){
            g.setColor(Color.ORANGE);
            g.fillOval(p.getX(), p.getY(), 10, 10);
        }
    }
}
