import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class MapPanel extends JPanel {

    private int [][] map ;
    private AssetManager assetmanager ;
    private EnemyManager enemyManager;
    private List<Tower> towers;
    private List<Projectile> projectiles;
    private final int Tile_SiZE = 85 ;
    private ScoreTracker scoreTracker;

    public MapPanel(int [][] map, AssetManager assetmanager,EnemyManager enemyManager,List<Tower> towers, List<Projectile>projectiles,ScoreTracker sc){
        this.map = map;
        this.assetmanager = assetmanager;
        this.enemyManager = enemyManager;
        this.towers = towers;
        this.projectiles = projectiles;
        this.scoreTracker = sc ;
        setPreferredSize(new Dimension(map[0].length *Tile_SiZE,map.length*Tile_SiZE));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent e ){
                int tilex = e.getX() / Tile_SiZE;
                int tiley = e.getY() / Tile_SiZE;
                if (map[tiley][tilex] == 5 ){
                    handleTowerPlacement (tilex,tiley);
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mousePressed (MouseEvent e ){
                int mouseX = e.getX();
                int mouseY = e.getY();

                int clickedCol = mouseX / Tile_SiZE;
                int clickedRow = mouseY / Tile_SiZE;

                for (Tower tower : towers){
                    if (tower.getx() == clickedCol && tower.gety() == clickedRow){
                        openUpgradeMenu(tower);
                        break;
                    }
                }
            }
        });
    }
    private void openUpgradeMenu(Tower tower ){
        int result = JOptionPane.showConfirmDialog(this, "Upgrade this Soldier for 50 score?", "Upgrade",JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION){
            if (scoreTracker.get()>= 50){
                scoreTracker.spend(50);
                tower.upgrade();
            }else {
                JOptionPane.showMessageDialog(this, "Not enough score to upgrade!");
            }
        }
    
    }
    private void handleTowerPlacement(int tilex,int tiley){
        String [] options = {"Basic (20)","Sniper (50)"};
        int choice = JOptionPane.showOptionDialog(
            this, "Select a Defender to place : ", " Defender Selection ", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
        if (choice == 0 && scoreTracker.get() >= 20 ){
            towers.add(new BasicTower(tilex,tiley,assetmanager.getTowerSprite(0),assetmanager.getTowerSprite(1)));
            scoreTracker.spend (20);
            map[tiley][tilex] = 0 ;
        }else if (choice == 1 && scoreTracker.get() >= 50 ){
            towers.add(new SniperTower(tilex,tiley,assetmanager.getTowerSprite(2),assetmanager.getTowerSprite(3)));
            scoreTracker.spend(50);
            map[tiley][tilex] = 0 ;
        }else {
            JOptionPane.showMessageDialog(this,"NOT ENOUGH SCORE ! ");
        }
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
        for (Tower tower : towers) {
            BufferedImage sprite = tower.getSprite();
            int px = tower.getx() * Tile_SiZE;
            int py = tower.gety() * Tile_SiZE;
        
            double scale = tower.getScale();
            int scaledWidth = (int)(Tile_SiZE * scale);
            int scaledHeight = (int)(Tile_SiZE * scale);
            int offsetX = (scaledWidth - Tile_SiZE) / 2;
            int offsetY = (scaledHeight - Tile_SiZE) / 2;
        
            g.drawImage(sprite, px - offsetX, py - offsetY, scaledWidth, scaledHeight, null);
        }
        
        for (Enemy enemy : enemyManager.getEnemies()) {
            double scale = enemy.getScale(); 
            
            int px = enemy.getX() * Tile_SiZE;
            int py = enemy.getY() * Tile_SiZE;
        
            int scaledWidth = (int)(Tile_SiZE * scale);
            int scaledHeight = (int)(Tile_SiZE * scale);
        
            int offsetX = (scaledWidth - Tile_SiZE) / 2;
            int offsetY = (scaledHeight - Tile_SiZE) / 2;
        
            g.drawImage(enemy.getSprite(), px - offsetX, py - offsetY, scaledWidth, scaledHeight, null);
        
            int barWidth = Tile_SiZE;
            int barHeight = 5;
            double healthPercent = (double) enemy.getHelth() / enemy.getMaxHealth();
        
            g.setColor(Color.RED);
            g.fillRect(px, py - 5, barWidth, barHeight);
            g.setColor(Color.GREEN);
            g.fillRect(px, py - 5, (int)(barWidth * healthPercent), barHeight);
        }
        
        for (Projectile p : projectiles){
            g.drawImage(assetmanager.getPrSprite(0),p.getX() - 8, p.getY() - 8, 36, 36, null);
        }
        g.drawImage(assetmanager.getUI(0),10,10,120,120,null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.BOLD,15));
        g.drawString(" " + scoreTracker.get() ,29,75);


    }
}
