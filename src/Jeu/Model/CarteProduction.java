package Jeu.Model;

import Jeu.Jeu;
import Jeu.Model.Carte;
import javafx.scene.image.Image;

public class CarteProduction extends Carte {
    int[] tab_achat;
    Image image;

    public CarteProduction(int nb_Jaune, int nb_Rouge, int nb_Vert, int nb_Brun, Image image){
        tab_achat = new int[4];
        tab_achat[0]=nb_Jaune;
        tab_achat[1]=nb_Rouge;
        tab_achat[2]=nb_Vert;
        tab_achat[3]=nb_Brun;
        this.image=image;
    }

    public int[] getTab(){
        return this.tab_achat;
    }
}
