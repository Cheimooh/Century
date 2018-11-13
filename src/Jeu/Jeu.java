package Jeu;

import Jeu.Model.Century;
import Jeu.View.Fenetre;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Jeu extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Century : la route des épices");
        Group root = new Group();
        Century century = new Century();
        int height = 700;
        int width = 1000;
        Fenetre fenetre = new Fenetre(century, width, height);
        root.getChildren().add(fenetre);
        primaryStage.setScene(new Scene(root, width, height, Color.LIGHTGREY));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
