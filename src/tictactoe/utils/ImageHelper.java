package tictactoe.utils;

import javafx.scene.image.Image;

public class ImageHelper {
    private static final String imageDirectory = "tictactoe/images/";
    public static final Image CIRCLE = new Image(imageDirectory + "imageO.png");
    public static final Image CROSS = new Image(imageDirectory + "imageX.png");
    public static final Image SMALL_CIRCLE = new Image(imageDirectory + "imageO-small.png");
    public static final Image SMALL_CROSS = new Image(imageDirectory + "imageX-small.png");
}
