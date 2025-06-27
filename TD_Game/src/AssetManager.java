import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class AssetManager {
    private HashMap<Integer,BufferedImage> tileAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> enemyAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> towerAssets = new HashMap<>();
    private HashMap<Integer,BufferedImage> UI = new HashMap<>();
    private HashMap<Integer,BufferedImage> Pr = new HashMap<>();




    public void loadAssets(){
        try{
            tileAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile21.png")));
            tileAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile22.png")));
            tileAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/TileR.png")));
            tileAssets.put(3, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile25.png")));
            tileAssets.put(4, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile26.png")));
            tileAssets.put(5, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile55.png")));
            tileAssets.put(8, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile44.png")));
            tileAssets.put(9, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Tile23.png")));


        }catch(IOException e ){
            System.err.println("Error loading tile assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadEnemyAssets(){
        try {
            enemyAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E12.png")));
            enemyAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E11.png")));
            enemyAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E13.png")));
            enemyAssets.put(3, ImageIO.read(new File("TD_Game/src/Assets/Tiles/E4.png")));
        }catch(IOException e ){
            System.err.println("Error loading enemy assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadTowerAssets (){
        try{
            towerAssets.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Simple1.png")));
            towerAssets.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Simple2.png")));
            towerAssets.put(2, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Sniper1.png")));
            towerAssets.put(3, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Sniper2.png")));
        }catch(IOException e ){
            System.err.println("Error loading tower assets: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void LoadUI(){
        try{
            UI.put(0, ImageIO.read(new File("TD_Game/src/Assets/Tiles/Uscore.png")));
            UI.put(1, ImageIO.read(new File("TD_Game/src/Assets/Tiles/155.png")));

        }catch(IOException e ){
            e.printStackTrace();
        }

    }
    public void LoadPrAssets (){
        try{
            Pr.put(0,ImageIO.read(new File("TD_Game/src/Assets/Tiles/PRFS.png")));
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
    public BufferedImage getTowerSprite(int towerId){
        return towerAssets.get(towerId);
    }
    public BufferedImage getUI(int uiid){
        return UI.get(uiid);
    }
    public BufferedImage getPrSprite(int prid){
        return Pr.get(prid);
    }

}
