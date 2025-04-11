abstract class Tower extends GameObject {
    protected int cost;
    protected int range;
    protected int damage;
    protected long cooldown;
    protected long lastShotTime;
    protected Enemy target;
    
    public Tower(int x, int y, int cost, int range, int damage, long cooldown) {
        super(x, y);
        this.cost = cost;
        this.range = range;
        this.damage = damage;
        this.cooldown = cooldown;
        this.lastShotTime = 0;
    }
    
    public void findTarget(java.util.List<Enemy> enemies) {
        target = null;
        double closestDistance = Double.MAX_VALUE;
        
        for (Enemy enemy : enemies) {
            double distance = calculateDistance(enemy);
            if (distance <= range && distance < closestDistance) {
                closestDistance = distance;
                target = enemy;
            }
        }
    }
    
    private double calculateDistance(Enemy enemy) {
        int dx = enemy.getX() - x;
        int dy = enemy.getY() - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public boolean canShoot() {
        return target != null && System.currentTimeMillis() - lastShotTime >= cooldown;
    }
    
    public Projectile shoot() {
        if (canShoot()) {
            lastShotTime = System.currentTimeMillis();
            return createProjectile();
        }
        return null;
    }
    
    protected abstract Projectile createProjectile();
    
    @Override
    public void update() {
        // Tower-specific behavior
    }
    
    // Getters
    public int getCost() { return cost; }
    public int getRange() { return range; }
    public int getDamage() { return damage; }
}