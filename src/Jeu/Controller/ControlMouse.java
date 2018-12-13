package Jeu.Controller;

import Jeu.Model.*;
import Jeu.View.FenetrePrincipale;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlMouse implements EventHandler<MouseEvent> {
    private FenetrePrincipale fenetrePrincipale;
    private int width;
    private int height;

    public ControlMouse(FenetrePrincipale fenetrePrincipale, int width, int height){
        this.fenetrePrincipale = fenetrePrincipale;
        this.width=width;
        this.height=height;
    }

    @Override
    public void handle(MouseEvent event) {
        int hauteurCarte=500/3;
        int largeurImage = 111;
        int x = (int) event.getX();
        int y = (int) event.getY();
        Joueur j = fenetrePrincipale.getCentury().getTabJoueur()[fenetrePrincipale.getCentury().getJoueurActuel()];
        if(y>100 && y<100+hauteurCarte){
            gestionCarteCommande(x,largeurImage,j);
        } else if (y>(height/3)+100 && (y<height/3+hauteurCarte+100)){
            gestionCarteMarchande(x,largeurImage,j);
        }
    }

    private void gestionCarteCommande(int x, int largeurImage, Joueur j) {
        int emplacement;
        for (int i = 0; i <5 ; i++) {
            emplacement = width- largeurImage *(i+1)-(30*(i+1));
            if (x>emplacement && x<emplacement+largeurImage){
                if (fenetrePrincipale.getCentury().getCartePresenteSurLaPiocheCommande().size()>i) {
                    if (j.verifCarteCommandePrenable(fenetrePrincipale.getCentury().getCartePresenteSurLaPiocheCommande().get(i))){
                        if (i==0){
                            j.addPieceOr();
                            fenetrePrincipale.getCentury().retirerPieceOr();
                        } else if (i==1){
                            j.addPieceArgent();
                            fenetrePrincipale.getCentury().retirerPieceArgent();
                        }
                        fenetrePrincipale.getCentury().ajouterCarteCommandeALaMain(fenetrePrincipale.getCentury().getCartePresenteSurLaPiocheCommande().get(i));
                        fenetrePrincipale.retirerCarteCommande(i);
                        fenetrePrincipale.getCentury().tourSuivant();
                        fenetrePrincipale.tourSuivant();
                        fenetrePrincipale.afficheFenetreFinJeu();
                    } else {
                        fenetrePrincipale.afficheErreur("Carte non prenable","Vous n'avez pas suffisamment d'épices pour récupérer cette carte !");
                    }
                }
            }
        }
    }

    private void gestionCarteMarchande(int x, int largeurImage, Joueur j){
        int emplacement;
        for (int i = 0; i < 5; i++) {
            emplacement = width- largeurImage *(i+1)-(30*(i+1));
            if(x>emplacement && x<emplacement+largeurImage){
                //i est l'emplacement de la carte
                if (fenetrePrincipale.getCentury().getCartePresenteSurLaPiocheMarchande().size()>i) {
                    if (fenetrePrincipale.confirmation(i)) {
                        if (j.verifCartePrenable(i)) {
                            addEpicesPiocheMarchande(j,i);
                            fenetrePrincipale.getCentury().ajouterCarteALaMain(fenetrePrincipale.getCentury().getCartePresenteSurLaPiocheMarchande().get(i));
                            fenetrePrincipale.retirerCarte(i);
                            fenetrePrincipale.getCentury().tourSuivant();
                            fenetrePrincipale.tourSuivant();
                        } else {
                            fenetrePrincipale.afficheErreur("Carte non prenable","Vous n'avez pas suffisamment d'épices pour récupérer cette carte !");
                        }
                    }
                }
            }
        }
    }

    private void addEpicesPiocheMarchande(Joueur j, int numCarte) {
        for (int i = 0; i < numCarte; i++) {
            Epice e = j.getCaravane().getEpices().remove(0);
            if (i==0) fenetrePrincipale.getCentury().getEpicesSurLaCarte1().add(e);
            if (i==1) fenetrePrincipale.getCentury().getEpicesSurLaCarte2().add(e);
            if (i==2) fenetrePrincipale.getCentury().getEpicesSurLaCarte3().add(e);
            if (i==3) fenetrePrincipale.getCentury().getEpicesSurLaCarte4().add(e);
            if (i==4) fenetrePrincipale.getCentury().getEpicesSurLaCarte5().add(e);
        }
    }
}
