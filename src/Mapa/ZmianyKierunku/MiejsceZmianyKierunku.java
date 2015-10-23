package Mapa.ZmianyKierunku;

import Mapa.PunktNaMapie;
import Pojazdy.Pojazd;

import java.util.ArrayList;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class MiejsceZmianyKierunku extends PunktNaMapie {
    private ArrayList<Pojazd> listaPojazdowOczekujacych;
    private boolean zajetaPrzestrzen;

    public boolean isZajetaPrzestrzen() {
        return zajetaPrzestrzen;
    }

    public void setZajetaPrzestrzen(boolean zajetaPrzestrzen) {
        this.zajetaPrzestrzen = zajetaPrzestrzen;
    }

    public ArrayList<Pojazd> getListaPojazdowOczekujacych() {
        return listaPojazdowOczekujacych;
    }

    public void setListaPojazdowOczekujacych(ArrayList<Pojazd> listaPojazdowOczekujacych) {
        this.listaPojazdowOczekujacych = listaPojazdowOczekujacych;
    }

    public void zajmij(){

    }
    public void zwolnij(){

    }
    public void poinformuj(){

    }
}
