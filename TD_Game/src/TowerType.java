enum TowerType {
    BASIC(30, 2.0f, 10),    // Cost, range, damage
    SNIPER(50, 5.0f, 25),
    AOE(75, 1.5f, 15);
    
    private final int cost;
    private final float range;
    private final int damage;
    
    TowerType(int cost, float range, int damage) {
        this.cost = cost;
        this.range = range;
        this.damage = damage;
    }
    
    public int getCost() {
        return cost;
    }
    
    public float getRange() {
        return range;
    }
    
    public int getDamage() {
        return damage;
    }
}
