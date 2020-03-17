package bearmaps;


import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point closestPoint = this.points.get(0);
        double closestDist = this.distance(x, y, closestPoint.getX(), closestPoint.getY());

        for (Point point: this.points) {
            double distanceBetween = distance(x, y, point.getX(), point.getY());
            if (distanceBetween < closestDist) {
                closestDist = distanceBetween;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
