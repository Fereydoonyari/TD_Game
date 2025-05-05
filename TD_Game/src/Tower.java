import java.awt.image.BufferedImage;
import java.util.List;


public abstract class Tower {
    protected int x,y ;
    protected int range ;
    protected int firerate ;
    protected int cooldown = 0 ;
    protected BufferedImage sprite ;
    protected double scale = 1.0 ;
    private int animationTick = 0 ;
    protected int damage ;
    protected int level = 1 ; 
    protected BufferedImage upgraded;

    public Tower(int x , int y , BufferedImage sprite, int damage,BufferedImage upgradedSprite ){
        this.x = x ; 
        this.y = y ;
        this.sprite = sprite;
        this.damage = damage;
        this.upgraded = upgradedSprite;

    }
    public void upgrade (){
        if (level < 2 ){
            level ++ ;
            sprite = upgraded;
            damage += 20 ;    
        }

    }
    public void animate(){
        animationTick ++ ;
        scale = 1.0 + 0.05 * Math.sin(animationTick * 0.1) ;
    }
    public double getScale(){
        return scale;
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
        animate();
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
    public int getDamage(){return damage;}
    public int getLevel(){return level;}
}
