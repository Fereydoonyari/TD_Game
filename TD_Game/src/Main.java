public class Main {
    public static void main(String[] args) {
        TowerDefenseGame game = new TowerDefenseGame();
        
        // Place some initial towers
        game.placeTower(TowerType.BASIC, 2, 1);
        game.placeTower(TowerType.BASIC, 5, 3);
        
        game.start();
    }
}