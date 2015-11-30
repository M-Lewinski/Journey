package Mapa.ZmianyKierunku;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * Created by Lewin on 2015-10-18.
 */
public class Skrzyzowanie extends MiejsceZmianyKierunku {
    public Skrzyzowanie(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen,nazwa);
        this.setPromien(10);
    }
    @Override
    public void rysuj(Group group) {
        super.rysuj(group);
        this.getImageNode().setStroke(Color.GREEN);
        this.getImageNode().setFill(Color.GREEN);
        group.getChildren().add(this.getImageNode());
//        this.getImageNode().
    }
}
