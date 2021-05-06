package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.gamecomponents.Game;
import tictactoe.utils.GameMode;

import java.io.IOException;

public class HomeController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchToPickSide(ActionEvent event) throws IOException {
        Game.setGame(GameMode.SOLO);
        root = FXMLLoader.load(getClass().getResource("../screens/pickSide.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGamePlay(ActionEvent event) throws IOException {
        Game.setGame(GameMode.MULTIPLAYER);
        root = FXMLLoader.load(getClass().getResource("../screens/gamePlay.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}




















