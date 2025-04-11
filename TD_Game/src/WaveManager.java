class WaveManager {
    private int currentWave;
    private int enemiesPerWave;
    private int enemiesSpawned;
    private long spawnInterval;
    private long lastSpawnTime;
    private boolean waveInProgress;
    private GameBoard gameBoard;
    
    public WaveManager(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.currentWave = 0;
        this.enemiesPerWave = 10;
        this.spawnInterval = 1500; // 1.5 seconds
        this.waveInProgress = false;
    }
    
    public void startNextWave() {
        currentWave++;
        enemiesSpawned = 0;
        waveInProgress = true;
        // Increase difficulty with each wave
        enemiesPerWave = 10 + (currentWave - 1) * 2;
    }
    
    public void update() {
        if (!waveInProgress) return;
        
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime >= spawnInterval && enemiesSpawned < enemiesPerWave) {
            spawnEnemy();
            lastSpawnTime = currentTime;
            enemiesSpawned++;
        }
        
        if (enemiesSpawned >= enemiesPerWave && gameBoard.getEnemies().isEmpty()) {
            waveInProgress = false;
        }
    }
    
    private void spawnEnemy() {
        Enemy enemy;
        int enemyType = (int) (Math.random() * 3);
        
        switch (enemyType) {
            case 0:
                enemy = new BasicEnemy();
                break;
            case 1:
                enemy = new FastEnemy();
                break;
            case 2:
                enemy = new TankEnemy();
                break;
            default:
                enemy = new BasicEnemy();
        }
        
        gameBoard.spawnEnemy(enemy);
    }
    
    // Getters
    public int getCurrentWave() { return currentWave; }
    public boolean isWaveInProgress() { return waveInProgress; }
}