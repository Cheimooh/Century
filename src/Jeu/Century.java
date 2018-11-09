import java.util.Queue;

public class Century {
    private static Queue<Carte> pioche;
    
    public Century(){}
    
    public void creer_cartes_achat(){
        pioche.add(new Carte_Achat(0,0,1,0));
        pioche.add(new Carte_Achat(0,0,0,1));
        pioche.add(new Carte_Achat(1,0,1,0));
        pioche.add(new Carte_Achat(0,2,0,0));
        pioche.add(new Carte_Achat(1,1,0,0));
        pioche.add(new Carte_Achat(0,0,0,0));
        pioche.add(new Carte_Achat(2,1,0,0));
        pioche.add(new Carte_Achat(3,0,0,0));
        pioche.add(new Carte_Achat(4,0,0,0));



    }
}
