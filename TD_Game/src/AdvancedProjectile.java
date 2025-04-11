class AdvancedProjectile extends Projectile {
    public AdvancedProjectile(int x, int y, Enemy target, int damage) {
        super(x, y, target, damage, 7.0); // Speed 7.0
    }
    
    @Override
    public void render(java.awt.Graphics g) {
        g.setColor(java.awt.Color.RED);
        g.fillOval(x - 4, y - 4, 8, 8);
    }
}