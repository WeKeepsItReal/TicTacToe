import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;

import static javafx.scene.input.KeyCode.X;
import static sun.rmi.transport.TransportConstants.Return;

/**
 * Created by jarvis on 6/13/2017.
 */
public class Board {
    private char[] topRow;
    private char[] midRow;
    private char[] botRow;
    private static final int ROW_SIZE = 9;
    private boolean playerOneTurn = true;

    Scanner reader = new Scanner(System.in);

    public Board() {
        topRow = new char[ROW_SIZE];
        midRow = new char[ROW_SIZE];
        botRow = new char[ROW_SIZE];
        // This for loop sets up the Board
        for (int i = 0; i < ROW_SIZE; i++) {
            topRow[i] = ' ';
            midRow[i] = ' ';
            botRow[i] = ' ';
        }

        topRow[2] = '*';
        topRow[6] = '*';

        midRow[2] = '*';
        midRow[6] = '*';

        botRow[2] = '*';
        botRow[6] = '*';
    }

    public void playTurn() {
        if (this.playerOneTurn) {
            this.askPlayerLocation('X');
        } else {
            this.askPlayerLocation('O');
        }
        this.playerOneTurn = !this.playerOneTurn;
        checkEndCondition();
        System.out.println();
    }

    public void askPlayerLocation(char playerPiece) {
        System.out.println("Where do you want to go? TL,TM,TR, ML,MM.MR BL,BM,BR");
        String location = reader.nextLine();
        int index = 0;
        if (location.equals("TL") || location.equals("ML") || location.equals("BL")) {
            index = 0;
        }
        if (location.equals("TM") || location.equals("MM") || location.equals("BM")) {
            index = 4;
        }
        if (location.equals("TR") || location.equals("MR") || location.equals("BR")) {
            index = 8;
        }
        this.checkEmptySpace(location, index, playerPiece);
    }

    public void checkEmptySpace(String location, int index, char playerPiece) {
        if ((location.equals("TL") || location.equals("TM") || location.equals("TR"))) {
            if (topRow[index] == ' ') {
                topRow[index] = playerPiece;
            } else {
                System.out.println("Space taken, Please select a new space.");
                System.out.println();
                this.askPlayerLocation(playerPiece);
            }
        } else if ((location.equals("ML") || location.equals("MM") || location.equals("MR"))) {
            if (midRow[index] == ' ') {
                midRow[index] = playerPiece;
            } else {
                System.out.println("Space taken, Please select a new space.");
                System.out.println();
                this.askPlayerLocation(playerPiece);
            }
        } else if ((location.equals("BL") || location.equals("BM") || location.equals("BR"))) {
            if (botRow[index] == ' ') {
                botRow[index] = playerPiece;
            } else {
                System.out.println("Space taken, Please select a new space.");
                System.out.println();
                this.askPlayerLocation(playerPiece);
            }
        } else {
            System.out.println("this is not a valid location.");
            System.out.println();
            this.askPlayerLocation(playerPiece);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(topRow);
        sb.append("\n");

        sb.append("*********\n");

        sb.append(midRow);
        sb.append("\n");

        sb.append("*********\n");

        sb.append(botRow);
        sb.append("\n");

        return sb.toString();
    }

    public void checkEndCondition() {
        // Check Draw Condition
        if (topRow[0] != ' '
                && topRow[4] != ' '
                && topRow[8] != ' '
                && midRow[0] != ' '
                && midRow[4] != ' '
                && midRow[8] != ' '
                && botRow[0] != ' '
                && botRow[4] != ' '
                && botRow[8] != ' '
                ) {
            System.out.println(this);
            System.out.println("There is no more spaces left. Game OVER. GG");
            System.exit(0);
        }

        // Check win condition for x
        // Check each row for 3 in a row Player X
        checkRowWinCondition('X');
        // Check for 3 in a column Player X
        checkColWinCondition('X');
        // Check for 3 diagonally Player X
        checkDiagWinCondition('X');

        // Check win condition for O
        // Check for 3 in a row Player O
        checkRowWinCondition('O');
        // Check for 3 in a column Player X
        checkColWinCondition('O');
        // Check for 3 diagonally Player X
        checkDiagWinCondition('O');
    }

    private void checkRowWinCondition(char playerPiece){
        if (topRow[0] == playerPiece && topRow[4] == playerPiece && topRow[8] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " Player wins with 3 in a row");
            System.exit(0);
        } else if (midRow[0] == playerPiece && midRow[4] == playerPiece && midRow[8] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " Player wins with 3 in a row");
            System.exit(0);
        } else if (botRow[0] == playerPiece && botRow[4] == playerPiece && botRow[8] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " Player wins with 3 in a row");
            System.exit(0);
        }
    }

    private void checkColWinCondition(char playerPiece){
        if (topRow[0] == playerPiece && midRow[0] == playerPiece && botRow[0] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece +" player wins with 3 in a row");
            System.exit(0);
        } else if (topRow[4] == playerPiece && midRow[4] == playerPiece && botRow[4] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " player wins with 3 in a row");
            System.exit(0);
        } else if (topRow[8] == playerPiece && midRow[8] == playerPiece && botRow[8] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " player wins with 3 in a row");
            System.exit(0);
        }
    }

    private void checkDiagWinCondition(char playerPiece){
        if (topRow[0] == playerPiece && midRow[4] == playerPiece && botRow[8] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " player wins with 3 in a row");
            System.exit(0);
        } else if (topRow[8] == playerPiece && midRow[4] == playerPiece && botRow[0] == playerPiece) {
            System.out.println(this);
            System.out.println(playerPiece + " player wins with 3 in a row");
            System.exit(0);
        }
    }
}


//Ask for rematch

//make git 'properly'