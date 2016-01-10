package Drogi;

import Gui.MainPanel;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;

/** Klasa drogi powietrznej, ktora implementuje obiekt droga powietrzna.
 * Created by Lewin on 2015-10-18.
 */
public class DrogaPowietrzna extends Droga implements Serializable {
    private static final long serialVersionUID = -3569023475866065706L;

    /**
     * Konstruktor klasy droga powietrzna, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param poczatek poczatek drogi.
     * @param koniec koniec drogi.
     */
    public DrogaPowietrzna(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec,boolean istniejeWSwiecie) {
        super(poczatek, koniec, istniejeWSwiecie);
//        if (istniejeWSwiecie == true) {
            this.setColor(Color.ORANGE);
            this.rysuj(MainPanel.getGrupaDrog());
//        }
    }
    public DrogaPowietrzna(){

    }
//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.ORANGE);
//        group.getChildren().add(this.getImageNode());
//    }
}
