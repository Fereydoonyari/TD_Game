class EnemyFactory {
    public static Enemy createEnemy(EnemyType type, List<Point> path, AssetManager assetManager) {
        switch (type) {
            case BASIC:
                return new BasicEnemy(path, assetManager.getImage("basic_enemy"));
            case FAST:
                return new FastEnemy(path, assetManager.getImage("fast_enemy"));
            case TANK:
                return new TankEnemy(path, assetManager.getImage("tank_enemy"));
            default:
                return new BasicEnemy(path, assetManager.getImage("basic_enemy"));
        }
    }
}
