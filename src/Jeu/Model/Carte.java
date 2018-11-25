package Jeu.Model;

import javafx.scene.image.Image;

public class Carte {
    private Image image; // Image de la carte

    public Carte(){}

    public Carte(Image image) {
        this.image=image;
    }

    public Image getImage() { return image; }
}
