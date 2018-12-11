package Jeu.Model;

public class CarteCommande extends Carte {
    private int nbTumeric;
    private int nbSafran;
    private int nbCardamome;
    private int nbCannelle;

    private int nbPoints;

    public CarteCommande(String path, int nbTumeric, int nbSafran, int nbCardamome, int nbCannelle, int nbPoints){
        super(path);
        this.nbTumeric=nbTumeric;
        this.nbSafran=nbSafran;
        this.nbCardamome=nbCardamome;
        this.nbCannelle=nbCannelle;
        this.nbPoints=nbPoints;
    }

    public int getNbTumeric() { return nbTumeric; }

    public int getNbSafran() { return nbSafran; }

    public int getNbCardamome() { return nbCardamome; }

    public int getNbCannelle() { return nbCannelle; }

    public int getNbPoints() { return nbPoints; }
}
