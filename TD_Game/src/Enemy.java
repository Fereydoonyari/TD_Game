abstract class Enemy extends GameObject {
    protected int health;
    protected int maxHealth;
    protected double speed;
    protected int reward;
    protected int damage; // Damage to player if enemy reaches the end
    protected Path path;
    protected int currentWaypointIndex;
    protected double progressToNextWaypoint;
    
    public Enemy(int health, double speed, int reward, int damage) {
        super(0, 0); // Position will be set when spawned on path
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.reward = reward;
        this.damage = damage;
        this.currentWaypointIndex = 0;
        this.progressToNextWaypoint = 0;
    }
    
    public void setPath(Path path) {
        this.path = path;
        if (path != null && !path.getWaypoints().isEmpty()) {
            java.awt.Point start = path.getWaypoints().get(0);
            this.x = start.x;
            this.y = start.y;
        }
    }
    
    @Override
    public void update() {
        if (!isActive || path == null) return;
        
        java.util.List<java.awt.Point> waypoints = path.getWaypoints();
        
        if (currentWaypointIndex >= waypoints.size() - 1) {
            // Enemy reached the end of the path
            reachedEnd();
            return;
        }
        
        // Get current and next waypoints
        java.awt.Point current = waypoints.get(currentWaypointIndex);
        java.awt.Point next = waypoints.get(currentWaypointIndex + 1);
        
        // Calculate direction
        double dx = next.x - current.x;
        double dy = next.y - current.y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        
        // Update progress
        progressToNextWaypoint += speed / distance;
        
        if (progressToNextWaypoint >= 1.0) {
            // Move to next waypoint
            currentWaypointIndex++;
            progressToNextWaypoint = 0;
        } else {
            // Interpolate position
            x = (int) (current.x + dx * progressToNextWaypoint);
            y = (int) (current.y + dy * progressToNextWaypoint);
        }
    }
    
    @Override
    public void render(java.awt.Graphics g) {
        // Render enemy
        renderEnemy(g);
        
        // Render health bar
        renderHealthBar(g);
    }
    
    protected abstract void renderEnemy(java.awt.Graphics g);
    
    protected void renderHealthBar(java.awt.Graphics g) {
        int barWidth = 30;
        int barHeight = 4;
        int healthWidth = (int) ((double) health / maxHealth * barWidth);
        
        g.setColor(java.awt.Color.RED);
        g.fillRect(x - barWidth / 2, y - 20, barWidth, barHeight);
        g.setColor(java.awt.Color.GREEN);
        g.fillRect(x - barWidth / 2, y - 20, healthWidth, barHeight);
    }
    
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            die();
        }
    }
    
    protected void die() {
        isActive = false;
        // Add reward to player (handled by game manager)
    }
    
    protected void reachedEnd() {
        isActive = false;
        // Deal damage to player (handled by game manager)
    }
    
    // Getters
    public int getHealth() { return health; }
    public int getReward() { return reward; }
    public int getDamage() { return damage; }
}
