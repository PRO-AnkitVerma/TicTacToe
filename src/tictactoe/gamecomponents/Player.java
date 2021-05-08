package tictactoe.gamecomponents;

import tictactoe.utils.PlayerType;
import tictactoe.utils.Symbol;


public class Player {
    private final PlayerType playerType;
    private Symbol symbol;

    public Player() {
        playerType = PlayerType.HUMAN;
    }

    public Player(PlayerType playerType) {
        this.playerType = playerType;
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
}
