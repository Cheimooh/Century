package Jeu;

import java.util.Queue;

public class Century {
    private static Queue<Carte> piocheMarchand;
    int nbJoueurs;
    int nbPieceOr;
    int nbPieceArgent;
    
    public Century(){}
    
    public void creerCartesAchat(){
        piocheMarchand.add(new CarteProduction(0,0,1,0));
        piocheMarchand.add(new CarteProduction(0,0,0,1));
        piocheMarchand.add(new CarteProduction(1,0,1,0));
        piocheMarchand.add(new CarteProduction(0,2,0,0));
        piocheMarchand.add(new CarteProduction(1,1,0,0));
        piocheMarchand.add(new CarteProduction(0,0,0,0));
        piocheMarchand.add(new CarteProduction(2,1,0,0));
        piocheMarchand.add(new CarteProduction(3,0,0,0));
        piocheMarchand.add(new CarteProduction(4,0,0,0));
    }

    public void creerCartesEchange(){
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

    public void creerCarteAmelioration(){
    }

    public void creerPieces(){
        this.nbPieceOr = 2*nbJoueurs;
        this.nbPieceArgent = 2*nbJoueurs;
    }
}
