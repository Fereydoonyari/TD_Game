import java.util.*;


public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();
    private AssetManager assetManager;
    private int spawnCounter = 0 ;
    private int spawnInterval = 100 ;

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
        int [] rows = {1,2,3}; /// i should make it random after 
        for (int row : rows){
            enemies.add(new EnemyT1(row,assetManager.getEnemySprite(0)));
        }
    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
}
