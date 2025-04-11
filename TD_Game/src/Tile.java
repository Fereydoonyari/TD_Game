class Tile extends GameObject{
    private TileType type;
    
    public Tile(int x, int y, TileType type) {
        super(x, y);
        this.type = type;
    }
    @Override
    public void update() {
        // Tiles don't usually need updating
    }
    @Override
    public void render(java.awt.Graphics g) {
        // Render tile based on type
        switch (type) {
            case PATH:
                g.setColor(java.awt.Color.BLACK);
                break;
            case GRASS:
                g.setColor(java.awt.Color.GREEN);
                break;
            case WATER:
                g.setColor(java.awt.Color.BLUE);
                break;
        }
        g.fillRect(x * 32, y * 32, 32, 32);
        g.setColor(java.awt.Color.BLACK);
        g.drawRect(x * 32, y * 32, 32, 32);
    }
    public TileType getType() { return type; }
    public void setType(TileType type) { this.type = type; }
}

