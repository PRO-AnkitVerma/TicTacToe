package tictactoe.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.gamecomponents.Game;
import tictactoe.gamecomponents.Player;
import tictactoe.gamecomponents.Square;
import tictactoe.utils.GameStatus;
import tictactoe.utils.PlayerType;
import tictactoe.utils.Position;
import tictactoe.utils.Symbol;

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

    public void markSquare(MouseEvent event) {
        //TODO: To be continued!!!!

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
        square.setSquare(currentPlayer.getSymbol());
        imageViewClicked.setImage(square.getImage());


        System.out.println("Clicked: " + x + " " + y + " :" + Game.getCurrentPlayer().getSymbol());
        System.out.println(Game.getCurrentPlayer().getPlayerType());

        //TODO: evaluate who won?
        Position position = new Position(square.getX(), square.getY());
        GameStatus gameStatus = Game.isGameFinished(position);
        navigateResultsIfGameFinished(gameStatus);

        currentPlayer = Game.switchCurrentPlayer();

        if (Game.getPlayer2().getPlayerType() == PlayerType.BOT) {

            System.out.println(currentPlayer.getPlayerType());
            //TODO: bot makes a move - backtracking
            square = Player.getADumbMove(Game.getBoard());
            if (square == null) return;
            square.setSquare(currentPlayer.getSymbol());
            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());

            //TODO: evaluate who won?
            position = new Position(square.getX(), square.getY());
            gameStatus = Game.isGameFinished(position);
            navigateResultsIfGameFinished(gameStatus);


            currentPlayer = Game.switchCurrentPlayer();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPlayer = Game.getCurrentPlayer();
        if (currentPlayer.getPlayerType() == PlayerType.BOT) {

            //TODO: bot makes a move - backtracking
            Square square = Player.getADumbMove(Game.getBoard());
            if (square == null) return;
            square.setSquare(currentPlayer.getSymbol());
            squareId = "square" + square.getX() + square.getY();
            ImageView imageView = (ImageView) grid.lookup("#" + squareId);
            imageView.setImage(square.getImage());
            currentPlayer = Game.switchCurrentPlayer();
        }
    }

    public void navigateResultsIfGameFinished(GameStatus gameStatus) {
        if (gameStatus == GameStatus.PLAYER1_WINS) {
            //TODO: Navigate to next page
            //TODO: show result
            System.out.println(GameStatus.PLAYER1_WINS);
        } else if (gameStatus == GameStatus.PLAYER2_WINS) {
            //TODO: Navigate to next page
            //TODO: show result
            System.out.println(GameStatus.PLAYER2_WINS);
        } else if (gameStatus == GameStatus.DRAW) {
            //TODO: Navigate to next page
            //TODO: show result
            System.out.println(GameStatus.DRAW);
        }
    }
}
