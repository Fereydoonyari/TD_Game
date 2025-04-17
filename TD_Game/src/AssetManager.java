import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class AssetManager {
    private HashMap<Integer,BufferedImage> tileAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> enemyAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> towerAssets = new HashMap<>();


    public void loadAssets(){
        try{
            tileAssets.put(0, ImageIO.read(new File("src/Assets/Tiles/Tile41.png")));
            tileAssets.put(1, ImageIO.read(new File("src/Assets/Tiles/Tile42.png")));
            tileAssets.put(2, ImageIO.read(new File("src/Assets/Tiles/Tile43.png")));
            tileAssets.put(3, ImageIO.read(new File("src/Assets/Tiles/Tile44.png")));
            tileAssets.put(4, ImageIO.read(new File("src/Assets/Tiles/Tile45.png")));
            tileAssets.put(5, ImageIO.read(new File("src/Assets/Tiles/Tile46.png")));
            tileAssets.put(7, ImageIO.read(new File("src/Assets/Tiles/Tile48.png")));
            tileAssets.put(8, ImageIO.read(new File("src/Assets/Tiles/Tile49.png")));
            tileAssets.put(9, ImageIO.read(new File("src/Assets/Tiles/Tile53.png")));


        }catch(IOException e ){
            System.err.println("Error loading tile assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadEnemyAssets(){
        try {
            enemyAssets.put(0, ImageIO.read(new File("src/Assets/Tiles/E5.png")));
            enemyAssets.put(1, ImageIO.read(new File("src/Assets/Tiles/E3.png")));

        }catch(IOException e ){
            System.err.println("Error loading enemy assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadTowerAssets (){
        try{
            towerAssets.put(0, ImageIO.read(new File("src/Assets/Tiles/Tile47.png")));

        }catch(IOException e ){
            System.err.println("Error loading tower assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public BufferedImage getTileAsset(int tileId){
        return tileAssets.get(tileId);
    }
    public BufferedImage getEnemySprite (int Enemyid ){
        return enemyAssets.get(Enemyid);
    }
    public BufferedImage getTowerSprite(int towerId){
        return towerAssets.get(towerId);
    }

}
