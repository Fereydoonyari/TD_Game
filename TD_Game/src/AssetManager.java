import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class AssetManager {
    private HashMap<Integer,BufferedImage> tileAssets = new HashMap<>();

    public void loadAssets(){
        try{
            tileAssets.put(0, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile1.png")));
            tileAssets.put(1, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile6.png")));
            tileAssets.put(2, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile5.png")));
            tileAssets.put(3, ImageIO.read(getClass().getResource("/Assets/Tiles/Tile4.png")));


        }catch(IOException e ){
            e.printStackTrace();
        }
    }
    public BufferedImage getTileAsset(int tileId){
        return tileAssets.get(tileId);
    }

}
