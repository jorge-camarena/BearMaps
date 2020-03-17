package bearmaps;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.Random;

public class KDTreeTest {

    @Test
    public void testingConstructor() {
        Point p1 = new Point(2.1, -3.0);
        Point p2 = new Point(1.7, 3.7);
        Point p3 = new Point(0.5, 5.4);
        Point p4 = new Point(-2.5, -3.2);
        Point p5 = new Point(3.1, -6.7);
        Point p6 = new Point(2.8, 2.9);
        Point p7 = new Point(-7.0, 5.2);
        Point p8 = new Point(-5.1, -8.0);
        Point p9 = new Point(-4.7, 5.9);
        Point p10 = new Point(-5.8, 9.6);
        Point p11 = new Point(-8.7, 3.7);
        Point p12 = new Point(2.6, -1.2);
        Point p13 = new Point(7.1, -5.2);
        Point p14 = new Point(-4.4, 1.7);
        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);
        points.add(p8);
        points.add(p9);
        points.add(p10);
        points.add(p11);
        points.add(p12);
        points.add(p13);
        points.add(p14);


        KDTree testing1 = new KDTree(points);
        Point expected = p2;
        long start = System.currentTimeMillis();
        Point actual = testing1.nearest(3.0, 5.0);
        Point actual2 = testing1.nearest(3.0, 3.0);
        Point expected2 = p6;
        Assert.assertEquals(expected2, actual2);
        long end = System.currentTimeMillis();
        long startFromEnd = end - start;
        Assert.assertEquals(actual, expected);


    }

    @Test
    public void testingLotsOfPointsForSpeed() {
        Random generator = new Random(31);
        ArrayList<Point> points = new ArrayList<>();
        DoubleStream genDoubles = generator.doubles(-15.0, 15.0);
        for (int i = 0; i < 1000000; i++) {
            double xx = generator.nextDouble() * 10 - 10;
            double yy = generator.nextDouble() * 10 - 10;
            Point p = new Point(xx, yy);
            points.add(p);
        }

        KDTree testTree = new KDTree(points);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double xx = generator.nextDouble();
            double yy = generator.nextDouble();
            testTree.nearest(xx, yy);
        }
        long end = System.currentTimeMillis();
        long dif = end - start;

        NaivePointSet testNaive = new NaivePointSet(points);

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double xx = generator.nextDouble();
            double yy = generator.nextDouble();
            testNaive.nearest(xx, yy);
        }
        long end2 = System.currentTimeMillis();
        long dif2 = end2 - start2;
        double timeComp = (double) dif / (double) dif2;
        System.out.println(timeComp);
        boolean timeRatio = timeComp < 0.1;
        Assert.assertTrue(timeRatio);
        System.out.println(dif);
        System.out.println(dif2);
    }

    @Test
    public void testingLotsOfPointsForCorrectness() {
        Random generator = new Random(80);
        ArrayList<Point> points = new ArrayList<>();
        DoubleStream genDoubles = generator.doubles(-15.0, 15.0);
        for (int i = 0; i < 1000000; i++) {
            double xx = generator.nextDouble() * 10 - 10.0;
            double yy = generator.nextDouble() * 10 - 10.0;
            Point p = new Point(xx, yy);
            points.add(p);
        }
        KDTree testingKD = new KDTree(points);
        NaivePointSet testingNaive = new NaivePointSet(points);
        ArrayList<Point> nearestPointsKD = new ArrayList<>();
        ArrayList<Point> nearestPointsNaive = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            double x = generator.nextDouble() * 10 - 10.0;
            double y = generator.nextDouble() * 10 - 10.0;
            Point kdPoint = testingKD.nearest(x, y);
            Point naivePoint = testingNaive.nearest(x, y);
            nearestPointsKD.add(kdPoint);
            nearestPointsNaive.add(naivePoint);
        }
        Assert.assertEquals(nearestPointsKD, nearestPointsNaive);

    }


}

