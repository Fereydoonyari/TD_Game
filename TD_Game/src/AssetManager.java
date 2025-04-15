import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


public class AssetManager {
    private HashMap<Integer,BufferedImage> tileAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> enemyAssets = new HashMap<>();

    public void loadAssets(){
        try{
            tileAssets.put(0, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile41.png")));
            tileAssets.put(1, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile42.png")));
            tileAssets.put(2, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile43.png")));
            tileAssets.put(3, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile44.png")));
            tileAssets.put(4, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile45.png")));
            tileAssets.put(5, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile46.png")));
            tileAssets.put(6, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile47.png")));
            tileAssets.put(7, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile48.png")));
            tileAssets.put(8, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile49.png")));
            tileAssets.put(9, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile48.png")));




        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    public void LoadEnemyAssets(){
        try {
            enemyAssets.put(1, ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/E5.png")));
            enemyAssets.put(2, ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/E3.png")));

        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    public BufferedImage getTileAsset(int tileId){
        return tileAssets.get(tileId);
    }
    public BufferedImage getEnemySprite (int Enemyid ){
        return enemyAssets.get(Enemyid);
    }

}
