package com.example.demo10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import static javafx.geometry.Pos.CENTER;

public class game3moreovercontroler implements Initializable {

    @FXML
    ImageView back;
    @FXML
    ImageView next;
    @FXML
    ImageView reload;
    @FXML
    Label algo;
    @FXML
    Label huer;
    @FXML
    Label po1, po2, po3, po4, po5, po6, po7, po8, po9;
    @FXML
    Button button1, button2, button3, button4, button5, button6, button7, button8, buttonempty;
    @FXML
    Label header;
    @FXML
    GridPane grid1;
    @FXML
    GridPane grid2;


    int poscounter = 1;
    boolean flag = false;
    Button[] buttonarray;
    Label[] labelarray;
    int[][] curr = new int[3][3];
    a8puzzle curr1 = new a8puzzle(curr);

    int[][] goal = new int[3][3];

    a8puzzle goal1 = new a8puzzle(goal);
    PriorityQueue<a8puzzle> priority = new PriorityQueue<>();

    List<a8puzzle> openlist = new ArrayList<>();
    List<a8puzzle> closed = new ArrayList<>();
    List<a8puzzle> Solotion = new ArrayList<>();


    private Parent root;
    private Stage stage;
    private Scene scene;
    int algo1;
    int huer1;

    static int getInvCount(int[] arr) {
        int inv_count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++)

                // Value 0 is used for empty space
                if (arr[i] > 0 &&
                        arr[j] > 0 && arr[i] > arr[j])
                    inv_count++;
        return inv_count;
    }

    // This function returns true
// if given 8 puzzle is solvable.
    static boolean isSolvable(int[][] puzzle) {
        int[] linearPuzzle;
        linearPuzzle = new int[9];
        int k = 0;

        // Converting 2-D puzzle to linear form
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                linearPuzzle[k++] = puzzle[i][j];

        // Count inversions in given 8 puzzle
        int invCount = getInvCount(linearPuzzle);

        // return true if inversion count is even.
        return (invCount % 2 == 0);
    }


    public void Startalgo() {


        closed.clear();
        priority.clear();

        curr1.cost = hur(curr1);
        priority.add(curr1);

        if (isSolvable(curr1.grid))
            System.out.println("Solvable");
        else
            System.out.println("Not Solvable");


        if (algo.getText() == "algo1") {
            do {
                if (priority.isEmpty()) {
                    System.out.print("nosoltion");
                    return;
                }
                a8puzzle n = priority.peek();
                if (n.cost == 0) {
                    while (n.parent != null) {
                        Solotion.add(n);
                        n = n.parent;
                        System.out.print("hello");

                    }
                    System.out.print("about to exit");
                    return;
                }


                a8puzzle[] s = n.makechildren(n);
                for (int i = 0; i < s.length; i++) {

                    s[i].cost = hur(s[i]);

                    if (!priority.contains(s[i]) && !closed.contains(s[i])) {
                        s[i].parent = n;
                        priority.add(s[i]);

                    }
                    priority.remove(n);
                    closed.add(n);
                }
            } while (!priority.isEmpty());

        } else {

        }

        // }

    }

    public int hur(a8puzzle curr12) {
        if (huer1 == 1) {
            int count = 0;
            for (int b = 0; b < 3; b++)
                for (int a = 0; a < 3; a++)
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (curr12.grid[b][a] == goal1.grid[i][j]) count += Math.abs(b - i) + Math.abs(a - j);
                        }
                    }
            return count;
        } else {
            int count = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (curr12.grid[i][j] != goal1.grid[i][j]) count++;
                }
            }

            return count;
        }
    }

    public void goback(MouseEvent event) {
        if (!flag) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Game3.FXML"));
                root = loader.load();


                stage = (Stage) (((Node) event.getSource()).getScene().getWindow());

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag) {
            header.setText("Choose firstly the start point");
            flag = false;
        }
    }

    public void gonext(MouseEvent event) throws InterruptedException {
        if (!flag && poscounter == 10) {
            flag = true;
            header.setText("secondly choose the goal point you want to reach");
            int counter = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    curr1.grid[i][j] = Integer.parseInt(labelarray[counter++].getText());

            }
            restart(event);
        } else if (flag && poscounter == 10) {
            int counter = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    goal1.grid[i][j] = Integer.parseInt(labelarray[counter++].getText());
            }
            grid2.setVisible(false);
            header.setText("Starting ...");
            header.setAlignment(CENTER);
            grid1.setLayoutX(339);
            grid1.setLayoutY(107);
            reload.setVisible(false);
            Startalgo();

            System.out.print(Solotion.size() + "  " + closed.size());
//            for (int i=x.length;i>=0;i--){((a8puzzle)x[i]).printgrid();System.out.print("\n\n\n"); }
            header.setText("Finished");

        }
    }

    public void restart(MouseEvent event) {
        poscounter = 1;
        for (int i = 0; i < 9; i++) {
            labelarray[i].setVisible(false);
            buttonarray[i].setVisible(true);
        }


    }


    public void move(ActionEvent event) {
        Button x = (Button) event.getSource();
        String y = x.getText();

        x.setVisible(false);
        if (y == "") y = "9";
        if (y == "9") ;
        else labelarray[poscounter - 1].setVisible(true);
        labelarray[poscounter - 1].setText(y);

//

        poscounter++;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, this::goback);
        next.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                gonext(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        reload.addEventHandler(MouseEvent.MOUSE_CLICKED, this::restart);
        buttonarray = new Button[9];
        labelarray = new Label[9];
        {
            buttonarray[0] = button1;
            buttonarray[1] = button2;
            buttonarray[2] = button3;
            buttonarray[3] = button4;
            buttonarray[4] = button5;
            buttonarray[5] = button6;
            buttonarray[6] = button7;
            buttonarray[7] = button8;
            buttonarray[8] = buttonempty;
            labelarray[0] = po1;
            labelarray[1] = po2;
            labelarray[2] = po3;
            labelarray[3] = po4;
            labelarray[4] = po5;
            labelarray[5] = po6;
            labelarray[6] = po7;
            labelarray[7] = po8;
            labelarray[8] = po9;
            if (algo.getText() == "algo1") algo1 = 1;
            else algo1 = 2;
            if (huer.getText() == "huer1") huer1 = 1;
            else huer1 = 2;
            for (int x = 0; x < 3; x++) {

            }

        }

    }

}


