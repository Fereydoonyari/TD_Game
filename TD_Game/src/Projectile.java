abstract class Projectile extends GameObject {
    protected Enemy target;
    protected int damage;
    protected double speed;
    protected double dx, dy;
    protected boolean hasHitTarget;
    
    public Projectile(int x, int y, Enemy target, int damage, double speed) {
        super(x, y);
        this.target = target;
        this.damage = damage;
        this.speed = speed;
        this.hasHitTarget = false;
        calculateDirection();
    }
    
    private void calculateDirection() {
        if (target != null) {
            double angle = Math.atan2(target.getY() - y, target.getX() - x);
            dx = Math.cos(angle) * speed;
            dy = Math.sin(angle) * speed;
        }
    }
    
    @Override
    public void update() {
        if (!isActive || target == null || !target.isActive()) {
            isActive = false;
            return;
        }
        
        // Move projectile
        x += dx;
        y += dy;
        
        // Check if out of bounds
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            isActive = false;
        }
    }
    
    public boolean collidesWith(Enemy enemy) {
        // Simple collision detection
        double distance = Math.sqrt(Math.pow(x - enemy.getX(), 2) + Math.pow(y - enemy.getY(), 2));
        return distance < 16; // Adjust this value based on your sprites
    }
    
    // Getters and setters
    public int getDamage() { return damage; }
    public boolean hasHitTarget() { return hasHitTarget; }
    public void setHasHitTarget(boolean hasHitTarget) { this.hasHitTarget = hasHitTarget; }
}