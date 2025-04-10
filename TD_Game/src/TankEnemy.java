class TankEnemy extends Enemy {
    public TankEnemy(List<Point> path, Image sprite) {
        super(path, sprite, EnemyType.TANK.getHealth(), EnemyType.TANK.getSpeed(),
              EnemyType.TANK.getDamage(), EnemyType.TANK.getReward());
    }
}