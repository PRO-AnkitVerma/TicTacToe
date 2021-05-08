package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import tictactoe.gamecomponents.Game;
import tictactoe.utils.GUIHelper;
import tictactoe.utils.GameMode;
import tictactoe.utils.GameStatus;
import tictactoe.utils.ImageHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    public Label winnerPlayerNameLabel;
    public Label wonDrawLabel;
    public ImageView winnerPlayerSymbolImage;
    public Button playAgainButton;
    public Button backToHomeButton;

    public void switchToGamePlay(ActionEvent event) throws IOException {
        Game.playAgain();
        GUIHelper.switchScene("gamePlay.fxml");
    }

    public void switchToHome(ActionEvent event) throws IOException {
        GUIHelper.switchScene("home.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Game.getGameStatus() == GameStatus.PLAYER1_WINS) {
            if (Game.getGameMode() == GameMode.SOLO) {
                winnerPlayerNameLabel.setText("Player");
            } else {
                winnerPlayerNameLabel.setText("Player 1");
            }
            winnerPlayerSymbolImage.setImage(ImageHelper.CROSS);
        } else if (Game.getGameStatus() == GameStatus.PLAYER2_WINS) {
            if (Game.getGameMode() == GameMode.SOLO) {
                winnerPlayerNameLabel.setText("Computer");
            } else {
                winnerPlayerNameLabel.setText("Player 2");
            }
            winnerPlayerSymbolImage.setImage(ImageHelper.CIRCLE);
        } else if (Game.getGameStatus() == GameStatus.DRAW) {
            winnerPlayerNameLabel.setText("");
            winnerPlayerSymbolImage.setVisible(false);
            wonDrawLabel.setText("DRAW!");
            wonDrawLabel.setTranslateY(-70);
            wonDrawLabel.setTextFill(Color.SLATEGRAY);
        }
    }
}
