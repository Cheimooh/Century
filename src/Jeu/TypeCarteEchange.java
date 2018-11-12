package Jeu;

import javafx.scene.image.Image;

public enum TypeCarteEchange {
    trade_R_YYY(new int[]{0,1,0,0},new int[]{3,0,0,0}, new Image("imgCartes/R_YYY.png")),
    trade_G_RR(new int[]{0,0,1,0},new int[]{0,2,0,0}, new Image("imgCartes/G_RR.png")),
    trade_G_RRY(new int[]{0,0,1,0},new int[]{1,2,0,0}, new Image("imgCartes/G_RRY.png")),
    trade_G_RYYYY(new int[]{0,0,1,0},new int[]{4,1,0,0}, new Image("imgCartes/G_RYYYY.png")),
    trade_B_GYYY(new int[]{0,0,0,1},new int[]{3,0,1,0}, new Image("imgCartes/B_GYYY.png")),
    trade_B_RRR(new int[]{0,0,0,1},new int[]{0,3,0,0}, new Image("imgCartes/B_RRR.png")),
    trade_B_RRYY(new int[]{0,0,0,1},new int[]{2,2,0,0}, new Image("imgCartes/B_RRYY.png")),
    trade_B_GRY(new int[]{0,0,0,1},new int[]{1,1,1,0}, new Image("imgCartes/B_GRY.png")),
    trade_B_GG(new int[]{0,0,0,1},new int[]{0,0,2,0}, new Image("imgCartes/B_GG.png")),

    trade_RY_G(new int[]{1,1,0,0},new int[]{0,0,1,0}, new Image("imgCartes/RY_G.png")),
    trade_YY_G(new int[]{2,0,0,0},new int[]{0,0,1,0}, new Image("imgCartes/YY_G.png")),
    trade_YY_RR(new int[]{2,0,0,0},new int[]{0,2,0,0}, new Image("imgCartes/YY_RR.png")),
    trade_RR_GG(new int[]{0,2,0,0},new int[]{0,0,2,0}, new Image("imgCartes/RR_GG.png")),
    trade_RR_GYYY(new int[]{0,2,0,0},new int[]{3,0,1,0}, new Image("imgCartes/RR_GYYY.png")),
    trade_RR_BYY(new int[]{0,2,0,0},new int[]{2,0,0,1}, new Image("imgCartes/RR_BYY.png")),
    trade_GG_BRYY(new int[]{0,0,2,0},new int[]{2,1,0,1}, new Image("imgCartes/GG_BRYY.png")),
    trade_GG_RRRYY(new int[]{0,0,2,0},new int[]{2,3,0,0}, new Image("imgCartes/GG_RRRYY.png")),
    trade_GG_BRR(new int[]{0,0,2,0},new int[]{0,2,0,1}, new Image("imgCartes/GG_BRR.png")),
    trade_GG_BB(new int[]{0,0,2,0},new int[]{0,0,0,2}, new Image("imgCartes/GG_BB.png")),
    trade_BB_GGGRY(new int[]{0,0,0,2},new int[]{1,1,3,0}, new Image("imgCartes/BB_GGGRY.png")),
    trade_BB_GGRRR(new int[]{0,0,0,2},new int[]{0,3,2,0}, new Image("imgCartes/BB_GGRRR.png")),

    trade_GYY_BB(new int[]{2,0,1,0},new int[]{0,0,0,2}, new Image("imgCartes/GYY_BB.png")),
    trade_YYY_RRR(new int[]{3,0,0,0},new int[]{0,3,0,0}, new Image("imgCartes/YYY_RRR.png")),
    trade_YYY_RG(new int[]{3,0,0,0},new int[]{0,1,1,0}, new Image("imgCartes/YYY_RG.png")),
    trade_YYY_B(new int[]{3,0,0,0},new int[]{0,0,0,1}, new Image("imgCartes/YYY_B.png")),
    trade_RRR_BGY(new int[]{0,3,0,0},new int[]{1,0,1,1}, new Image("imgCartes/RRR_BGY.png")),
    trade_RRR_GGG(new int[]{0,3,0,0},new int[]{0,0,3,0}, new Image("imgCartes/RRR_GGG.png")),
    trade_RRR_GGYY(new int[]{0,3,0,0},new int[]{2,0,2,0}, new Image("imgCartes/RRR_GGYY.png")),
    trade_RRR_BB(new int[]{0,3,0,0},new int[]{0,0,0,2}, new Image("imgCartes/RRR_BB.png")),
    trade_GGG_BBB(new int[]{0,0,3,0},new int[]{0,0,0,3}, new Image("imgCartes/GGG_BBB.png")),

    trade_YYYY_GB(new int[]{4,0,0,0},new int[]{0,0,1,1},new Image("imgCartes/YYYY_GB.png")),
    trade_YYYY_GG(new int[]{4,0,0,0},new int[]{0,0,2,0}, new Image("imgCartes/YYYY_GG.png")),

    trade_YYYYY_BB(new int[]{5,0,0,0},new int[]{0,0,0,2}, new Image("imgCartes/YYYYY_BB.png")),
    trade_YYYYY_GGG(new int[]{5,0,0,0},new int[]{0,0,3,0}, new Image("imgCartes/YYYYY_GGG.png"));

    private int[] epicesDonnees;
    private int[] epicesRecues;

    TypeCarteEchange(int[] epicesDonnees, int[] epicesRecues, Image image){
        this.epicesDonnees=epicesDonnees;
        this.epicesRecues=epicesRecues;
    }

    public int[] getEpicesDonnees() {
        return epicesDonnees;
    }

    public int[] getEpicesRecues() {
        return epicesRecues;
    }
}