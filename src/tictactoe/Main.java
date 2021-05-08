package tictactoe;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tictactoe.utils.GUIHelper;


public class Main extends Application {

    public static final Image CIRCLE = new Image("tictactoe/images/imageO.png");
    public static final Image CROSS = new Image("tictactoe/images/imageX.png");

    public static final Image SMALL_CIRCLE = new Image("tictactoe/images/imageO-small.png");
    public static final Image SMALL_CROSS = new Image("tictactoe/images/imageX-small.png");


    @Override
    public void start(Stage primaryStage) throws Exception {
        GUIHelper.setupStage(primaryStage, "TicTacToe", "tictactoe/images/logo.png");
        GUIHelper.switchScene("../screens/home.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
