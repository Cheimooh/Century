package Jeu;

import java.awt.*;

public enum Epices {
    tumeric("Tumeric", Color.yellow),
    safran("Safran", Color.red),
    cardamome("Cardamome", Color.green),
    cannelle("Cannelle", Color.gray);

    private String nom;
    private Color color;

    Epices(String nom, Color color){
        this.nom=nom;
        this.color=color;
    }
}
