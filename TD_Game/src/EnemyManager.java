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
        int [] rows = {1,2,3,4,5,6}; /// i should make it random after 
        for (int row : rows){
            int rand = random.nextInt(3);
            enemies.add(new EnemyT1(row,assetManager.getEnemySprite(rand)));

        }
    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
}
