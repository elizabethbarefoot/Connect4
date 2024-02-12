package ConnectFour; 

/**
 * Tic-Tac-Toe state variables.
 */
public class State
{
    private int gameState = Constants.STANDBY;
    private int whoseMove = Constants.X;
    private String xName = "";
    private String oName = "";
    private int[][] board = new int[Constants.BOARD_SIZE_HEIGHT][Constants.BOARD_SIZE_WIDTH];

    public boolean isWinner() {
        int total;
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH - 2; col++) {
                total = getBoardCell(row, col) + getBoardCell(row, col + 1) + getBoardCell(row, col + 2);
                if (total == -3 || total == 3) {
                    return true;
                }
            }
        }
        for (int col = 0; col < Constants.BOARD_SIZE_WIDTH; col++) {
            for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT - 2; row++) {
                total = getBoardCell(row, col) + getBoardCell(row + 1, col) + getBoardCell(row + 2, col);
                if (total == -3 || total == 3) {
                    return true;
                }
            }
        }
        for (int row = 0; row < Constants.BOARD_SIZE_HEIGHT - 2; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH - 2; col++) {
                total = getBoardCell(row, col) + getBoardCell(row + 1, col + 1) + getBoardCell(row + 2, col + 2);
                if (total == -3 || total == 3) {
                    return true;
                }
            }
        }
        for (int row = 2; row < Constants.BOARD_SIZE_HEIGHT; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE_WIDTH - 2; col++) {
                total = getBoardCell(row, col) + getBoardCell(row - 1, col + 1) + getBoardCell(row - 2, col + 2);
                if (total == -3 || total == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // Adjusted method for isTie to match a 6x7 board
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
        this.board = new int[Constants.BOARD_SIZE_WIDTH][Constants.BOARD_SIZE_HEIGHT];
        this.gameState = Constants.STANDBY;
        this.whoseMove = Constants.X;
        this.xName = "";
        this.oName = "";
    }
}

