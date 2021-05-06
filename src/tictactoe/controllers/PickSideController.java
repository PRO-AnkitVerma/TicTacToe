package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import tictactoe.gamecomponents.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PickSideController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private Game game;

    @FXML
    private RadioButton firstRadioButton, secondRadioButton;

    public void switchToGamePlay(ActionEvent event) throws IOException {
        if (firstRadioButton.isSelected() || secondRadioButton.isSelected()) {
            root = FXMLLoader.load(getClass().getResource("../screens/gamePlay.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
