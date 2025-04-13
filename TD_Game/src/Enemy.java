import java.awt.image.BufferedImage ;
import java.nio.Buffer;

public abstract class Enemy {
    protected int x, y ;
    protected int health;
    protected int speed ;
    protected BufferedImage sprite ;
    protected int moveCounter = 0 ;

    public Enemy (int x, int y , BufferedImage sprite){
        this.x = x ;
        this.y = y ;
        this.sprite = sprite;
    }
    public void update (){
        moveCounter ++ ;
        if (moveCounter >= speed){
            x -- ;
            moveCounter = 0 ;
        }
    }
    public int getX(){return x ;}
    public int getY(){return y ;}
    public BufferedImage getSprite(){return sprite;}
    public boolean isOutofbound (){return x < 0 ;}

}
