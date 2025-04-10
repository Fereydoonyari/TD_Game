abstract class Enemy extends Entity {
    protected int health;
    protected int maxHealth;
    protected float speed;
    protected int damage;
    protected int reward;
    protected List<Point> path;
    protected int currentPathIndex;
    
    public Enemy(List<Point> path, Image sprite, int health, float speed, int damage, int reward) {
        super(path.get(0).x, path.get(0).y, sprite);
        this.path = path;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.damage = damage;
        this.reward = reward;
        this.currentPathIndex = 0;
    }
    
    @Override
    public void update(float deltaTime) {
        // Move along the path
        if (currentPathIndex < path.size() - 1) {
            Point target = path.get(currentPathIndex + 1);
            float dx = target.x - x;
            float dy = target.y - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            
            if (distance > 0.1f) {
                float moveX = dx / distance * speed * deltaTime;
                float moveY = dy / distance * speed * deltaTime;
                x += moveX;
                y += moveY;
            } else {
                currentPathIndex++;
            }
        }
    }
    
    public void takeDamage(int amount) {
        health -= amount;
    }
    
    public boolean isDead() {
        return health <= 0;
    }
    
    public boolean hasReachedEnd() {
        return currentPathIndex >= path.size() - 1;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getReward() {
        return reward;
    }
}
