package Jeu.Controller;

import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import Jeu.Model.Joueur;
import Jeu.View.FenetreAmelioration;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControlMouseAmelioration implements EventHandler<MouseEvent> {
    private FenetreAmelioration fenetreAmelioration;

    public ControlMouseAmelioration(FenetreAmelioration fenetreAmelioration) {
        this.fenetreAmelioration=fenetreAmelioration;
    }

    @Override
    public void handle(MouseEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Joueur j = fenetreAmelioration.getJ();
        Caravane caravane = j.getCaravane();
        int emplacementX;
        for (int i = 0; i < caravane.getEpices().size() ; i++) {
            emplacementX = 22*i+50;
            if (x>emplacementX && x<emplacementX+20 && y>40 && y<60){
                Epice epice = caravane.getEpices().get(i);
                if (j.amelioreEpice(epice)) fenetreAmelioration.close();
            }
        }
    }
}
