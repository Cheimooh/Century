package Jeu;

import Jeu.Model.*;
import Jeu.Model.Joueur;
import org.junit.Assert;
import org.junit.Test;

public class JoueurUnitTest {

    @Test
    public void testAddEpices(){
        Joueur j = new Joueur("");
        j.addEpices(new Epice[]{Epice.tumeric, Epice.safran});

        Assert.assertEquals(Epice.tumeric, j.getCaravane().getEpices().get(0));
        Assert.assertEquals(Epice.safran, j.getCaravane().getEpices().get(1));
    }

}
