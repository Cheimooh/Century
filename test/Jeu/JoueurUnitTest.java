package Jeu;

import Jeu.Model.*;
import Jeu.Model.Joueur;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JoueurUnitTest {

    @Test
    public void testAddEpices(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric, Epice.safran});

        Assert.assertEquals(Epice.tumeric, j.getCaravane().getEpices().get(0));
        Assert.assertEquals(Epice.safran, j.getCaravane().getEpices().get(1));
    }

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
    public void testVerificationCarteMarchandeNonPrenable(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric, Epice.tumeric, Epice.tumeric});

        Assert.assertFalse(j.verifCartePrenable(4));
    }

    @Test
    public void testVerificationCarteMarchandePrenable(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric, Epice.tumeric, Epice.tumeric});

        Assert.assertTrue(j.verifCartePrenable(3));
    }

    @Test
    public void testCarteDesactiveeApresUneUtilisation(){
        Joueur j = new Joueur("");
        j.jouerCarte(0);
        // La carte 0 sera jouée et la carte 1 ne sera pas jouée
        ArrayList<Integer> listeCartesActivesTheoriques = new ArrayList<>();
        listeCartesActivesTheoriques.add(0);
        listeCartesActivesTheoriques.add(1);

        Assert.assertEquals(listeCartesActivesTheoriques, j.getCartesActives());
    }

    @Test
    public void testVerifCarteCommandePrenable(){
        CarteCommande carteCommande = new CarteCommande("",1,1,1,1,10);
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric,Epice.safran,Epice.cardamome,Epice.cannelle});
        // Le joueur a suffisamment d'épices pour valider cette commande
        Assert.assertTrue(j.verifCarteCommandePrenable(carteCommande));
    }

    @Test
    public void testVerifCarteCommandeNonPrenable(){
        CarteCommande carteCommande = new CarteCommande("",1,1,1,1,10);
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric,Epice.safran});
        // Le joueur n'a pas suffisamment d'épices pour valider cette commande
        Assert.assertFalse(j.verifCarteCommandePrenable(carteCommande));
    }

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

    @Test
    public void testAddCarteCommande(){
        Joueur j = new Joueur("");
        CarteCommande cc = new CarteCommande("",1,1,1,1,5);

        j.addEpices(new Epice[]{Epice.tumeric,Epice.safran,Epice.cardamome,Epice.cannelle});
        j.addCarteCommande(cc);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();

        Assert.assertEquals(epicesTheoriques, j.getCaravane().getEpices());
        Assert.assertEquals(5,j.getNbPoints());
        Assert.assertEquals(cc, j.getListeCartesCommande().get(0));
    }

    @Test
    public void testTrierLesCartesEnFonctionDeLeurUtilisation(){
        Joueur j = new Joueur("");
        j.jouerCarte(0);

        ArrayList<Integer> listeCartesActivesTheoriques = new ArrayList<>();
        listeCartesActivesTheoriques.add(1);
        listeCartesActivesTheoriques.add(0);

        j.trierCarte();

        Assert.assertEquals(listeCartesActivesTheoriques,j.getCartesActives());
    }

    @Test
    public void testSeReposer(){
        Joueur j = new Joueur("");

        j.jouerCarte(0);

        ArrayList<Integer> listeCartesActivesTheoriques = new ArrayList<>();
        listeCartesActivesTheoriques.add(1);
        listeCartesActivesTheoriques.add(1);

        j.seReposer();

        Assert.assertEquals(listeCartesActivesTheoriques,j.getCartesActives());
    }

    @Test
    public void testAddPieceOr(){
        Joueur j = new Joueur("");
        j.addPieceOr();

        Assert.assertEquals(1,j.getNbPiecesOr());
    }

    @Test
    public void testAddPieceArgent(){
        Joueur j = new Joueur("");
        j.addPieceArgent();

        Assert.assertEquals(1,j.getNbPiecesArgent());
    }

    @Test
    public void testNombreDePoints(){
        Joueur j = new Joueur("");
        j.addCarteCommande(new CarteCommande("",0,0,0,0,10));
        j.addCarteCommande(new CarteCommande("",0,0,0,0,7));

        Assert.assertEquals(17,j.getNbPoints());
    }
}
