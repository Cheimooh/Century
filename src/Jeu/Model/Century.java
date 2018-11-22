package Jeu.Model;

import Jeu.View.Fenetre;

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
    private Fenetre f; // Fenetre affichant le plateau

    public Century(){
        cartePresenteSurLaPiocheMarchande=new ArrayList<>();
        pioche = new Pioche();
        initPiocheMarchande();
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

    /*
     * Retire la carte marchande à l'index i (quand une carte est piochée)
     */
    public void retirerCartePiocheMarchande(int i) {
        cartePresenteSurLaPiocheMarchande.remove(i);
        if(getPioche().getPiocheMarchand().size()>0) {
            cartePresenteSurLaPiocheMarchande.add(getPioche().piocherCarteMarchand());
        } else {
            int emplacementARetirer = cartePresenteSurLaPiocheMarchande.size()-1;
            f.retirerEmplacementCarteMarchande(emplacementARetirer);
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
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(Arrays.asList(tabJoueur).subList(0, nbJoueur));
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

    public int getNbJoueur() { return nbJoueur; }

    public Joueur[] getTabJoueur() { return tabJoueur; }

    public Pioche getPioche() { return pioche; }

    public int getJoueurActuel() { return joueurActuel; }

    public ArrayList<Carte> getCartePresenteSurLaPiocheMarchande() {return cartePresenteSurLaPiocheMarchande;}

    public void setF(Fenetre f) { this.f = f; }
}
