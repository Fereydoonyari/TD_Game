abstract class Entity {
    protected float x, y;
    protected Image sprite;
    
    public Entity(float x, float y, Image sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    
    public abstract void update(float deltaTime);
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }
}
