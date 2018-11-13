package Jeu.Model;

import Jeu.Jeu;
import Jeu.Model.Epice;

import java.util.ArrayList;

public class Caravane {
    ArrayList<Epice> listeEpices;

    public Caravane(){
        listeEpices = new ArrayList<>();
    }

    public void addEpice(Epice epice) {
        if (listeEpices.size()<10){
            listeEpices.add(epice);
        }
    }

    public ArrayList<Epice> getEpices() {
        return listeEpices;
    }
}
