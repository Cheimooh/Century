package Jeu.Model;

public class CarteAmelioration extends Carte {
    private int nbAmelioration; // Nombre d'amélioration que la carte permet de faire

    public CarteAmelioration(int nbAmelioration){
        super("Jeu/imgCartes/A"+nbAmelioration+".png");
        this.nbAmelioration = nbAmelioration;
    }

    public int getNbAmelioration() { return nbAmelioration;}
}
