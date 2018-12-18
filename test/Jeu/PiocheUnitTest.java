package Jeu;

import Jeu.Model.Pioche;
import org.junit.Assert;
import org.junit.Test;

public class PiocheUnitTest {

    @Test
    public void testContientLeBonNombreDeCartesCommandesDansLaPioche(){
        Pioche p = new Pioche();

        Assert.assertEquals(33,p.getPiocheCommande().size());
    }

    @Test
    public void testContientLeBonNombreDeCartesMarchandesDansLaPioche(){
        Pioche p = new Pioche();

        Assert.assertEquals(45,p.getPiocheMarchand().size());
    }

    @Test
    public void testPiocherUneCarteCommande(){
        Pioche p = new Pioche();

        p.piocherCarteCommande();

        Assert.assertEquals(32,p.getPiocheCommande().size());
    }

    @Test
    public void testPiocherUneCarteMarchande(){
        Pioche p = new Pioche();

        p.piocherCarteMarchand();

        Assert.assertEquals(44,p.getPiocheMarchand().size());
    }
}
