package tictactoe;

import javafx.application.Application;
import javafx.stage.Stage;
import tictactoe.utils.GUIHelper;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIHelper.setupStage(primaryStage, "TicTacToe", "tictactoe/images/logo.png");
        GUIHelper.switchScene("home.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
