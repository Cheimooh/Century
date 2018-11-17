package Jeu.View;

import Jeu.Model.Carte;
import Jeu.Model.Century;
import Jeu.Controller.ControlMouse;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Optional;

public class Fenetre extends Parent {
    private Century century;
    private int height;
    private int width;
    private GraphicsContext graphicsContext;

    public Fenetre(Century century, int width, int height) {
        this.century=century;
        this.height=height;
        this.width=width;
        Canvas canvas = new Canvas(width, height);
        ControlMouse controlMouse = new ControlMouse(this, width, height);
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
        for (int i = 0; i < 5; i++) {
            Image imageCarte = century.getCartePresenteSurLaPiocheMarchande().get(i).getImage();
            drawCartePiocheMarchande(imageCarte,i);
        }
        afficherMainDuJoueur();
    }

    public void tourSuivant(){
        afficherMainDuJoueur();
        Color color = Color.LIGHTGREY;
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, 250, height);
        for (int i = 0; i < century.getNbJoueur(); i++) {
            int hauteur = (height/century.getNbJoueur())*i;
            drawLine(0,hauteur-1,250,hauteur-1);
            afficherJoueur(i,hauteur);
        }
    }

    private void afficherMainDuJoueur() {
        int largeurImage = 111;
        int hauteurImage = 500 / 3;
        Joueur j = century.getTabJoueur()[century.getJoueurActuel()];
        int y = (height/3)*2;
        Color color = Color.LIGHTGREY;
        graphicsContext.setFill(color);
        graphicsContext.fillRect(250, y, width, height);
        drawLine(250, y, width, y);
        graphicsContext.strokeText(j.getNom(), 260, y+20);
        Image imageCarte;
        int emplacement;
        for (int i = 0; i < j.getListeCartes().size(); i++) {
            emplacement = 250+ largeurImage *(i+1)+(30*(i));
            imageCarte=j.getListeCartes().get(i).getImage();
            graphicsContext.drawImage(imageCarte,emplacement,y+30, largeurImage, hauteurImage);
        }
    }

    private void drawCartePiocheMarchande(Image imageCarte,int i) {
        int largeurImage = 111;
        int emplacement = width- largeurImage *(i+1)-(30*(i+1));
        int hauteurImage = 500 / 3;
        graphicsContext.drawImage(imageCarte,emplacement,(height/3), largeurImage, hauteurImage);
    }

    private void afficherJoueur(int numJoueur, int hauteur) {
        String nom = century.getTabJoueur()[numJoueur].getNom();
        graphicsContext.strokeText(nom, 20, hauteur+20);
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(10, hauteur+30, 30, 45);
        int nbCarte = century.getTabJoueur()[numJoueur].getListeCartes().size();
        graphicsContext.strokeText("x"+nbCarte, 45, hauteur+55);

        ArrayList<Epice> listeEpices = century.getTabJoueur()[numJoueur].getCaravane().getEpices();
        int nbTumeric=0;
        int nbSafran=0;
        int nbCardamome=0;
        int nbCannelle=0;
        for (Epice listeEpice : listeEpices) {
            if (listeEpice == Epice.tumeric) nbTumeric++;
            if (listeEpice == Epice.safran) nbSafran++;
            if (listeEpice == Epice.cardamome) nbCardamome++;
            if (listeEpice == Epice.cannelle) nbCannelle++;
        }
        drawEpices(Epice.tumeric.getColor(), nbTumeric, 70, hauteur+30);
        drawEpices(Epice.safran.getColor(), nbSafran, 70, hauteur+60);
        drawEpices(Epice.cardamome.getColor(), nbCardamome, 70, hauteur+90);
        drawEpices(Epice.cannelle.getColor(), nbCannelle, 70, hauteur+120);
    }

    private void drawEpices(Color color, int nbEpices, int x, int y) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x,y,20,20);
        graphicsContext.strokeText("x"+nbEpices, x+25, y+15);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        graphicsContext.moveTo(x1,y1);
        graphicsContext.lineTo(x2,y2);
        graphicsContext.stroke();
    }

    public void retirerCarte(int i) {
        century.retirerCartePiocheMarchande(i);
        for (int j = 0; j < 5; j++) {
            Image imageCarte = century.getCartePresenteSurLaPiocheMarchande().get(j).getImage();
            drawCartePiocheMarchande(imageCarte,j);
        }
    }

    public boolean confirmation(int i) {
        Carte c = century.getCartePresenteSurLaPiocheMarchande().get(i);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");

        String s = "Voulez-vous prendre cette carte ?";
        ImageView img = new ImageView(c.getImage());
        img.setFitWidth(111);
        img.setFitHeight(500/3);

        alert.setContentText(s);
        alert.setGraphic(img);
        Optional<ButtonType> result = alert.showAndWait();
        boolean confirmation=false;
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            confirmation = true;
        }

        return confirmation;
    }

    public Century getCentury() { return century; }
}
