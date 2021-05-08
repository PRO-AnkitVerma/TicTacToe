package tictactoe;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tictactoe.utils.GUIHelper;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIHelper.setupStage(primaryStage, "TicTacToe", "tictactoe/images/logo.png");
        GUIHelper.switchScene("home.fxml");
        //TODO: change switchscene method to take scene fxml file name as input!
    }


    public static void main(String[] args) {
        launch(args);
    }
}
