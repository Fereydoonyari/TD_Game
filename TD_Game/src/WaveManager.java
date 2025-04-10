import java.util.ArrayList;
import java.util.List;

class WaveManager {
    private List<Wave> waves;
    private int currentWaveIndex;
    private boolean waveInProgress;
    
    public WaveManager() {
        this.waves = new ArrayList<>();
        this.currentWaveIndex = 0;
        this.waveInProgress = false;
        
        initializeWaves();
    }
    
    private void initializeWaves() {
        // Wave 1: 5 basic enemies
        List<EnemyType> wave1Enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            wave1Enemies.add(EnemyType.BASIC);
        }
        waves.add(new Wave(wave1Enemies, 2.0f));
        
        // Wave 2: mix of basic and fast enemies
        List<EnemyType> wave2Enemies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            wave2Enemies.add(EnemyType.BASIC);
        }
        for (int i = 0; i < 3; i++) {
            wave2Enemies.add(EnemyType.FAST);
        }
        waves.add(new Wave(wave2Enemies, 1.5f));
        
        // Wave 3: more enemies with tanks
        List<EnemyType> wave3Enemies = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            wave3Enemies.add(EnemyType.BASIC);
        }
        for (int i = 0; i < 4; i++) {
            wave3Enemies.add(EnemyType.FAST);
        }
        wave3Enemies.add(EnemyType.TANK);
        waves.add(new Wave(wave3Enemies, 1.0f));
    }
    
    public void update(float deltaTime, TowerDefenseGame game) {
        if (currentWaveIndex < waves.size()) {
            if (!waveInProgress) {
                waveInProgress = true;
            }
            
            Wave currentWave = waves.get(currentWaveIndex);
            boolean waveComplete = currentWave.update(deltaTime, game);
            
            if (waveComplete && game.getActiveEnemies().isEmpty()) {
                waveInProgress = false;
                currentWaveIndex++;
            }
        }
    }
    
    public int getCurrentWave() {
        return currentWaveIndex + 1;
    }
    
    public boolean isAllWavesCompleted() {
        return currentWaveIndex >= waves.size();
    }
}
