abstract class Tower extends Entity {
    protected float range;
    protected int damage;
    protected float attackSpeed;
    protected float attackCooldown;
    
    public Tower(float x, float y, Image sprite, float range, int damage, float attackSpeed) {
        super(x, y, sprite);
        this.range = range;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attackCooldown = 0;
    }
    
    public void update(float deltaTime, List<Enemy> enemies) {
        attackCooldown -= deltaTime;
        
        if (attackCooldown <= 0) {
            Enemy target = findTarget(enemies);
            if (target != null) {
                attack(target);
                attackCooldown = 1.0f / attackSpeed;
            }
        }
    }
    
    protected Enemy findTarget(List<Enemy> enemies) {
        Enemy closest = null;
        float closestDistance = Float.MAX_VALUE;
        
        for (Enemy enemy : enemies) {
            float distance = calculateDistance(enemy);
            if (distance <= range && distance < closestDistance) {
                closest = enemy;
                closestDistance = distance;
            }
        }
        
        return closest;
    }
    
    protected float calculateDistance(Enemy enemy) {
        float dx = x - enemy.getX();
        float dy = y - enemy.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
    
    protected abstract void attack(Enemy target);
}
