package Jeu;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class CaravaneUnitTest {

    @Test
    public void testAddEpice(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric);
        caravane.addEpice(Epice.safran);
        caravane.addEpice(Epice.tumeric);

        ArrayList<Epice> epicesCaravane = caravane.getEpices();

        Assert.assertEquals(Epice.tumeric, epicesCaravane.get(0));
        Assert.assertEquals(Epice.safran, epicesCaravane.get(1));
        Assert.assertEquals(Epice.tumeric, epicesCaravane.get(2));
    }
}