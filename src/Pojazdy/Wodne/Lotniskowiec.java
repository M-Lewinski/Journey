package Pojazdy.Wodne;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.TworzeniePojazdu;

/** Klasa lotniskowca, ktora implementuje obiekt lotniskowiec.
 * Created by Lewin on 2015-10-18.
 */
public class Lotniskowiec extends Statek implements TworzeniePojazdu {
    /**
     * Konstruktor klasy lotniskowiec, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     * @param polozenieX
     * @param polozenieY
     * @param maksymalnaPredkosc
     * @param ladunek
     */
    public Lotniskowiec(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek);
        Swiat.getInstance().addLotniskowiec(this);
    }

    @Override
    public void stworz() {

    }

    @Override
    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {

    }
}
