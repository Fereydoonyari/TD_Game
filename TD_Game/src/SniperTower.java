import java.awt.image.BufferedImage;

public class SniperTower extends Tower {
    public SniperTower(int x , int y , BufferedImage sprite){
        super (x,y,sprite);
        this.range = 5 ;
        this.firerate = 40 ;

    }
    @Override 
    protected Projectile createProjectile(Enemy target){
        return new Projectile (x,y,target,5,20);
    }
}
