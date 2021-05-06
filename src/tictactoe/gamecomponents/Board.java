package tictactoe.gamecomponents;

import tictactoe.utils.Symbol;

import java.util.ArrayList;

public class Board {
    private static final int TOTAL_ROWS = 3;
    private static final int TOTAL_COLUMNS = 3;
    private static final int totalSquares = TOTAL_ROWS * TOTAL_COLUMNS;
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

    public void markSquare(int i, int j, Symbol symbol) {
        Square square = grid.get(i).get(j);
        if (square.isEmpty()) {
            //TODO: mark the square filled!
            square.setSquare(symbol);
            --numberOfEmptySquares;
        }
    }

    public ArrayList<ArrayList<Square>> getGrid() {
        return grid;
    }
}
