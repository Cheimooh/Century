package Jeu.Model;

import Jeu.View.FenetreJoueur;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlMouseJoueur implements EventHandler<MouseEvent> {
    private FenetreJoueur fenetreJoueur;

    public ControlMouseJoueur(FenetreJoueur fenetreJoueur) {
        this.fenetreJoueur=fenetreJoueur;
    }

    @Override
    public void handle(MouseEvent event) {
        int hauteurCarte=500/3;
        int largeurImage = 111;
        int x = (int) event.getX();
        int y = (int) event.getY();
        Joueur j = fenetreJoueur.getCentury().getTabJoueur()[fenetreJoueur.getCentury().getJoueurActuel()];
        if (x>600 && x<700 && y>145 && y<167){
            j.seReposer();
            fenetreJoueur.getCentury().tourSuivant();
            fenetreJoueur.getFenetrePrincipale().tourSuivant();
        } else {
            int largeurImageMain = largeurImage / 2;
            int hauteurImageMain = hauteurCarte / 2;
            int emplacement;
            for (int i = 0; i < j.getListeCartes().size(); i++) {
                if (i <= (j.getListeCartes().size() / 2) - 1) {
                    emplacement = largeurImageMain * (i + 1) + (30 * (i));
                    if (x > emplacement && x < emplacement + largeurImageMain && y > 30 && y < 30 + hauteurImageMain) {
                        joueCarte(j, i);
                    }
                } else {
                    int i2 = i - j.getListeCartes().size() / 2;
                    emplacement = largeurImageMain * (i2 + 1) + (30 * (i2));
                    if (x > emplacement && x < emplacement + largeurImageMain && y > hauteurImageMain + 50 && y < y + hauteurCarte * 2 + 50) {
                        joueCarte(j, i);
                    }
                }
            }
        }
    }

    private void joueCarte(Joueur j, int i) {
        if (j.getCartesActives().get(i)==1) {
            if (j.getListeCartes().get(i) instanceof CarteEchange) {
                if (j.verifEchangePossible((CarteEchange) j.getListeCartes().get(i))) {
                    j.jouerCarte(i);
                    j.trierCarte();
                    fenetreJoueur.getCentury().tourSuivant();
                    fenetreJoueur.getFenetrePrincipale().tourSuivant();
                } else {
                    fenetreJoueur.afficheErreur("Echange impossible", "Vous n'avez pas suffisamment d'épices pour faire cet échange !");
                }
            } else if (j.getListeCartes().get(i) instanceof CarteAmelioration) {
                j.jouerCarte(i);
                fenetreJoueur.afficheDemandeAmelioration(((CarteAmelioration) j.getListeCartes().get(i)).getNbAmelioration());
                j.trierCarte();
                fenetreJoueur.getCentury().tourSuivant();
                fenetreJoueur.getFenetrePrincipale().tourSuivant();
            } else if (j.getListeCartes().get(i) instanceof CarteProduction) {
                j.jouerCarte(i);
                j.trierCarte();
                fenetreJoueur.getCentury().tourSuivant();
                fenetreJoueur.getFenetrePrincipale().tourSuivant();
            }
        } else fenetreJoueur.afficheErreur("Carte non jouable", "Cette carte a déjà été jouée !");
    }
}
