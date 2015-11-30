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
public class LotniskoCywilne extends Lotnisko implements TworzeniePojazdu {
    public LotniskoCywilne(String nazwa,int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(nazwa,dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
        this.setColor(Color.BROWN);
        this.rysuj(MainPanel.getGrupaMiejscZmianyKierunku());
        Swiat.getInstance().addLotniskoCywilne(this);
//        System.out.println("Utworzono Lotnisko Cywilne");
    }
    public LotniskoCywilne(){

    }

    @Override
    public void stworz() {

    }

//    @Override
//    public void rysuj(Group group) {
//        super.rysuj(group);
//        this.getImageNode().setStroke(Color.YELLOW);
//        this.getImageNode().setFill(Color.YELLOW);
////        group.getChildren().add(this.getImageNode());
//        this.getOutRing().setStroke(Color.YELLOW);
//        this.getOutRing().setFill(Color.TRANSPARENT);
//        group.getChildren().add(this.getImageNode());
//        group.getChildren().add(this.getOutRing());
////        this.getImageNode().
//    }
}
