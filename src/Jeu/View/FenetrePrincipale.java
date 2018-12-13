package Jeu.View;

import Jeu.Model.*;
import Jeu.Controller.ControlMouse;
import javafx.scene.Parent;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.util.*;

public class FenetrePrincipale extends Parent {
    private Century century;
    private int height;
    private int width;
    private GraphicsContext graphicsContext;
    private FenetreJoueur fenetreJoueur;

    private int largeurImage = 111;
    private int hauteurImage = 500 / 3;

    public FenetrePrincipale(Century century, int width, int height, FenetreJoueur fenetreJoueur) {
        this.fenetreJoueur=fenetreJoueur;
        fenetreJoueur.setFenetrePrincipale(this);
        this.century=century;
        century.setF(this);
        this.height=height;
        this.width=width;
        Canvas canvas = new Canvas(width, height);
        ControlMouse controlMouse = new ControlMouse(this, width, height);
        canvas.setOnMouseClicked(controlMouse);
        graphicsContext = canvas.getGraphicsContext2D();
        afficherPlateau();
        this.getChildren().add(canvas);
    }

    //AFFICHAGE GENERAL
    private void afficherPlateau() {
        drawLine(250,0,250,height);
        for (int i = 0; i < century.getNbJoueur(); i++) {
            int hauteur = (height/century.getNbJoueur())*i;
            drawLine(0,hauteur-1,250,hauteur-1);
            afficherJoueur(i,hauteur);
        }
        for (int i = 0; i < 5; i++) {
            ArrayList<Epice> epicesSurLaCarte = new ArrayList<>();
            Image imageCarte = new Image(century.getCartePresenteSurLaPiocheMarchande().get(i).getPath());
            drawCartePiocheMarchande(imageCarte,i, epicesSurLaCarte);
        }
        for (int i = 0; i < 5; i++) {
            Image imageCarteCommande = new Image(century.getCartePresenteSurLaPiocheCommande().get(i).getPath());
            int nbPieces=0;
            if (i==0) nbPieces=century.getNbPiecesOr();
            if (i==1) nbPieces=century.getNbPiecesArgent();
            drawCartePiocheCommande(imageCarteCommande,i,nbPieces);
        }
        fenetreJoueur.afficherMainDuJoueur();
    }

