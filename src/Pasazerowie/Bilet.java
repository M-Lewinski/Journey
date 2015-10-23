package Pasazerowie;

import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Bilet {
    private Pojazd pojazd;
    private MiejsceZmianyKierunku miejsceZmianyKierunku;

    public Pojazd getPojazd() {
        return pojazd;
    }

    public void setPojazd(Pojazd pojazd) {
        this.pojazd = pojazd;
    }

    public MiejsceZmianyKierunku getMiejsceZmianyKierunku() {
        return miejsceZmianyKierunku;
    }

    public void setMiejsceZmianyKierunku(MiejsceZmianyKierunku miejsceZmianyKierunku) {
        this.miejsceZmianyKierunku = miejsceZmianyKierunku;
    }
}
