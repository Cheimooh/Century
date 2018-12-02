package Jeu.Model;

import javafx.scene.image.Image;

public class CarteCommande extends Carte {
    private int nbTumeric;
    private int nbSafran;
    private int nbCardamome;
    private int nbCannelle;

    private int nbPoints;

    public CarteCommande(Image image, int nbTumeric, int nbSafran, int nbCardamome, int nbCannelle, int nbPoints){
        super(image);
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
}