    //CHANGEMENT DE L'AFFICHAGE A CHAQUE TOUR
    public void tourSuivant(){
        fenetreJoueur.afficherMainDuJoueur();
        Color color = Color.LIGHTGREY;
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0, 0, 250, height);
        for (int i = 0; i < century.getNbJoueur(); i++) {
            int hauteur = (height/century.getNbJoueur())*i;
            drawLine(0,hauteur-1,250,hauteur-1);
            afficherJoueur(i,hauteur);
        }
    }

    //AFFICHAGE DE LA PIOCHE MARCHANDE
    private void drawCartePiocheMarchande(Image imageCarte,int i, ArrayList<Epice> epices) {
        int emplacement = width- largeurImage *(i+1)-(30*(i+1));
        graphicsContext.drawImage(imageCarte,emplacement,(height/3.)+100, largeurImage, hauteurImage);
        for (int j = 0; j < epices.size(); j++) {
            drawEpices(epices.get(j).getColor(), 0,emplacement+(j*25), (height/3)+hauteurImage+110, true);
        }
    }

    private void drawCartePiocheCommande(Image imageCarte,int i, int nbPieces) {
        int emplacement = width- largeurImage *(i+1)-(30*(i+1));
        graphicsContext.drawImage(imageCarte,emplacement,100, largeurImage, hauteurImage);
        if (i==0){
            if (nbPieces>0) {
                graphicsContext.setFill(Color.GOLD);
                graphicsContext.fillOval(emplacement + 30, 60, 30, 30);
                graphicsContext.strokeText("x" + nbPieces, emplacement + 65, 80);
            }
        }
        if (i==1){
            if (nbPieces>0) {
                graphicsContext.setFill(Color.SILVER);
                graphicsContext.fillOval(emplacement + 30, 60, 30, 30);
                graphicsContext.strokeText("x" + nbPieces, emplacement + 65, 80);
            }
        }
    }

    //AFFICHAGE DES JOUEURS A GAUCHE (DANS L'ORDRE DE LEUR TOUR
    private void afficherJoueur(int numJoueur, int hauteur) {
        //AFFICHAGE DU NOM DU JOUEUR
        String nom = century.getTabJoueur()[numJoueur].getNom();
        graphicsContext.strokeText(nom, 20, hauteur+20);

        // AFFICHAGE DU NOMBRE DE CARTES
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(10, hauteur+30, 30, 45);
        int nbCarte = century.getTabJoueur()[numJoueur].getListeCartes().size();
        graphicsContext.strokeText("x"+nbCarte, 45, hauteur+55);

        // AFFICHAGE DU NOMBRE DE CARTES COMMANDE
        graphicsContext.setFill(Color.INDIANRED);
        graphicsContext.fillRect(120, hauteur+30, 30,45);
        int nbCarteCommande = century.getTabJoueur()[numJoueur].getListeCartesCommande().size();
        graphicsContext.strokeText("x"+nbCarteCommande, 155, hauteur+55);

        // AFFICHAGE DU NOMBRE DE PIECES D'OR
        graphicsContext.setFill(Color.GOLD);
        graphicsContext.fillOval(180, hauteur+30, 30,30);
        int nbPiecesOr = century.getTabJoueur()[numJoueur].getNbPiecesOr();
        graphicsContext.strokeText("x"+nbPiecesOr, 215, hauteur+50);

        // AFFICHAGE DU NOMBRE DE PIECES D'ARGENT
        graphicsContext.setFill(Color.SILVER);
        graphicsContext.fillOval(180, hauteur+70, 30,30);
        int nbPiecesArgent = century.getTabJoueur()[numJoueur].getNbPiecesArgent();
        graphicsContext.strokeText("x"+nbPiecesArgent, 215, hauteur+90);

        // AFFICHAGE DU NOMBRE D'EPICES
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
        drawEpices(Epice.tumeric.getColor(), nbTumeric, 70, hauteur+30, false);
        drawEpices(Epice.safran.getColor(), nbSafran, 70, hauteur+60, false);
        drawEpices(Epice.cardamome.getColor(), nbCardamome, 70, hauteur+90, false);
        drawEpices(Epice.cannelle.getColor(), nbCannelle, 70, hauteur+120, false);
    }

    //AFFICHAGE DES EPICES
    private void drawEpices(Color color, int nbEpices, int x, int y, boolean isMainDuJoueur) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x,y,20,20);
        if (!isMainDuJoueur) {
            graphicsContext.strokeText("x" + nbEpices, x + 25, y + 15);
        }
    }

    //AFFICHAGE D'UNE LIGNE du point (x1,y1) à (x2,y2)
    private void drawLine(int x1, int y1, int x2, int y2) {
        graphicsContext.moveTo(x1,y1);
        graphicsContext.lineTo(x2,y2);
        graphicsContext.stroke();
    }

    //PERMET DE RETIRER UNE CARTE ET DE REDESSINER UNE NOUVELLE CARTE
    public void retirerCarte(int i) {
        century.retirerCartePiocheMarchande(i);
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(width- largeurImage *(i+1)-(30*(i+1)),(height/3.+hauteurImage)+100, largeurImage, 50);
        for (int j = 0; j < 5; j++) {
            ArrayList<Epice> epicesSurLaCarte = new ArrayList<>();
            if (j==0) epicesSurLaCarte=century.getEpicesSurLaCarte1();
            if (j==1) epicesSurLaCarte=century.getEpicesSurLaCarte2();
            if (j==2) epicesSurLaCarte=century.getEpicesSurLaCarte3();
            if (j==3) epicesSurLaCarte=century.getEpicesSurLaCarte4();
            if (j==4) epicesSurLaCarte=century.getEpicesSurLaCarte5();
            if (century.getCartePresenteSurLaPiocheMarchande().size()>j) {
                Image imageCarte = new Image(century.getCartePresenteSurLaPiocheMarchande().get(j).getPath());
                drawCartePiocheMarchande(imageCarte, j, epicesSurLaCarte);
            }
        }
    }

    public void retirerCarteCommande(int i) {
        century.retirerCartePiocheCommande(i);
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(width- largeurImage *(i+1)-(30*(i+1)),0, largeurImage, 100);
        for (int j = 0; j < 5; j++) {
            int nbPieces = 0;
            if (j==0) nbPieces=century.getNbPiecesOr();
            if (j==1) nbPieces=century.getNbPiecesArgent();
            if (century.getCartePresenteSurLaPiocheCommande().size()>j) {
                Image imageCarte = new Image(century.getCartePresenteSurLaPiocheCommande().get(j).getPath());
                drawCartePiocheCommande(imageCarte, j,nbPieces);
            }
        }
    }

    //MESSAGE DE CONFIRMATION LORSQUE L'ON SOUHAITE PRENDRE UNE CARTE
    public boolean confirmation(int i) {
        Carte c = century.getCartePresenteSurLaPiocheMarchande().get(i);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");

        String s = "Voulez-vous prendre cette carte ?";
        ImageView img = new ImageView(c.getPath());
        img.setFitWidth(111);
        img.setFitHeight(500/3.);

        alert.setContentText(s);
        alert.setGraphic(img);
        Optional<ButtonType> result = alert.showAndWait();
        boolean confirmation=false;
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            confirmation = true;
        }

        return confirmation;
    }

    //PERMET DE RETIRER UN EMPLACEMENT DANS LA CARTE MARCHANDE
    public void retirerEmplacementCarteMarchande(int i) {
        i=i+1;
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(width- 111 *(i+1)-(30*(i+1)), height/3., 111, 500/3.);
    }

    public void retirerEmplacementCarteCommande(int i) {
        i=i+1;
        graphicsContext.setFill(Color.LIGHTGREY);
        graphicsContext.fillRect(width- 111 *(i+1)-(30*(i+1)), 50, 111, 500/3.);
    }

    public void afficheErreur(String titre, String erreur) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(erreur);
        alert.showAndWait();
    }

    private void fenetreFinJeu(String[] classement) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Résultats");
        alert.setHeight(300);
        alert.setWidth(300);
        String[] affichageFinal = new String[century.getTabJoueur().length];

        for (int i = 0; i < classement.length; i++) {
            affichageFinal[i] = (i+1) +" : " + classement[i] + "\n";
        }

        String finale = "";
        finale+= "Le vainqueur de cette partie est : " + classement[0] + "\n" +
                "Voici le classment : \n";

        for (int i = 0; i <classement.length ; i++) {
            finale += affichageFinal[i];
        }

        alert.setContentText(finale);

        //Afficher classement

        ButtonType recommencer = new ButtonType("Recommencer");
        ButtonType quitter = new ButtonType("Quitter");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(recommencer,quitter);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == recommencer){
            // Recommencer le jeu
        }
        else if (option.get() == quitter){
            System.exit(0);
        }
    }

    public void afficheFenetreFinJeu(){
        int[] listTotalPoint = new int[century.getTabJoueur().length];
        boolean partieFinie = false;
        String[] classement = new String[century.getTabJoueur().length];


        for (int i = 0; i < century.getTabJoueur().length; i++) {
            int j = century.getTabJoueur()[i].getNbPoints();
            listTotalPoint[i]=j;
        }
        Arrays.sort(listTotalPoint);

        if (century.getTabJoueur().length == 4 || century.getTabJoueur().length == 5){
            for (int i = 0; i < century.getTabJoueur().length; i++){
                if (century.getTabJoueur()[i].getListeCartesCommande().size() == 5){
                    //recuperer joueur ayant le plus de point
                    for (int k = 0; k < century.getTabJoueur().length; k++){
                        for (int j = 1; j < century.getTabJoueur().length + 1 ; j++) {
                            if (century.getTabJoueur()[k].getNbPoints() == listTotalPoint[listTotalPoint.length - j]){
                                if (k>0 && century.getTabJoueur()[k].getNbPoints() == century.getTabJoueur()[k-1].getNbPoints()){
                                    classement[j-1] = century.getTabJoueur()[k].getNom();
                                }
                                else classement[j-1] = century.getTabJoueur()[k].getNom();
                                //partieFinie=true;
                            }
                        }
                        partieFinie=true;
                    }
                }
            }
        }
        else if (century.getTabJoueur().length == 2 || century.getTabJoueur().length == 3){
            for (int i = 0; i < century.getTabJoueur().length; i++){
                if (century.getTabJoueur()[i].getListeCartesCommande().size() == 6){
                    //recuperer joueur ayant le plus de point
                    for (int k = 0; k < century.getTabJoueur().length; k++){
                        for (int j = 1; j < century.getTabJoueur().length +1; j++)
                            if (century.getTabJoueur()[k].getNbPoints() == listTotalPoint[listTotalPoint.length - j]) {
                                if (k > 0 && century.getTabJoueur()[k].getNbPoints() == century.getTabJoueur()[k - 1].getNbPoints()) {
                                    classement[j - 1] = century.getTabJoueur()[k].getNom();
                                }
                                else classement[j - 1] = century.getTabJoueur()[k].getNom();
                                //partieFinie=true;
                            }
                        partieFinie=true;
                    }
                }
            }
        }
        if (partieFinie) fenetreFinJeu(classement);
    }

    public Century getCentury() { return century; }
}
