package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import tictactoe.gamecomponents.Game;
import tictactoe.utils.GUIHelper;
import tictactoe.utils.Symbol;

import java.io.IOException;

public class PickSideController {

    @FXML
    private RadioButton firstRadioButton, secondRadioButton;

    public void switchToGamePlay(ActionEvent event) throws IOException {
        if (firstRadioButton.isSelected()) {
            Game.setPlayerSymbol(Symbol.CROSS);

        } else if (secondRadioButton.isSelected()) {
            Game.setPlayerSymbol(Symbol.CIRCLE);
        }

        if (firstRadioButton.isSelected() || secondRadioButton.isSelected()) {
            GUIHelper.switchScene("gamePlay.fxml");
        }
    }
}
