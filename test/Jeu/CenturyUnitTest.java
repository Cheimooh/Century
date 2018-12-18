package Jeu;

import Jeu.Model.Carte;
import Jeu.Model.Century;
import Jeu.Model.Epice;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CenturyUnitTest {

    @Test
    public void testInitialisationCarteDansLaPiocheMarchande(){
        Century c = new Century();
        Assert.assertEquals(5,c.getCartePresenteSurLaPiocheMarchande().size());
    }

    @Test
    public void testInitialisationCarteDansLaPiocheCommande(){
        Century c = new Century();
        Assert.assertEquals(5,c.getCartePresenteSurLaPiocheCommande().size());
    }

    @Test
    public void testRetirerCarteMarchande(){
        Century c = new Century();

        Carte carte = c.getCartePresenteSurLaPiocheMarchande().get(1);

        c.retirerCartePiocheMarchande(1);

        Assert.assertNotSame(c.getCartePresenteSurLaPiocheMarchande().get(1),carte);

    }

    @Test
    public void testRetirerLesEpicesSurLaCartePrise(){
        Century c = new Century();
        c.initJoueur(new String[]{""});

        c.getEpicesSurLaCarte2().add(Epice.tumeric);

        c.retirerCartePiocheMarchande(1);

        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte2());
    }
}
