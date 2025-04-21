import java.util.List;
import java.awt.Point;
import java.awt.image.BufferedImage;



public class EnemyF extends Enemy {
    public EnemyF(BufferedImage sprite,List<Point> path){
        super(sprite,70,path);
        this.speed = 40 ;
        this.scoreValue = 20 ;
    }

}
