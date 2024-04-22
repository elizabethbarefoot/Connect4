package ConnectFour;

public class EventLoop {

    // Instance variables for the UI and State classes
    State state = new State();
    UI ui = new UI();
    int row;
    int col;

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();
        eventLoop.run();
    }

    public void run() {
        while (state.getGameState() != Constants.QUIT_PROGRAM) {
            int gameState = state.getGameState();
            if (gameState == Constants.STANDBY) {
                state.setGameState(Constants.GET_X_NAME);
            } else if (gameState == Constants.GET_X_NAME) {
                state.setXName(ui.promptForName("X"));
                state.setGameState(Constants.GET_O_NAME);
            } else if (gameState == Constants.GET_O_NAME) {
                state.setOName(ui.promptForName("O"));
                state.setGameState(Constants.GET_X_MOVE);
            } else if (gameState == Constants.GET_X_MOVE || gameState == Constants.GET_O_MOVE) {
                ui.printBoard(state); // Print the board before getting move
                row = ui.getMoveRow(state.getWhoseMove(), state.getXName(), state.getOName());
                if (!ui.isLegalMove(state, 1, row)) {
                    System.out.println("Column is full. Please choose another column.");
                    continue;
                }
                int row = makeMove(state, col);
                state.setGameState(Constants.MAKE_MOVE);
                state.setLastMoveRow(row);
                state.setLastMoveCol(col);
            } else if (gameState == Constants.MAKE_MOVE) {
                ui.printMove(state, state.getLastMoveRow(), state.getLastMoveCol());
                if (state.isWinner()) {
                    if (state.getWhoseMove() == Constants.X) {
                        state.setGameState(Constants.X_WINS);
                    } else {
                        state.setGameState(Constants.O_WINS);
                    }
                } else if (state.isTie()) {
                    state.setGameState(Constants.GAME_OVER);
                    ui.printTieGame();
                } else {
                    state.setWhoseMove(state.getWhoseMove() * -1);
                    state.setGameState(state.getWhoseMove() == Constants.X ? Constants.GET_X_MOVE : Constants.GET_O_MOVE);
                }
            } else if (gameState == Constants.X_WINS || gameState == Constants.O_WINS) {
                ui.printWinner(state);
                state.setGameState(Constants.GAME_OVER);
            } else if (gameState == Constants.GAME_OVER) {
                if (ui.startNewGame()) {
                    state.resetGameBoard(); // Reset the game board
                    state.setGameState(Constants.STANDBY);
                } else {
                    state.setGameState(Constants.QUIT_PROGRAM);
                }
            }
        }
    }

    public int makeMove(State state, int col) {
        for (int row = Constants.BOARD_SIZE_HEIGHT - 1; row >= 0; row--) {
            if (state.getBoardCell(row, col) == Constants.BLANK) {
                state.setBoardCell(row, col, state.getWhoseMove());
                return row; // Return the row where the disc was placed
            }
        }
        return -1; // Column is full
    }
}
