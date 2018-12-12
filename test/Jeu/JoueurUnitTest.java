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
}
