package Pojazdy.Wodne;

import Drogi.DrogaMorska;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Pojazd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Statek extends Pojazd {
    /**
     * Konstruktor klasy statek, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     * @param polozenieX
     * @param polozenieY
     * @param maksymalnaPredkosc
     * @param ladunek
     */
    public Statek(int dlugosc, int szerokosc, int polozenieX, int polozenieY, double maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, maksymalnaPredkosc);
    }

    /**
     * Pusty konstruktor klasy statek.
     */
    public Statek(){

    }

    public void tworzenieTrasy(){
        this.setTrasa(szukanieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),new DrogaMorska()));
        List<Object> listaMozliwychLadowan = new ArrayList<Object>();
        listaMozliwychLadowan.add(new Miasto());
        listaMozliwychLadowan.add(new Port());
//        poinformujOPrzyjezdzie(listaMozliwychLadowan);
    }
    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return null;
    }
}
