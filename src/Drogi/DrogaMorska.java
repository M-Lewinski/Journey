package Drogi;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

/** Klasa drogi morskiej, ktora implementuje obiekt droga morska.
 * Created by Lewin on 2015-10-18.
 */
public class DrogaMorska extends Droga {
    /**
     * Konstruktor klasy droga morska, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param poczatek poczatek drogi.
     * @param koniec koniec drogi.
     * @param odleglosc odleglosc miedzy poczatkiem a koncem drogi.
     */
    public DrogaMorska(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec, int odleglosc) {
        super(poczatek, koniec, odleglosc);
    }

}
