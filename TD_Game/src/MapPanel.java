import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class MapPanel extends JPanel {
    private int [][] map ;
    private AssetManager assetmanager ;
    private final int Tile_SiZE = 64 ;

    public MapPanel(int [][] map, AssetManager assetmanager){
        this.map = map;
        this.assetmanager = assetmanager;
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
    }
}
