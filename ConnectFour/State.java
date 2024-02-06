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
    private int[][] board = new int[Constants.BOARD_SIZE_WIDTH][Constants.BOARD_SIZE_HEIGHT];

    public boolean isWinner() {
        int total;
        for (int row=0; row<Constants.BOARD_SIZE_WIDTH; row++) {
            total = getBoardCell(row, 0) + getBoardCell(row,1) + getBoardCell(row,2);
            if (total == -4 || total == 4) return true;
        }
        for (int col=0; col<Constants.BOARD_SIZE_HEIGHT; col++) {
            total = getBoardCell(0, col) + getBoardCell(1,col) + getBoardCell(2, col);
            if (total == -4 || total == 4) return true;
        }
        total = getBoardCell(0, 0) + getBoardCell(1,1) + getBoardCell(2, 2);
        if (total == -4 || total == 4) return true;
        total = getBoardCell(2, 0) + getBoardCell(1,1) + getBoardCell(0, 2);
        if (total == -4 || total == 4) return true;
        return false;
    }

    public boolean isTie() {
        for (int row=0; row<Constants.BOARD_SIZE_WIDTH; row++) {
            for (int col=0; col<Constants.BOARD_SIZE_HEIGHT; col++) {
                if (getBoardCell(row,col) == Constants.BLANK) {
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

