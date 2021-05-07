package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.gamecomponents.Game;
import tictactoe.gamecomponents.Player;
import tictactoe.gamecomponents.Square;
import tictactoe.utils.GameStatus;
import tictactoe.utils.MiniMax;
import tictactoe.utils.PlayerType;
import tictactoe.utils.Position;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    public GridPane grid;
    private Player currentPlayer;

    public ImageView square02;
    public ImageView square01;
    public ImageView square00;
    public ImageView square12;
    public ImageView square11;
    public ImageView square10;
    public ImageView square22;
    public ImageView square21;
    public ImageView square20;
    public Button BackToHomeButton;
    private Parent root;
    private Stage stage;
    private Scene scene;

    private ImageView imageViewClicked;
    private String squareId;

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../screens/home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void markSquare(MouseEvent event) throws IOException, InterruptedException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        imageViewClicked = (ImageView) event.getSource();
        squareId = imageViewClicked.getId();
        String coords = squareId.substring(squareId.length() - 2);
        int x = Integer.parseInt(String.valueOf(coords.charAt(0)));
        int y = Integer.parseInt(String.valueOf(coords.charAt(1)));

        Square square = Game.getBoard().getGrid().get(x).get(y);
        if (!square.isEmpty()) {
            return;
        }

        currentPlayer = Game.getCurrentPlayer();
        Game.getBoard().markSquare(new Position(square.getX(), square.getY()), currentPlayer.getSymbol());
        imageViewClicked.setImage(square.getImage());

        //System.out.println("Clicked: " + x + " " + y + " :" + Game.getCurrentPlayer().getSymbol());
        //System.out.println(Game.getCurrentPlayer().getPlayerType());

        Position position = new Position(square.getX(), square.getY());
        GameStatus gameStatus = Game.isGameFinished(position);
        if (gameStatus != GameStatus.PLAYING) {
            navigateResults(stage);
            return;
        }

        currentPlayer = Game.switchCurrentPlayer();

        Game.getBoard().print();


        if (Game.getPlayer2().getPlayerType() == PlayerType.BOT) {
            //TODO: add a smooth delay for bot move

            System.out.println(currentPlayer.getPlayerType());
            //TODO: bot makes a move - backtracking


            int[] pos = MiniMax.getBestMove(Game.getBoard(), Game.getCurrentPlayer());
            square = Game.getBoard().getGrid().get(pos[0]).get(pos[1]);


//            square = Player.getADumbMove(Game.getBoard());
            if (square == null) return;
//            square.setSquare(currentPlayer.getSymbol());
            Game.getBoard().markSquare(new Position(square.getX(), square.getY()), currentPlayer.getSymbol());

            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());

            position = new Position(square.getX(), square.getY());
            gameStatus = Game.isGameFinished(position);
            if (gameStatus != GameStatus.PLAYING) {
                navigateResults(stage);
                return;
            }

            currentPlayer = Game.switchCurrentPlayer();

            Game.getBoard().print();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer = Game.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.BOT) {

            //TODO: bot makes a move - backtracking
            int[] pos = MiniMax.getBestMove(Game.getBoard(), Game.getCurrentPlayer());
            Square square = Game.getBoard().getGrid().get(pos[0]).get(pos[1]);

//            Square square = Player.getADumbMove(Game.getBoard());
            if (square == null) return;

            Game.getBoard().markSquare(new Position(square.getX(), square.getY()), currentPlayer.getSymbol());

            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());

            currentPlayer = Game.switchCurrentPlayer();

            Game.getBoard().print();
        }
    }


    public void navigateResults(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../screens/result.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
