import java.awt.Point;

public class GameBoard {
    private Tile [][] tiles ;
    private int width,height;
    private java.util.List<Tower> towers;
    private java.util.List<Enemy> enemies;
    private java.util.List<Projectile> projectiles;
    public Path path ;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        towers = new java.util.ArrayList<>();
        enemies = new java.util.ArrayList<>();
        projectiles = new java.util.ArrayList<>();
        initializeBoard();
    }
    //// new ccode 
    public Path getPath(){
        return path;
    }
    public Tile getTile(int x , int y ){
        return tiles[x][y];
    }
    private void initializeBoard() {
        // Create tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, TileType.GRASS);
            }
        }

        
        // Create path on the board
        path = new Path();
        // Define path tiles
        ///path.addWaypoint(10, 10);
        /* 
        path.addWaypoint(0, 0);
        path.addWaypoint(0, 1);
        path.addWaypoint(0, 2);
        path.addWaypoint(1, 2);
        path.addWaypoint(2, 2);
        path.addWaypoint(3, 2);
        path.addWaypoint(1, 0);
        path.addWaypoint(2, 0);
        path.addWaypoint(3, 0);
        path.addWaypoint(4, 0);
        path.addWaypoint(1, 1);
        */

    }

    public void update() {
        // Update towers
        for (Tower tower : towers) {
            tower.update();
            tower.findTarget(enemies);
            
            if (tower.canShoot()) {
                Projectile projectile = tower.shoot();
                if (projectile != null) {
                    projectiles.add(projectile);
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = enemies.get(i);

            enemy.update();
            
            if (!enemy.isActive()) {
                enemies.remove(i);
            }
        }
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            projectile.update();
            
            if (projectile.hasHitTarget() || !projectile.isActive()) {
                projectiles.remove(i);
                continue;
            }
            
            // Check for collisions with enemies
            for (Enemy enemy : enemies) {
                if (projectile.collidesWith(enemy)) {
                    enemy.takeDamage(projectile.getDamage());
                    projectile.setActive(false);
                    break;
                }
            }
        }
    }


    public void render(java.awt.Graphics g) {
        // Render tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y].render(g);
            }
        }
        
        // Render path
        path.render(g);
        
        // Render towers
        for (Tower tower : towers) {
            tower.render(g);
        }
        
        // Render enemies
        for (Enemy enemy : enemies) {
            enemy.render(g);
        }
        
        // Render projectiles
        for (Projectile projectile : projectiles) {
            projectile.render(g);
        }
    }

    public boolean canPlaceTower(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        
        if (tiles[x][y].getType() != TileType.GRASS) {
            return false;
        }
        
        // Check if there's already a tower
        for (Tower tower : towers) {
            if (tower.getX() == x && tower.getY() == y) {
                return false;
            }
        }
        
        return true;
    }

    public void placeTower(Tower tower) {
        if (canPlaceTower(tower.getX(), tower.getY())) {
            towers.add(tower);
        }
    }

    public void spawnEnemy(Enemy enemy) {
        enemies.add(enemy);
        enemy.setPath(path);
    }
    
    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public java.util.List<Enemy> getEnemies() { return enemies; }
}
