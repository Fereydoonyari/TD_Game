import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PathManager {
    public static List<Point> getCustomPath() {
        List<Point> path = new ArrayList<>();

        path.add(new Point(0, 2));
        path.add(new Point(1, 2));
        path.add(new Point(2, 2));
        path.add(new Point(2, 3));
        path.add(new Point(2, 4));
        path.add(new Point(2, 5));
        path.add(new Point(2, 6));
        path.add(new Point(3, 6));
        path.add(new Point(4, 6));
        path.add(new Point(4, 5));
        path.add(new Point(4, 4));
        path.add(new Point(4, 3));
        path.add(new Point(4, 2));
        path.add(new Point(4, 1));
        path.add(new Point(5, 1));
        path.add(new Point(6, 1));
        path.add(new Point(6, 2));
        path.add(new Point(6, 2));
        path.add(new Point(6, 3));
        path.add(new Point(6, 4));
        path.add(new Point(6, 5));
        path.add(new Point(6, 6));
        path.add(new Point(7, 6));

        return path;
    }
}
