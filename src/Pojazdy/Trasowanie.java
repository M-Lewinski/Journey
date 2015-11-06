package Pojazdy;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-27.
 */
public class Trasowanie implements Comparator<Trasowanie> {
    private double dlugosc=0.0;
    private LinkedList<MiejsceZmianyKierunku> listaPunktowNaMapie = new LinkedList<MiejsceZmianyKierunku>();

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public LinkedList<MiejsceZmianyKierunku> getListaPunktowNaMapie() {
        return listaPunktowNaMapie;
    }

    public void setListaPunktowNaMapie(LinkedList<MiejsceZmianyKierunku> listaPunktowNaMapie) {
        this.listaPunktowNaMapie = listaPunktowNaMapie;
    }

    public void addListaPunktowNaMapie(MiejsceZmianyKierunku miejsceZmianyKierunku, double odleglosc){
        this.listaPunktowNaMapie.add(miejsceZmianyKierunku);
        this.dlugosc=this.dlugosc+odleglosc;
    }

    public void addCopyListaPunktowNaMapie(MiejsceZmianyKierunku miejsceZmianyKierunku){
        this.listaPunktowNaMapie.add(miejsceZmianyKierunku);
    }


    @Override
    public int compare(Trasowanie o1, Trasowanie o2) {
        return Double.compare(o1.dlugosc,o2.dlugosc);
    }
}
