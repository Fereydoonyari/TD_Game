enum EnemyType {
    BASIC(50, 1.0f, 10, 5),    // Health, speed, damage, reward
    FAST(30, 2.0f, 5, 10),
    TANK(100, 0.5f, 20, 15);
    
    private final int health;
    private final float speed;
    private final int damage;
    private final int reward;
    
    EnemyType(int health, float speed, int damage, int reward) {
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.reward = reward;
    }
    
    public int getHealth() {
        return health;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public int getReward() {
        return reward;
    }
}
