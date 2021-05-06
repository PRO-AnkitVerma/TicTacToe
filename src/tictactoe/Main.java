package tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    public static final Image CIRCLE = new Image("tictactoe/images/imageO.png");
    public static final Image CROSS = new Image("tictactoe/images/imageX.png");


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("screens/home.fxml"));
        primaryStage.setTitle("TicTacToe");
        Scene scene = new Scene(root);

        Image icon = new Image("tictactoe/images/logo.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
