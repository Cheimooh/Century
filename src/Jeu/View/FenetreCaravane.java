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
import javafx.stage.StageStyle;

public class FenetreCaravane {
    private Caravane caravane;
    private Pane pane;
    private Stage popup;
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    public FenetreCaravane(Caravane caravane){
        this.caravane=caravane;
        popup = new Stage();
        canvas = new Canvas(420,120);
        graphicsContext = canvas.getGraphicsContext2D();
        initPopup();
    }

    private void initPopup() {
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.initStyle(StageStyle.TRANSPARENT);
        pane = new Pane();
        Scene scene = new Scene(pane);
        popup.setScene(scene);
    }

    public void fenetreSuppressionEpice(Epice epice){
        pane.getChildren().clear();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,420,120);
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(10,10,400,100);
        String s = "Veuillez sélectionner une épice à remplacer par l'épice suivante : ";
        Color color = epice.getColor();
        graphicsContext.setFill(color);
        graphicsContext.fillRect(  365, 15, 20, 20);
        graphicsContext.strokeText(s, 20,30);
        for (int k = 0; k < caravane.getEpices().size(); k++) {
            color = caravane.getEpices().get(k).getColor();
            graphicsContext.setFill(color);
            graphicsContext.fillRect(k * 22 + 90, 60, 20, 20);
        }
        pane.getChildren().add(canvas);
        ControlMouseCaravane controlMouseCaravane = new ControlMouseCaravane(this);
        pane.setOnMouseClicked(controlMouseCaravane);
        popup.showAndWait();
    }

    public Caravane getCaravane() { return caravane; }

    public void close() { popup.close();}
}
