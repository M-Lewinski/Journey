package Pasazerowie;

import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by Lewin on 2015-11-08.
 */
public class TrasowaniePasazerow implements Comparator<TrasowaniePasazerow> {
    private double dlugosc=0.0;
    private LinkedList<Przystanek> listaPunktowNaMapie = new LinkedList<Przystanek>();

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public LinkedList<Przystanek> getListaPunktowNaMapie() {
        return listaPunktowNaMapie;
    }

    public void setListaPunktowNaMapie(LinkedList<Przystanek> listaPunktowNaMapie) {
        this.listaPunktowNaMapie = listaPunktowNaMapie;
    }

    public void addListaPunktowNaMapie(Przystanek przystanek, double odleglosc){
        this.listaPunktowNaMapie.add(przystanek);
        this.dlugosc=this.dlugosc+odleglosc;
    }

    public void addCopyListaPunktowNaMapie(Przystanek przystanek){
        this.listaPunktowNaMapie.add(przystanek);
    }


    @Override
    public int compare(TrasowaniePasazerow o1, TrasowaniePasazerow o2) {
        return Double.compare(o1.dlugosc,o2.dlugosc);
    }
}
