package vinjetter.TFYA15;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Physics {

    static Point SpringOrigin = new Point(250, 250);
    static Point StartPoint = new Point((int) (SpringOrigin.getX() + 10), (int) (SpringOrigin.getY() + 10));

    private static final double springLength = 10;
    private static final double springConstant = 10;
    private static final double gravityConstant = 9.82;
    private static final double mass = 0.1;
    private static final double timeStep = 1d / 60d; // 1/fps

    public static void main(String[] args) {
        //createPosStream();
        simulate(createPosStream());
    }

    private static Stream<Point> createPosStream() {
        double xPos = StartPoint.getY();
        double xVelocity = 3;
        double xAcceleration;

        double yPos = StartPoint.getY();
        double yVelocity = 2;
        double yAcceleration;

        List<Point> posList = new LinkedList<>();

        int time = 60; //Time in seconds
        for (int n = 1; n < time / timeStep; n++) {

            yAcceleration = yAcceleration(xPos, yPos) / 10;
            xAcceleration = xAcceleration(xPos, yPos) / 10;

            yVelocity += yAcceleration * timeStep;
            xVelocity += xAcceleration * timeStep;

            yPos += yVelocity * timeStep;
            xPos += xVelocity * timeStep;

            posList.add(new Point((int) xPos, (int) yPos));
            print(n, xPos, yPos, xVelocity, yVelocity, xAcceleration, yAcceleration);
        }
        return posList.stream();
    }

    private static double distanceFromOrigin(double xPos, double yPos) {
        return Math.sqrt(Math.pow(xPos - SpringOrigin.getX(), 2) + Math.pow(yPos - SpringOrigin.getY(), 2));
    }

    private static double getSpringAcceleration(double xPos, double yPos) {
        return springConstant * (distanceFromOrigin(xPos, yPos) - springLength) / mass;
    }

    private static double yAcceleration(double xPos, double yPos) {

        double SpringAcc = getSpringAcceleration(xPos, yPos) * Math.abs(Math.cos(distanceFromOrigin(xPos, yPos) / (yPos - SpringOrigin.getY())));

        if (yPos > SpringOrigin.getY()) {
            return -(SpringAcc + gravityConstant);
        }
        if (yPos <= SpringOrigin.getY()) {
            return SpringAcc - gravityConstant;
        }
        return 0;
    }

    private static double xAcceleration(double xPos, double yPos) {
        double SpringAcc = getSpringAcceleration(xPos, yPos) * Math.cos(distanceFromOrigin(xPos, yPos) / (xPos - SpringOrigin.getX()));

        if (xPos > SpringOrigin.getX()) {
            return -SpringAcc;
        }
        if (xPos <= SpringOrigin.getY()) {
            return SpringAcc;
        }
        return 0;
    }

    private static void print(int n, double xPos, double yPos, double xVelocity, double yVelocity, double xAcceleration, double yAcceleration) {
        System.out.println("n: " + n + ", x: " + xPos + ", y: " + yPos + ", xV: " + xVelocity + ", yV: " + yVelocity + ", xAcc: " + xAcceleration + ", yAcc: " + yAcceleration);
    }

    public static void simulate(Stream<Point> s) {
        JFrame frame = new JFrame();
        frame.setTitle("Spring Simulator");
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel();
        l1.setLocation((int) SpringOrigin.getX(), (int) (SpringOrigin.getY()));
        l1.setText("o");
        l1.setSize(50, 50); // Needs to set a size or the label won't show up
        frame.add(l1);

        JLabel l = new JLabel();
        l.setText("0");
        frame.add(l);

        s.forEach(v -> {

            l.setLocation((int) v.getX(), (int) v.getY());

            double startTime = System.currentTimeMillis();
            while (startTime + 1000 * timeStep > System.currentTimeMillis()) {
                System.out.println("Wait: " + (System.currentTimeMillis() - startTime) + "ms");
            }
        });
    }
}
