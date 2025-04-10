public class TowerDefenseGame {
    private GameMap gameMap;
    private WaveManager waveManager;
    private Player player;
    private List<Tower> towers;
    private List<Enemy> activeEnemies;
    private AssetManager assetManager;
    private boolean isRunning;
    private long lastUpdateTime;
    
    public TowerDefenseGame() {
        this.assetManager = new AssetManager();
        this.gameMap = new GameMap(12, 8, assetManager);
        this.waveManager = new WaveManager();
        this.player = new Player(100, 50); // Starting with 100 health and 50 gold
        this.towers = new ArrayList<>();
        this.activeEnemies = new ArrayList<>();
        this.isRunning = false;
    }
    
    public void start() {
        isRunning = true;
        lastUpdateTime = System.currentTimeMillis();
        gameLoop();
    }
    
    private void gameLoop() {
        while (isRunning) {
            long currentTime = System.currentTimeMillis();
            float deltaTime = (currentTime - lastUpdateTime) / 1000.0f;
            lastUpdateTime = currentTime;
            
            update(deltaTime);
            render();
            
            try {
                Thread.sleep(16); // Approx 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (player.getHealth() <= 0) {
                isRunning = false;
                System.out.println("Game Over!");
            }
        }
    }
    
    private void update(float deltaTime) {
        // Update all game entities
        waveManager.update(deltaTime, this);
        
        // Update towers
        for (Tower tower : towers) {
            tower.update(deltaTime, activeEnemies);
        }
        
        // Update enemies and check if they've reached the end
        Iterator<Enemy> enemyIterator = activeEnemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.update(deltaTime);
            
            if (enemy.hasReachedEnd()) {
                player.takeDamage(enemy.getDamage());
                enemyIterator.remove();
            } else if (enemy.isDead()) {
                player.addGold(enemy.getReward());
                enemyIterator.remove();
            }
        }
    }
    
    private void render() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        // Render the game map
        gameMap.render();
        
        // Render game stats
        System.out.println("Health: " + player.getHealth() + " | Gold: " + player.getGold());
        System.out.println("Wave: " + waveManager.getCurrentWave());
    }
    
    public void placeTower(TowerType type, int x, int y) {
        Tile tile = gameMap.getTile(x, y);
        if (tile != null && tile.getType() == TileType.BUILDABLE) {
            int cost = type.getCost();
            if (player.getGold() >= cost) {
                Tower tower = TowerFactory.createTower(type, x, y, assetManager);
                towers.add(tower);
                tile.setOccupied(true);
                player.spendGold(cost);
            }
        }
    }
    
    public void spawnEnemy(EnemyType type) {
        Enemy enemy = EnemyFactory.createEnemy(type, gameMap.getPath(), assetManager);
        activeEnemies.add(enemy);
    }
    
    // Getters
    public GameMap getGameMap() {
        return gameMap;
    }
    
    public List<Enemy> getActiveEnemies() {
        return activeEnemies;
    }
}