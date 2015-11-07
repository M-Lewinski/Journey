package Mapa.ZmianyKierunku.Przystanki;

import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Port extends Przystanek implements TworzeniePojazdu {
    public Port(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, nazwa);
        Swiat.getInstance().addPort(this);
    }
    public Port(){

    }

    @Override

    public void stworz() {

    }
}
