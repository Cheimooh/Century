package Jeu.Model;

import javafx.scene.paint.Color;

public enum Epice {
    tumeric("Tumeric",Color.color(1.0,0.74,0.0)),
    safran("Safran", Color.color(0.71,0.14,0.05)),
    cardamome("Cardamome", Color.color(0.42,0.76,0.09)),
    cannelle("Cannelle", Color.color(0.18,0.08,0.09));

    private String nom;
    private Color color;

    Epice(String nom, Color color){
        this.nom=nom;
        this.color=color;
    }

    // AFFICHAGE GRAPHIQUE
    public Color getColor() { return color; }
}
