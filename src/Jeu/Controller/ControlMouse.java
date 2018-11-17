package Jeu.Controller;

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
                    if (fenetre.confirmation(i)) {
                        fenetre.getCentury().ajouterCarteALaMain(fenetre.getCartePresente().get(i));
                        fenetre.retirerCarte(i);
                        fenetre.getCentury().tourSuivant();
                        fenetre.tourSuivant();
                    }
                    return;
                }
            }
        }
    }
}