package com.example.demo10.connect4_classes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class VsHumanConnect4Controller implements Initializable {
    private State currentState = new State();
    public static final short FIRST_PLAYER = -1;
    public static final short SECOND_PLAYER = 1;

    @FXML
    private GridPane gameGrid;

    private short whoWillTurnNext;

    private GridPane[] grids = new GridPane[7];
    private Circle[][] circles = new Circle[7][6];


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        whoWillTurnNext = FIRST_PLAYER;
        gameGrid.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null), null, null));
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
                currentState.insertADisk(whoWillTurnNext, (short) x);
                if (whoWillTurnNext == FIRST_PLAYER)
                    circles[x][currentState.getLastMoveY()].setFill(Color.RED);
                else
                    circles[x][currentState.getLastMoveY()].setFill(Color.YELLOW);
                whoWillTurnNext = (short) -whoWillTurnNext;
                if(currentState.isAWinningState() == State.RED) System.out.println("Red wins");
                else if(currentState.isAWinningState() == State.YELLOW) System.out.println("Yellow wins");

            });
        }


    }

}
