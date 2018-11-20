package Jeu.Model;

import java.util.ArrayList;

public class Caravane {
    private ArrayList<Epice> listeEpices; // Liste contenant les épices du joueur

    public Caravane(){
        listeEpices = new ArrayList<>();
    }

    /*
     * Permet d'ajouter une épice à la caravane
     * La caravane doit contenir au maximum 10 épices
     */
    public void addEpice(Epice epice) {
        if (listeEpices.size()<10){
            listeEpices.add(epice);
        }
    }

    public ArrayList<Epice> getEpices() {
        return listeEpices;
    }
}
