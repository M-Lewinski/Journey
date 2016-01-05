package Drogi;

import Gui.MainPanel;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/** Klasa drogi morskiej, ktora implementuje obiekt droga morska.
 * Created by Lewin on 2015-10-18.
 */
public class DrogaMorska extends Droga {
    /**
     * Konstruktor klasy droga morska, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param poczatek poczatek drogi.
     * @param koniec koniec drogi.
     */
    public DrogaMorska(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec) {
        super(poczatek, koniec);
        this.setColor(Color.CYAN);
        this.rysuj(MainPanel.getGrupaDrog());
    }
    public DrogaMorska (){

    }

//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.CYAN);
//        group.getChildren().add(this.getImageNode());
//    }
}
