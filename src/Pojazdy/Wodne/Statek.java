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
import Pojazdy.Pojazd;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Statek extends Pojazd {
//    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
//    private static Droga typDrogi;
    /**
     * Konstruktor klasy statek, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     */
    public Statek(double dlugosc, double szerokosc) {
        super(dlugosc, szerokosc);
    }

//    @Override
//    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
//        if(Statek.listaGdzieMozeLadowac.isEmpty()){
//            Statek.listaGdzieMozeLadowac.add(new Miasto());
//            Statek.listaGdzieMozeLadowac.add(new Port());
//        }
//        return Statek.listaGdzieMozeLadowac;
//    }
//
//    @Override
//    public Droga getTypDrogi() {
//        if (Statek.typDrogi==null){
//            Statek.typDrogi = new DrogaMorska();
//        }
//        return Statek.typDrogi;
//    }

    /**
     * Pusty konstruktor klasy statek.
     */


    public Statek(){

    }

}
