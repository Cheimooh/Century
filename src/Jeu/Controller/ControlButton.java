package Jeu.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlButton implements Initializable {

    @FXML private ComboBox<String> comboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }

    @FXML
    private void loadData(){
        comboBox.getItems().removeAll(comboBox.getItems());
        String nbJoueurs2 = "2 joueurs";
        String nbJoueurs3 = "3 joueurs";
        String nbJoueurs4 = "4 joueurs";
        String nbJoueurs5 = "5 joueurs";
        comboBox.getItems().addAll(nbJoueurs2,nbJoueurs3,nbJoueurs4,nbJoueurs5);
    }

}
