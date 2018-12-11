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
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_BBBBB_20.png", 0, 0, 0, 5, 20));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_BBBB_16.png", 0, 0, 0, 4, 16));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_GGBB_14.png", 0, 0, 2, 2, 14));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_GGBBB_18.png", 0, 0, 2, 3, 18));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_GGGBB_17.png", 0, 0, 3, 2, 17));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRBB_12.png", 0, 2, 0, 2, 12));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRBBB_16.png", 0, 2, 0, 3, 16));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRGB_12.png", 0, 2, 1, 1, 12));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRGG_10.png", 0, 2, 2, 0, 10));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRGGBB_19.png", 0, 2, 2, 2, 19));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRGGG_13.png", 0, 2, 3, 0, 13));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRRBB_14.png", 0, 3, 0, 2, 14));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRRGG_12.png", 0, 3, 2, 0, 12));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRRR_6.png", 0, 3, 0, 0, 6));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_RRRRR_10.png", 0, 4, 0, 0, 10));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YGGB_12.png", 1, 0, 2, 1, 12));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YRGB_12.png", 1, 1, 1, 1, 12));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YRGBBB_20.png", 1, 1, 1, 3, 20));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YRGGGB_18.png", 1, 1, 3, 1, 18));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YRRRGB_16.png", 1, 3, 1, 1, 16));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYBB_10.png", 2, 0, 0, 2, 10));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYBBB_14.png", 2, 0, 0, 3, 14));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYGG_8.png", 2, 0, 2, 0, 8));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYGGBB_17.png", 2, 0, 3, 2, 17));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYGGG_11.png", 2, 0, 3, 0, 11));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYRB_9.png", 2, 1, 0, 1, 9));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYRR_6.png", 2, 2, 0, 0, 6));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYRRBB_15.png", 2, 2, 0, 2, 15));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYRRGG_13.png", 2, 2, 2, 0, 13));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYRRR_8.png", 2, 3, 0, 0, 8));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYYGG_9.png", 3, 0, 2, 0, 9));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYYRGB_14.png", 3, 1, 1, 1, 14));
        piocheCommande.add(new CarteCommande("Jeu/imgCartesCommande/C_YYYRR_7.png", 3, 2, 0, 0, 7));
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
        piocheMarchand.add(new CarteProduction(0,0,1,0, "Jeu/imgCartes/PG.png"));
        piocheMarchand.add(new CarteProduction(0,0,0,1, "Jeu/imgCartes/PB.png"));
        piocheMarchand.add(new CarteProduction(1,0,1,0, "Jeu/imgCartes/PYG.png"));
        piocheMarchand.add(new CarteProduction(0,2,0,0, "Jeu/imgCartes/PRR.png"));
        piocheMarchand.add(new CarteProduction(2,0,0,0, "Jeu/imgCartes/PYY.png"));
        piocheMarchand.add(new CarteProduction(2,1,0,0, "Jeu/imgCartes/PRYY.png"));
        piocheMarchand.add(new CarteProduction(3,0,0,0, "Jeu/imgCartes/PYYY.png"));
        piocheMarchand.add(new CarteProduction(4,0,0,0, "Jeu/imgCartes/PYYYY.png"));
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

    public Queue<CarteCommande> getPiocheCommande() { return piocheCommande; }
}
