package Mapa.ZmianyKierunku.Przystanki;

import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-10-18.
 */
public class LotniskoWojskowe extends Lotnisko implements TworzeniePojazdu {
    public LotniskoWojskowe(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(nazwa,dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
        Swiat.getInstance().addLotniskoWojskowe(this);
    }

    @Override
    public void stworz() {

    }
}
