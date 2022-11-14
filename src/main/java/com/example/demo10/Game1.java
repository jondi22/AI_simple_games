package com.example.demo10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Game1 {
    @FXML
    Label L1;
    @FXML
    Label L2;
    @FXML
    Label L3;
    @FXML
    Label L4;
    @FXML
    Label L5;
    @FXML
    Label L6;
    @FXML
    Label L7;
    @FXML
    Label L8;
    @FXML
    Label L9;
    boolean x=true;


    public void XO(ActionEvent e)throws IOException {

        Label y=(Label) e.getSource();
        if(x==true)SET_X(y);
        else SET_O(y);
        x=!x;


    }
    public void SET_X(Label H){
        H.setText("X");
    }
    public void SET_O(Label H){
        H.setText("O");
    }









}
