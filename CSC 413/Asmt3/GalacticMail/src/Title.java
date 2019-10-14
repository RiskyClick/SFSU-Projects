import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Title extends JFrame {

    public static ArrayList<String> scores = new ArrayList<>();
    private static boolean startGame = true;

    private static int bHeight = 1000;
    private static int bWidth = 1900;

    //used for high nameAndScores
    public static int scroll = 500;

    public Title() {

        this.setSize(bWidth, bHeight);
        this.setTitle("Galactic Mail");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setStartGame(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        BufferedReader level;
        String line;
        try {

            level = new BufferedReader(new InputStreamReader(GameBoard.class.getResource("HighScores.txt").openStream()));
            line = level.readLine();

            while(line != null) {
                scores.add(line);
                line = level.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        TitlePanel gamePanel = new TitlePanel();
        this.add(gamePanel, BorderLayout.CENTER);

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleAtFixedRate(new TitleScreen(this), 0L, 20L, TimeUnit.MILLISECONDS);

        this.setVisible(true);
    }

    private void setStartGame(boolean b) {
        startGame = b;
    }

    public static boolean getStartGame() {
        return startGame;
    }
}

class TitlePanel extends JComponent {

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);

        if(Title.scroll < -750){
            Title.scroll = 500;
        }

        Title.scroll--;

        Font f = new Font(Font.MONOSPACED, Font.BOLD, 150);
        g.setFont(f);
        g.drawString("GALACTIC MAIL!", 325,300);

        Font o = new Font(Font.MONOSPACED, Font.BOLD, 75);
        g.setFont(o);
        g.drawString("PRESS ENTER TO START", 500,500);

        Font n = new Font(Font.MONOSPACED, Font.BOLD, 25);
        g.setFont(n);
        for(int i = 0; i < Title.scores.size(); i++){
            g.drawString(Title.scores.get(i),5,500 + (i * 25) + Title.scroll);
        }
    }
}

class TitleScreen implements Runnable {

    Title title;

    public TitleScreen(Title title) {
        this.title = title;
    }

    @Override
    public void run() {
        title.repaint();
    }
}
