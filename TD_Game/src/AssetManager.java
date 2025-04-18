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
            System.out.println("Current working directory: " + System.getProperty("user.dir"));
            File tileFile = new File("TD_Game/src/Assets/Tiles/Tile21.png");
            System.out.println("Looking for tile at: " + tileFile.getAbsolutePath());
            System.out.println("File exists: " + tileFile.exists());
            
            tileAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile21.png")));
            tileAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile22.png")));
            tileAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile43.png")));
            tileAssets.put(5, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile46.png")));
            tileAssets.put(7, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile48.png")));
            tileAssets.put(8, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile49.png")));
            tileAssets.put(9, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile23.png")));
            tileAssets.put(6, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile55.png")));

        }catch(IOException e ){
            System.err.println("Error loading tile assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadEnemyAssets(){
        try {
            enemyAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E1.png")));
            enemyAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E2.png")));
            enemyAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E3.png")));
            enemyAssets.put(3, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E4.png")));


        }catch(IOException e ){
            System.err.println("Error loading enemy assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadTowerAssets (){
        try{
            towerAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/P1.png")));
            towerAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/P2.png")));
            towerAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/P3.png")));
            towerAssets.put(3, ImageIO.read(new File("TD_Game/src/Assets/Tiles/P4.png")));
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
