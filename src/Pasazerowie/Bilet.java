package Pasazerowie;

import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Bilet {
    private Pojazd pojazd;
    private Przystanek przystanek;

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public Przystanek getPrzystanek() {
        return przystanek;
    }

    public void setPrzystanek(Przystanek przystanek) {
        this.przystanek = przystanek;
    }
}
