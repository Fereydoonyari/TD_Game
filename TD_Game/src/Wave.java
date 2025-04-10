import java.util.List;

class Wave {
    private List<EnemyType> enemies;
    private float spawnInterval;
    private float timeSinceLastSpawn;
    private int enemiesSpawned;
    
    public Wave(List<EnemyType> enemies, float spawnInterval) {
        this.enemies = enemies;
        this.spawnInterval = spawnInterval;
        this.timeSinceLastSpawn = 0;
        this.enemiesSpawned = 0;
    }
    
    public boolean update(float deltaTime, TowerDefenseGame game) {
        if (isComplete()) {
            return true;
        }
        
        timeSinceLastSpawn += deltaTime;
        
        if (timeSinceLastSpawn >= spawnInterval) {
            game.spawnEnemy(enemies.get(enemiesSpawned));
            enemiesSpawned++;
            timeSinceLastSpawn = 0;
        }
        
        return false;
    }
    
    public boolean isComplete() {
        return enemiesSpawned >= enemies.size();
    }
}