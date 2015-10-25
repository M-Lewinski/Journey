package Pojazdy;

import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Statek;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Lotniskowiec extends Statek implements TworzeniePojazdu {
    public Lotniskowiec(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek);
    }

    @Override
    public void stworz() {

    }
}
