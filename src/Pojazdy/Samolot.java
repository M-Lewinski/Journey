package Pojazdy;

import Drogi.DrogaPowietrzna;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu;
    private int maksymalnaIloscPaliwa;
    private int aktualnaIloscPaliwa;
    private MiejsceZmianyKierunku nastepnyPrzystanek;

    public Samolot(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, maksymalnaPredkosc);
        this.liczbaPersonelu = liczbaPersonelu;
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }

    public Samolot(){

    }

    public MiejsceZmianyKierunku getNastepnyPrzystanek() {
        return nastepnyPrzystanek;
    }

    public void setNastepnyPrzystanek(MiejsceZmianyKierunku nastepnyPrzystanek) {
        this.nastepnyPrzystanek = nastepnyPrzystanek;
    }

    public int getLiczbaPersonelu() {
        return liczbaPersonelu;
    }

    public int getAktualnaIloscPaliwa() {

        return aktualnaIloscPaliwa;
    }

    public void setAktualnaIloscPaliwa(int aktualnaIloscPaliwa) {
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }


    public int getMaksymalnaIloscPaliwa() {

        return maksymalnaIloscPaliwa;
    }

    public void setMaksymalnaIloscPaliwa(int maksymalnaIloscPaliwa) {
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
    }
    public void awaryjneLadowanie(){

    }
    public void poinformujMiejsceZmianyKierunku(){

    }
    public void tworzenieTrasy(){
        this.setTrasa(szukanieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),new DrogaPowietrzna()));
    }

    public MiejsceZmianyKierunku nastepnyPrzystanekZTrasy(List<MiejsceZmianyKierunku> trasa){
        for (int i = 0; i < trasa.size(); i++) {
            if(trasa.get(i) instanceof Przystanek){
                return trasa.get(i);
            }
        }
        return null;
    }
}
