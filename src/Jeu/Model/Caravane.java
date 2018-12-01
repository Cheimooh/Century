package Jeu.Model;

import Jeu.View.FenetreCaravane;

import java.util.ArrayList;

public class Caravane {
    private ArrayList<Epice> listeEpices; // Liste contenant les épices du joueur
    FenetreCaravane fenetreCaravane;

    public Caravane(){
        listeEpices = new ArrayList<>();
        fenetreCaravane = new FenetreCaravane(this);
    }

    /*
     * Permet d'ajouter une épice à la caravane
     * La caravane doit contenir au maximum 10 épices
     */
    public void addEpice(Epice epice) {
        if (listeEpices.size()<10){
            listeEpices.add(epice);
        } else {
            fenetreCaravane.fenetreSuppressionEpice();
            listeEpices.add(epice);
        }
        triEpices();
    }

    private void triEpices() {
        ArrayList<Epice> newListeEpices=new ArrayList<>();
        for (Epice listeEpice : listeEpices) {
            if (listeEpice.equals(Epice.tumeric)) newListeEpices.add(listeEpice);
        }
        for (Epice listeEpice : listeEpices) {
            if (listeEpice.equals(Epice.safran)) newListeEpices.add(listeEpice);
        }
        for (Epice listeEpice : listeEpices) {
            if (listeEpice.equals(Epice.cardamome)) newListeEpices.add(listeEpice);
        }
        for (Epice listeEpice : listeEpices) {
            if (listeEpice.equals(Epice.cannelle)) newListeEpices.add(listeEpice);
        }
        listeEpices=newListeEpices;
    }

    public ArrayList<Epice> getEpices() {
        return listeEpices;
    }
}
