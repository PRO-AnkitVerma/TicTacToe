package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import tictactoe.gamecomponents.Game;
import tictactoe.gamecomponents.Player;
import tictactoe.gamecomponents.Square;
import tictactoe.utils.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    public GridPane grid;
    public Label playerXLabel;
    public Label playerOLabel;
    public Button BackToHomeButton;


    public void switchToHome(ActionEvent event) throws IOException {
        GUIHelper.switchScene("home.fxml");
    }

    public void markSquare(MouseEvent event) throws IOException {
        Square square = playHumanMove(event);
        if (square == null) return;

        if (showResultsIfGameFinished(square)) return;
        Game.switchCurrentPlayer();

        if (Game.getCurrentPlayer().getPlayerType() == PlayerType.BOT) {
            square = playBotMove();
            if (showResultsIfGameFinished(square)) return;
            Game.switchCurrentPlayer();
        }
    }

    private Square playHumanMove(MouseEvent event) {
        ImageView imageViewClicked = (ImageView) event.getSource();

        //get square
        Position position = getSquarePosition(imageViewClicked);
        Square square = Game.getBoard().getSquare(position);

        if (!square.isEmpty()) {
            return null;
        }

        //mark square
        markSquare(square);

        //set image
        imageViewClicked.setImage(square.getImage());

        return square;
    }

    private void markSquare(Square square) {
        Player currentPlayer = Game.getCurrentPlayer();
        Game.getBoard().markSquare(square.getPosition(), currentPlayer.getSymbol());
    }

    private Position getSquarePosition(ImageView imageViewClicked) {
        String squareId = imageViewClicked.getId();
        String coords = squareId.substring(squareId.length() - 2);
        int x = Integer.parseInt(String.valueOf(coords.charAt(0)));
        int y = Integer.parseInt(String.valueOf(coords.charAt(1)));
        return new Position(x, y);
    }

    private boolean showResultsIfGameFinished(Square square) throws IOException {
        GameStatus gameStatus = Game.isGameFinished(square.getPosition());
        if (gameStatus != GameStatus.PLAYING) {
            switchToResults();
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPlayerTextLabels();

        if (Game.getCurrentPlayer().getPlayerType() == PlayerType.BOT) {
            playBotMove();
            Game.switchCurrentPlayer();
        }
    }

    private void setPlayerTextLabels() {
        if (Game.getGameMode() == GameMode.SOLO) {
            if (Game.getPlayer2().getSymbol() == Symbol.CROSS) {
                playerXLabel.setText("Computer");
                playerOLabel.setText("Player");
            } else {
                playerOLabel.setText("Computer");
                playerXLabel.setText("Player");
            }
        }
    }

    private Square playBotMove() {
        //get square
        Position position = MiniMax.getBestMove(Game.getBoard(), Game.getCurrentPlayer());
        Square square = Game.getBoard().getSquare(position);

        //mark square
        markSquare(square);

        //set image
        String squareId = "square" + square.getX() + square.getY();
        ImageView imageView = (ImageView) grid.lookup("#" + squareId);
        imageView.setImage(square.getImage());

        return square;
    }

    public void switchToResults() throws IOException {
        GUIHelper.switchScene("result.fxml");
    }
}
