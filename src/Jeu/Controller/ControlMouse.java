package Jeu.Controller;

import Jeu.Model.Joueur;
import Jeu.View.Fenetre;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlMouse implements EventHandler<MouseEvent> {
    private Fenetre fenetre;
    private int width;
    private int height;

    public ControlMouse(Fenetre fenetre, int width, int height){
        this.fenetre=fenetre;
        this.width=width;
        this.height=height;
    }

    @Override
    public void handle(MouseEvent event) {
        int hauteurCarte=500/3;
        int largeurImage = 111;
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (y>(height/3) && (y<height/3+hauteurCarte)){
            int emplacement;
            for (int i = 0; i < 5; i++) {
                emplacement = width- largeurImage *(i+1)-(30*(i+1));
                if(x>emplacement && x<emplacement+largeurImage){
                    //i est l'emplacement de la carte
                    if (fenetre.getCentury().getCartePresenteSurLaPiocheMarchande().size()>i) {
                        if (fenetre.confirmation(i)) {
                            fenetre.getCentury().ajouterCarteALaMain(fenetre.getCentury().getCartePresenteSurLaPiocheMarchande().get(i));
                            fenetre.retirerCarte(i);
                            fenetre.getCentury().tourSuivant();
                            fenetre.tourSuivant();
                        }
                    }
                    return;
                }
            }
        }
        if (y>(height*2/3) && (y<height) && (x>250)){
            int largeurImageMain = largeurImage/2;
            int hauteurImageMain = hauteurCarte/2;
            int y2 = (height/3)*2;
            Joueur j = fenetre.getCentury().getTabJoueur()[fenetre.getCentury().getJoueurActuel()];
            int emplacement;
            for (int i = 0; i < j.getListeCartes().size(); i++) {
                if(i<=(j.getListeCartes().size()/2)-1) {
                    emplacement = 250 + largeurImageMain * (i + 1) + (30 * (i));
                    if (x>emplacement && x<emplacement+largeurImageMain && y>y2+30 && y<y2+30+hauteurImageMain){
                        j.jouerCarte(i);
                        fenetre.getCentury().tourSuivant();
                        fenetre.tourSuivant();
                    }
                } else {
                    int i2 = i-j.getListeCartes().size()/2;
                    emplacement = 250 + largeurImageMain * (i2 + 1) + (30 * (i2));
                    if (x>emplacement && x<emplacement+largeurImageMain && y>y2+hauteurImageMain+50 && y<y+hauteurCarte*2+50){
                        j.jouerCarte(i);
                        fenetre.getCentury().tourSuivant();
                        fenetre.tourSuivant();
                    }
                }
            }
        }
    }
}
