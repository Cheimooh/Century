package Jeu.Controller;

import Jeu.Model.Caravane;
import Jeu.View.FenetreCaravane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlMouseCaravane implements EventHandler<MouseEvent> {
    private FenetreCaravane fenetreCaravane;

    public ControlMouseCaravane(FenetreCaravane fenetreCaravane) {
        this.fenetreCaravane=fenetreCaravane;
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Caravane caravane = fenetreCaravane.getCaravane();
        int emplacementX;
        for (int i = 0; i < caravane.getEpices().size() ; i++) {
            emplacementX = 22*i+10;
            if (x>emplacementX && x<emplacementX+20 && y>50 && y<70){
                caravane.removeEpice(i);
                fenetreCaravane.close();
            }
        }
    }
}
