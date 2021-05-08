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
    private Player currentPlayer;
    public Button BackToHomeButton;
    private String squareId;

    public void switchToHome(ActionEvent event) throws IOException {
        GUIHelper.switchScene("home.fxml");
    }

    public void markSquare(MouseEvent event) throws IOException {

        ImageView imageViewClicked = (ImageView) event.getSource();
        squareId = imageViewClicked.getId();
        String coords = squareId.substring(squareId.length() - 2);
        int x = Integer.parseInt(String.valueOf(coords.charAt(0)));
        int y = Integer.parseInt(String.valueOf(coords.charAt(1)));

        Square square = Game.getBoard().getGrid().get(x).get(y);
        if (!square.isEmpty()) {
            return;
        }

        currentPlayer = Game.getCurrentPlayer();
        Game.getBoard().markSquare(square.getPosition(), currentPlayer.getSymbol());
        imageViewClicked.setImage(square.getImage());


        GameStatus gameStatus = Game.isGameFinished(square.getPosition());
        if (gameStatus != GameStatus.PLAYING) {
            switchToResults(event);
            return;
        }

        currentPlayer = Game.switchCurrentPlayer();

        if (Game.getPlayer2().getPlayerType() == PlayerType.BOT) {

            Position position = MiniMax.getBestMove(Game.getBoard(), Game.getCurrentPlayer());
            square = Game.getBoard().getGrid().get(position.getX()).get(position.getY());

            if (square == null) return;
            Game.getBoard().markSquare(square.getPosition(), currentPlayer.getSymbol());

            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());

            gameStatus = Game.isGameFinished(square.getPosition());
            if (gameStatus != GameStatus.PLAYING) {
                switchToResults(event);
                return;
            }

            currentPlayer = Game.switchCurrentPlayer();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Game.getGameMode() == GameMode.SOLO) {
            if (Game.getPlayer2().getSymbol() == Symbol.CROSS) {
                playerXLabel.setText("Computer");
                playerOLabel.setText("Player");
            } else {
                playerOLabel.setText("Computer");
                playerXLabel.setText("Player");
            }
        }

        currentPlayer = Game.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.BOT) {

            Position position = MiniMax.getBestMove(Game.getBoard(), Game.getCurrentPlayer());
            Square square = Game.getBoard().getGrid().get(position.getX()).get(position.getY());

            if (square == null) return;

            Game.getBoard().markSquare(square.getPosition(), currentPlayer.getSymbol());

            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());

            currentPlayer = Game.switchCurrentPlayer();
        }
    }


    public void switchToResults(MouseEvent event) throws IOException {
        GUIHelper.switchScene("result.fxml");
    }
}
