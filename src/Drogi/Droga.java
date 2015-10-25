package Drogi;

import Mapa.Rysowanie;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Droga implements Rysowanie {
    private MiejsceZmianyKierunku poczatek;
    private MiejsceZmianyKierunku koniec;

    public void setKoniec(MiejsceZmianyKierunku koniec) {
        this.koniec = koniec;
    }

    public void setPoczatek(MiejsceZmianyKierunku poczatek) {

        this.poczatek = poczatek;
    }

    public MiejsceZmianyKierunku getKoniec() {

        return koniec;
    }

    public MiejsceZmianyKierunku getPoczatek() {

        return poczatek;
    }

    public Droga(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec) {
        this.poczatek = poczatek;
        this.koniec = koniec;
    }

    public Droga(){

    }
    @Override
    public void rysuj() {

    }
}
