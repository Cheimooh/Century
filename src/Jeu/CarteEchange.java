package Jeu;

public enum CarteEchange {
    trade_R_YYY(new int[]{0,1,0,0},new int[]{3,0,0,0}),
    trade_G_RR(new int[]{0,0,1,0},new int[]{0,2,0,0}),
    trade_G_RRY(new int[]{0,0,1,0},new int[]{1,2,0,0}),
    trade_G_RYYYY(new int[]{0,0,1,0},new int[]{4,1,0,0}),
    trade_B_GYYY(new int[]{0,0,0,1},new int[]{3,0,1,0}),
    trade_B_RRR(new int[]{0,0,0,1},new int[]{0,3,0,0}),
    trade_B_RRYY(new int[]{0,0,0,1},new int[]{2,2,0,0}),
    trade_B_GRY(new int[]{0,0,0,1},new int[]{1,1,1,0}),
    trade_B_GG(new int[]{0,0,0,1},new int[]{0,0,2,0}),

    trade_RY_G(new int[]{1,1,0,0},new int[]{0,0,1,0}),
    trade_YY_G(new int[]{2,0,0,0},new int[]{0,0,1,0}),
    trade_YY_RR(new int[]{2,0,0,0},new int[]{0,2,0,0}),
    trade_RR_GG(new int[]{0,2,0,0},new int[]{0,0,2,0}),
    trade_RR_GYYY(new int[]{0,2,0,0},new int[]{3,0,1,0}),
    trade_RR_BYY(new int[]{0,2,0,0},new int[]{2,0,0,1}),
    trade_GG_BRYY(new int[]{0,0,2,0},new int[]{2,1,0,1}),
    trade_GG_RRRYY(new int[]{0,0,2,0},new int[]{2,3,0,0}),
    trade_GG_BRR(new int[]{0,0,2,0},new int[]{0,2,0,1}),
    trade_GG_BB(new int[]{0,0,2,0},new int[]{0,0,0,2}),
    trade_BB_GGGRY(new int[]{0,0,0,2},new int[]{1,1,3,0}),
    trade_BB_GGRRR(new int[]{0,0,0,2},new int[]{0,3,2,0}),

    trade_GYY_BB(new int[]{2,0,1,0},new int[]{0,0,0,2}),
    trade_YYY_RRR(new int[]{3,0,0,0},new int[]{0,3,0,0}),
    trade_YYY_RG(new int[]{3,0,0,0},new int[]{0,1,1,0}),
    trade_YYY_B(new int[]{3,0,0,0},new int[]{0,0,0,1}),
    trade_RRR_BGY(new int[]{0,3,0,0},new int[]{1,0,1,1}),
    trade_RRR_GGG(new int[]{0,3,0,0},new int[]{0,0,3,0}),
    trade_RRR_GGYY(new int[]{0,3,0,0},new int[]{2,0,2,0}),
    trade_RRR_BB(new int[]{0,3,0,0},new int[]{0,0,0,2}),
    trade_GGG_BBB(new int[]{0,0,3,0},new int[]{0,0,0,3}),

    trade_YYYY_GB(new int[]{4,0,0,0},new int[]{0,0,1,1}),
    trade_YYYY_GG(new int[]{4,0,0,0},new int[]{0,0,2,0}),

    trade_YYYYY_BB(new int[]{5,0,0,0},new int[]{0,0,0,2}),
    trade_YYYYY_GGG(new int[]{5,0,0,0},new int[]{0,0,3,0});

    private int[] epicesDonnees;
    private int[] epicesRecues;

    CarteEchange(int[] epicesDonnees, int[] epicesRecues){
        this.epicesDonnees=epicesDonnees;
        this.epicesRecues=epicesRecues;
    }
}