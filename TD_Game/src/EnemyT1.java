import java.awt.image.BufferedImage;

public class EnemyT1 extends Enemy {
    public EnemyT1(int y , BufferedImage sprite){
        super(8, y, sprite);
        this.health = 50 ;
        this.speed = 30 ;
    }

}
