package ConnectFour; 

/**
 * Connect4 state variables.
 */
public class State
{
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.X;
    private String xName = "";
    private String oName = "";
    private int[][] board = new int[Constants.BOARD_SIZE_HEIGHT][Constants.BOARD_SIZE_WIDTH];
    private int lastMoveRow;
    private int lastMoveCol;

    // Initialize the game board with default values
    public State() {
        // Initialize the game board with blank cells
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH; col++) {
                board[row][col] = Constants.BLANK;
            }
        }
    }

    public boolean isWinner() {
        int total;
        //Horizontal
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH - 3; col++) {
                if (getBoardCell(row, col) != Constants.BLANK &&
                getBoardCell(row, col) == getBoardCell(row, col + 1) &&
                getBoardCell(row, col) == getBoardCell(row, col + 2) &&
                getBoardCell(row, col) == getBoardCell(row, col + 3)) {
                    return true;
                }
            }
        }

        // Vertical
        for (int col = 0; col < Constants.BOARD_SIZE_WIDTH; col++) {
            for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT - 3; row++) {
                if (getBoardCell(row, col) != Constants.BLANK &&
                getBoardCell(row, col) == getBoardCell(row + 1, col) &&
                getBoardCell(row, col) == getBoardCell(row + 2, col) &&
                getBoardCell(row, col) == getBoardCell(row + 3, col)) {
                    return true;
                }
            }
        }

        // Diagonal (from top-left to bottom-right)
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT - 3; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH - 3; col++) {
                if (getBoardCell(row, col) != Constants.BLANK &&
                getBoardCell(row, col) == getBoardCell(row + 1, col + 1) &&
                getBoardCell(row, col) == getBoardCell(row + 2, col + 2) &&
                getBoardCell(row, col) == getBoardCell(row + 3, col + 3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTie() {
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH; col++) {
                if (getBoardCell(row, col) == Constants.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getGameState() {
        return this.gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getWhoseMove() {
        return this.whoseMove;
    }

    public void setWhoseMove(int whoseMove) {
        this.whoseMove = whoseMove;
    }

    public String getXName() {
        return xName;
    }

    public void setXName(String xName) {
        this.xName = xName;
    }

    public String getOName() {
        return oName;
    }

    public void setOName(String oName) {
        this.oName = oName;
    }

    public int getBoardCell(int row, int col) {
        return this.board[row][col];
    }

    public void setBoardCell(int row, int col, int value) {
        this.board[row][col] = value;
    }

    public void resetGameBoard() {
        this.board = new int[Constants.BOARD_SIZE_HEIGHT][Constants.BOARD_SIZE_WIDTH]; // Fix the size
        this.gameState = Constants.STANDBY;
        this.whoseMove = Constants.X;
        this.xName = "";
        this.oName = "";
    }

    public int getLastMoveRow() {
        return lastMoveRow;
    }

    public void setLastMoveRow(int lastMoveRow) {
        this.lastMoveRow = lastMoveRow;
    }

    public int getLastMoveCol() {
        return lastMoveCol;
    }

    public void setLastMoveCol(int lastMoveCol) {
        this.lastMoveCol = lastMoveCol;
    }

    public int[][] getBoard() {
        return board;
    }
} 




