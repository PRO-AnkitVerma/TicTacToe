package tictactoe.gamecomponents;

import javafx.scene.image.Image;
import tictactoe.utils.Position;
import tictactoe.utils.Symbol;

import java.util.ArrayList;

public class Board {
    public static final int TOTAL_ROWS = 3;
    public static final int TOTAL_COLUMNS = 3;
    public static final int totalSquares = TOTAL_ROWS * TOTAL_COLUMNS;
    private int numberOfEmptySquares;
    private final ArrayList<ArrayList<Square>> grid;

    public Board() {
        grid = new ArrayList<ArrayList<Square>>();
        for (int i = 0; i < TOTAL_ROWS; i++) {
            ArrayList<Square> row = new ArrayList<Square>();
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                row.add(new Square());
            }
            grid.add(row);
        }
        numberOfEmptySquares = totalSquares;
    }

    public boolean isFull() {
        return numberOfEmptySquares == 0;
    }

    public void markSquare(Position position, Symbol symbol) {
        Square square = grid.get(position.getX()).get(position.getY());
        if (square.isEmpty()) {
            //TODO: mark the square filled!
            square.setSquare(symbol);
            --numberOfEmptySquares;
        }
    }

    public ArrayList<ArrayList<Square>> getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.getGrid().get(1).get(1).setSquare(Symbol.CIRCLE);
        for (int i = 0; i < Board.TOTAL_ROWS; i++) {
            for (int j = 0; j < Board.TOTAL_COLUMNS; j++) {
                System.out.print(Symbol.EMPTY == board.getGrid().get(i).get(j).getSymbol());
            }
            System.out.println();
        }
    }
}
