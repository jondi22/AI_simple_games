package com.example.demo10;

import com.example.demo10.connect4_classes.AIState;
import com.example.demo10.connect4_classes.AlphaBetaAlgorithm;
import com.example.demo10.connect4_classes.VsComputerController;
import com.example.demo10.connect4_classes.VsHumanController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
       launch();
    }
}