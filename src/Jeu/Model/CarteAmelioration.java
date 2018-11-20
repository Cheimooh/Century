package Jeu.Model;

import javafx.scene.image.Image;

public class CarteAmelioration extends Carte {
    private int nbAmelioration; // Nombre d'amélioration que la carte permet de faire

    public CarteAmelioration(int nbAmelioration){
        super(new Image("Jeu/imgCartes/A"+nbAmelioration+".png"));
        this.nbAmelioration = nbAmelioration;
    }
}
