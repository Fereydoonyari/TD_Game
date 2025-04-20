public class Projectile {
    private double x, y;                   // Pixel coordinates
    private Enemy target;
    private int speed;
    private int damage;
    private boolean active = true;
    private static final int TILE_SIZE = 85; // Or 85 — depending on your setup

    public Projectile(int tileX, int tileY, Enemy target, int speed, int damage) {
        this.x = tileX * TILE_SIZE + TILE_SIZE / 2.0;
        this.y = tileY * TILE_SIZE + TILE_SIZE / 2.0;
        this.target = target;
        this.speed = speed;
        this.damage = damage;
    }

    public void update() {
        if (!active || target == null || target.isDead()) {
            active = false;
            return;
        }

        // Target center
        double targetX = target.getX() * TILE_SIZE + TILE_SIZE / 2.0;
        double targetY = target.getY() * TILE_SIZE + TILE_SIZE / 2.0;

        double dx = targetX - x;
        double dy = targetY - y;
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 10) {  // Hit threshold in pixels
            target.takeDamage(damage);
            active = false;
        } else {
            x += (dx / dist) * speed;
            y += (dy / dist) * speed;
        }
    }

    public boolean isActive() {
        return active;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
}
