package bearmaps;

import java.util.List;

public class KDTree implements PointSet {

    XYNode root;
    int size;

    private class XYNode {
        private XYNode left;
        private XYNode right;
        private Point nodePoint;
        private boolean orientation;

        private XYNode(Point point, boolean orientation) {
            this.nodePoint = point;
            this.orientation = orientation;
        }
    }

    private void add(Point point) {
        this.root = addHelper(point, this.root, true);
    }

    private XYNode addHelper(Point point, XYNode n, boolean horizontal) {
        if (n == null) {
            return new XYNode(point, horizontal);
        }
        boolean currOrientation = n.orientation;
        double cmp = comparePoints(n.nodePoint, point, horizontal);
        if (cmp >= 0) {
            n.left = addHelper(point, n.left, !currOrientation);
        } else {
            n.right = addHelper(point, n.right, !currOrientation);
        }
        return n;
    }

    private double comparePoints(Point p1, Point p2, boolean horizontal) {
        if (horizontal) {
            return p1.getX() - p2.getX();
        } else {
            return p1.getY() - p2.getY();
        }
    }

    public KDTree(List<Point> points) {
        this.size = 0;
        for (Point p: points) {
            this.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point point = new Point(x, y);
        return nearestHelper(this.root, point, this.root).nodePoint;
    }

    private XYNode nearestHelper(XYNode node, Point goal, XYNode best) {
        if (node == null) {
            return best;
        }
        if (Point.distance(node.nodePoint, goal) < Point.distance(best.nodePoint, goal)) {
            best = node;
        }
        double cmp = comparePoints(node.nodePoint, goal, node.orientation);
        double cmpAbs = Math.abs(cmp);
        XYNode goodSide;
        XYNode badSide;
        if (cmp >= 0) {
            goodSide = node.left;
            badSide = node.right;
        } else {
            goodSide = node.right;
            badSide = node.left;
        }
        best = nearestHelper(goodSide, goal, best);
        if (cmpAbs < Math.sqrt(Point.distance(best.nodePoint, goal))) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }
}


