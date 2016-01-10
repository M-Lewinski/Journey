package Mapa.ZmianyKierunku;

import Gui.MainPanel;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;


/**
 * Created by Lewin on 2015-10-18.
 */
public class Skrzyzowanie extends MiejsceZmianyKierunku implements Serializable {
    private static final long serialVersionUID = -6210785879157624427L;

    public Skrzyzowanie(String nazwa, double dlugosc, double szerokosc, double polozenieX, double polozenieY, boolean czyIstniejeWSwiecie) {
        super(dlugosc, szerokosc, polozenieX, polozenieY,nazwa,czyIstniejeWSwiecie);
        if(czyIstniejeWSwiecie==true) {
            this.setColor(Color.GREEN);
            //this.setPromien(10);
            this.rysuj(MainPanel.getGrupaMiejscZmianyKierunku());
        }
    }
//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.GREEN);
//        this.getImageNode().setFill(Color.GREEN);
//        group.getChildren().add(this.getImageNode());
////        this.getImageNode().
//    }
}
