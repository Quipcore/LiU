package physics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;


import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;


public class PhysicsCanvas extends Canvas implements Runnable, MouseInputListener {

    private boolean running;
    public ArrayList<Particle> balls = new ArrayList<Particle>();
    private double deltaTime;
    private Random random = new Random();


    public PhysicsCanvas() {
        Dimension d = new Dimension(800, 600);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        deltaTime = 0.05;

        addMouseListener(new MouseInputAdapter() {
            public void mousePressed(MouseEvent e) {
                createBall(e);
            }
        });
    }

    public void createBall(MouseEvent e) {
        balls.add(new Particle((double) e.getX(), (double) e.getY(), 0.02, 10, randomColor(), deltaTime, this));
    }

    public Color randomColor() {
        int r = random.nextInt(255);
        int x = random.nextInt(255);
        int y = random.nextInt(255);

        return new Color(r, x, y);
    }

    //Modified code Skeleton
    @Override
    public void run() {
        long dtInMs = Long.valueOf(Math.round(deltaTime * 1000));
        while (running) {

            try {
                update();
                render();
            } catch (ConcurrentModificationException e) { // Caused when balls collision doesn't go through properly
                System.out.println("WARNING: System can't keep up! Last update skipped.");
                continue;
            }


            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
    }

    //Part of code skeleton
    public void start() {
        if (!running) {
            Thread t = new Thread(this);
            createBufferStrategy(3);
            running = true;
            t.start();
        }
    }

    //Part of code skeleton
    private void render() {
        BufferStrategy strategy = getBufferStrategy();
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());


        for (Particle i : balls) {
            i.render(g);
        }

        strategy.show();
    }

    //  Called to update every balls in the scene
    private void update() {

        for (Particle i : balls) {
            i.update();
        }
    }

    //Part of code skeleton
    public static void main(String[] args) {
        JFrame myFrame = new JFrame("My physics canvas");
        PhysicsCanvas physics = new PhysicsCanvas();
        myFrame.add(physics);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
        physics.start();
        myFrame.setLocationRelativeTo(null);
    }

    // Leftovers from MouseInputListener interface
    @Override
    public void mouseClicked(MouseEvent e) {return;}
    @Override
    public void mousePressed(MouseEvent e) {return;}
    @Override
    public void mouseReleased(MouseEvent e) {return;}
    @Override
    public void mouseEntered(MouseEvent e) {return;}
    @Override
    public void mouseExited(MouseEvent e) {return;}
    @Override
    public void mouseDragged(MouseEvent e) {return;}
    @Override
    public void mouseMoved(MouseEvent e) {return;}
}
