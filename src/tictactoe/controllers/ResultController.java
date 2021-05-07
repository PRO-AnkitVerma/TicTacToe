package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.gamecomponents.Game;
import tictactoe.utils.GameStatus;

import java.io.IOException;

public class ResultController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchToGamePlay(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../screens/gamePlay.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../screens/home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //TODO: How to use?? Game.getGameStatus() or currentPlayer

}
