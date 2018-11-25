package Jeu.Model;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class Joueur {
    private String nom; // Nom du joueur
    private ArrayList<Carte> listeCartes; // Main du joueur (liste de cartes)
    private Caravane caravane; // Caravane du joueur qui contient les épices
    private ArrayList<CarteCommande> listeCartesCommande;
    private int nbPiecesOr;
    private int nbPiecesArgent;

    public Joueur(String nom){
        this.nom=nom;
        this.listeCartes = new ArrayList<>();
        this.listeCartesCommande = new ArrayList<>();
        this.caravane=new Caravane();
        nbPiecesArgent=0;
        nbPiecesOr=0;
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
        if (carteJouee instanceof CarteEchange){
            jouerCarteEchange((CarteEchange) carteJouee);
        }
    }

    private void jouerCarteEchange(CarteEchange carteJouee) {
        int[] epicesDonnees = carteJouee.getTabEpicesDonnees();
        int[] epicesRecues = carteJouee.getTabEpicesRecues();

        for (int i = 0; i < epicesDonnees[0] ; i++) {
            caravane.getEpices().remove(Epice.tumeric);
        }
        for (int i = 0; i < epicesDonnees[1] ; i++) {
            caravane.getEpices().remove(Epice.safran);
        }
        for (int i = 0; i < epicesDonnees[2] ; i++) {
            caravane.getEpices().remove(Epice.cardamome);
        }
        for (int i = 0; i < epicesDonnees[3] ; i++) {
            caravane.getEpices().remove(Epice.cannelle);
        }
        for (int i = 0; i < epicesRecues[0]; i++) {
            caravane.addEpice(Epice.tumeric);
        }
        for (int i = 0; i < epicesRecues[1]; i++) {
            caravane.addEpice(Epice.safran);
        }
        for (int i = 0; i < epicesRecues[2]; i++) {
            caravane.addEpice(Epice.cardamome);
        }
        for (int i = 0; i < epicesRecues[3]; i++) {
            caravane.addEpice(Epice.cannelle);
        }
    }

    public boolean verifEchangePossible(CarteEchange carteJouee) {
        int[] epicesDonnees = carteJouee.getTabEpicesDonnees();
        int compteurTumeric=0;
        int compteurSafran=0;
        int compteurCardamome=0;
        int compteurCannelle=0;
        for (int i = 0; i < caravane.getEpices().size() ; i++) {
            if(caravane.getEpices().get(i).equals(Epice.tumeric)) compteurTumeric++;
            if(caravane.getEpices().get(i).equals(Epice.safran)) compteurSafran++;
            if(caravane.getEpices().get(i).equals(Epice.cardamome)) compteurCardamome++;
            if(caravane.getEpices().get(i).equals(Epice.cannelle)) compteurCannelle++;
        }
        boolean retour=true;
        if (compteurTumeric<epicesDonnees[0]) retour=false;
        if (compteurSafran<epicesDonnees[1]) retour=false;
        if (compteurCardamome<epicesDonnees[2]) retour=false;
        if (compteurCannelle<epicesDonnees[3]) retour=false;
        return retour;
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

    public ArrayList<CarteCommande> getListeCartesCommande() { return listeCartesCommande; }

    public int getNbPiecesOr() { return nbPiecesOr; }

    public int getNbPiecesArgent() { return nbPiecesArgent; }
}
