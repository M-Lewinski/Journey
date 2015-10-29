package Pojazdy;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lewin on 2015-10-27.
 */
public class Trasowanie implements Comparator<Trasowanie> {
    private int dlugosc;
    private List<MiejsceZmianyKierunku> listaPunktowNaMapie = new ArrayList<MiejsceZmianyKierunku>();

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public List<MiejsceZmianyKierunku> getListaPunktowNaMapie() {
        return listaPunktowNaMapie;
    }

    public void setListaPunktowNaMapie(List<MiejsceZmianyKierunku> listaPunktowNaMapie) {
        this.listaPunktowNaMapie = listaPunktowNaMapie;
    }

    public void addListaPunktowNaMapie(MiejsceZmianyKierunku miejsceZmianyKierunku, int odleglosc){
        this.listaPunktowNaMapie.add(miejsceZmianyKierunku);
        this.dlugosc=this.dlugosc+odleglosc;
    }


    @Override
    public int compare(Trasowanie o1, Trasowanie o2) {
        return Integer.compare(o1.dlugosc,o2.dlugosc);
    }
}
