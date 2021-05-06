package tictactoe.gamecomponents;

import javafx.scene.image.Image;
import tictactoe.Main;
import tictactoe.utils.Symbol;

public class Square {
    private Symbol symbol;
    private Image image;

    public Square() {
        symbol = Symbol.EMPTY;
    }

    public void setSquare(Symbol symbol) {
        this.symbol = symbol;
        //TODO: add image location for O and X
        if (symbol == Symbol.CIRCLE) {
            this.image = Main.CIRCLE;

        } else if (symbol == Symbol.CROSS) {
            this.image = Main.CROSS;

        }
    }

    public void resetSquare() {
        symbol = Symbol.EMPTY;
        image = null;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Image getImage() {
        return image;
    }

    public boolean isEmpty() {
        return symbol == Symbol.EMPTY;
    }
}
