class BasicProjectile extends Projectile {
    public BasicProjectile(int x, int y, Enemy target, int damage) {
        super(x, y, target, damage, 5.0); // Speed 5.0
    }
    
    @Override
    public void render(java.awt.Graphics g) {
        g.setColor(java.awt.Color.BLACK);
        g.fillOval(x - 3, y - 3, 6, 6);
    }
}