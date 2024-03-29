package Mapa.ZmianyKierunku.Przystanki;

import Gui.MainPanel;
import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;


/**
 * Created by Lewin on 2015-11-06.
 */
public class Miasto extends Lotnisko implements TworzeniePojazdu,Serializable{
    private static final long serialVersionUID = -5756002411913524208L;

    public Miasto(String nazwa, double dlugosc, double szerokosc, double polozenieX, double polozenieY, boolean czyIstniejeWSwiecie) {
        super(nazwa, dlugosc, szerokosc, polozenieX, polozenieY, czyIstniejeWSwiecie);
        if (czyIstniejeWSwiecie) {
            this.setColor(Color.PURPLE);
            this.rysuj(MainPanel.getGrupaMiejscZmianyKierunku());
            Swiat.getInstance().addMiasto(this);
        }
    }
    public Miasto(){

    }
    @Override
    public void stworz() {

    }
//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.PURPLE);
//        this.getImageNode().setFill(Color.PURPLE);
//        this.getOutRing().setStroke(Color.PURPLE);
//        this.getOutRing().setFill(Color.TRANSPARENT);
//        this.getOutRing().setStrokeWidth(this.getPromien()/2);
//        group.getChildren().add(this.getImageNode());
//        group.getChildren().add(this.getOutRing());
////        this.getImageNode().
//    }
}
