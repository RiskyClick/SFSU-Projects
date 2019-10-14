import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class Main extends Applet implements Runnable, KeyListener{

    private Image theImage;
    private Graphics theGraphic;

    private int score = 0;
    Ball ball;

    Platform platform[] = new Platform[7];
    Item Item[] = new Item[3];

    double backgroundX = 0;
    double backgroundDx = .3;

    URL url;
    Image background;

    int levelCheck = 0;
    boolean gameOver = false;

    boolean mouse = false;

    public void init() {

        setSize(800, 600);
        addKeyListener(this);

    }

    public void start() {
        ball = new Ball();
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            if (backgroundX > getWidth() * -1) {
                backgroundX -= backgroundDx;
            } else {
                backgroundX = 0;
            }

            Random r = new Random();
            ball.update(this);
            repaint();
            try {
                //Frame Rate
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Graphics graphics) {
        if (theImage == null) {
            //Get the size THIS applet and set the size to that
            theImage = createImage(this.getSize().width, this.getSize().height);
            theGraphic = theImage.getGraphics();
        }
        theGraphic.setColor(getBackground());
        theGraphic.fillRect(0, 0, this.getSize().width, this.getSize().height);

        theGraphic.setColor(getForeground());
        paint(theGraphic);

        graphics.drawImage(theImage, 0, 0, this);
    }

    public void paint(Graphics g) {

        g.drawImage(background, (int) backgroundX, 0, this);
        g.drawImage(background, (int) backgroundX + getWidth(), 0, this);
        ball.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                ball.left();
                break;

            case KeyEvent.VK_RIGHT:
                ball.right();
                break;

            case KeyEvent.VK_UP:
                ball.up();
                break;

            case KeyEvent.VK_DOWN:
                ball.down();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}

