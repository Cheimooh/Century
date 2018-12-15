package Jeu.Controller;

import Jeu.Model.CarteAmelioration;
import Jeu.Model.CarteEchange;
import Jeu.Model.CarteProduction;
import Jeu.Model.Joueur;
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
        Joueur joueurFenetre = fenetreJoueur.getCentury().getTabJoueur()[fenetreJoueur.getIdJoueur()];
        if (x > 50 && x < 130 && y > 250 && y < 272) {
            if (fenetreJoueur.getIndexCartes()>0){
                fenetreJoueur.setIndexCartes(fenetreJoueur.getIndexCartes()-1);
                fenetreJoueur.afficherMainDuJoueur();
                return;
            }
        }
        if (x > 150 && x < 230 && y > 250 && y < 272) {
            if ((fenetreJoueur.getIndexCartes()+1)*6<joueurFenetre.getListeCartes().size()){
                fenetreJoueur.setIndexCartes(fenetreJoueur.getIndexCartes()+1);
                fenetreJoueur.afficherMainDuJoueur();
                return;
            }
        }
        if (fenetreJoueur.getIdJoueur()==fenetreJoueur.getCentury().getJoueurActuel()) {
            if (x > 600 && x < 700 && y > 145 && y < 167) {
                j.seReposer();
                fenetreJoueur.getCentury().tourSuivant();
                fenetreJoueur.getFenetrePrincipale().tourSuivant();
            } else {
                int largeurImageMain = largeurImage / 2;
                int hauteurImageMain = hauteurCarte / 2;
                int emplacement;
                int indexCartes = fenetreJoueur.getIndexCartes();
                for (int i = indexCartes*6; i < (indexCartes+1)*6; i++) {
                    if (j.getListeCartes().size()>i) {
                        if (i % 2 == 0) {
                            emplacement = largeurImageMain + ((i-indexCartes*6) + 1) + (30 * (i-indexCartes*6));
                            if (x > emplacement && x < emplacement + largeurImageMain && y > 30 && y < 30 + hauteurImageMain) {
                                joueCarte(j, i);
                            }
                        } else {
                            emplacement = largeurImageMain + (i-indexCartes*6) + (30 * ((i-1)-indexCartes*6));
                            if (x > emplacement && x < emplacement + largeurImageMain && y > hauteurImageMain + 50 && y < hauteurImageMain*2 + 50) {
                                joueCarte(j, i);
                            }
                        }
                    }
                }
            }
        } else {
            fenetreJoueur.afficheErreur("Vous ne pouvez pas jouer","Ce n'est pas votre tour !");
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
