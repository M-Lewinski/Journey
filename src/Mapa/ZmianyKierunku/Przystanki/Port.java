package Mapa.ZmianyKierunku.Przystanki;

import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Port extends Przystanek implements TworzeniePojazdu {
    public Port(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen);
    }

    @Override

    public void stworz() {

    }
}
