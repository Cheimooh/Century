package Jeu;

import javafx.scene.image.Image;

import java.util.*;

public class Pioche {
    private static Queue<Carte> pioche;

    public Pioche(){
        pioche = new ArrayDeque<Carte>();
        creerCartesProduction();
        creerCartesEchange();
        creerCarteAmelioration();
        melangerPioche();
    }

    private void melangerPioche() {
        List<Carte> listeCartes = new ArrayList<Carte>(pioche);
        Collections.shuffle(listeCartes);
        pioche= new ArrayDeque<>(listeCartes);
    }

    private void creerCarteAmelioration(){
        pioche.add(new CarteAmelioration(2));
        pioche.add(new CarteAmelioration(3));
    }

    private void creerCartesProduction(){
        pioche.add(new CarteProduction(0,0,1,0, new Image("imgCartes/PG.png")));
        pioche.add(new CarteProduction(0,0,0,1, new Image("imgCartes/PB.png")));
        pioche.add(new CarteProduction(1,0,1,0, new Image("imgCartes/PYG.png")));
        pioche.add(new CarteProduction(0,2,0,0, new Image("imgCartes/PRR.png")));
        pioche.add(new CarteProduction(1,1,0,0, new Image("imgCartes/PYR.png")));
        pioche.add(new CarteProduction(2,0,0,0,  new Image("imgCartes/PYY.png")));
        pioche.add(new CarteProduction(2,1,0,0,  new Image("imgCartes/PYYR.png")));
        pioche.add(new CarteProduction(3,0,0,0,  new Image("imgCartes/PYYY.png")));
        pioche.add(new CarteProduction(4,0,0,0,  new Image("imgCartes/PYYYY.png")));
    }

    private void creerCartesEchange(){
        pioche.add(new CarteEchange(TypeCarteEchange.trade_R_YYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_G_RR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_G_RRY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_G_RYYYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_GYYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_GYYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_RRR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_RRYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_GRY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_B_GG));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RY_G));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YY_RR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YY_G));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RR_GG));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RR_GYYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RR_BYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GG_BRYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GG_RRRYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GG_BRR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GG_BB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_BB_GGGRY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_BB_GGRRR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GYY_BB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYY_RRR));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYY_RG));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYY_B));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RRR_BGY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RRR_GGG));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RRR_GGYY));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_RRR_BB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_GGG_BBB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYYY_GB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYYY_GG));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYYYY_BB));
        pioche.add(new CarteEchange(TypeCarteEchange.trade_YYYYY_GGG));
    }
}
