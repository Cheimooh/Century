package Jeu.Model;

import java.util.ArrayList;

public class Joueur {
    private String nom; // Nom du joueur
    private ArrayList<Carte> listeCartes; // Main du joueur (liste de cartes)
    private Caravane caravane; // Caravane du joueur qui contient les épices
    private ArrayList<CarteCommande> listeCartesCommande;
    private ArrayList<Integer> cartesActives;
    private int nbPiecesOr;
    private int nbPiecesArgent;
    private int nbPoints;

    public Joueur(String nom){
        this.nom=nom;
        this.listeCartes = new ArrayList<>();
        this.listeCartesCommande = new ArrayList<>();
        this.cartesActives = new ArrayList<>();
        this.caravane=new Caravane();
        nbPiecesArgent=0;
        nbPiecesOr=0;
        nbPoints=0;
        //AJOUT DES CARTES DE DEPART A LA MAIN DU JOUEUR
        listeCartes.add(new CarteAmelioration(2));
        cartesActives.add(1);
        listeCartes.add(new CarteProduction(2,0,0,0,"Jeu/imgCartes/PYY.png"));
        cartesActives.add(1);
    }

    /*
     * Permet d'ajouter une ou plusieurs épices à la caravane
     */
    public void addEpices(Epice[] epices) {
        for (Epice epice : epices) {
            caravane.addEpice(epice);
        }
    }

    public boolean amelioreEpice(Epice epice) {
        if (epice==Epice.tumeric){
            caravane.getEpices().remove(Epice.tumeric);
            caravane.addEpice(Epice.safran);
            return true;
        } else if (epice==Epice.safran){
            caravane.getEpices().remove(Epice.safran);
            caravane.addEpice(Epice.cardamome);
            return true;
        } else if (epice==Epice.cardamome){
            caravane.getEpices().remove(Epice.cardamome);
            caravane.addEpice(Epice.cannelle);
            return true;
        } else return false;
    }

    public void jouerCarte(int i) {
        Carte carteJouee = listeCartes.get(i);
        if (carteJouee instanceof CarteProduction){
            jouerCarteProduction((CarteProduction) carteJouee);
            cartesActives.set(i,0);
        }
        if (carteJouee instanceof CarteEchange){
            jouerCarteEchange((CarteEchange) carteJouee);
            cartesActives.set(i,0);
        }
        if (carteJouee instanceof CarteAmelioration){
            cartesActives.set(i,0);
        }
    }

    public boolean verifCartePrenable(int i) { return caravane.getEpices().size() >= i; }

    public boolean verifCarteCommandePrenable(CarteCommande carteCommande) {
        int nbTumeric=caravane.getNbTumeric();
        int nbSafran=caravane.getNbSafran();
        int nbCardamome=caravane.getNbCardamome();
        int nbCannelle=caravane.getNbCannelle();

        if (carteCommande.getNbTumeric()>nbTumeric) return false;
        if (carteCommande.getNbSafran()>nbSafran) return false;
        if (carteCommande.getNbCardamome()>nbCardamome) return false;
        return carteCommande.getNbCannelle() <= nbCannelle;
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
        int nbTumeric=caravane.getNbTumeric();
        int nbSafran=caravane.getNbSafran();
        int nbCardamome=caravane.getNbCardamome();
        int nbCannelle=caravane.getNbCannelle();

        boolean retour=true;
        if (nbTumeric<epicesDonnees[0]) retour=false;
        if (nbSafran<epicesDonnees[1]) retour=false;
        if (nbCardamome<epicesDonnees[2]) retour=false;
        if (nbCannelle<epicesDonnees[3]) retour=false;
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
        cartesActives.add(1);
    }

    public void trierCarte(){
        ArrayList<Carte> newListeCartes=new ArrayList<>();
        ArrayList<Integer> newCartesActives=new ArrayList<>();
        for (int i = 0; i < listeCartes.size(); i++) {
            if (cartesActives.get(i)==1){
                newListeCartes.add(listeCartes.get(i));
                newCartesActives.add(1);
            }
        }

        for (int i = 0; i < listeCartes.size(); i++) {
            if (cartesActives.get(i)==0){
                newListeCartes.add(listeCartes.get(i));
                newCartesActives.add(0);
            }
        }
        listeCartes=newListeCartes;
        cartesActives=newCartesActives;
    }

    public void addCarteCommande(CarteCommande carteCommande) {
        listeCartesCommande.add(carteCommande);
        int nbTumeric=carteCommande.getNbTumeric();
        int nbSafran=carteCommande.getNbSafran();
        int nbCardamome=carteCommande.getNbCardamome();
        int nbCannelle=carteCommande.getNbCannelle();
        for (int i = 0; i < nbTumeric; i++) { caravane.getEpices().remove(Epice.tumeric); }
        for (int i = 0; i < nbSafran; i++) { caravane.getEpices().remove(Epice.safran); }
        for (int i = 0; i < nbCardamome; i++) { caravane.getEpices().remove(Epice.cardamome); }
        for (int i = 0; i < nbCannelle; i++) { caravane.getEpices().remove(Epice.cannelle); }
        nbPoints+=carteCommande.getNbPoints();
    }

    public void seReposer() {
        for (int i = 0; i < cartesActives.size() ; i++) {
            cartesActives.set(i,1);
        }
    }

    public void addPieceOr() {
        nbPiecesOr++;
        nbPoints+=3;
    }

    public void addPieceArgent() {
        nbPiecesArgent++;
        nbPoints++;
    }

    public String getNom() { return nom; }

    public ArrayList<Carte> getListeCartes() { return listeCartes; }

    public Caravane getCaravane(){return caravane;}

    public ArrayList<CarteCommande> getListeCartesCommande() { return listeCartesCommande; }

    public int getNbPiecesOr() { return nbPiecesOr; }

    public int getNbPiecesArgent() { return nbPiecesArgent; }

    public ArrayList<Integer> getCartesActives() { return cartesActives; }

    public int getNbPoints() { return nbPoints; }
}
