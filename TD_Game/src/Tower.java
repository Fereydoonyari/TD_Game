import java.awt.image.BufferedImage;
import java.util.List;


public abstract class Tower {
    protected int x,y ;
    protected int range ;
    protected int firerate ;
    protected int cooldown = 0 ;
    protected BufferedImage sprite ;

    public Tower(int x , int y , BufferedImage Sprite){
        this.x = x ; 
        this.y = y ;
        this.sprite = sprite;

    }
    public void update (List<Enemy> enemies,List<Projectile> projectiles){
        if (cooldown > 0 ) cooldown -- ;
        if (cooldown <= 0 ){
            for (Enemy enemy : enemies){
                if (inRange(enemy)){
                    projectiles.add(createProjectile(enemy));
                    cooldown = firerate;
                    break;
                }
            }
        }
    }
    protected boolean inRange(Enemy e ){
        int dx = x - e.getX();
        int dy = y - e.getY();
        return Math.sqrt(dx*dx + dy*dy) <= range ;

    }
    protected abstract Projectile createProjectile(Enemy target);
    public int getx(){return x ;}
    public int gety(){return y ;}
    public BufferedImage getSprite(){return sprite;}
}
