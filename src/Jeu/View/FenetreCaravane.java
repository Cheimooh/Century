package Jeu.View;

import Jeu.Model.Caravane;
import Jeu.Model.Epice;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

import java.util.Optional;

public class FenetreCaravane {
    Caravane caravane;

    public FenetreCaravane(Caravane caravane){
        this.caravane=caravane;
    }

    public void fenetreSuppressionEpice(){
        boolean epiceSupprimee = false;
        while (!epiceSupprimee) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supprimer une épice");
            alert.setHeaderText("Votre caravane contient trop d'épices\nVeuillez sélectionner une épice à supprimer");

            Canvas c = new Canvas(250, 100);
            GraphicsContext graphicsContext2 = c.getGraphicsContext2D();

            for (int k = 0; k < caravane.getEpices().size(); k++) {
                Color color;
                if (caravane.getEpices().get(k).equals(Epice.tumeric)) color = Epice.tumeric.getColor();
                else if (caravane.getEpices().get(k).equals(Epice.safran)) color = Epice.safran.getColor();
                else if (caravane.getEpices().get(k).equals(Epice.cardamome)) color = Epice.cardamome.getColor();
                else color = Epice.cannelle.getColor();
                graphicsContext2.setFill(color);
                graphicsContext2.fillRect(k * 22, 50, 20, 20);
            }

            ButtonType tumeric = new ButtonType("Tuméric");
            ButtonType safran = new ButtonType("Safran");
            ButtonType cardamome = new ButtonType("Cardamome");
            ButtonType cannelle = new ButtonType("Cannelle");

            alert.setGraphic(c);

            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(tumeric, safran, cardamome, cannelle);

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == tumeric) {
                if (caravane.getEpices().contains(Epice.tumeric)) {
                    caravane.getEpices().remove(Epice.tumeric);
                    epiceSupprimee=true;
                } else {
                    afficheErreur("Tuméric");
                }
            } else if (option.get() == safran) {
                if (caravane.getEpices().contains(Epice.safran)) {
                    caravane.getEpices().remove(Epice.safran);
                    epiceSupprimee=true;
                } else {
                    afficheErreur("Safran");
                }
            } else if (option.get() == cardamome) {
                if (caravane.getEpices().contains(Epice.cardamome)) {
                    caravane.getEpices().remove(Epice.cardamome);
                    epiceSupprimee=true;
                } else {
                    afficheErreur("Cardamome");
                }
            } else if (option.get() == cannelle) {
                if (caravane.getEpices().contains(Epice.cannelle)) {
                    caravane.getEpices().remove(Epice.cannelle);
                    epiceSupprimee=true;
                } else {
                    afficheErreur("Cannelle");
                }
            }
        }
    }

    private void afficheErreur(String typeEpice) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Suppression impossible");
        alert.setContentText("Vous ne possédez pas de "+typeEpice);
        alert.showAndWait();
    }

}
