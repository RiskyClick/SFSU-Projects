import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameBoard extends JFrame {

    public static ArrayList<String> nameAndScores = new ArrayList<>();
    public static ArrayList<Integer> justScores = new ArrayList<>();
    public static ArrayList<String> justNames = new ArrayList<>();
    public static ArrayList<Gun> bullets = new ArrayList<>();

    private static boolean gameOver = false;
    public static boolean keyHold = false;
    public static boolean changeOnce = true;

    public static int bHeight = 1000;
    public static int bWidth = 1900;

    public static int keyHeldCode;
    public static int spaceMoney;



    public GameBoard() {

        this.setSize(bWidth, bHeight);
        this.setTitle("Galactic Mail");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    keyHeldCode = e.getKeyCode();
                    keyHold = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyHeldCode = e.getKeyCode();
                    keyHold = true;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    keyHeldCode = e.getKeyCode();
                    keyHold = true;
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    bullets.add(new Gun(GameFrame.rocket.justTheTipX(),
                                        GameFrame.rocket.justTheTipY(),
                                        GameFrame.rocket.getAngle()));
                } else if (e.getKeyCode() == KeyEvent.VK_Y) {
                    keyHeldCode = e.getKeyCode();
                } else if (e.getKeyCode() == KeyEvent.VK_N) {
                    keyHeldCode = e.getKeyCode();
                }
            }

            public void keyReleased(KeyEvent e) {
                keyHold = false;
            }
        });

        BufferedReader level;
        String line;
        try {

            level = new BufferedReader(new InputStreamReader(GameBoard.class.getResource("HighScores.txt").openStream()));
            line = level.readLine();

            while(line != null) {
                nameAndScores.add(line);
                line = level.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(String score : nameAndScores){
//            score = score.replaceAll("[^0-9]","");
//            int toInt = Integer.parseInt(score);
//            justScores.add(toInt);
//        }
//
//        for(String name : nameAndScores){
//            name = name.replaceAll("[^a-zA-Z]","");
//            justNames.add(name);
//        }

        GameFrame gamePanel = new GameFrame();
        this.add(gamePanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new GameScreen(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }

    public static void setGameOver(boolean b) {
        gameOver = b;
    }

    public static boolean getGameOver() {
        return gameOver;
    }

}

class GameScreen implements Runnable{

    GameBoard board;

    public GameScreen(GameBoard board){
        this.board = board;
    }

    @Override
    public void run() {
        board.repaint();
    }

}

class GameFrame extends JComponent {

    public static ArrayList<Rock> rocks = new ArrayList<>();
    public static ArrayList<Moon> moons = new ArrayList<>();
    static NASA rocket = new NASA();

    public GameFrame() {

        for(int i = 0; i < 10; i++){
            int randX = (int) (Math.random() * (GameBoard.bWidth - 40) + 1);
            int randY = (int) (Math.random() * (GameBoard.bHeight - 40) + 1);

            rocks.add(new Rock(Rock.getXCords(randX), Rock.getYCords(randY), 13, randX, randY));
            Rock.rocks = rocks;
        }

        for(int i = 0; i < 5; i++){
            int randX = (int) (Math.random() * (GameBoard.bWidth - 40) + 1);
            int randY = (int) (Math.random() * (GameBoard.bHeight - 40) + 1);

            moons.add(new Moon(randX, randY));
            Moon.moons = moons;

        }
    }

    public void paint(Graphics g) {
        Graphics2D set = (Graphics2D)g;
        AffineTransform orient = new AffineTransform();

        set.setColor(Color.BLACK);
        set.fillRect(0, 0, getWidth(), getHeight());

        set.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        set.setPaint( Color.WHITE );

        if(rocket.getLives() <= 0){
            GameBoard.setGameOver(true);
        }

        if(GameBoard.getGameOver()) {
//            if(GameBoard.justScores.get(GameBoard.justScores.size()) < GameBoard.spaceMoney){
//                GameBoard.nameAndScores.clear();
//            }
//
//            for(int i = 0; i < GameBoard.justScores.size(); i++){
//                if(GameBoard.spaceMoney > GameBoard.justScores.get(i) && GameBoard.changeOnce){
//                    Font highScoreScreen = new Font(Font.MONOSPACED, Font.BOLD, 100);
//                    g.setFont(highScoreScreen);
//                    GameBoard.changeOnce = false;
//
//                    g.drawString("  HIGH SCORE!", 700, 300);
//                    g.drawString("ENTER YOUR NAME: ", 700, 350);
//
//                    Scanner scanner = new Scanner( System.in );
//                    String input = scanner.nextLine();
//
//                    GameBoard.justScores.add(i, GameBoard.spaceMoney);
//                    GameBoard.justNames.add(i, input);
//
//                    String newHighScoreList = Integer.toString(GameBoard.justScores.get(i));
//                    GameBoard.nameAndScores.add(GameBoard.justNames.get(i) + ": " + newHighScoreList);
//                    if(i == GameBoard.justScores.size()) {
//                        try {
//                            editTxtFile(GameBoard.nameAndScores);
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }

            Font gameOverScreen = new Font(Font.MONOSPACED, Font.BOLD, 50);
            g.setFont(gameOverScreen);

            g.drawString("   GAME OVER!",700, 300);
            g.drawString("  FINAL SCORE: " + GameBoard.spaceMoney,700, 350);
            g.drawString("PLANETS WITH MAIL: " + rocket.getMail(),700, 400);
            g.drawString("    NEW GAME?",700,450);
            g.drawString("      Y / N",700,500);

            if(GameBoard.keyHeldCode == KeyEvent.VK_Y){

                NASA newShip = new NASA();
                rocket = newShip;

                GameBoard.spaceMoney = 0;
                GameBoard.setGameOver(false);

                GameBoard.changeOnce = true;

                while(rocks.size() < 10){
                    int randX = (int) (Math.random() * (GameBoard.bWidth - 40) + 1);
                    int randY = (int) (Math.random() * (GameBoard.bHeight - 40) + 1);

                    rocks.add(new Rock(Rock.getXCords(randX), Rock.getYCords(randY), 13, randX, randY));
                    Rock.rocks = rocks;
                }
            } else if (GameBoard.keyHeldCode == KeyEvent.VK_N){
                System.exit(0);
            }
        }

        Font font = new Font(Font.MONOSPACED, Font.BOLD, 15);
        g.setFont(font);

        for(int i = 0; i < rocks.size(); i++){

            if(rocks.get(i).visible){
                rocks.get(i).update(rocket, GameBoard.bullets);
                set.draw(rocks.get(i));
            }
        }

        for(int i = 0; i < moons.size(); i++) {

            moons.get(i).paint(g);

            if (moons.get(i).update(rocket,rocks)) {
                moons.remove(moons.get(i));
                GameBoard.spaceMoney += 10000;
            }
        }

        if(moons.size() <= 0){

            for(int i = 0; i < 5; i++){

                int randX = (int) (Math.random() * (GameBoard.bWidth - 40) + 1);
                int randY = (int) (Math.random() * (GameBoard.bHeight - 40) + 1);

                moons.add(new Moon(randX, randY));
                Moon.moons = moons;

            }
        }

        if(GameBoard.keyHold == true && GameBoard.keyHeldCode == KeyEvent.VK_RIGHT){
            rocket.clockWise();

        } else if(GameBoard.keyHold == true && GameBoard.keyHeldCode == KeyEvent.VK_LEFT){
            rocket.counterClockWise();

        } else if (GameBoard.keyHold == true && GameBoard.keyHeldCode == KeyEvent.VK_UP){
            rocket.setMovingAngle(rocket.getAngle());
            rocket.upDeltaX(rocket.upAngleX(rocket.getMovingAngle())*0.1);
            rocket.upDeltaY(rocket.upAngleY(rocket.getMovingAngle())*0.1);
        }

        rocket.update();
        set.setTransform(orient);

        if(!GameBoard.getGameOver()){
            GameBoard.spaceMoney--;
        }

        g.drawString("SPACEMONEY: " + GameBoard.spaceMoney,2, 17);
        g.drawString("LIVES: " + rocket.getLives(), 1800, 17);

        set.translate(rocket.getXpos(), rocket.getYpos());
        set.rotate(Math.toRadians(rocket.getAngle()));

        set.draw(rocket);

        for(int i = 0; i < GameBoard.bullets.size(); i++){

            GameBoard.bullets.get(i).update();

            if(GameBoard.bullets.get(i).visible){

                set.setTransform(orient);
                set.translate(GameBoard.bullets.get(i).getXpos(),GameBoard.bullets.get(i).getYpos());
                set.draw(GameBoard.bullets.get(i));
            }
        }
    }

//    private void editTxtFile(ArrayList<String> nameAndScores) throws FileNotFoundException, UnsupportedEncodingException {
//        PrintWriter writer = new PrintWriter("src\\HighScores.txt", "UTF-8");
//        for(String i : nameAndScores){
//            writer.println(i);
//        }
//        writer.close();
//    }
}