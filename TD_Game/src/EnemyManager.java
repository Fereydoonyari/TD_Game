import java.awt.Point;
import java.util.*;
import java.util.random.*;

public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();
    private AssetManager assetManager;
    private int spawnCounter = 0 ;
    private int spawnInterval = 100 ;
    Random random = new Random();

    public EnemyManager(AssetManager assetManager){
        this.assetManager = assetManager;
    }
    public void update (){
        spawnCounter ++ ;
        if (spawnCounter >= spawnInterval){
            spawnWave();
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
    public void spawnWave(){
        int rand = random.nextInt(3);
        List<Point> path = PathManager.getCustomPath(); // simple straight path
        enemies.add(new EnemyT1(assetManager.getEnemySprite(rand), path));
            
    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
}
