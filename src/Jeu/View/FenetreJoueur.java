package Jeu.View;

import Jeu.Model.Century;
import Jeu.Controller.ControlMouseJoueur;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class FenetreJoueur extends Parent {
    private Century century;
    private int height;
    private int width;
    private GraphicsContext graphicsContext;
    private FenetrePrincipale fenetrePrincipale;
    private int idJoueur;
    private int indexCartes;

    public FenetreJoueur(Century century, int width, int height, int id) {
        this.idJoueur=id;
        this.century=century;
        this.height=height;
        this.width=width;
        this.indexCartes=0;
        Canvas canvas = new Canvas(width, height);
        ControlMouseJoueur controlMouseJoueur = new ControlMouseJoueur(this);
        canvas.setOnMouseClicked(controlMouseJoueur);
        graphicsContext = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
    }

    public void afficherMainDuJoueur() {
        //AFFICHAGE DE LA MAIN DU JOUEUR (EN BAS)
        Joueur j = century.getTabJoueur()[idJoueur];
        Color color = Color.LIGHTGREY;
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, width, height);
        String nom = j.getNom();
        if (century.getJoueurActuel()==idJoueur){
            nom+=" : à vous de jouer !";
        } else {
            nom+=" : veuillez patienter";
        }
        graphicsContext.strokeText(nom, 200-(nom.length()*3), 20);
        drawCartesMain(j);
        drawCaravane(j);
    }

    private void drawCaravane(Joueur j){
        ArrayList<Epice> listeEpices = j.getCaravane().getEpices();
        int debutCaravaneX=260;
        int debutCaravaneY = 45;
        graphicsContext.setFill(Color.color(0.87,0.69,0.68));
        graphicsContext.fillRect(debutCaravaneX, debutCaravaneY, 70,160);
        for (int i = 0; i < listeEpices.size() ; i++) {
            int emplacementX;
            int emplacementY;
            if(i % 2 ==0) {
                emplacementX= debutCaravaneX + 10;
                emplacementY= debutCaravaneY + i*15+10;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            } else {
                emplacementX= debutCaravaneX + 40;
                emplacementY= debutCaravaneY + (i-1)*15+10;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            }
        }
        graphicsContext.setFill(Color.color(0.4,0.4,0.4));
        graphicsContext.fillRect(250, 250, 80, 22);
        graphicsContext.strokeText("Se reposer", 260,265);
    }

    private void drawCartesMain(Joueur j){
        Image imageCarte;
        int emplacement;
        for (int i = indexCartes*6; i < (indexCartes+1)*6; i++) {
            if (j.getListeCartes().size()>i) {
                int hauteurImageMain = 500 / 6;
                int largeurImageMain = 111 / 2;
                if (i % 2 == 0) {
                    emplacement = largeurImageMain + ((i-indexCartes*6) + 1) + (30 * (i-indexCartes*6));
                    imageCarte = new Image(j.getListeCartes().get(i).getPath());
                    graphicsContext.drawImage(imageCarte, emplacement, 30, largeurImageMain, hauteurImageMain);
                    if (j.getCartesActives().get(i) == 0) {
                        graphicsContext.strokeText("Inutilisable", emplacement, 80);
                    }
                } else {
                    emplacement = largeurImageMain + (i-indexCartes*6) + (30 * ((i-1)-indexCartes*6));
                    imageCarte = new Image(j.getListeCartes().get(i).getPath());
                    graphicsContext.drawImage(imageCarte, emplacement, hauteurImageMain + 50, largeurImageMain, hauteurImageMain);
                    if (j.getCartesActives().get(i) == 0) {
                        graphicsContext.strokeText("Inutilisable", emplacement, hauteurImageMain + 100);
                    }
                }
            }
        }
        if (indexCartes>0){
            graphicsContext.setFill(Color.color(0.4,0.4,0.4));
            graphicsContext.fillRect(50, 250, 80, 22);
            graphicsContext.strokeText("Précédent", 60,265);
        }
        if ((indexCartes+1)*6<j.getListeCartes().size()) {
            graphicsContext.setFill(Color.color(0.4,0.4,0.4));
            graphicsContext.fillRect(150, 250, 80, 22);
            graphicsContext.strokeText("Suivant", 170,265);
        }
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

    public int getIdJoueur() { return idJoueur; }

    public int getIndexCartes() { return indexCartes; }

    public void setIndexCartes(int indexCartes) { this.indexCartes = indexCartes; }
}
