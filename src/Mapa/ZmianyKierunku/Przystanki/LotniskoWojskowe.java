package Mapa.ZmianyKierunku.Przystanki;

import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-10-18.
 */
public class LotniskoWojskowe extends Lotnisko implements TworzeniePojazdu {
    public LotniskoWojskowe(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
    }

    @Override
    public void stworz() {

    }
}
