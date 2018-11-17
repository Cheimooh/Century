package Jeu.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Joueur {
    String nom;
    ArrayList<Carte> listeCartes;
    Caravane caravane;

    public Joueur(String nom){
        this.nom=nom;
        this.listeCartes = new ArrayList<Carte>();
        this.caravane=new Caravane();
        listeCartes.add(new CarteAmelioration(2));
        listeCartes.add(new CarteProduction(2,0,0,0,new Image("Jeu/imgCartes/PYY.png")));
    }

    public void addEpices(Epice[] epices) {
        for (int i = 0; i < epices.length; i++) {
            caravane.addEpice(epices[i]);
        }
    }

    public void addCarte(Carte carte) {
        listeCartes.add(carte);
    }

    public String getNom() { return nom; }

    public ArrayList<Carte> getListeCartes() { return listeCartes; }

    public Caravane getCaravane(){return caravane;}
}