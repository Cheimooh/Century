package Jeu;

import Jeu.Controller.ControlButton;
import Jeu.Model.Century;
import Jeu.View.Fenetre;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Jeu extends Application {

    private ComboBox<Integer> comboBox;
    private int tabDeNombreDeJoueursDisponible[] = {2,3,4,5};
    private Century century;
    private Stage stage;
    private int nbJoueurs;
    private String[] nomsJoueurs;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Century : la route des épices");
        century = new Century();
        fenetreDemandeNbJoueurs(primaryStage);
    }

    private void afficheFenetreDeJeu(){
        century.initJoueur(nomsJoueurs);
        int height = 700;
        int width = 1000;
        Fenetre fenetre = new Fenetre(century, width, height);
        Group root = new Group();
        root.getChildren().add(fenetre);
        stage.setScene(new Scene(root, width, height, Color.LIGHTGREY));
        stage.show();
    }

    private void fenetreDemandeNbJoueurs(Stage primaryStage){
        this.stage = primaryStage;
        primaryStage.setTitle("Century : la route des épices");

        Label labelBienvenue = new Label("Bienvenue dans century!");
        labelBienvenue.setTranslateX(200);
        labelBienvenue.setTranslateY(150);
        labelBienvenue.setScaleX(2);
        labelBienvenue.setScaleY(2);

        Label labelChoixJoueurs = new Label("Choix du nombre de joueurs : ");
        labelChoixJoueurs.setTranslateX(180);
        labelChoixJoueurs.setTranslateY(170);

        comboBox = new ComboBox<>();
        comboBox.setPromptText("Voulez-vous jouer à ...");
        comboBox.setTranslateX(180);
        comboBox.setTranslateY(190);

        Button button = new Button("Valider");
        button.setTranslateX(225);
        button.setTranslateY(200);

        for (int aTabDeNombreDeJoueursDisponible : tabDeNombreDeJoueursDisponible) {
            comboBox.getItems().addAll(aTabDeNombreDeJoueursDisponible);
        }

        button.setOnAction(event -> inizialiseNbJoueurs(comboBox.getSelectionModel().getSelectedItem()));

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(labelBienvenue,labelChoixJoueurs,comboBox, button);
        Scene scene = new Scene(vBox, 500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void inizialiseNbJoueurs(int nbJoueur) {
        nbJoueurs = nbJoueur;
        fenetreDemandeNomsJoueurs();
    }

    private void fenetreDemandeNomsJoueurs(){
        Button buttonValider = new Button("Valider");
        Label[] label = new Label[nbJoueurs];
        TextField[] textField = new TextField[nbJoueurs];
        buttonValider.setOnAction(event -> initialiseNomsJoueurs(textField));
        VBox vBox2 = new VBox(10);
        for (int i = 0; i < textField.length; i++) {
            label[i] = new Label("Nom du joueur " + (i+1) + " :");
            textField[i] = new TextField();
            textField[i].setPromptText("Saisissez le nom du joueur " + (i+1));
            vBox2.getChildren().addAll(label[i],textField[i]);
        }
        vBox2.getChildren().addAll(buttonValider);
        Scene scene2 = new Scene(vBox2, 500, 500);
        stage.setScene(scene2);
        stage.show();
    }

    private void initialiseNomsJoueurs(TextField[] textField) {
        nomsJoueurs = new String[textField.length];
        for (int i = 0; i < textField.length; i++) {
            nomsJoueurs[i] = textField[i].getText();
        }
        afficheFenetreDeJeu();
    }
}