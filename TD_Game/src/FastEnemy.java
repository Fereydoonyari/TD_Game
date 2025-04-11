class FastEnemy extends Enemy {
    public FastEnemy() {
        super(30, 1.0, 15, 1); // Health 30, speed 1.0, reward 15, damage 1
    }
    
    @Override
    protected void renderEnemy(java.awt.Graphics g) {
        g.setColor(java.awt.Color.YELLOW);
        g.fillOval(x - 8, y - 8, 16, 16);
    }
}