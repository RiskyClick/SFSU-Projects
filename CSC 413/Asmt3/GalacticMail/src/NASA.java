import java.awt.*;

public class NASA extends Polygon {

    private static int[] xCords = {-20,-10,-5,0,5,10,15,10, 5, 0,-5,-10,-20,-12,-20,-12,-20};
    private static int[] yCords = {10,  10, 7,5,4, 3, 0,-3,-4,-5,-7,-10,-10, -5,  0,  5, 10};

    public int gBWidth = GameBoard.bWidth;
    public int gBHeight = GameBoard.bHeight;

    private double xpos = gBWidth / 2;
    private double ypos = gBHeight / 2;

    private double movingAngle = 0;
    private static int mail = 0;

    private double deltaX = 0;
    private double deltaY = 0;

    private int shipHeight = 20;
    private int shipWidth = 25;

    private double angle = 0;
    private int lives = 3;

    public NASA(){
        super(xCords, yCords, 17);
    }

    public void update(){

        this.upXpos(this.getDeltaX());

        if(this.getXpos() < 0){
            this.setXpos(gBWidth);

        } else if (this.getXpos() > gBWidth){
            this.setXpos(0);
        }

        this.upYpos(this.getDeltaY());

        if(this.getYpos() < 0){
            this.setYpos(gBHeight);

        } else if (this.getYpos() > gBHeight){
            this.setYpos(0);
        }
    }

    public void clockWise(){

        if(getAngle() >= 355) {
            angle = 0;
        } else {
            angle += 5;
        }
    }

    public void counterClockWise(){

        if(getAngle() < 0) {
            angle = 355;
        } else {
            angle -= 5;
        }
    }

    public void hit() {
        this.lives--;
    }

    public Rectangle recMaker(){
        return new Rectangle((int) (getXpos() - 14), (int) (getYpos() - 15), width(), height());
    }

    public double upAngleX(double angleX){
        return Math.cos(angleX * Math.PI / 180);
    }

    public double upAngleY(double angleY){
        return Math.sin(angleY * Math.PI / 180);
    }

    public double justTheTipX(){
        return this.getXpos() + Math.cos(angle) * 14;
    }

    public double justTheTipY(){  return this.getYpos() + Math.sin(angle) * 14; }

    public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }

    public void upXpos(double incAmt) { this.xpos += incAmt; }

    public void upYpos(double incAmt) { this.ypos += incAmt; }

    public void setDeltaX(double xVel){ this.deltaX = xVel; }

    public void setDeltaY(double yVel){ this.deltaY = yVel; }

    public void setXpos(double xCent){ this.xpos = xCent; }

    public void setYpos(double yCent){ this.ypos = yCent; }

    public void upDeltaX(double amt){ this.deltaX += amt; }

    public void upDeltaY(double amt){ this.deltaY += amt; }

    public double getMovingAngle(){ return movingAngle; }

    public static void mailDeliverd() {
        mail++;
    }

    public static int getMail() {
        return mail;
    }

    public double getDeltaX(){ return deltaX; }

    public double getDeltaY(){ return deltaY; }

    public int height(){ return shipHeight; }

    public double getAngle(){ return angle; }

    public double getXpos(){
        return xpos;
    }

    public int getLives() {
        return lives;
    }

    public double getYpos(){ return ypos; }

    public int width(){ return shipWidth; }



}