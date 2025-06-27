import java.awt.Point;
import java.util.*;

public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();
    private AssetManager assetManager;
    private int spawnCounter = 0 ;
    private int spawnInterval = 100 ;
    Random random = new Random();
    private int mapnumber;
    public EnemyManager(int mapnumber,AssetManager assetManager){
        this.mapnumber = mapnumber;
        this.assetManager = assetManager;
    }
    public void update (){
        spawnCounter ++ ;
        if (spawnCounter >= spawnInterval){
            spawnWave(mapnumber);
            spawnCounter = 0 ;
        }
        for (Iterator<Enemy> it = enemies.iterator();it.hasNext();){
            Enemy enemy = it.next();
            enemy.update();
            if (enemy.isOutofbound()){
                it.remove();
            }
        }
    }
    public void spawnWave(int mapnumber){
        int rand = random.nextInt(3);
        List<Point> path;
        if (mapnumber == 1 ){
            path = PathManager.getCustomPath(); 
        }else {
            path = PathManager.getCustomPath2(); 
        }  
        enemies.add(new EnemyT1(assetManager.getEnemySprite(rand), path)); 

    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
}
