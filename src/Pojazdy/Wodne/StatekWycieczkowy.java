package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class StatekWycieczkowy extends Statek {
    private String firma;
//    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
//    private static Droga typDrogi;
    public String getFirma() {
        return firma;
    }
    public StatekWycieczkowy(double dlugosc, double szerokosc, double maksymalnaPredkosc, String firma) {
        super(dlugosc, szerokosc, maksymalnaPredkosc,null);
        this.firma = firma;
    }

//    @Override
//    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {
//
//    }
}
