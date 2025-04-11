abstract class GameObject {
    protected int x, y ;
    protected boolean isActive;

    public GameObject(int x , int y ){
        this.x = x ;
        this.y = y ;
        this.isActive = true;
    }

    public abstract void update();
    public abstract void render(java.awt.Graphics g );

    public int getX(){ return x ;}
    public int getY(){ return y ;}
    public void setPosition(int x , int y){
        this.x = x;
        this.y = y ;
    }
    public boolean isActive(){ return isActive ;}
    public void setActive(boolean active){ isActive = active;}
}
