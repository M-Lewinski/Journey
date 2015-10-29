package Mapa.ZmianyKierunku.Przystanki;

import Pojazdy.TworzeniePojazdu;

/**
 * Created by Lewin on 2015-10-18.
 */
public class LotniskoCywilne extends Lotnisko implements TworzeniePojazdu {
    public LotniskoCywilne(String nazwa,int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(nazwa,dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen, maksymalnaPojemnosc);
//        System.out.println("Utworzono Lotnisko Cywilne");
    }

    @Override
    public void stworz() {

    }
}
