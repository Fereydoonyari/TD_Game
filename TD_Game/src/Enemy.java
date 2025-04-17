import java.awt.image.BufferedImage ;

public abstract class Enemy {
    protected int x, y ;
    protected int health;
    protected int speed ;
    protected BufferedImage sprite ;
    protected int moveCounter = 0 ;
    protected int maxHealth ;

    public Enemy (int x, int y , BufferedImage sprite, int maxhealth){
        this.x = x ;
        this.y = y ;
        this.sprite = sprite;
        this.maxHealth = maxhealth;
        this.health = maxhealth;
    }
    public void update (){
        moveCounter ++ ;
        if (moveCounter >= speed){
            x -- ;
            moveCounter = 0 ;
        }
    }
    public void takeDamage(int dmg){
        health -= dmg;
        if (health <= 0 ){
            health = 0 ;
        }
    }
    public boolean isDead(){
        return health <=0 ;
    }
    public int getX(){return x ;}
    public int getY(){return y ;}
    public BufferedImage getSprite(){return sprite;}
    public boolean isOutofbound (){return x < 0 ;}
    public int getHelth(){return health;}
    public int getMaxHealth(){return maxHealth;}
}
