package Pojazdy;

import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Samolot;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotWojskowy extends Samolot {
    public SamolotWojskowy(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
    }
}
