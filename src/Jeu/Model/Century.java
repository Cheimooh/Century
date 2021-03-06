package Jeu.Model;

import Jeu.View.FenetrePrincipale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Century {
    private int nbJoueur; // Nombre de joueurs
    private Joueur[] tabJoueur; // Tableau contenant les joueurs
    private Pioche pioche; // Pioche contenant la pioche marchande et la pioche de commande
    private int joueurActuel; // Le numéro du joueur dont c'est le tour
    private ArrayList<Carte> cartePresenteSurLaPiocheMarchande; // La liste des 5 cartes présentent sur le plateau
                                                                // (allant de droite à gauche)
    private ArrayList<CarteCommande> cartePresenteSurLaPiocheCommande;

    private ArrayList<Epice> epicesSurLaCarte1;
    private ArrayList<Epice> epicesSurLaCarte2;
    private ArrayList<Epice> epicesSurLaCarte3;
    private ArrayList<Epice> epicesSurLaCarte4;
    private ArrayList<Epice> epicesSurLaCarte5;
    private FenetrePrincipale f; // FenetrePrincipale affichant le plateau

    private int nbPiecesOr;
    private int nbPiecesArgent;

    public Century(){
        cartePresenteSurLaPiocheMarchande=new ArrayList<>();
        cartePresenteSurLaPiocheCommande=new ArrayList<>();
        epicesSurLaCarte1=new ArrayList<>();
        epicesSurLaCarte2=new ArrayList<>();
        epicesSurLaCarte3=new ArrayList<>();
        epicesSurLaCarte4=new ArrayList<>();
        epicesSurLaCarte5=new ArrayList<>();
        pioche = new Pioche();
        initPiocheMarchande();
        initPiocheCommande();
        joueurActuel=0;
    }

    /*
     * Ajout des 5 cartes présentes sur le plateau dans la liste "cartePresenteSurLaPiocheMarchande"
     */
    private void initPiocheMarchande() {
        for (int i = 0; i < 5; i++) {
            Carte c = getPioche().piocherCarteMarchand();
            cartePresenteSurLaPiocheMarchande.add(c);
        }
    }

    private void initPiocheCommande() {
        for (int i = 0; i < 5; i++) {
            CarteCommande c = getPioche().piocherCarteCommande();
            cartePresenteSurLaPiocheCommande.add(c);
        }
    }

    /*
     * Retire la carte marchande à l'index i (quand une carte est piochée)
     */
    public void retirerCartePiocheMarchande(int i) {
        cartePresenteSurLaPiocheMarchande.remove(i);
        if(getPioche().getPiocheMarchand().size()>0) {
            cartePresenteSurLaPiocheMarchande.add(getPioche().piocherCarteMarchand());
        } else {
            // AFFICHAGE GRAPHIQUE
            int emplacementARetirer = cartePresenteSurLaPiocheMarchande.size()-1;
            f.retirerEmplacementCarteMarchande(emplacementARetirer);
        }

        if (i==0) {
            for (Epice anEpicesSurLaCarte1 : epicesSurLaCarte1) {
                tabJoueur[joueurActuel].getCaravane().addEpice(anEpicesSurLaCarte1);
            }
            epicesSurLaCarte1.clear();
        }
        if (i==1) {
            for (Epice anEpicesSurLaCarte2 : epicesSurLaCarte2) {
                tabJoueur[joueurActuel].getCaravane().addEpice(anEpicesSurLaCarte2);
            }
            epicesSurLaCarte2.clear();
        }
        if (i==2) {
            for (Epice anEpicesSurLaCarte3 : epicesSurLaCarte3) {
                tabJoueur[joueurActuel].getCaravane().addEpice(anEpicesSurLaCarte3);
            }
            epicesSurLaCarte3.clear();
        }
        if (i==3) {
            for (Epice anEpicesSurLaCarte4 : epicesSurLaCarte4) {
                tabJoueur[joueurActuel].getCaravane().addEpice(anEpicesSurLaCarte4);
            }
            epicesSurLaCarte4.clear();
        }
        if (i==4) {
            for (Epice anEpicesSurLaCarte5 : epicesSurLaCarte5) {
                tabJoueur[joueurActuel].getCaravane().addEpice(anEpicesSurLaCarte5);
            }
            epicesSurLaCarte5.clear();
        }
    }

    public void retirerCartePiocheCommande(int i) {
        cartePresenteSurLaPiocheCommande.remove(i);
        if(getPioche().getPiocheCommande().size()>0) {
            cartePresenteSurLaPiocheCommande.add(getPioche().piocherCarteCommande());
        } else {
            // AFFICHAGE GRAPHIQUE
            int emplacementARetirer = cartePresenteSurLaPiocheCommande.size()-1;
            f.retirerEmplacementCarteCommande(emplacementARetirer);
        }
    }

    /*
     * Incrémente la variable joueurActuel
     */
    public void tourSuivant(){
        joueurActuel++;
        if(joueurActuel>nbJoueur-1) joueurActuel=0;
    }

    /*
     * Initialise le tableau de joueur grâce à un tableau de noms
     * Distribue les épices aux joueurs
     */
    public void initJoueur(String[] noms) {
        nbJoueur = noms.length;
        nbPiecesOr=2*nbJoueur;
        nbPiecesArgent=2*nbJoueur;
        tabJoueur = new Joueur[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            tabJoueur[i] = new Joueur(noms[i]);
        }
        melangerTabJoueur();
        for (int i = 0; i < nbJoueur; i++) {
            if(i==0) tabJoueur[i].addEpices(new Epice[]{Epice.tumeric, Epice.tumeric, Epice.tumeric});
            if(i==1 || i==2) tabJoueur[i].addEpices(new Epice[]{Epice.tumeric, Epice.tumeric, Epice.tumeric, Epice.tumeric});
            if(i==3 || i==4) tabJoueur[i].addEpices(new Epice[]{Epice.tumeric, Epice.tumeric, Epice.tumeric, Epice.safran});
        }
    }

    /*
     * Mélange le tableau de joueur
     */
    private void melangerTabJoueur() {
        ArrayList<Joueur> listeJoueurs = new ArrayList<>(Arrays.asList(tabJoueur).subList(0, nbJoueur));
        Collections.shuffle(listeJoueurs);
        for (int i = 0; i < nbJoueur; i++) {
            tabJoueur[i]=listeJoueurs.get(i);
        }
    }

    /*
     * Ajoute une carte à la main du joueur qui est en train de jouer
     */
    public void ajouterCarteALaMain(Carte carte) {
        tabJoueur[joueurActuel].addCarte(carte);
    }

    public void ajouterCarteCommandeALaMain(CarteCommande carteCommande) {
        tabJoueur[joueurActuel].addCarteCommande(carteCommande);
    }

    public void retirerPieceOr() { if(nbPiecesOr>0) nbPiecesOr--; }

    public void retirerPieceArgent() { if (nbPiecesArgent>0) nbPiecesArgent--; }

    public int getNbJoueur() { return nbJoueur; }

    public Joueur[] getTabJoueur() { return tabJoueur; }

    private Pioche getPioche() { return pioche; }

    public int getJoueurActuel() { return joueurActuel; }

    public ArrayList<Carte> getCartePresenteSurLaPiocheMarchande() {return cartePresenteSurLaPiocheMarchande;}

    public ArrayList<CarteCommande> getCartePresenteSurLaPiocheCommande() { return cartePresenteSurLaPiocheCommande; }

    // AFFICHAGE GRAPHIQUE
    public void setF(FenetrePrincipale f) { this.f = f; }

    public ArrayList<Epice> getEpicesSurLaCarte1() { return epicesSurLaCarte1; }

    public ArrayList<Epice> getEpicesSurLaCarte2() { return epicesSurLaCarte2; }

    public ArrayList<Epice> getEpicesSurLaCarte3() { return epicesSurLaCarte3; }

    public ArrayList<Epice> getEpicesSurLaCarte4() { return epicesSurLaCarte4; }

    public ArrayList<Epice> getEpicesSurLaCarte5() { return epicesSurLaCarte5; }

    public int getNbPiecesOr() { return nbPiecesOr; }

    public int getNbPiecesArgent() { return nbPiecesArgent; }
}
