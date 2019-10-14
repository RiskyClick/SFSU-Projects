import java.awt.*;

public class Gun extends Polygon {

    private static int[] xCords = {-3,3,3,-3,-3};
    private static int[] yCords = {-3,-3,3,3,-3};

    public int gBWidth = GameBoard.bWidth;
    public int gBHeight = GameBoard.bHeight;

    private double deltaX = 10;
    private double deltaY = 10;

    public boolean visible;

    private double xpos;
    private double ypos;

    public Gun(double xpos, double ypos, double angle){
        super(xCords, yCords, 5);
        this.xpos = xpos;
        this.ypos = ypos;
        this.visible = true;
        this.setDeltaX(this.angleX(angle)*10);
        this.setDeltaY(this.angleY(angle)*10);
    }

    public void update(){

        if(this.visible){
            this.upXpos(this.getDeltaX());

            if(this.getXpos() < 0){
                this.visible = false;

            } else if (this.getXpos() > gBWidth){
                this.visible = false;
            }

            this.upYpos(this.getDeltaY());
            if(this.getYpos() < 0){
                this.visible = false;

            } else if (this.getYpos() > gBHeight){
                this.visible = false;
            }
        }
    }

    public double angleX(double angleX){
        return Math.cos(angleX * Math.PI / 180);
    }

    public double angleY(double angleY){
        return Math.sin(angleY * Math.PI / 180);
    }

    public void setDeltaX(double xVel){ this.deltaX = xVel; }

    public void setDeltaY(double yVel){ this.deltaY = yVel; }

    public void upXpos(double amt) { this.xpos += amt; }

    public void upYpos(double amt) { this.ypos += amt; }

    public double getDeltaX(){ return deltaX; }

    public double getDeltaY(){ return deltaY; }

    public double getXpos(){ return xpos; }

    public double getYpos(){ return ypos; }
}