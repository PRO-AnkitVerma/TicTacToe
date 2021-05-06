package tictactoe.gamecomponents;

import tictactoe.utils.GameMode;
import tictactoe.utils.PlayerType;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final GameMode gameMode;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new Board();
        this.player1 = new Player();
        this.player2 = (gameMode == GameMode.SOLO) ? new Player(PlayerType.BOT) : new Player();
    }
}
