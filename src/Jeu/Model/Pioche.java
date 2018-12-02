package Jeu.Model;

import javafx.scene.image.Image;

import java.util.*;

public class Pioche {
    private static Queue<Carte> piocheMarchand;
    private static Queue<CarteCommande> piocheCommande;

    public Pioche(){
        piocheMarchand = new ArrayDeque<>();
        piocheCommande = new ArrayDeque<>();
        creerCartesProduction();
        creerCartesEchange();
        creerCarteAmelioration();
        creerPiocheCommande();
        melangerPioche();
    }

    private void creerPiocheCommande() {
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/GGBB.png"), 0,0,2,2,14));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/GGGG.png"), 0,0,4,0,12));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/RRBB.png"), 0,2,0,2,12));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/RRGG.png"), 0,2,2,0,10));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/RRGGG.png"), 0,2,3,0,13));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/RRRGG.png"), 0,3,2,0,12));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/RRRRR.png"), 0,5,0,0,10));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/YYGG.png"), 2,0,2,0,8));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/YYRR.png"), 2,2,0,0,6));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/YYRRR.png"), 2,3,0,0,8));
        piocheCommande.add(new CarteCommande(new Image("Jeu/imgCartesCommande/YYYBB.png"), 3,0,0,2,11));
    }

    public Carte piocherCarteMarchand(){
        return piocheMarchand.poll();
    }

    public CarteCommande piocherCarteCommande(){
        return piocheCommande.poll();
    }

    private void melangerPioche() {
        List<Carte> listeCartes = new ArrayList<Carte>(piocheMarchand);
        Collections.shuffle(listeCartes);
        piocheMarchand = new ArrayDeque<>(listeCartes);

        List<CarteCommande> listeCartesCommande = new ArrayList<>(piocheCommande);
        Collections.shuffle(listeCartesCommande);
        piocheCommande = new ArrayDeque<>(listeCartesCommande);
    }

    private void creerCarteAmelioration(){
        piocheMarchand.add(new CarteAmelioration(2));
        piocheMarchand.add(new CarteAmelioration(3));
    }

    private void creerCartesProduction(){
        piocheMarchand.add(new CarteProduction(0,0,1,0, new Image("Jeu/imgCartes/PG.png")));
        piocheMarchand.add(new CarteProduction(0,0,0,1, new Image("Jeu/imgCartes/PB.png")));
        piocheMarchand.add(new CarteProduction(1,0,1,0, new Image("Jeu/imgCartes/PYG.png")));
        piocheMarchand.add(new CarteProduction(0,2,0,0, new Image("Jeu/imgCartes/PRR.png")));
        piocheMarchand.add(new CarteProduction(2,0,0,0,  new Image("Jeu/imgCartes/PYY.png")));
        piocheMarchand.add(new CarteProduction(2,1,0,0,  new Image("Jeu/imgCartes/PRYY.png")));
        piocheMarchand.add(new CarteProduction(3,0,0,0,  new Image("Jeu/imgCartes/PYYY.png")));
        piocheMarchand.add(new CarteProduction(4,0,0,0,  new Image("Jeu/imgCartes/PYYYY.png")));
    }

    private void creerCartesEchange(){
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_R_YYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_G_RR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_G_RRY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_G_RYYYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_GYYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_GYYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_RRR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_RRYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_GRY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_B_GG));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RY_G));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YY_RR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YY_G));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RR_GG));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RR_GYYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RR_BYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GG_BRYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GG_RRRYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GG_BRR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GG_BB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_BB_GGGRY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_BB_GGRRR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GYY_BB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYY_RRR));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYY_RG));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYY_B));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RRR_BGY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RRR_GGG));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RRR_GGYY));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_RRR_BB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_GGG_BBB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYYY_GB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYYY_GG));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYYYY_BB));
        piocheMarchand.add(new CarteEchange(TypeCarteEchange.trade_YYYYY_GGG));
    }

    public Queue<Carte> getPiocheMarchand() {return piocheMarchand;}
}
