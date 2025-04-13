import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;


public class AssetManager {
    private HashMap<Integer,BufferedImage> tileAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> enemyAssets = new HashMap<>();

    public void loadAssets(){
        try{
            tileAssets.put(0, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile11.png")));
            tileAssets.put(1, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile6.png")));
            tileAssets.put(2, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile5.png")));
            tileAssets.put(3, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile4.png")));


        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    public void LoadEnemyAssets(){
        try {
            enemyAssets.put(0, ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/E2.png")));
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
