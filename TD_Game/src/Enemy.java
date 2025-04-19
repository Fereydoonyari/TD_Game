import java.util.List;
import java.awt.Point;
import java.awt.image.BufferedImage ;

public abstract class Enemy {
    protected int x, y ;
    protected int health;
    protected int speed ;
    protected BufferedImage sprite ;
    protected int moveCounter = 0 ;
    protected int maxHealth ;
    protected int scoreValue = 10 ;
    protected int CurrentWayPoint = 0 ;
    protected List<Point> path;

    public Enemy (BufferedImage sprite, int maxhealth,List<Point> path){
        this.path = path;
        this.x = path.get(0).x ;
        this.y = path.get(0).y ;
        this.sprite = sprite;
        this.maxHealth = maxhealth;
        this.health = maxhealth;
    }
    public void update() {
        moveCounter++;
        if (moveCounter >= speed && CurrentWayPoint < path.size()) {
            Point target = path.get(CurrentWayPoint);
            if (x < target.x) x++;
            else if (x > target.x) x--;
            if (y < target.y) y++;
            else if (y > target.y) y--;
    
            // If we reached the target tile, go to next waypoint
            if (x == target.x && y == target.y) {
                CurrentWayPoint++;
            }
            moveCounter = 0;
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
    public int getScoreValue(){
        return scoreValue;
    }

    public boolean reachedEnd() {
        return CurrentWayPoint >= path.size();
    }
    
    public int getX(){return x ;}
    public int getY(){return y ;}
    public BufferedImage getSprite(){return sprite;}
    public boolean isOutofbound (){return x < 0 ;}
    public int getHelth(){return health;}
    public int getMaxHealth(){return maxHealth;}
}
