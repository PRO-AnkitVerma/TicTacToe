package tictactoe.utils;

import tictactoe.gamecomponents.Board;
import tictactoe.gamecomponents.Player;

public class MiniMax {
    public static final int MAX_DEPTH = 6;

    public static int miniMax(Symbol[][] symbols, int depth, boolean isMax, Symbol playerSymbol) {
        int boardVal = evaluateBoard(symbols, playerSymbol);
        Symbol opponentSymbol = getOpponentSymbol(playerSymbol);

        // Terminal node (win/lose/draw) or max depth reached.
        if (isGameFinished(boardVal, depth, symbols)) {
            return boardVal;
        }

        // Maximising player, find the maximum attainable value.
        if (isMax) {
            return getHighestValue(symbols, depth, playerSymbol);
            // Minimising player, find the minimum attainable value;
        } else {
            return getLowestValue(symbols, depth, playerSymbol, opponentSymbol);
        }
    }

    private static int getLowestValue(Symbol[][] symbols, int depth, Symbol playerSymbol, Symbol opponentSymbol) {
        int lowestVal = Integer.MAX_VALUE;
        for (int row = 0; row < Board.TOTAL_ROWS; row++) {
            for (int col = 0; col < Board.TOTAL_COLUMNS; col++) {
                if (symbols[row][col].isEmpty()) {
                    symbols[row][col] = opponentSymbol;
                    lowestVal = Math.min(lowestVal, miniMax(symbols,
                            depth - 1, true, playerSymbol));
                    symbols[row][col] = Symbol.EMPTY;
                }
            }
        }
        return lowestVal;
    }

    private static int getHighestValue(Symbol[][] symbols, int depth, Symbol playerSymbol) {
        int highestVal = Integer.MIN_VALUE;
        for (int row = 0; row < Board.TOTAL_COLUMNS; row++) {
            for (int col = 0; col < Board.TOTAL_ROWS; col++) {
                if (symbols[row][col].isEmpty()) {
                    symbols[row][col] = playerSymbol;
                    highestVal = Math.max(highestVal, miniMax(symbols,
                            depth - 1, false, playerSymbol));
                    symbols[row][col] = Symbol.EMPTY;
                }
            }
        }
        return highestVal;
    }

    private static boolean isGameFinished(int boardVal, int depth, Symbol[][] symbols) {
        return Math.abs(boardVal) == 10 || depth == 0
                || !isAnyMoveAvailable(symbols);
    }

    public static Position getBestMove(Board board, Player player) {
        Symbol[][] symbols = board.get2DSymbolMatrix();
        Position bestMove = new Position(-1, -1);
        int bestValue = Integer.MIN_VALUE;
        Symbol playerSymbol = player.getSymbol();
        Symbol opponentSymbol = getOpponentSymbol(playerSymbol);


        for (int row = 0; row < Board.TOTAL_ROWS; row++) {
            for (int col = 0; col < Board.TOTAL_COLUMNS; col++) {
                Symbol symbol = symbols[row][col];
                if (symbol.isEmpty()) {
                    symbols[row][col] = playerSymbol;
                    int moveValue = miniMax(symbols, MAX_DEPTH, false, playerSymbol);
                    symbols[row][col] = Symbol.EMPTY;
                    if (moveValue > bestValue) {
                        bestMove.setX(row);
                        bestMove.setY(col);
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int evaluateBoard(Symbol[][] symbols, Symbol playerSymbol) {
        Symbol opponentSymbol = getOpponentSymbol(playerSymbol);

        int rowSum = 0;
        int bWidth = Board.TOTAL_COLUMNS;
        int bHeight = Board.TOTAL_ROWS;
        int playerWin = Symbol.getValue(playerSymbol) * bWidth;
        int opponentWin = Symbol.getValue(opponentSymbol) * bWidth;

        // Check rows for winner.
        for (int row = 0; row < bHeight; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += Symbol.getValue(symbols[row][col]);
            }
            if (rowSum == playerWin) {
                return 10;
            } else if (rowSum == opponentWin) {
                return -10;
            }
            rowSum = 0;
        }

        // Check columns for winner.
        rowSum = 0;
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bHeight; row++) {
                rowSum += Symbol.getValue(symbols[row][col]);
            }
            if (rowSum == playerWin) {
                return 10;
            } else if (rowSum == opponentWin) {
                return -10;
            }
            rowSum = 0;
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSum = 0;
        for (int i = 0; i < bWidth; i++) {
            rowSum += Symbol.getValue(symbols[i][i]);
        }
        if (rowSum == playerWin) {
            return 10;
        } else if (rowSum == opponentWin) {
            return -10;
        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += Symbol.getValue(symbols[i][indexMax - i]);
        }
        if (rowSum == playerWin) {
            return 10;
        } else if (rowSum == opponentWin) {
            return -10;
        }

        return 0;
    }

    private static Symbol getOpponentSymbol(Symbol playerSymbol) {
        return playerSymbol.isCircle() ? Symbol.CROSS : Symbol.CIRCLE;
    }

    public static boolean isAnyMoveAvailable(Symbol[][] symbols) {
        for (int i = 0; i < Board.TOTAL_ROWS; i++) {
            for (int j = 0; j < Board.TOTAL_COLUMNS; j++) {
                if (symbols[i][j].isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
