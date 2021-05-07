package tictactoe.utils;

import tictactoe.gamecomponents.Board;
import tictactoe.gamecomponents.Player;

public class MiniMax {
    public static final int MAX_DEPTH = 6;

    public static int miniMax(Symbol[][] symbols, int depth, boolean isMax, Symbol playerSymbol) {
        int boardVal = evaluateBoard(symbols, playerSymbol);
        Symbol opponentSymbol = (playerSymbol == Symbol.CIRCLE) ? Symbol.CROSS : Symbol.CIRCLE;

        // Terminal node (win/lose/draw) or max depth reached.
        if (Math.abs(boardVal) == 10 || depth == 0
                || !isAnyMoveAvailable(symbols)) {
            return boardVal;
        }

        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < Board.TOTAL_COLUMNS; row++) {
                for (int col = 0; col < Board.TOTAL_ROWS; col++) {
                    if (symbols[row][col] == Symbol.EMPTY) {
                        symbols[row][col] = playerSymbol;
                        highestVal = Math.max(highestVal, miniMax(symbols,
                                depth - 1, false, playerSymbol));
                        symbols[row][col] = Symbol.EMPTY;
                    }
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < Board.TOTAL_ROWS; row++) {
                for (int col = 0; col < Board.TOTAL_COLUMNS; col++) {
                    if (symbols[row][col] == Symbol.EMPTY) {
                        symbols[row][col] = opponentSymbol;
                        lowestVal = Math.min(lowestVal, miniMax(symbols,
                                depth - 1, true, playerSymbol));
                        symbols[row][col] = Symbol.EMPTY;
                    }
                }
            }
            return lowestVal;
        }
    }

    public static int[] getBestMove(Board board, Player player) {
        Symbol[][] symbols = board.get2DSymbolMatrix();
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;
        Symbol playerSymbol = player.getSymbol();
        Symbol opponentSymbol = (playerSymbol == Symbol.CIRCLE) ? Symbol.CROSS : Symbol.CIRCLE;


        for (int row = 0; row < Board.TOTAL_ROWS; row++) {
            for (int col = 0; col < Board.TOTAL_COLUMNS; col++) {
                Symbol symbol = symbols[row][col];
                if (symbol == Symbol.EMPTY) {
                    symbols[row][col] = playerSymbol;
                    int moveValue = miniMax(symbols, MAX_DEPTH, false, playerSymbol);
                    symbols[row][col] = Symbol.EMPTY;
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;
                    }
                }
            }
        }
        return bestMove;
    }

    private static int evaluateBoard(Symbol[][] symbols, Symbol playerSymbol) {
        Symbol opponentSymbol = (playerSymbol == Symbol.CIRCLE) ? Symbol.CROSS : Symbol.CIRCLE;

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

    public static boolean isAnyMoveAvailable(Symbol[][] symbols) {
        for (int i = 0; i < Board.TOTAL_ROWS; i++) {
            for (int j = 0; j < Board.TOTAL_COLUMNS; j++) {
                if (symbols[i][j] == Symbol.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }
}
