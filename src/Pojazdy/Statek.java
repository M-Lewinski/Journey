package Pojazdy;

import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Pojazd;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Statek extends Pojazd {
    public Statek(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek);
    }
    public Statek(){

    }
}
