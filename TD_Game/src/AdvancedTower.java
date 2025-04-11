class AdvancedTower extends Tower {
    public AdvancedTower(int x, int y) {
        super(x, y, 200, 4, 20, 800); // Cost 200, range 4, damage 20, cooldown 0.8s
    }
    
    @Override
    protected Projectile createProjectile() {
        return new AdvancedProjectile(x, y, target, damage);
    }
    
    @Override
    public void render(java.awt.Graphics g) {
        g.setColor(java.awt.Color.BLUE);
        g.fillRect(x * 32 + 4, y * 32 + 4, 24, 24);
    }
}