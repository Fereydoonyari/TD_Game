class BasicEnemy extends Enemy {
    public BasicEnemy() {
        super(50, 0.5, 10, 1); // Health 50, speed 0.5, reward 10, damage 1
    }
    
    @Override
    protected void renderEnemy(java.awt.Graphics g) {
        g.setColor(java.awt.Color.RED);
        g.fillOval(x - 10, y - 10, 20, 20);
    }
}
