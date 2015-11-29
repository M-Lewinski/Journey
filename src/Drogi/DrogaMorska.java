package Drogi;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
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
    public DrogaMorska(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec,int poprawkaX,int poprawkaY) {
        super(poczatek, koniec,poprawkaX,poprawkaY);
    }
    public DrogaMorska (){

    }

    @Override
    public void rysuj(Pane panel) {
        super.rysuj(panel);
        this.getImageNode().setStroke(Color.CYAN);
        panel.getChildren().add(this.getImageNode());
    }
}
