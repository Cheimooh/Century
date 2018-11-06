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

    @Test
    public void testTailleListeEpicesToujoursInferieureOuEgaleA10(){
        Caravane caravane = new Caravane();
        caravane.addEpice(Epice.tumeric); //1er ajout
        caravane.addEpice(Epice.tumeric); //2e ajout
        caravane.addEpice(Epice.tumeric); //3e ajout
        caravane.addEpice(Epice.tumeric); //4e ajout
        caravane.addEpice(Epice.tumeric); //5e ajout
        caravane.addEpice(Epice.tumeric); //6e ajout
        caravane.addEpice(Epice.tumeric); //7e ajout
        caravane.addEpice(Epice.tumeric); //8e ajout
        caravane.addEpice(Epice.tumeric); //9e ajout
        caravane.addEpice(Epice.tumeric); //10e ajout
        caravane.addEpice(Epice.tumeric); //11e ajout

        Assert.assertEquals(10, caravane.getEpices().size());
    }
}