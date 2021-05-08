package tictactoe.gamecomponents;

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
        grid = new ArrayList<>();
        for (int i = 0; i < TOTAL_ROWS; i++) {
            ArrayList<Square> row = new ArrayList<>();
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                row.add(new Square(i, j));
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
            square.setSquare(symbol);
            --numberOfEmptySquares;
        }
    }

    public Symbol[][] get2DSymbolMatrix() {
        Symbol[][] symbols = new Symbol[TOTAL_ROWS][TOTAL_COLUMNS];
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                symbols[i][j] = this.grid.get(i).get(j).getSymbol();
            }
        }
        return symbols;
    }


    public void print() {
        Symbol[][] symbols = get2DSymbolMatrix();
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLUMNS; j++) {
                System.out.print(symbols[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Square getSquare(Position position) {
        return grid.get(position.getX()).get(position.getY());
    }

    public ArrayList<ArrayList<Square>> getGrid() {
        return grid;
    }
}
