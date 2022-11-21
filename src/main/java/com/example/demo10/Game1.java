package com.example.demo10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Game1 implements Initializable
{
    public static class XO {
        public boolean[] space = new boolean[9];

        XO[] makechild(XO curr) {
            int childcounter = 0;
            for (boolean x2 : curr.space) if (!x2) childcounter++;
            XO[] r = new XO[childcounter];
            for (int j = 0; j < childcounter; j++) r[j] = new XO();
            int counter = 0;
            for (int c = 0; c < 9; c++) {
                if (!curr.space[c]) {
                    counter = c;
                    break;
                }
            }
            for (int i = 0; i < childcounter; i++) {

                for (int j = 0; j < 9; j++) {
                    r[i].space[j] = curr.space[j];
                }
                r[i].space[counter] = true;
                for (int c = counter + 1; c < 9; c++) {
                    if (!curr.space[c]) {
                        counter = c;
                        break;
                    }
                }

            }
            return r;
        }
    }

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
    public void changestate(MouseEvent event){

        try {
            Label x=(Label)event.getSource();
            if (x.getText()=="")SET_X(x);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        L1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L8.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));
        L9.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changestate(event));

    }
}
