package Jeu.View;

import Jeu.Model.Joueur;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FenetreAmelioration {
    private Joueur j;
    private Pane pane;
    private Stage popupAmelioration;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    public FenetreAmelioration(Joueur j) {
        this.j=j;
        popupAmelioration = new Stage();
        canvas = new Canvas(420,120);
        graphicsContext = canvas.getGraphicsContext2D();
        initPopup();
    }

    private void initPopup() {
        popupAmelioration.initModality(Modality.APPLICATION_MODAL);
        popupAmelioration.initStyle(StageStyle.TRANSPARENT);
        pane = new Pane();
        Scene scene = new Scene(pane);
        popupAmelioration.setScene(scene);
    }
}
