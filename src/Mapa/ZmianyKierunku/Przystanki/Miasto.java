package Mapa.ZmianyKierunku.Przystanki;

import Gui.MainPanel;
import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * Created by Lewin on 2015-11-06.
 */
public class Miasto extends Lotnisko implements TworzeniePojazdu{
    public Miasto(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(nazwa, dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
        this.setColor(Color.PURPLE);
        this.rysuj(MainPanel.getGrupaMiejscZmianyKierunku());
        Swiat.getInstance().addMiasto(this);
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
