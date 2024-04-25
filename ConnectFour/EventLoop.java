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
                col = ui.getMoveCol(state.getWhoseMove(), state.getXName(), state.getOName());

                // Find the correct row for the move
                row = Constants.BOARD_SIZE_HEIGHT - 1;
                while (row >= 0 && state.getBoardCell(row, col - 1) != Constants.BLANK) {
                    row--;
                }

                if (row >= 0) { // If there's an available space in the column
                    state.setLastMoveRow(row); // Update last move row
                    state.setLastMoveCol(col);
                    state.setGameState(Constants.MAKE_MOVE);
                } else {
                    ui.printInvalidMove(row, col); // If column is full
                }
            } else if (gameState == Constants.MAKE_MOVE) {
                ui.printMove(state, state.getLastMoveRow(), state.getLastMoveCol());
                state.setBoardCell(state.getLastMoveRow(), state.getLastMoveCol() - 1, state.getWhoseMove());
                state.setGameState(Constants.CHECK_IF_WINNER);

            } else if (gameState == Constants.CHECK_IF_WINNER) {
                if (state.isWinner()) {
                    if (state.getWhoseMove() == Constants.X) {
                        state.setGameState(Constants.X_WINS);
                    } else {
                        state.setGameState(Constants.O_WINS);
                    }
                } else {
                    state.setGameState(Constants.CHECK_IF_TIE);
                }

            } else if (gameState == Constants.CHECK_IF_TIE) {
                if (state.isTie()) {
                    ui.printTieGame();
                    state.setGameState(Constants.GAME_OVER);
                } else {
                    state.setWhoseMove(state.getWhoseMove() * -1);
                    if (state.getWhoseMove() == Constants.X) {
                        state.setGameState(Constants.GET_X_MOVE);
                    } else {
                        state.setGameState(Constants.GET_O_MOVE);
                    }
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
}
