package com.example.demo10.connect4_classes;

import com.example.demo10.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChooseForConnect4 implements Initializable {
    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private RadioButton easyButton;

    @FXML
    private RadioButton hardButton;

    @FXML
    private RadioButton mediumButton;

    @FXML
    private ImageView vsComputer;

    @FXML
    private ImageView vsHuman;

    @FXML
    void goToTheGame(ActionEvent event) throws IOException {
        Object controller = null;
        if (selectionMode == NO_SELECTION) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please Choose the mode");
            a.show();
            return;
        } else if (selectionMode == VS_COMPUTER) {

            if (hardButton.isSelected()) {
                controller = new VsComputerController(AlphaBetaAlgorithm.HARD_DIFFICULTY);
            } else if (mediumButton.isSelected()) {
                controller = new VsComputerController(AlphaBetaAlgorithm.MEDIUM_DIFFICULTY);
            } else if (easyButton.isSelected()) {
                controller = new VsComputerController(AlphaBetaAlgorithm.EASY_DIFFICULTY);
            } else if (difficultyGroup.getSelectedToggle() == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Please Choose the difficulty");
                a.show();
                return;
            }

        } else if (selectionMode == VS_HUMAN) {

            controller = new VsHumanController();
        }
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("connect4_game.fxml"));
        loader.setController(controller);
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setScene(new Scene(loader.load()));

    }

    private short selectionMode;
    public static final short VS_HUMAN = -1;
    public static final short NO_SELECTION = 0;
    public static final short VS_COMPUTER = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectionMode = NO_SELECTION;
        easyButton.setDisable(true);
        mediumButton.setDisable(true);
        hardButton.setDisable(true);
        vsHuman.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {

            selectionMode = VS_HUMAN;
            //vsHuman.setVisible(false);
            easyButton.setDisable(true);
            mediumButton.setDisable(true);
            hardButton.setDisable(true);


            //vsComputer.setVisible(true);


        });

        vsComputer.addEventHandler(MouseEvent.MOUSE_CLICKED, (mouseEvent) -> {

            selectionMode = VS_COMPUTER;
            //vsComputer.setVisible(false);
            easyButton.setDisable(false);
            mediumButton.setDisable(false);
            hardButton.setDisable(false);


            //vsHuman.setVisible(true);


        });

    }
}
