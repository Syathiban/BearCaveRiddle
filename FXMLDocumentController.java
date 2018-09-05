/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BearCave;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author Startklar
 */
public class FXMLDocumentController implements Initializable {

    private int baeren, hoehlen, eingabe1, eingabe2;

    private int[] num = new int[6];
    Random r = new Random();
    @FXML
    private Label wish;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private TextField text1, text2;
    @FXML
    private Label Würfel1, Würfel2, Würfel3, Würfel4, Würfel5;
    @FXML
    private Button Button;
    @FXML
    private Button restart;

    private double xOffset = 0;

    private double yOffset = 0;
    @FXML
    private ImageView topbar;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
    try {
        eingabe1 = Integer.parseInt(text1.getText());
        eingabe2 = Integer.parseInt(text2.getText());
        if (eingabe1 == baeren && eingabe2 == hoehlen) {
            Stage stage = Model.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("Gewinner.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Stage stage = Model.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("Verlierer.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Incorrect input made");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Stage stage = Model.getStage();

        topbar.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }

        });

        topbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        neustarten();
    }

    public void neustarten() {
        wish.setText("");
        hoehlen = 0;
        for (int i = 0; i < 5; i++) {
            num[i] = r.nextInt(6) + 1;
            if (num[i] % 2 == 1) {
                hoehlen += 1;
                switch (num[i]) {
                    case 1:
                        baeren += 6;
                        break;
                    case 3:
                        baeren += 4;
                        break;
                    case 5:
                        baeren += 2;
                        break;

                }

            }

        }

        Würfel1.setText(String.valueOf(num[0]));
        Würfel2.setText(String.valueOf(num[1]));
        Würfel3.setText(String.valueOf(num[2]));
        Würfel4.setText(String.valueOf(num[3]));
        Würfel5.setText(String.valueOf(num[4]));

    }

    @FXML
    private void restart(ActionEvent event) {
        neustarten();
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        stage.setIconified(true);
    }
}
