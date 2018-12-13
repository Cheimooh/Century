package Jeu.View;

import Jeu.Model.Century;
import Jeu.Model.ControlMouseJoueur;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FenetreJoueur extends Parent {
    private Century century;
    private int height;
    private int width;
    private GraphicsContext graphicsContext;
    private FenetrePrincipale fenetrePrincipale;

    public FenetreJoueur(Century century, int width, int height) {
        this.century=century;
        this.height=height;
        this.width=width;
        Canvas canvas = new Canvas(width, height);
        ControlMouseJoueur controlMouseJoueur = new ControlMouseJoueur(this);
        canvas.setOnMouseClicked(controlMouseJoueur);
        graphicsContext = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
    }

    public void afficherMainDuJoueur() {
        //AFFICHAGE DE LA MAIN DU JOUEUR (EN BAS)
        int largeurImage = 111;
        int largeurImageMain = largeurImage /2;
        int hauteurImage = 500 / 3;
        int hauteurImageMain = hauteurImage /2;
        Joueur j = century.getTabJoueur()[century.getJoueurActuel()];
        Color color = Color.LIGHTGREY;
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, width, height);
        graphicsContext.strokeText(j.getNom(), 10, 20);
        Image imageCarte;
        int emplacement;
        for (int i = 0; i < j.getListeCartes().size(); i++) {
            if(i<=(j.getListeCartes().size()/2)-1) {
                emplacement = largeurImageMain * (i + 1) + (30 * (i));
                imageCarte = new Image(j.getListeCartes().get(i).getPath());
                graphicsContext.drawImage(imageCarte, emplacement,  30, largeurImageMain, hauteurImageMain);
                if (j.getCartesActives().get(i)==0){
                    graphicsContext.strokeText("Inutilisable",emplacement,80);
                }
            } else {
                int i2 = i-j.getListeCartes().size()/2;
                emplacement = largeurImageMain * (i2 + 1) + (30 * (i2));
                imageCarte = new Image(j.getListeCartes().get(i).getPath());
                graphicsContext.drawImage(imageCarte, emplacement, hauteurImageMain+50, largeurImageMain, hauteurImageMain);
                if (j.getCartesActives().get(i)==0) {
                    graphicsContext.strokeText("Inutilisable", emplacement, hauteurImageMain + 100);
                }
            }
        }
        ArrayList<Epice> listeEpices = j.getCaravane().getEpices();
        int debutCaravaneX=600;
        graphicsContext.setFill(Color.color(0.65,0.62,0.94));
        graphicsContext.fillRect(debutCaravaneX, 30, 180,70);
        for (int i = 0; i < listeEpices.size() ; i++) {
            int emplacementX;
            int emplacementY;
            if(i<=(listeEpices.size()/2)-1) {
                emplacementX= debutCaravaneX + i*30+10;
                emplacementY= 40;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            } else {
                int i2 = i-listeEpices.size()/2;
                emplacementX= debutCaravaneX + i2*30+10;
                emplacementY= 70;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            }
        }
        graphicsContext.setFill(Color.color(0.4,0.4,0.4));
        graphicsContext.fillRect(debutCaravaneX, 145, 100, 22);
        graphicsContext.strokeText("Se reposer", debutCaravaneX+10,160);
    }

    //AFFICHAGE DES EPICES DANS LA MAIN
    private void drawEpicesMain(ArrayList<Epice> listeEpices, int i, int emplacementX, int emplacementY) {
        if (listeEpices.get(i).equals(Epice.tumeric)) {
            drawEpices(Epice.tumeric.getColor(),emplacementX, emplacementY);
        } else if (listeEpices.get(i).equals(Epice.safran)) {
            drawEpices(Epice.safran.getColor(),emplacementX, emplacementY);
        }else if (listeEpices.get(i).equals(Epice.cardamome)) {
            drawEpices(Epice.cardamome.getColor(),emplacementX, emplacementY);
        }else if (listeEpices.get(i).equals(Epice.cannelle)) {
            drawEpices(Epice.cannelle.getColor(),emplacementX, emplacementY);
        }
    }

    //AFFICHAGE DES EPICES
    private void drawEpices(Color color,  int x, int y) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x,y,20,20);
    }

    public void afficheErreur(String titre, String erreur) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(erreur);
        alert.showAndWait();
    }

    public void afficheDemandeAmelioration(int nbAmelioration) {
        Joueur j = century.getTabJoueur()[century.getJoueurActuel()];
        FenetreAmelioration fenetreAmelioration=new FenetreAmelioration(j);
        for (int i = 0; i <nbAmelioration ; i++) {
            fenetreAmelioration.fenetreAmeliorationEpice();
        }
    }

    public Century getCentury() { return century; }

    public FenetrePrincipale getFenetrePrincipale() { return fenetrePrincipale; }

    public void setFenetrePrincipale(FenetrePrincipale fenetrePrincipale) { this.fenetrePrincipale = fenetrePrincipale; }
}
