package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
            setPlayerWinState("Player", "Player 1", ImageHelper.CROSS);
        } else if (Game.getGameStatus() == GameStatus.PLAYER2_WINS) {
            setPlayerWinState("Computer", "Player 2", ImageHelper.CIRCLE);
        } else if (Game.getGameStatus() == GameStatus.DRAW) {
            setDrawScreenState();
        }
    }

    private void setPlayerWinState(String soloWinnerName, String multiplayerWinnerName, Image winnerImage) {
        if (Game.getGameMode() == GameMode.SOLO) {
            winnerPlayerNameLabel.setText(soloWinnerName);
        } else {
            winnerPlayerNameLabel.setText(multiplayerWinnerName);
        }
        winnerPlayerSymbolImage.setImage(winnerImage);
    }

    private void setDrawScreenState() {
        winnerPlayerNameLabel.setText("");
        winnerPlayerSymbolImage.setVisible(false);
        wonDrawLabel.setText("DRAW!");
        wonDrawLabel.setTranslateY(-70);
        wonDrawLabel.setTextFill(Color.SLATEGRAY);
    }
}
