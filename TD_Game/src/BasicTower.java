class BasicTower extends Tower {
    public BasicTower(int x, int y) {
        super(x, y, 100, 3, 10, 1000); // Cost 100, range 3, damage 10, cooldown 1s
    }
    
    @Override
    protected Projectile createProjectile() {
        return new BasicProjectile(x, y, target, damage);
    }
    
    @Override
    public void render(java.awt.Graphics g) {
        g.setColor(java.awt.Color.GRAY);
        g.fillRect(x * 32 + 4, y * 32 + 4, 24, 24);
        
        // Draw range (when selected)
        /*
        g.setColor(new java.awt.Color(255, 0, 0, 100));
        g.fillOval((x - range) * 32, (y - range) * 32, 
                  (range * 2 + 1) * 32, (range * 2 + 1) * 32);
        */
    }
}