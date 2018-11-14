package Jeu.Model;

import Jeu.Jeu;
import Jeu.Model.Carte;
import javafx.scene.image.Image;

public class CarteEchange extends Carte {
    private int[] tab_cartes_donnees;
    private int[] tab_cartes_recues;

    public CarteEchange (TypeCarteEchange t){
        super(t.getImage());
        this.tab_cartes_donnees = t.getEpicesDonnees();
        this.tab_cartes_recues = t.getEpicesRecues();
    }

}
