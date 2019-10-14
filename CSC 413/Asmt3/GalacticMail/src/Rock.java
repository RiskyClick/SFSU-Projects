import java.awt.*;
import java.util.ArrayList;

class Rock extends Polygon {

    private static int[] xCords = {10,17,26,34,27,36,26,14, 8, 1, 5,1,10};
    private static int[] yCords = { 0, 5, 1, 8,13,20,31,28,31,22,16,7, 0};

    public static ArrayList<Rock> rocks = new ArrayList<>();

    private int width = GameBoard.bWidth;
    private int height = GameBoard.bHeight;

    public boolean visible = true;

    private int rockHeight = 31;
    private int rockWidth = 26;

    public int deltaX = 1;
    public int deltaY = 1;

    public int xpos;
    public int ypos;

    public Rock(int[] x, int[] y, int amt, int randX, int randY){
        super(x, y, amt);
        this.deltaX = (int) (Math.random() * 4 + 1);
        this.deltaY = (int) (Math.random() * 4 + 1);
        this.xpos = randX;
        this.ypos = randY;
    }

    public void update(NASA rocket, ArrayList<Gun> bullets){

        Rectangle rockToCheck = this.recMaker();
        for(int i = 0; i < rocks.size(); i++){

            if(rocks.get(i).visible) {
                Rectangle secondRock = rocks.get(i).recMaker();

                if (rocks.get(i) != this && secondRock.intersects(rockToCheck)) {
                    int tempX = this.deltaX;
                    int tempY = this.deltaY;

                    this.deltaX = rocks.get(i).deltaX;
                    this.deltaY = rocks.get(i).deltaY;

                    rocks.get(i).deltaX = tempX;
                    rocks.get(i).deltaY = tempY;
                }

                Rectangle shipBox = rocket.recMaker();

                if(secondRock.intersects(shipBox) && rocket.getXpos() != rocket.gBWidth / 2 && rocket.getYpos() != rocket.gBHeight / 2){
                    rocket.setXpos(rocket.gBWidth/2);
                    rocket.setYpos(rocket.gBHeight/2);

                    rocket.setDeltaX(0);
                    rocket.setDeltaY(0);

                    rocket.hit();
                }

                for(int j = 0; j < bullets.size(); j++){

                    if(bullets.get(j).visible){

                        if(secondRock.contains(bullets.get(j).getXpos(), bullets.get(j).getYpos())){
                            bullets.get(j).visible = false;
                            rocks.get(i).visible = false;
                            rocks.remove(rocks.get(i));
                        }
                    }
                }
            }
        }

        int xpos = super.xpoints[0];
        int ypos = super.ypoints[0];

        if (xpos < 0 || (xpos + 25) > width) {
            deltaX = -deltaX;
        }

        if (ypos < 0 || (ypos + 50) > height) {
            deltaY = -deltaY;
        }

        for (int i = 0; i < super.xpoints.length; i++){

            super.xpoints[i] += deltaX;
            super.ypoints[i] += deltaY;
        }
    }

    public static int[] getXCords(int randX){

        int[] tempXCords = xCords.clone();

        for (int i = 0; i < tempXCords.length; i++){
            tempXCords[i] += randX;
        }

        return tempXCords;
    }

    public static int[] getYCords(int randY){

        int[] tempYCords = yCords.clone();

        for (int i = 0; i < tempYCords.length; i++){
            tempYCords[i] += randY;
        }

        return tempYCords;
    }

    public Rectangle recMaker() {
        return new Rectangle(super.xpoints[0], super.ypoints[0], rockWidth, rockHeight);
    }
}