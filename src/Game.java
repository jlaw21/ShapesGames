import javax.swing.*;

public class Game extends JFrame {

    Board board;

    public Game(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Shapes Game");

        board = new Board();

        add(board);

        pack();

        setLocationRelativeTo(null);

    }


    public static void main(String[] args) {

        Game game = new Game();
        game.board.setup();
    }
}
