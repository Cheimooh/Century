package Jeu.Model;

import Jeu.View.Fenetre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Century {
    private int nbJoueur;
    private Joueur[] tabJoueur;
    private Pioche pioche;
    private int joueurActuel;
    private ArrayList<Carte> cartePresenteSurLaPiocheMarchande;
    private Fenetre f;

    public Century(){
        cartePresenteSurLaPiocheMarchande=new ArrayList<>();
        pioche = new Pioche();
        initPiocheMarchande();
        // A MODIFIER
        nbJoueur = 4;
        String[] nomsJoueurs = new String[4];
        nomsJoueurs[0]="Maeva";
        nomsJoueurs[1]="Ayman";
        nomsJoueurs[2]="Matthieu";
        nomsJoueurs[3]="Theo";
        initJoueur(nomsJoueurs);
        joueurActuel=0;
    }

    private void initPiocheMarchande() {
        for (int i = 0; i < 5; i++) {
            Carte c = getPioche().piocherCarteMarchand();
            cartePresenteSurLaPiocheMarchande.add(c);
        }
    }

    public void retirerCartePiocheMarchande(int i) {
        cartePresenteSurLaPiocheMarchande.remove(i);
        if(getPioche().getPiocheMarchand().size()>0) {
            cartePresenteSurLaPiocheMarchande.add(getPioche().piocherCarteMarchand());
        } else {
            int emplacementARetirer = cartePresenteSurLaPiocheMarchande.size()-1;
            f.retirerEmplacementCarteMarchande(emplacementARetirer);
        }
    }

    public void tourSuivant(){
        joueurActuel++;
        if(joueurActuel>nbJoueur-1) joueurActuel=0;
    }

    private void initJoueur(String[] noms) {
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

    private void melangerTabJoueur() {
        ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>(Arrays.asList(tabJoueur).subList(0, nbJoueur));
        Collections.shuffle(listeJoueurs);
        for (int i = 0; i < nbJoueur; i++) {
            tabJoueur[i]=listeJoueurs.get(i);
        }
    }

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
