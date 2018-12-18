package Jeu;

import Jeu.Model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JoueurJoueUneCarteUnitTest {

    // CARTE PRODUCTION

    @Test
    public void testJouerCarteAvecCarteProduction(){
        Joueur j = new Joueur("");
        j.jouerCarte(1);
        // Joue une carte production (la carte en position 1 étant une carte production)
        ArrayList<Epice> listeEpicesTheoriques = new ArrayList<>();
        listeEpicesTheoriques.add(Epice.tumeric);
        listeEpicesTheoriques.add(Epice.tumeric);
        // La carte production jouée va produire deux épices tuméric
        Assert.assertEquals(listeEpicesTheoriques, j.getCaravane().getEpices());
    }

    @Test
    public void testJouerCarteProductionAvecToutesLesEpices(){
        Joueur j =new Joueur("");
        CarteProduction cp = new CarteProduction(1,1,1,1,"");

        j.addCarte(cp);
        j.jouerCarte(j.getListeCartes().size()-1);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.tumeric);
        epicesTheoriques.add(Epice.safran);
        epicesTheoriques.add(Epice.cardamome);
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques,j.getCaravane().getEpices());
    }

    // CARTE ECHANGE

    @Test
    public void testJouerCarteAvecCarteEchange(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric,Epice.tumeric});
        j.addCarte(new CarteEchange(TypeCarteEchange.trade_YY_G));
        j.jouerCarte(2);
        // Joue une carte échange (la carte en position 2 étant une carte échange)
        ArrayList<Epice> listeEpicesTheoriques = new ArrayList<>();
        listeEpicesTheoriques.add(Epice.cardamome);
        // La carte échange jouée va échanger deux épices tuméric contre une épice cardamome
        Assert.assertEquals(listeEpicesTheoriques, j.getCaravane().getEpices());
    }

    @Test
    public void testEchangeAvecToutesLesEpices(){
        Joueur j = new Joueur("");
        CarteEchange ce = new CarteEchange(TypeCarteEchange.trade_Test);
        j.addEpices(new Epice[]{Epice.tumeric,Epice.cannelle,Epice.cardamome,Epice.safran});
        j.addEpices(new Epice[]{Epice.tumeric,Epice.cannelle,Epice.cardamome,Epice.safran});

        j.addCarte(ce);
        j.jouerCarte(j.getListeCartes().size()-1);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.tumeric);
        epicesTheoriques.add(Epice.safran);
        epicesTheoriques.add(Epice.cardamome);
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques, j.getCaravane().getEpices());
    }

    @Test
    public void testVerificationEchangePossible() {
        Joueur j = new Joueur("");
        CarteEchange ce = new CarteEchange(TypeCarteEchange.trade_Test);
        j.addEpices(new Epice[]{Epice.tumeric, Epice.cannelle, Epice.cardamome, Epice.safran});
        j.addEpices(new Epice[]{Epice.tumeric, Epice.cannelle, Epice.cardamome, Epice.safran});

        Assert.assertTrue(j.verifEchangePossible(ce));
    }

    @Test
    public void testVerificationEchangeImpossible() {
        Joueur j = new Joueur("");
        CarteEchange ce = new CarteEchange(TypeCarteEchange.trade_Test);
        j.addEpices(new Epice[]{Epice.tumeric, Epice.cannelle, Epice.cardamome, Epice.safran});

        Assert.assertFalse(j.verifEchangePossible(ce));
    }

    // CARTE AMELIORATION

    @Test
    public void testAmeliorationEpice(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric,Epice.safran,Epice.cardamome});
        Assert.assertTrue(j.amelioreEpice(Epice.tumeric));
        Assert.assertTrue(j.amelioreEpice(Epice.safran));
        Assert.assertTrue(j.amelioreEpice(Epice.cardamome));

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.safran);
        epicesTheoriques.add(Epice.cardamome);
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques, j.getCaravane().getEpices());
    }

    @Test
    public void testAmeliorationEpiceImpossible(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.cannelle});
        Assert.assertFalse(j.amelioreEpice(Epice.cannelle));

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques, j.getCaravane().getEpices());
    }

    @Test
    public void testAmeliorationEpiceAvecEpiceNonPresenteDansLaCaravane(){
        Joueur j = new Joueur("");
        Assert.assertFalse(j.amelioreEpice(Epice.safran));
    }
}
