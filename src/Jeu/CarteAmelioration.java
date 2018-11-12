package Jeu;

import javafx.scene.image.Image;

public class CarteAmelioration extends Carte{
    int nbAmelioration;
    Image image;

    public CarteAmelioration(int nbAmelioration){
        this.nbAmelioration = nbAmelioration;
        String url = "imgCartes/A"+nbAmelioration+".png";
        image = new Image(url);
    }
}
