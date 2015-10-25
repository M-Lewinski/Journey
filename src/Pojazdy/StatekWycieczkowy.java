package Pojazdy;

import Pojazdy.Ladunki.TypLadunku;

/**
 * Created by Lewin on 2015-10-18.
 */
public class StatekWycieczkowy extends Statek {
    private String firma;

    public String getFirma() {
        return firma;
    }

    public StatekWycieczkowy(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek, String firma) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek);
        this.firma = firma;
    }
}
