package com.example.demo10;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Game3 implements Initializable {
    @FXML
    ImageView back;
    @FXML
    ImageView next;
    @FXML
    RadioButton algo1;
    @FXML
    RadioButton hui1;

    private Parent root;
    private Stage stage;
    private Scene scene;


    public void goback(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.FXML"));
            root = loader.load();


            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void gonext(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game3moreover.fxml"));
            root = loader.load();
            game3moreovercontroler control=loader.getController();
            if (algo1.isSelected())control.algo.setText("algo1");
            else control.algo.setText("algo2");
            if (hui1.isSelected())control.huer.setText("huer1");
            else control.huer.setText("huer2");
            control.huer.setVisible(false);
            control.algo.setVisible(false);

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
        back.addEventHandler(MouseEvent.MOUSE_CLICKED,this::goback);
        next.addEventHandler(MouseEvent.MOUSE_CLICKED,this::gonext);

    }
}
