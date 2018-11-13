package Jeu.View;

import Jeu.Model.Century;
import Jeu.Controller.ControlMouse;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Fenetre extends Parent {
    private Century century;
    private int height;
    private GraphicsContext graphicsContext;

    public Fenetre(Century century, int width, int height) {
        this.century=century;
        this.height=height;
        Canvas canvas = new Canvas(width, height);
        ControlMouse controlMouse = new ControlMouse(this);
        canvas.setOnMouseClicked(controlMouse);
        graphicsContext = canvas.getGraphicsContext2D();
        afficherPlateau();
        this.getChildren().add(canvas);
    }

    private void afficherPlateau() {
        drawLine(250,0,250,height);
        for (int i = 0; i < century.getNbJoueur(); i++) {
            int hauteur = (height/century.getNbJoueur())*i;
            drawLine(0,hauteur-1,250,hauteur-1);
            afficherJoueur(i,hauteur);
        }
    }

    private void afficherJoueur(int numJoueur, int hauteur) {
        String nom = century.getTabJoueur()[numJoueur].getNom();
        graphicsContext.strokeText(nom, 20, hauteur+20);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        graphicsContext.moveTo(x1,y1);
        graphicsContext.lineTo(x2,y2);
        graphicsContext.stroke();
    }
}
