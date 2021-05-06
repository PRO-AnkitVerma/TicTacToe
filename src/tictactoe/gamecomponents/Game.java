package tictactoe.gamecomponents;

import tictactoe.utils.GameMode;
import tictactoe.utils.PlayerType;

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
    }

    public static GameMode getGameMode() {
        return gameMode;
    }

    public void playGame() {

    }
}
