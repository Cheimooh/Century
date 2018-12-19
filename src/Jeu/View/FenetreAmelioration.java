package Jeu.View;

import Jeu.Controller.ControlMouseAmelioration;
import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        canvas = new Canvas(420,140);
        graphicsContext = canvas.getGraphicsContext2D();
        initPopup();
    }

    private void initPopup() {
        popupAmelioration.initStyle(StageStyle.TRANSPARENT);
        popupAmelioration.initModality(Modality.APPLICATION_MODAL);
        pane = new Pane();
        Scene scene = new Scene(pane);
        popupAmelioration.setScene(scene);
    }

    public void fenetreAmeliorationEpice(){
        Image fleche = new Image("Jeu/View/fleche.png");
        pane.getChildren().clear();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,420,140);
        graphicsContext.setFill(Color.color(0.76, 0.76, 0.64));
        graphicsContext.fillRect(10,10,400,120);
        String s = "Veuillez sélectionner une épice à améliorer : ";
        Color color;
        graphicsContext.strokeText(s, 20,30);
        Caravane caravane = j.getCaravane();
        for (int k = 0; k < caravane.getEpices().size(); k++) {
            color = caravane.getEpices().get(k).getColor();
            graphicsContext.setFill(color);
            graphicsContext.fillRect(k * 22 + 50, 40, 20, 20);
            // DRAW FLECHE
            if (caravane.getEpices().get(k)!=Epice.cannelle) {
                graphicsContext.drawImage(fleche, k * 22 + 50, 65, 20, 20);
                // SHOW AMELIORATION
                if (caravane.getEpices().get(k).equals(Epice.tumeric)) color = Epice.safran.getColor();
                else if (caravane.getEpices().get(k).equals(Epice.safran)) color = Epice.cardamome.getColor();
                else color = Epice.cannelle.getColor();
                graphicsContext.setFill(color);
                graphicsContext.fillRect(k * 22 + 50, 90, 20, 20);
            }
        }
        pane.getChildren().add(canvas);
        ControlMouseAmelioration controlMouseAmelioration = new ControlMouseAmelioration(this);
        pane.setOnMouseClicked(controlMouseAmelioration);
        popupAmelioration.showAndWait();
    }

    public void close() { popupAmelioration.close();}

    public Joueur getJ() { return j; }
}
