package Jeu;

import Jeu.Model.Carte;
import Jeu.Model.CarteCommande;
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

        c.getEpicesSurLaCarte1().add(Epice.tumeric);
        c.retirerCartePiocheMarchande(0);
        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte1());

        c.getEpicesSurLaCarte2().add(Epice.tumeric);
        c.retirerCartePiocheMarchande(1);
        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte2());

        c.getEpicesSurLaCarte3().add(Epice.tumeric);
        c.retirerCartePiocheMarchande(2);
        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte3());

        c.getEpicesSurLaCarte4().add(Epice.tumeric);
        c.retirerCartePiocheMarchande(3);
        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte4());

        c.getEpicesSurLaCarte5().add(Epice.tumeric);
        c.retirerCartePiocheMarchande(4);
        Assert.assertEquals(new ArrayList<>(),c.getEpicesSurLaCarte5());
    }

    @Test
    public void testRetirerCarteCommande(){
        Century c = new Century();

        Carte carte1 = c.getCartePresenteSurLaPiocheCommande().get(1);

        c.retirerCartePiocheCommande(1);

        Assert.assertNotSame(c.getCartePresenteSurLaPiocheMarchande().get(1),carte1);
    }

    @Test
    public void testTourSuivant(){
        Century c =new Century();
        c.initJoueur(new String[]{"",""});

        c.tourSuivant();
        Assert.assertEquals(1, c.getJoueurActuel());

        c.tourSuivant();
        Assert.assertEquals(0, c.getJoueurActuel());
    }

    @Test
    public void testDistributionInitialeDesEpices(){
        Century c =new Century();
        c.initJoueur(new String[]{"","","",""});

        ArrayList<Epice> epicesTheoriquesJoueur1 = new ArrayList<>();
        epicesTheoriquesJoueur1.add(Epice.tumeric);
        epicesTheoriquesJoueur1.add(Epice.tumeric);
        epicesTheoriquesJoueur1.add(Epice.tumeric);
        ArrayList<Epice> epicesTheoriquesJoueur2 = new ArrayList<>();
        epicesTheoriquesJoueur2.add(Epice.tumeric);
        epicesTheoriquesJoueur2.add(Epice.tumeric);
        epicesTheoriquesJoueur2.add(Epice.tumeric);
        epicesTheoriquesJoueur2.add(Epice.tumeric);
        ArrayList<Epice> epicesTheoriquesJoueur4 = new ArrayList<>();
        epicesTheoriquesJoueur4.add(Epice.tumeric);
        epicesTheoriquesJoueur4.add(Epice.tumeric);
        epicesTheoriquesJoueur4.add(Epice.tumeric);
        epicesTheoriquesJoueur4.add(Epice.safran);

        Assert.assertEquals(epicesTheoriquesJoueur1,c.getTabJoueur()[0].getCaravane().getEpices());
        Assert.assertEquals(epicesTheoriquesJoueur2,c.getTabJoueur()[1].getCaravane().getEpices());
        Assert.assertEquals(epicesTheoriquesJoueur4,c.getTabJoueur()[3].getCaravane().getEpices());
    }

    @Test
    public void testAjouterUneCarteAuJoueurActuel(){
        Century c = new Century();
        c.initJoueur(new String[]{"","","",""});

        c.tourSuivant();

        Carte carte = c.getCartePresenteSurLaPiocheMarchande().get(0);
        c.ajouterCarteALaMain(carte);

        //Le joueur possède déjà deux cartes dans sa main
        Assert.assertEquals(carte, c.getTabJoueur()[1].getListeCartes().get(2));
    }

    @Test
    public void testAjouterUneCarteCommandeAuJoueurActuel(){
        Century c = new Century();
        c.initJoueur(new String[]{"","","",""});

        c.tourSuivant();

        CarteCommande carte = c.getCartePresenteSurLaPiocheCommande().get(0);
        c.ajouterCarteCommandeALaMain(carte);

        Assert.assertEquals(carte, c.getTabJoueur()[1].getListeCartesCommande().get(0));
    }

    @Test
    public void testBonNombreDePiecesOrEtArgent(){
        Century c = new Century();
        c.initJoueur(new String[]{"","","",""});

        int nbPiecesOr = 2*c.getNbJoueur();
        int nbPiecesArgent = 2*c.getNbJoueur();

        Assert.assertEquals(nbPiecesOr, c.getNbPiecesOr());
        Assert.assertEquals(nbPiecesArgent, c.getNbPiecesArgent());
    }

    @Test
    public void testRetraitPiecesOrEtArgent(){
        Century c = new Century();
        c.initJoueur(new String[]{"","","",""});

        int nbPiecesOr = 2*c.getNbJoueur();
        int nbPiecesArgent = 2*c.getNbJoueur();

        c.retirerPieceArgent();
        c.retirerPieceOr();

        Assert.assertEquals(nbPiecesOr-1, c.getNbPiecesOr());
        Assert.assertEquals(nbPiecesArgent-1, c.getNbPiecesArgent());
    }
}
