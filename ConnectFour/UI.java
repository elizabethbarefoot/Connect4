package ConnectFour;

import java.util.Scanner;

/**
 * UI class
 */
public class UI
{

    Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);         
    }

    // Utility methods
    public String getXOrO(int whoseMove) {
        if (whoseMove == -1) {
            return "X";
        } else if (whoseMove == 1) {
            return "O";
        } else {
            return " ";
        }
    }

    public String getPlayerName(int whoseMove, String xName, String yName) {
        return (whoseMove == -1) ? xName : yName;
    }

    public boolean isLegalMove(State state, int row, int col) {
        return 1 <= row && row <= Constants.BOARD_SIZE_HEIGHT &&
        1 <= col && col <= Constants.BOARD_SIZE_WIDTH &&
        state.getBoardCell(row - 1, col - 1) == Constants.BLANK;
    }

    public String promptForName(String player) {
        System.out.printf(Constants.GET_PLAYER_NAME, player);
        return scanner.next();
    }

    public int getMoveCol(int whoseMove, String xName, String oName) {
        int col = 0;
        while (true) {
            try {
                System.out.printf(Constants.GET_COL_MOVE, getXOrO(whoseMove), getPlayerName(whoseMove, xName, oName));
                col = scanner.nextInt();
                System.out.println("DEBUG: Column input received: " + col); // Debugging statement
                if (1 > col || col > Constants.BOARD_SIZE_WIDTH) {
                    System.out.println(Constants.INVALID_COL);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println(Constants.INVALID_COL);
                scanner.next();
            }
        }
        return col;
    }

    public boolean startNewGame() {
        System.out.println(Constants.START_NEW_GAME);
        String yesOrNo = scanner.next();
        return yesOrNo.equals("Y") || yesOrNo.equals("y");
    }

    // Printing text methods
    public void printWelcome() {
        System.out.println(Constants.TITLE);
    }

    public void printBoard(State state) {
        System.out.println(Constants.DIVIDER_STRING);
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH; col++) {
                System.out.print("| " + getXOrO(state.getBoardCell(row, col)) + " ");
            }
            System.out.println("|");
            System.out.println(Constants.DIVIDER_STRING);
        }
    }

    public void printInvalidRowOrColumn(int rowOrCol) {
        System.out.printf(Constants.INVALID_COL);
    }

    public void printInvalidMove(int row, int col) {
        System.out.printf(Constants.INVALID_MOVE_ERROR, row, col);
    }

    public void printMove(State state, int row, int col) {
        System.out.printf(
            Constants.PRINT_MOVE,
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state.getXName(), state.getOName()), 
            row,
            col
        );
        System.out.println();
    } 

    public void printWinner(State state) {
        System.out.printf(
            Constants.WINNER, 
            getXOrO(state.getWhoseMove()), 
            getPlayerName(state.getWhoseMove(), state.getXName(), state.getOName())
        );
        System.out.println();
    }

    public void printTieGame() {
        System.out.println(Constants.TIE_GAME);
    }
}
