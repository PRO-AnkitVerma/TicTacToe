package tictactoe.gamecomponents;

import tictactoe.utils.GameMode;
import tictactoe.utils.PlayerType;
import tictactoe.utils.Symbol;

public class Game {
    private static Board board;
    private static Player player1;
    private static Player player2;
    private static GameMode gameMode;

    public static void setGame(GameMode gM) {
        gameMode = gM;
        board = new Board();
        player1 = new Player();
        player2 = (gameMode == GameMode.SOLO) ? new Player(PlayerType.BOT) : new Player();
        player1.setSymbol(Symbol.CROSS);
        player2.setSymbol(Symbol.CIRCLE);
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void playGame() {
        //TODO: Implement playGame
    }

    public static void setPlayerSymbol(Symbol symbol) {
        player1.setSymbol(symbol);
        if (symbol == Symbol.CIRCLE) {
            player2.setSymbol(Symbol.CROSS);
        } else {
            player2.setSymbol(Symbol.CIRCLE);
        }
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }
}
