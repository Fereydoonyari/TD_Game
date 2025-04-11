class TankEnemy extends Enemy {
    public TankEnemy() {
        super(150, 0.3, 25, 2); // Health 150, speed 0.3, reward 25, damage 2
    }
    
    @Override
    protected void renderEnemy(java.awt.Graphics g) {
        g.setColor(java.awt.Color.DARK_GRAY);
        g.fillOval(x - 12, y - 12, 24, 24);
    }
}
