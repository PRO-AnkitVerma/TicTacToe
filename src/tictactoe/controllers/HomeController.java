package tictactoe.controllers;

import javafx.event.ActionEvent;
import tictactoe.gamecomponents.Game;
import tictactoe.utils.GUIHelper;
import tictactoe.utils.GameMode;

import java.io.IOException;

public class HomeController {

    public void switchToPickSide(ActionEvent event) throws IOException {
        Game.setGame(GameMode.SOLO);
        GUIHelper.switchScene("pickSide.fxml");
    }

    public void switchToGamePlay(ActionEvent event) throws IOException {
        Game.setGame(GameMode.MULTIPLAYER);
        GUIHelper.switchScene("gamePlay.fxml");
    }
}




















