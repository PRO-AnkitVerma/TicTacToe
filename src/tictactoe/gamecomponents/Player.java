package tictactoe.gamecomponents;

import tictactoe.utils.PlayerType;
import tictactoe.utils.Position;
import tictactoe.utils.Symbol;

import java.util.ArrayList;

public class Player {
    private final PlayerType playerType;
    private Symbol symbol;

    public Player() {
        playerType = PlayerType.HUMAN;
    }

    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Position getMove(Board board) {
        ArrayList<ArrayList<Square>> grid = board.getGrid();
        //TODO: implement backtracking algorithm for calculating optimum move
        return new Position(0, 0);
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public static Square getADumbMove(Board board) throws NullPointerException {
        ArrayList<ArrayList<Square>> grid = board.getGrid();
        for (int i = 0; i < Board.TOTAL_ROWS; i++) {
            for (int j = 0; j < Board.TOTAL_COLUMNS; j++) {
                Square square = grid.get(i).get(j);
                if (square.isEmpty()) {
                    return square;
                }
            }
        }
        return null;
    }
}
