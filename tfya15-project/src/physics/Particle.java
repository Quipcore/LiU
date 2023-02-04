package physics;

import java.awt.*;
import java.util.Random;


public class Particle {

    protected final double g = 9.82; //Never Used, using Gv instead
    private double Gv;
    private Color color;
    private double r;
    protected double m;
    private double A;
    private double vx;
    private double vy;
    private double x;
    private double y;
    private double deltaTime;
    private Random random = new Random();

    private PhysicsCanvas canvas;

    public double getM() {return m;}
    public double getR() {
        return r;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public Particle(double x, double y, double r, double m, Color color, double deltaTime, PhysicsCanvas canvas) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.m = m;

        double speedX = random.nextInt(40);

        this.vx = -speedX;
        this.vy = 0;
        this.deltaTime = deltaTime;
        this.Gv = g * deltaTime;
        this.color = color;
        A = r * r * Math.PI;
        this.r = r * 800;
        this.canvas = canvas;
    }

    // Update method called in canvas
    public void update() {

        // Collision performance and detection
        for (Particle j : canvas.balls) {
            if (this != j) {

                double xd = x - j.getX();
                double yd = y - j.getY();

                double sumR = r + j.getR();

                double dist = Math.sqrt((xd * xd) + (yd * yd));

                if (dist < sumR) {

                    double newvx1 = (vx * (m - j.getM()) + (2 * j.getM() * j.vx)) / (m + j.getM());
                    double newvy1 = (vy * (m - j.getM()) + (2 * j.getM() * j.vy)) / (m + j.getM());

                    double newvx2 = (j.vx * (j.getM() - m) + (2 * m * vx)) / (j.getM() + m);
                    double newvy2 = (j.vy * (j.getM() - m) + (2 * m * vy)) / (j.getM() + m);

                    vx = newvx1;
                    vy = newvy1;

                    j.vx = newvx2;
                    j.vy = newvy2;
                }
            }
        }

        //Leapfrog Integration
        double dvx = airResi(vx);
        double dvy = Gv + airResi(vy);

        vx += dvx;
        vy += dvy;
        x += vx * deltaTime;
        y += vy * deltaTime;

        //Wall detection, totally elastic
        if (x < r) {
            x = r;
            vx = Math.abs(vx);
        } else if (x > 800 - r) {
            x = 800 - r;
            vx = -Math.abs(vx);
        }

        //Ground and roof collision detection, totally elastic
        if (y < r) {
            y = r;
            vy = Math.abs(vy);

        } else if (y > 600 - r) {
            y = 600 - r;
            vy = -Math.abs(vy) /* 0.8*/;
        }

        //Apply friction when the ball is on the floor, Currently doesn't seem to do much
        if (getY() < 600 - r) {
            friction(0.5);
        }

    }

    //Calc acceleration caused by drag
    private double airResi(double v) {
        double Air = ((0.5 * 0.45 * A * 1.29 * v * v) / m) * deltaTime;
        if (v > 0) {
            return Air * (-1);
        } else {
            return Air;
        }
    }

    private void friction(double n) {return;}

    public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) Math.round(x - r), (int) Math.round(y - r), (int) Math.round(2 * r), (int) Math.round(2 * r));
    }

}
        



