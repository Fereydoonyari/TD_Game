class GameMap {
    private Tile[][] tiles;
    private List<Point> path;
    private int width;
    private int height;
    
    public GameMap(int width, int height, AssetManager assetManager) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.path = new ArrayList<>();
        
        // Initialize with default tiles
        initializeMap(assetManager);
    }
    
    private void initializeMap(AssetManager assetManager) {
        // Create a simple map with a path
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((y == 2 && x < 8) || (x == 8 && y >= 2 && y <= 5) || (y == 5 && x >= 8)) {
                    tiles[x][y] = new Tile(TileType.PATH, assetManager.getImage("path"));
                    path.add(new Point(x, y));
                } else {
                    tiles[x][y] = new Tile(TileType.BUILDABLE, assetManager.getImage("grass"));
                }
            }
        }
        
        // Sort path points to ensure proper enemy movement
        sortPath();
    }
    
    private void sortPath() {
        // Simple path sorting for a linear path
        // In a real game, you'd need more sophisticated path handling
        Collections.sort(path, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            }
            return Integer.compare(p1.y, p2.y);
        });
    }
    
    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y].render();
            }
            System.out.println();
        }
    }
    
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return tiles[x][y];
        }
        return null;
    }
    
    public List<Point> getPath() {
        return path;
    }
}