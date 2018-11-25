package Jeu.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Joueur {
    private String nom; // Nom du joueur
    private ArrayList<Carte> listeCartes; // Main du joueur (liste de cartes)
    private Caravane caravane; // Caravane du joueur qui contient les épices

    public Joueur(String nom){
        this.nom=nom;
        this.listeCartes = new ArrayList<Carte>();
        this.caravane=new Caravane();
        //AJOUT DES CARTES DE DEPART A LA MAIN DU JOUEUR
        listeCartes.add(new CarteAmelioration(2));
        listeCartes.add(new CarteProduction(2,0,0,0,new Image("Jeu/imgCartes/PYY.png")));
    }

    /*
     * Permet d'ajouter une ou plusieurs épices à la caravane
     */
    public void addEpices(Epice[] epices) {
        for (Epice epice : epices) {
            caravane.addEpice(epice);
        }
    }

    public void jouerCarte(int i) {
        Carte carteJouee = listeCartes.get(i);
        if (carteJouee instanceof CarteProduction){
            jouerCarteProduction((CarteProduction) carteJouee);
        }
    }

    private void jouerCarteProduction(CarteProduction carteJouee) {
        int[] epicesProduites = carteJouee.getTab();
        for (int i = 0; i <epicesProduites[0] ; i++) {
            caravane.addEpice(Epice.tumeric);
        }
        for (int i = 0; i < epicesProduites[1]; i++) {
            caravane.addEpice(Epice.safran);
        }
        for (int i = 0; i < epicesProduites[2]; i++) {
            caravane.addEpice(Epice.cardamome);
        }
        for (int i = 0; i < epicesProduites[3]; i++) {
            caravane.addEpice(Epice.cannelle);
        }
    }

    /*
     * Permet d'ajouter une carte à la main du joueur
     */
    public void addCarte(Carte carte) {
        listeCartes.add(carte);
    }

    public String getNom() { return nom; }

    public ArrayList<Carte> getListeCartes() { return listeCartes; }

    public Caravane getCaravane(){return caravane;}
}
