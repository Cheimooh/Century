package Jeu.View;

import Jeu.Model.*;
import Jeu.Controller.ControlMouse;
import javafx.scene.Parent;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.util.*;

public class Fenetre extends Parent {
    private Century century;
    private int height;
    private int width;
    private GraphicsContext graphicsContext;

    private int largeurImage = 111;
    private int hauteurImage = 500 / 3;

    public Fenetre(Century century, int width, int height) {
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
            Image imageCarte = century.getCartePresenteSurLaPiocheMarchande().get(i).getImage();
            drawCartePiocheMarchande(imageCarte,i, epicesSurLaCarte);
        }
        afficherMainDuJoueur();
    }

    //CHANGEMENT DE L'AFFICHAGE A CHAQUE TOUR
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

    //AFFICHAGE DE LA MAIN DU JOUEUR (EN BAS)
    private void afficherMainDuJoueur() {
        int largeurImageMain = largeurImage/2;
        int hauteurImageMain = hauteurImage/2;
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
            if(i<=(j.getListeCartes().size()/2)-1) {
                emplacement = 250 + largeurImageMain * (i + 1) + (30 * (i));
                imageCarte = j.getListeCartes().get(i).getImage();
                graphicsContext.drawImage(imageCarte, emplacement, y + 30, largeurImageMain, hauteurImageMain);
                if (j.getCartesActives().get(i)==0){
                    graphicsContext.strokeText("Inutilisable",emplacement,y+80);
                }
            } else {
                int i2 = i-j.getListeCartes().size()/2;
                emplacement = 250 + largeurImageMain * (i2 + 1) + (30 * (i2));
                imageCarte = j.getListeCartes().get(i).getImage();
                graphicsContext.drawImage(imageCarte, emplacement, y + hauteurImageMain+50, largeurImageMain, hauteurImageMain);
                if (j.getCartesActives().get(i)==0) {
                    graphicsContext.strokeText("Inutilisable", emplacement, y + hauteurImageMain + 100);
                }
            }
        }
        ArrayList<Epice> listeEpices = j.getCaravane().getEpices();
        int debutCaravaneX=800;
        graphicsContext.setFill(Color.color(0.65,0.62,0.94));
        graphicsContext.fillRect(debutCaravaneX, y+30, 180,70);
        for (int i = 0; i < listeEpices.size() ; i++) {
            int emplacementX;
            int emplacementY;
            if(i<=(listeEpices.size()/2)-1) {
                emplacementX= debutCaravaneX + i*30+10;
                emplacementY= y+40;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            } else {
                int i2 = i-listeEpices.size()/2;
                emplacementX= debutCaravaneX + i2*30+10;
                emplacementY= y+70;
                drawEpicesMain(listeEpices, i, emplacementX, emplacementY);
            }
        }
        graphicsContext.setFill(Color.color(0.4,0.4,0.4));
        graphicsContext.fillRect(debutCaravaneX, y+145, 100, 22);
        graphicsContext.strokeText("Se resposer", debutCaravaneX+10,y+160);
    }

    //AFFICHAGE DES EPICES DANS LA MAIN
    private void drawEpicesMain(ArrayList<Epice> listeEpices, int i, int emplacementX, int emplacementY) {
        if (listeEpices.get(i).equals(Epice.tumeric)) {
            drawEpices(Epice.tumeric.getColor(),0,emplacementX, emplacementY,true);
        } else if (listeEpices.get(i).equals(Epice.safran)) {
            drawEpices(Epice.safran.getColor(),0,emplacementX, emplacementY,true);
        }else if (listeEpices.get(i).equals(Epice.cardamome)) {
            drawEpices(Epice.cardamome.getColor(),0,emplacementX, emplacementY,true);
        }else if (listeEpices.get(i).equals(Epice.cannelle)) {
            drawEpices(Epice.cannelle.getColor(),0,emplacementX, emplacementY,true);
        }
    }

    //AFFICHAGE DE LA PIOCHE MARCHANDE
    private void drawCartePiocheMarchande(Image imageCarte,int i, ArrayList<Epice> epices) {
        int emplacement = width- largeurImage *(i+1)-(30*(i+1));
        graphicsContext.drawImage(imageCarte,emplacement,(height/3.), largeurImage, hauteurImage);
        for (int j = 0; j < epices.size(); j++) {
            drawEpices(epices.get(j).getColor(), 0,emplacement+(j*25), (height/3)+hauteurImage+10, true);
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
        graphicsContext.fillRect(width- largeurImage *(i+1)-(30*(i+1)),(height/3.+hauteurImage), largeurImage, 50);
        for (int j = 0; j < 5; j++) {
            ArrayList<Epice> epicesSurLaCarte = new ArrayList<>();
            if (j==0) epicesSurLaCarte=century.getEpicesSurLaCarte1();
            if (j==1) epicesSurLaCarte=century.getEpicesSurLaCarte2();
            if (j==2) epicesSurLaCarte=century.getEpicesSurLaCarte3();
            if (j==3) epicesSurLaCarte=century.getEpicesSurLaCarte4();
            if (j==4) epicesSurLaCarte=century.getEpicesSurLaCarte5();
            if (century.getCartePresenteSurLaPiocheMarchande().size()>j) {
                Image imageCarte = century.getCartePresenteSurLaPiocheMarchande().get(j).getImage();
                drawCartePiocheMarchande(imageCarte, j, epicesSurLaCarte);
            }
        }
    }

    //MESSAGE DE CONFIRMATION LORSQUE L'ON SOUHAITE PRENDRE UNE CARTE
    public boolean confirmation(int i) {
        Carte c = century.getCartePresenteSurLaPiocheMarchande().get(i);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");

        String s = "Voulez-vous prendre cette carte ?";
        ImageView img = new ImageView(c.getImage());
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

    public void afficheErreur(String titre, String erreur) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setContentText(erreur);
        alert.showAndWait();
    }

    public void afficheDemandeAmelioration(int nbAmelioration) {
        for (int i = 0; i <nbAmelioration ; i++) {
            Joueur j = century.getTabJoueur()[century.getJoueurActuel()];
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Améliorer une épice");
            alert.setHeaderText("Veuillez choisir une épice à améliorer");

            Canvas c = new Canvas(250, 100);
            GraphicsContext graphicsContext2 = c.getGraphicsContext2D();

            for (int k = 0; k < j.getCaravane().getEpices().size(); k++) {
                Color color;
                if (j.getCaravane().getEpices().get(k).equals(Epice.tumeric))color = Epice.tumeric.getColor();
                else if (j.getCaravane().getEpices().get(k).equals(Epice.safran))color = Epice.safran.getColor();
                else if (j.getCaravane().getEpices().get(k).equals(Epice.cardamome))color = Epice.cardamome.getColor();
                else color=Epice.cannelle.getColor();
                graphicsContext2.setFill(color);
                graphicsContext2.fillRect(k*22,50,20,20);
            }

            ButtonType tumeric = new ButtonType("Tuméric");
            ButtonType safran = new ButtonType("Safran");
            ButtonType cardamome = new ButtonType("Cardamome");

            alert.setGraphic(c);

            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(tumeric, safran, cardamome);

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == tumeric) {
                if (j.getCaravane().getEpices().contains(Epice.tumeric)){
                    j.getCaravane().getEpices().remove(Epice.tumeric);
                    j.getCaravane().addEpice(Epice.safran);
                } else {
                    i--;
                    afficheErreur("Amélioration impossible", "Vous ne posséder de Tuméric");
                }
            } else if (option.get() == safran) {
                if (j.getCaravane().getEpices().contains(Epice.safran)){
                    j.getCaravane().getEpices().remove(Epice.safran);
                    j.getCaravane().addEpice(Epice.cardamome);
                } else {
                    i--;
                    afficheErreur("Amélioration impossible", "Vous ne posséder de Safran");
                }
            } else if (option.get() == cardamome) {
                if (j.getCaravane().getEpices().contains(Epice.cardamome)){
                    j.getCaravane().getEpices().remove(Epice.cardamome);
                    j.getCaravane().addEpice(Epice.cannelle);
                } else {
                    i--;
                    afficheErreur("Amélioration impossible", "Vous ne posséder de Cardamome");
                }
            }
        }
    }

    public Century getCentury() { return century; }
}
