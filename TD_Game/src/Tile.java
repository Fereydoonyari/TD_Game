class Tile {
    private TileType type;
    private Image sprite;
    private boolean isOccupied;
    
    public Tile(TileType type, Image sprite) {
        this.type = type;
        this.sprite = sprite;
        this.isOccupied = false;
    }
    
    public TileType getType() {
        return type;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
    
    public void render() {
        // Render the tile sprite
        // This is a placeholder for actual rendering code
        char display = ' ';
        switch (type) {
            case PATH:
                display = '#';
                break;
            case BUILDABLE:
                display = isOccupied ? 'T' : '.';
                break;
            case BLOCKED:
                display = 'X';
                break;
        }
        System.out.print(display);
    }
}
