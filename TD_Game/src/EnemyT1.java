import java.awt.image.BufferedImage;

public class EnemyT1 extends Enemy {
    public EnemyT1(int y , BufferedImage sprite){
        super(16, y, sprite,10);
        this.speed = 30 ;
    }

}
