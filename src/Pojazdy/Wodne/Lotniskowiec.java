package Pojazdy.Wodne;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.TworzeniePojazdu;

import java.util.ArrayList;
import java.util.List;

/** Klasa lotniskowca, ktora implementuje obiekt lotniskowiec.
 * Created by Lewin on 2015-10-18.
 */
public class Lotniskowiec extends Statek implements TworzeniePojazdu {
    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    /**
     * Konstruktor klasy lotniskowiec, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     * @param maksymalnaPredkosc
     * @param ladunek
     */
    public Lotniskowiec(double dlugosc, double szerokosc, double maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, maksymalnaPredkosc, ladunek);
        Swiat.getInstance().addLotniskowiec(this);
    }

    @Override
    public void stworz() {

    }

//    @Override
//    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {
//
//    }
}
