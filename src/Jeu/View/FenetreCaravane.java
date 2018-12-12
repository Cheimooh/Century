package Jeu.View;

import Jeu.Controller.ControlMouseCaravane;
import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FenetreCaravane {
    private Caravane caravane;
    private Pane pane;
    private Stage popup;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    public FenetreCaravane(Caravane caravane){
        this.caravane=caravane;
        popup = new Stage();
        canvas = new Canvas(240,50);
        graphicsContext = canvas.getGraphicsContext2D();
        initPopup();
    }

    private void initPopup() {
        popup.initModality(Modality.APPLICATION_MODAL);
        pane = new Pane();
        Scene scene = new Scene(pane);
        popup.setScene(scene);
    }

    public void fenetreSuppressionEpice(){
        pane.getChildren().clear();
        for (int k = 0; k < caravane.getEpices().size(); k++) {
            Color color;
            if (caravane.getEpices().get(k).equals(Epice.tumeric)) color = Epice.tumeric.getColor();
            else if (caravane.getEpices().get(k).equals(Epice.safran)) color = Epice.safran.getColor();
            else if (caravane.getEpices().get(k).equals(Epice.cardamome)) color = Epice.cardamome.getColor();
            else color = Epice.cannelle.getColor();
            graphicsContext.setFill(color);
            graphicsContext.fillRect(k * 22 + 10, 15, 20, 20);
        }
        pane.getChildren().add(canvas);
        ControlMouseCaravane controlMouseCaravane = new ControlMouseCaravane(this);
        pane.setOnMouseClicked(controlMouseCaravane);
        popup.showAndWait();
    }

    public Caravane getCaravane() { return caravane; }

    public void close() { popup.close();}
}
