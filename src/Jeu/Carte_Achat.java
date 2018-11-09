package Jeu;

public class Carte_Achat extends Carte {
    int[] tab_achat;

    public Carte_Achat(int nb_Jaune, int nb_Rouge, int nb_Vert, int nb_Brun){
        tab_achat = new int[4];
        tab_achat[0]=nb_Jaune;
        tab_achat[1]=nb_Rouge;
        tab_achat[2]=nb_Vert;
        tab_achat[3]=nb_Brun;
    }

    public int[] getTab(){
        return this.tab_achat;
    }
}
