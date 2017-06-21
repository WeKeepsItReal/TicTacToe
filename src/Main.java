import java.util.Scanner;

/**
 * Created by jarvis on 6/9/2017.
 */
public class Main {
    public static void main(String[] arg) {
        Board board = new Board();

        while (true) {
            System.out.println(board);
            board.playTurn();
        }
    }
}

// make tic-tac-toe game
//make board with system.out.println
//TL/TM/TR if(TL) println.....
//represent board as object
//turn based 2players ends 3in a row or all squares filled.
//ask if you want to play again
//make example board
//
//          *  *
//      *************
//          *  *
//      *************
//          *  *
//
//choose board,pieces,who goes first
//
//
//
//make sure players don't pick same spots......
//
//
//
//WHAT ELSE?¿