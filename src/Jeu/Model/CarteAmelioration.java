package Jeu.Model;

import Jeu.Jeu;
import Jeu.Model.Carte;
import javafx.scene.image.Image;

public class CarteAmelioration extends Carte {
    int nbAmelioration;

    public CarteAmelioration(int nbAmelioration){
        super(new Image("Jeu/imgCartes/A"+nbAmelioration+".png"));
        this.nbAmelioration = nbAmelioration;
    }
}
