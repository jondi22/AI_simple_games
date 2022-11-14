package com.example.demo10;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class HelloController implements Initializable

{
@FXML
ImageView XO;
    private Parent root;
    private Stage stage;
    private Scene scene;
    public void changescenes2(MouseEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game1.FXML"));
            root = loader.load();


            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   public void changescenes(ActionEvent e) throws IOException {


    FXMLLoader loader=new FXMLLoader(getClass().getResource("game1.FXML"));
    root=loader.load();


    stage= (Stage)(((Node) e.getSource()).getScene().getWindow());

    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XO.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changescenes2(event));
    }
}