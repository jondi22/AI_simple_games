package com.example.demo10.connect4_classes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VsHumanConnect4Controller implements Initializable {
    private State state = new State();
    public static final short FIRST_PLAYER = 0;
    public static final short SECOND_PLAYER = 1;

    @FXML
    private Pane gameGrid;
    private short whoWillTurnNext;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        whoWillTurnNext = FIRST_PLAYER;
        createRectanglesWithHoles();
        for (int i = 0; i < 7; i++) {

            gameGrid.getChildren().add(rectanglesList.get(i));

        }


    }
    List<Shape> rectanglesList;

    private void createRectanglesWithHoles(){
        List<Shape> rectangles = new ArrayList<>();
        for (int column = 0; column < 7; column++) {
            Shape rectangle = new Rectangle(88, 540);

            for (int row = 0; row < 6; row++) {
                Circle circle = new Circle();
                circle.setRadius(40);
                circle.setCenterX(40);
                circle.setCenterY(40);
                circle.setSmooth(true);

                circle.setTranslateX(3);
                circle.setTranslateY(86 * row + 3);

                rectangle = Shape.subtract(rectangle, circle);
            }
            rectangle.setTranslateX(column * 88);
            rectangle.setFill(Color.BLUEVIOLET);
            rectangles.add(rectangle);
        }


        this.rectanglesList = rectangles;
    }
}
