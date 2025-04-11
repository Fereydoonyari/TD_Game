class Player {
    private int money;
    private int lives;
    
    public Player(int startingMoney, int startingLives) {
        this.money = startingMoney;
        this.lives = startingLives;
    }
    
    public void earnMoney(int amount) {
        money += amount;
    }
    
    public boolean spendMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    
    public void takeDamage(int damage) {
        lives -= damage;
        if (lives < 0) lives = 0;
    }
    
    public boolean isGameOver() {
        return lives <= 0;
    }
    
    // Getters
    public int getMoney() { return money; }
    public int getLives() { return lives; }
}
