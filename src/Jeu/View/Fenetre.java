package Jeu.View;

import Jeu.Model.Carte;
import Jeu.Model.Century;
import Jeu.Controller.ControlMouse;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fenetre extends Parent {
    private Century century;
    private int height;
    private int width;
    private final int largeurImage=111;
    private final int hauteurImage=500/3;
    private GraphicsContext graphicsContext;

    public Fenetre(Century century, int width, int height) {
        this.century=century;
        this.height=height;
        this.width=width;
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
        afficherPiocheMarchande();
    }

    private void afficherPiocheMarchande() {
        for (int i = 0; i < 5; i++) {
            Carte c = century.getPioche().getCarteMarchand();
            Image imageCarte = c.getImage();
            drawCartePiocheMarchande(imageCarte,i);
        }
    }

    private void drawCartePiocheMarchande(Image imageCarte,int i) {
        int emplacement = width-largeurImage*(i+1)-(30*(i+1));
        graphicsContext.drawImage(imageCarte,emplacement,(height/3)+30,largeurImage,hauteurImage);
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
