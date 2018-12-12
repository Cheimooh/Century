package Jeu.View;

import Jeu.Controller.ControlMouseAmelioration;
import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    public void fenetreAmeliorationEpice(){
        pane.getChildren().clear();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,420,120);
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(10,10,400,100);
        String s = "Veuillez sélectionner une épice à améliorer : ";
        Color color;
        graphicsContext.strokeText(s, 20,30);
        Caravane caravane = j.getCaravane();
        for (int k = 0; k < caravane.getEpices().size(); k++) {
            if (caravane.getEpices().get(k).equals(Epice.tumeric)) color = Epice.tumeric.getColor();
            else if (caravane.getEpices().get(k).equals(Epice.safran)) color = Epice.safran.getColor();
            else if (caravane.getEpices().get(k).equals(Epice.cardamome)) color = Epice.cardamome.getColor();
            else color = Color.LIGHTGREY;
            graphicsContext.setFill(color);
            graphicsContext.fillRect(k * 22 + 90, 60, 20, 20);
        }
        pane.getChildren().add(canvas);
        ControlMouseAmelioration controlMouseAmelioration = new ControlMouseAmelioration(this);
        pane.setOnMouseClicked(controlMouseAmelioration);
        popupAmelioration.showAndWait();
    }

    public void close() { popupAmelioration.close();}

    public Joueur getJ() { return j; }
}
