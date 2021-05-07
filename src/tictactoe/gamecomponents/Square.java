package tictactoe.gamecomponents;

import javafx.scene.image.Image;
import tictactoe.Main;
import tictactoe.utils.Position;
import tictactoe.utils.Symbol;

public class Square {
    private Symbol symbol;
    private Image image;
    private final int x;
    private final int y;

    public Square(int x, int y) {
        symbol = Symbol.EMPTY;
        this.x = x;
        this.y = y;
    }

    public void setSquare(Symbol symbol) {
        this.symbol = symbol;
        if (symbol == Symbol.CIRCLE) {
            this.image = Main.SMALLCIRCLE;

        } else if (symbol == Symbol.CROSS) {
            this.image = Main.SMALLCROSS;

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
        return (symbol == Symbol.EMPTY);
    }

    public boolean isCircle() {
        return (symbol == Symbol.CIRCLE);
    }

    public boolean isCross() {
        return (symbol == Symbol.CROSS);
    }

    public Position getPosition() {
        return new Position(x, y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
