package Jeu.Model;

import Jeu.Jeu;
import Jeu.Model.Carte;

public class CarteEchange extends Carte {
    int[] tab_cartes_donnees;
    int[] tab_cartes_recues;

    public CarteEchange (TypeCarteEchange t){
        this.tab_cartes_donnees = t.getEpicesDonnees();
        this.tab_cartes_recues = t.getEpicesRecues();
    }

}
