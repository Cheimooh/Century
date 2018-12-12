package Jeu;

import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class CaravaneUnitTest {

    @Test
    public void testAddEpice(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.safran);
        caravane.addEpice(Epice.cannelle);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.tumeric);
        epicesTheoriques.add(Epice.safran);
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques, caravane.getEpices());
    }

    @Test
    public void testTriEpice(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.cannelle);
        caravane.addEpice(Epice.safran);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.tumeric);
        epicesTheoriques.add(Epice.safran);
        epicesTheoriques.add(Epice.cannelle);

        Assert.assertEquals(epicesTheoriques, caravane.getEpices());
    }

    @Test
    public void testRemoveEpice(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.safran);

        caravane.removeEpice(1);

        ArrayList<Epice> epicesTheoriques = new ArrayList<>();
        epicesTheoriques.add(Epice.tumeric);
        epicesTheoriques.add(Epice.safran);

        Assert.assertEquals(epicesTheoriques, caravane.getEpices());
    }
}