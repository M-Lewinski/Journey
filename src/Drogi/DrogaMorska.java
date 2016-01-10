package Drogi;

import Gui.MainPanel;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.Serializable;

/** Klasa drogi morskiej, ktora implementuje obiekt droga morska.
 * Created by Lewin on 2015-10-18.
 */
public class DrogaMorska extends Droga implements Serializable{
    private static final long serialVersionUID = -7818479784395045799L;

    /**
     * Konstruktor klasy droga morska, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param poczatek - parametr odpowiedzialny za poczatek danej drogi.
     * @param koniec - parametr odpowiedzialny za koniec danej drogi.
     * @param istniejeWSwiecie - parametr umozliwia sprawdzenie czy dana droga ma byc wyswietlana oraz znajdywac sie w Swiecie.
     */
    public DrogaMorska(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec,boolean istniejeWSwiecie) {
        super(poczatek, koniec,istniejeWSwiecie);
            this.setColor(Color.CYAN);
            this.rysuj(MainPanel.getGrupaDrog());
    }

    /**
     * Pusty konstruktor klasy DrogaMorska.
     */
    public DrogaMorska (){

    }
}
