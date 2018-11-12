package Jeu;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Joueur {
    String nom;
    ArrayList<Carte> listeCartes;

    public Joueur(String nom){
        this.nom=nom;
        this.listeCartes = new ArrayList<Carte>();
        listeCartes.add(new CarteAmelioration(2));
        listeCartes.add(new CarteProduction(2,0,0,0,new Image("imgCartes/PYY.png")));
    }
}
