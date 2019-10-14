import java.awt.*;

public class Intro extends java.applet.Applet
{
    Image img1, img2;
    int x1, x2, y1, speed;
    boolean opening, opened;

    public Intro()
    {
        this.x1 = 0;
        this.x2 = 0;
        this.opening = false;
        this.opened = false;
        this.speed = 10;
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0+x1,0,170,360);
        g.fillRect(170+x2,0,230,360);
        g.drawImage(img1,0+x1,0,this);
        g.drawImage(img2,170+x2,1,this);

        g.setColor(Color.red);
        g.setFont(new Font("Verdana", Font.BOLD,  20));
        g.drawString("Spring 2001 CAIT 618 Final Project",50,330+y1);
    }

    public void move(){
        x1 -= speed;
        x2 += speed+1;
        y1 += (int)speed/4;
        if ( (x1<-175) && (x2>175) ) {
            opening=false;
            opened=true;
        }
    }

}