import java.util.List;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class EnemyT1 extends Enemy {
    public EnemyT1(BufferedImage sprite,List<Point> path){
        super(sprite,100,path);
        this.speed = 20 ;
        this.scoreValue = 10 ;
    }

}
