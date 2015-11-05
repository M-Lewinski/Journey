package Drogi;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

/** Klasa drogi powietrznej, ktora implementuje obiekt droga powietrzna.
 * Created by Lewin on 2015-10-18.
 */
public class DrogaPowietrzna extends Droga {
    /**
     * Konstruktor klasy droga powietrzna, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param poczatek poczatek drogi.
     * @param koniec koniec drogi.
     * @param odleglosc odleglosc miedzy poczatkiem i koncem drogi.
     */
    public DrogaPowietrzna(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec) {
        super(poczatek, koniec);
    }
}
