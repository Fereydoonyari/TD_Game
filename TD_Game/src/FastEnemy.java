class FastEnemy extends Enemy {
    public FastEnemy(List<Point> path, Image sprite) {
        super(path, sprite, EnemyType.FAST.getHealth(), EnemyType.FAST.getSpeed(),
              EnemyType.FAST.getDamage(), EnemyType.FAST.getReward());
    }
}