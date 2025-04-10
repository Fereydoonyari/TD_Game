class Player {
    private int health;
    private int gold;
    
    public Player(int health, int gold) {
        this.health = health;
        this.gold = gold;
    }
    
    public void takeDamage(int damage) {
        health -= damage;
    }
    
    public void addGold(int amount) {
        gold += amount;
    }
    
    public boolean spendGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false;
    }
    
    public int getHealth() {
        return health;
    }
    
    public int getGold() {
        return gold;
    }
}