package tictactoe.gamecomponents;

import tictactoe.utils.*;

import java.util.ArrayList;

public class Game {
    private static Board board;
    private static Player player1;
    private static Player player2;
    private static GameMode gameMode;
    private static Player currentPlayer;
    private static GameStatus gameStatus;

    public static void setGame(GameMode gM) {
        gameMode = gM;
        board = new Board();
        player1 = new Player();
        player2 = (gameMode == GameMode.SOLO) ? new Player(PlayerType.BOT) : new Player();
        player1.setSymbol(Symbol.CROSS);
        player2.setSymbol(Symbol.CIRCLE);
        currentPlayer = player1;
    }

    public static Player switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        return currentPlayer;
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setPlayerSymbol(Symbol symbol) {
        player1.setSymbol(symbol);
        if (symbol == Symbol.CIRCLE) {
            player2.setSymbol(Symbol.CROSS);
            currentPlayer = player2;
        } else {
            player2.setSymbol(Symbol.CIRCLE);
        }
    }

    public static GameStatus isGameFinished(Position position) {
        ArrayList<ArrayList<Square>> grid = board.getGrid();
        int row = 0, col = 0, diag = 0, revDiag = 0;
        int n = Board.TOTAL_ROWS - 1;
        for (int i = 0; i < Board.TOTAL_ROWS; i++) {
            int x = position.getX();
            int y = position.getY();
            if (grid.get(x).get(i).getSymbol() == currentPlayer.getSymbol())
                col++;
            if (grid.get(i).get(y).getSymbol() == currentPlayer.getSymbol())
                row++;
            if (grid.get(i).get(i).getSymbol() == currentPlayer.getSymbol())
                diag++;
            if (grid.get(i).get(n - i).getSymbol() == currentPlayer.getSymbol())
                revDiag++;
        }
        if (row == n + 1 || col == n + 1 || diag == n + 1 || revDiag == n + 1) {
            if (currentPlayer.getSymbol() == Symbol.CROSS) {
                gameStatus = GameStatus.PLAYER1_WINS;
                return GameStatus.PLAYER1_WINS;
            } else {
                gameStatus = GameStatus.PLAYER2_WINS;
                return GameStatus.PLAYER2_WINS;
            }
        } else if (Game.getBoard().isFull()) {
            gameStatus = GameStatus.DRAW;
            System.out.println("DRAW!!!");
            return GameStatus.DRAW;
        }
        gameStatus = GameStatus.PLAYING;
        return GameStatus.PLAYING;
    }

    public static void playAgain() {
        board = new Board();
        gameStatus = GameStatus.PLAYING;
        currentPlayer = (currentPlayer.getSymbol() == Symbol.CROSS) ? currentPlayer : switchCurrentPlayer();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Board getBoard() {
        return board;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static GameStatus getGameStatus() {
        return gameStatus;
    }
}
