class Path {
    private java.util.List<java.awt.Point> waypoints;
    
    public Path() {
        waypoints = new java.util.ArrayList<>();
    }
    
    public void addWaypoint(int x, int y) {
        waypoints.add(new java.awt.Point(x, y));
    }
    
    public java.util.List<java.awt.Point> getWaypoints() {
        return waypoints;
    }
    
    public void render(java.awt.Graphics g) {
        // Render path (optional, as tiles already show the path)
    }
}
