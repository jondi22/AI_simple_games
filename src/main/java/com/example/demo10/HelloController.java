package com.example.demo10;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    ImageView XO;
    @FXML
    ImageView conect4;
    @FXML
    ImageView puzzle;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void changeScenes2(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game1.FXML"));
            root = loader.load();

            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goToConnect4Game(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("choose_for_connect4.FXML"));
            root = loader.load();


            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void goTo8puzzel(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game3.FXML"));
            root = loader.load();


            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XO.addEventHandler(MouseEvent.MOUSE_CLICKED, this::changeScenes2);
        conect4.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goToConnect4Game);
        puzzle.addEventHandler(MouseEvent.MOUSE_CLICKED,this::goTo8puzzel);
    }
}