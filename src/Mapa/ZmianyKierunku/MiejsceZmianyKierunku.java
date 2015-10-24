package Mapa.ZmianyKierunku;

import Mapa.PunktNaMapie;
import Pojazdy.Pojazd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class MiejsceZmianyKierunku extends PunktNaMapie {
    private List<Pojazd> listaPojazdowOczekujacych= new ArrayList<Pojazd>();
    private boolean zajetaPrzestrzen;

    public List<Pojazd> getListaPojazdowOczekujacych() {
        return listaPojazdowOczekujacych;
    }

    public void setListaPojazdowOczekujacych(List<Pojazd> listaPojazdowOczekujacych) {
        this.listaPojazdowOczekujacych = listaPojazdowOczekujacych;
    }

    public boolean isZajetaPrzestrzen() {
        return zajetaPrzestrzen;
    }

    public void setZajetaPrzestrzen(boolean zajetaPrzestrzen) {
        this.zajetaPrzestrzen = zajetaPrzestrzen;
    }

    public void addPojazdOczekujacy(Pojazd pojazd){
        this.listaPojazdowOczekujacych.add(pojazd);
    }
    public void removePojazdOczekujacy(Pojazd pojazd){
        this.listaPojazdowOczekujacych.remove(pojazd);
    }

    public void zajmij(){

    }
    public void zwolnij(){

    }
    public void poinformuj(){

    }

    public MiejsceZmianyKierunku(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY);
        this.zajetaPrzestrzen = zajetaPrzestrzen;
    }
    public MiejsceZmianyKierunku(){

    }
}
