package com.example.demo10.connect4_classes;

import com.example.demo10.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VsComputerController implements Initializable {
    private short difficulty;
    public VsComputerController(short difficulty) {
        alphaBetaAlgorithm = new AlphaBetaAlgorithm(difficulty);
        this.difficulty = difficulty;
    }

    AIState currentState = new AIState();
    AlphaBetaAlgorithm alphaBetaAlgorithm;
    @FXML
    private GridPane gameGrid;
    @FXML
    private ImageView goBackButton;
    @FXML
    private ImageView resetButton;

    private short whoWillTurnNext;

    private GridPane[] grids = new GridPane[7];
    private Circle[][] circles = new Circle[7][6];
    @FXML
    private Label playerWillTurnLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gameGrid.setBackground(new Background(
                new BackgroundFill(Color.DEEPSKYBLUE, null, null), null, null));
        String labelText = null;
        Color color = null;
        if(difficulty == AlphaBetaAlgorithm.HARD_DIFFICULTY){
            labelText = "HARD";
            color = Color.RED;
        }else if(difficulty == AlphaBetaAlgorithm.MEDIUM_DIFFICULTY){
            labelText = "MEDIUM";
            color = Color.ORANGE;
        } else if(difficulty == AlphaBetaAlgorithm.EASY_DIFFICULTY){
            labelText = "EASY";
            color = Color.YELLOW;
        }


        playerWillTurnLabel.setText(labelText);
        playerWillTurnLabel.setBackground(new Background(new BackgroundFill(color,
                null, null), null, null));
        playerWillTurnLabel.setAlignment(Pos.CENTER);
        for (int i = 0; i < 7; i++) {
            grids[i] = new GridPane();
            grids[i].setVgap(10);
            grids[i].setAlignment(Pos.CENTER);
            grids[i].setId(Integer.toString(i));
            for (int j = 0; j < 6; j++) {
                Circle circle = new Circle();
                circle.setRadius(25);
                circle.setCenterX(25);
                circle.setCenterY(25);
                circle.setFill(Color.WHITE);
                grids[i].addRow(j);
                grids[i].add(circle, 0, j);
                circles[i][5 - j] = circle;
            }
            gameGrid.add(grids[i], i, 0);


            grids[i].addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> {
                grids[Integer.parseInt(((GridPane) mouseEvent.getSource()).getId())]
                        .setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null), null, null));
            });
            grids[i].addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> {
                grids[Integer.parseInt(((GridPane) mouseEvent.getSource()).getId())]
                        .setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null), null, null));
            });
            grids[i].addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                int x = Integer.parseInt(((GridPane) mouseEvent.getSource()).getId());
                if(currentState.getColumnsPointers()[x] == 6)return;

                currentState.insertADisk(State.RED, (short) x);
                circles[x][currentState.getLastMoveY()].setFill(Color.RED);
                if(currentState.isAWinningState() == State.RED){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("You wins");
                    a.show();
                    return;
                }

                currentState.insertADisk(State.YELLOW, (short) alphaBetaAlgorithm.nextMove(currentState.instantiate()));
                circles[currentState.lastMoveX][currentState.getLastMoveY()].setFill(Color.YELLOW);
                if(currentState.isAWinningState() == State.YELLOW){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Computer wins");
                    a.show();

                }
                if(currentState.tieGame()){
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("It's a tie");
                    a.show();
                }


            });

        }
        goBackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goBack);
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, this::resetGame);
    }
    private void goBack(MouseEvent event) {
        try {

            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(FXMLLoader.
                    load(Objects.requireNonNull(HelloApplication.class.getResource("hello-view.fxml")))));
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
    }
    private void resetGame(MouseEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("connect4_game.fxml"));
            loader.setController(new VsComputerController(difficulty));
            Stage stage = (Stage)playerWillTurnLabel.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
