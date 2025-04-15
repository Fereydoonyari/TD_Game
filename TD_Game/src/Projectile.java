import java.security.ProtectionDomain;

public class Projectile {
    private double x,y ;
    private Enemy target ; 
    private int speed;
    private int damage ;
    private Boolean active = true;

    public Projectile(int x , int y , Enemy target,int speed,int damage){
        this.x = x ;
        this.y = y ; 
        this.target  = target;
        this.speed = speed;
        this.damage = damage;
    }
    public void update (){
        if (!active || target == null || target.isDead()){
            active = false ;
            return;
        }

        double dx = target.getX() - x ;
        double dy = target.getY() - y ;
        double dist = Math.sqrt(dx*dx + dy*dy);
        if (dist < 0.1){
            target.takeDamage(damage);
            active = false;
        }else {
            x += (dx / dist)*0.1*speed;
            y += (dy /dist)*0.1*speed;
        }
    }
    public boolean isActive(){return active ;}
    public int getX(){return (int)(x*64) ;}
    public int getY(){return (int)(y*64); }

}
