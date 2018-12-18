package Jeu.Model;

public class Carte {
    private String path; // Image de la carte

    public Carte(String path) {
        this.path=path;
    }

    // AFFICHAGE GRAPHIQUE
    public String getPath() { return path; }
}
