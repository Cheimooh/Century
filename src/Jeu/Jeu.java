package Jeu;

import Jeu.Model.Century;
import Jeu.View.Fenetre;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        Button button = new Button("Valider");

        comboBox = new ComboBox<>();

        comboBox.setPromptText("Voulez-vous jouer à ...");

        for (int aTabDeNombreDeJoueursDisponible : tabDeNombreDeJoueursDisponible) {
            comboBox.getItems().addAll(aTabDeNombreDeJoueursDisponible);
        }

        button.setOnAction(event -> initialiseNbJoueurs(comboBox.getSelectionModel().getSelectedItem()));

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(comboBox, button);
        Scene scene = new Scene(vBox, 250,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initialiseNbJoueurs(int nbJoueur) {
        nbJoueurs = nbJoueur;
        fenetreDemandeNomsJoueurs();
    }

    private void fenetreDemandeNomsJoueurs(){
        Button buttonValider = new Button("Valider");
        TextField[] textField = new TextField[nbJoueurs];
        buttonValider.setOnAction(event -> initialiseNomsJoueurs(textField));
        VBox vBox2 = new VBox(10);
        for (int i = 0; i < textField.length; i++) {
            textField[i] = new TextField();
            vBox2.getChildren().add(textField[i]);
        }
        vBox2.getChildren().addAll(buttonValider);
        Scene scene2 = new Scene(vBox2, 250, 200);
        stage.setScene(scene2);
        stage.show();
    }

    private void initialiseNomsJoueurs(TextField[] textField) {
        nomsJoueurs = new String[textField.length];
        for (int i = 0; i < textField.length; i++) {
            nomsJoueurs[i] = textField[i].getText();
            System.out.println(nomsJoueurs[i]);
        }
        afficheFenetreDeJeu();
    }
}