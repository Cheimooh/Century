package Jeu.Model;

public class CarteEchange extends Carte {
    private int[] tabEpicesDonnees;
    private int[] tabEpicesRecues;

    public CarteEchange (TypeCarteEchange t){
        super(t.getPath());
        this.tabEpicesDonnees = t.getEpicesDonnees();
        this.tabEpicesRecues = t.getEpicesRecues();
    }

    public int[] getTabEpicesDonnees() { return tabEpicesDonnees; }

    public int[] getTabEpicesRecues() { return tabEpicesRecues; }
}
