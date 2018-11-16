package Jeu.View;

import Jeu.Model.Carte;
import Jeu.Model.Century;
import Jeu.Controller.ControlMouse;
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
    private ArrayList<Carte> cartePresente;

    public Fenetre(Century century, int width, int height) {
        this.century=century;
        this.height=height;
        this.width=width;
        cartePresente=new ArrayList<>();
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
            Carte c = century.getPioche().getCarteMarchand();
            Image imageCarte = c.getImage();
            drawCartePiocheMarchande(imageCarte,i);
            cartePresente.add(c);
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
        graphicsContext.fillRect(10, hauteur+30, 20, 30);
        int nbCarte = century.getTabJoueur()[numJoueur].getListeCartes().size();
        graphicsContext.strokeText("x"+nbCarte, 35, hauteur+55);
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        graphicsContext.moveTo(x1,y1);
        graphicsContext.lineTo(x2,y2);
        graphicsContext.stroke();
    }

    public void retirerCarte(int i) {
        cartePresente.remove(i);
        cartePresente.add(century.getPioche().getCarteMarchand());
        for (int j = 0; j < 5; j++) {
            Image imageCarte = cartePresente.get(j).getImage();
            drawCartePiocheMarchande(imageCarte,j);
        }
    }

    public boolean confirmation(int i) {
        Carte c = cartePresente.get(i);

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

    public ArrayList<Carte> getCartePresente() { return cartePresente; }
}
