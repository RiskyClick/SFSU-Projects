import java.awt.*;
import java.util.ArrayList;

class Moon {

    public static ArrayList<Moon> moons = new ArrayList<>();

    private int radius;
    private int time;

    private int xpos;
    private int ypos;

    public Moon(int xpos, int ypos){
        this.time = 500;
        this.radius = 55;
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public boolean update(NASA theShip, ArrayList<Rock> rocks) {

        Rectangle moonRec = this.recMaker();
        Rectangle shipRec = theShip.recMaker();

        for(Rock rock : rocks){

            Rectangle rockRec = rock.recMaker();

            if(moonRec.intersects(rockRec)){
                rock.deltaX = -rock.deltaX;
                rock.deltaY = -rock.deltaY;
            }
        }

        if(moonRec.intersects(shipRec)){

            this.time--;

            if(this.time < 0){
                NASA.mailDeliverd();
                return true;
            }
        }
        return false;
    }

    public void paint(Graphics g) {
        g.drawOval(xpos, ypos, radius, radius);
    }

    public Rectangle recMaker() {
        return new Rectangle(this.xpos, this.ypos, 50, 50);
    }
}
