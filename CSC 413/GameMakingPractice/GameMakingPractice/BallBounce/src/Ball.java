import java.awt.*;

public class Ball {

    private int x = 400;
    private int y = 25;
    private int radius = 20;
    private double dx = 0;
    private double dy = 0;

    public Ball() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getRadius() {
        return radius;
    }

    public void right(){
        dx -= 25;
    }

    public void left() {
        dx += 25;
    }

    public void up(){
        dy -= 25;
    }

    public void down(){
        dy += 25;
    }

    public void update(Main startingPoint){
        if (x + dx > startingPoint.getWidth() - radius - 1){
            x = startingPoint.getWidth() - radius - 1;
            dx = -dx;
        }
        else if(x + dx < 0 + radius){
            x = 0 + radius;
            dx = -dx;
        }
        else{
            x += dx;
        }

        if (y == startingPoint.getHeight() - radius - 1){
            if(Math.abs(dx) < .8){
                dx = 0;
            }
        }

        else{

        }
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);

    }
}
