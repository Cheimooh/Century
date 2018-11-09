package Jeu;

import java.awt.*;

public enum Epice {
    tumeric("Tumeric", Color.yellow),
    safran("Safran", Color.red),
    cardamome("Cardamome", Color.green),
    cannelle("Cannelle", Color. getColor("#774428"));

    private String nom;
    private Color color;

    Epice(String nom, Color color){
        this.nom=nom;
        this.color=color;
    }
}
