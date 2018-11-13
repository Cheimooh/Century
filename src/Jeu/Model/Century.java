package Jeu.Model;

import Jeu.Jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Century {
    int nbJoueur;
    Joueur[] tabJoueur;

    public Century(){
        Pioche p = new Pioche();
        // A MODIFIER
        nbJoueur = 4;
        String[] nomsJoueurs = new String[4];
        nomsJoueurs[0]="Maeva";
        nomsJoueurs[1]="Ayman";
        nomsJoueurs[2]="Matthieu";
        nomsJoueurs[3]="Theo";
        initJoueur(nomsJoueurs);
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
}
