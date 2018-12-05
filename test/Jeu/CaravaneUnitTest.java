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

        ArrayList<Epice> epicesCaravane = caravane.getEpices();

        Assert.assertEquals(Epice.tumeric, epicesCaravane.get(0));
        Assert.assertEquals(Epice.safran, epicesCaravane.get(1));
        Assert.assertEquals(Epice.cannelle, epicesCaravane.get(2));
    }

    @Test
    public void testTriEpice(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.cannelle);
        caravane.addEpice(Epice.safran);

        ArrayList<Epice> epicesCaravane = caravane.getEpices();

        Assert.assertEquals(Epice.tumeric, epicesCaravane.get(0));
        Assert.assertEquals(Epice.safran, epicesCaravane.get(1));
        Assert.assertEquals(Epice.cannelle, epicesCaravane.get(2));
    }
}