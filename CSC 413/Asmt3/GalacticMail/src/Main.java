public class Main {
    public static void main(String[] args) {

        Title welcomeScreen = new Title();

        while(Title.getStartGame()){
            System.out.println();
        }

        welcomeScreen.dispose();
        GameBoard gameBoard = new GameBoard();
    }
}
