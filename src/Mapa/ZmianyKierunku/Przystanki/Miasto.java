package Mapa.ZmianyKierunku.Przystanki;

import Mapa.Swiat;
import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-11-06.
 */
public class Miasto extends Lotnisko implements TworzeniePojazdu{
    public Miasto(String nazwa, int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(nazwa, dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
        Swiat.getInstance().addMiasto(this);
    }
    public Miasto(){

    }
    @Override
    public void stworz() {

    }
}
