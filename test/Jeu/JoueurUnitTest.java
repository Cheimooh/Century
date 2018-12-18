package Jeu;

import Jeu.Model.*;
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
