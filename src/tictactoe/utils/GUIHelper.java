package tictactoe.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIHelper {
    private static Stage stage;

    public static void switchScene(String scenePath) throws IOException {
        Parent root = FXMLLoader.load(GUIHelper.class.getResource(scenePath));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void setupStage(Stage primaryStage, String title, String iconPath) {
        stage = primaryStage;
        primaryStage.setTitle(title);
        Image icon = new Image(iconPath);
        primaryStage.getIcons().add(icon);
    }
}
