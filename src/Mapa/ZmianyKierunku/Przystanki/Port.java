package Mapa.ZmianyKierunku.Przystanki;

import Gui.MainPanel;
import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * Created by Lewin on 2015-10-18.
 */
public class Port extends Przystanek implements TworzeniePojazdu {
    public Port(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, nazwa);
        this.setColor(Color.BLUE);
        this.rysuj(MainPanel.getGrupaMiejscZmianyKierunku());
        Swiat.getInstance().addPort(this);
    }
    public Port(){

    }

    @Override

    public void stworz() {

    }

//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.BLUE);
//        this.getImageNode().setFill(Color.BLUE);
//        this.getOutRing().setStroke(Color.BLUE);
//        this.getOutRing().setFill(Color.TRANSPARENT);
//        this.getOutRing().setStrokeWidth(this.getPromien()/2);
//        group.getChildren().add(this.getImageNode());
//        group.getChildren().add(this.getOutRing());
////        this.getImageNode().
//    }
}
