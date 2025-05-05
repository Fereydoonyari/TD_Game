import java.awt.image.BufferedImage;

public class BasicTower extends Tower{
    public BasicTower(int x , int y , BufferedImage sprite,BufferedImage up){
        super (x,y,sprite,20,up);
        this.range = 3 ;
        this.firerate = 60 ;

    }
    @Override 
    protected Projectile createProjectile(Enemy target){
        return new Projectile (x,y,target,5,10);
    }
}
