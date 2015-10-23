package Pojazdy;

import Mapa.ZmianyKierunku.Przystanki.Lotnisko;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu;
    private int maksymalnaIloscPaliwa;
    private int aktualnaIloscPaliwa;
    private Lotnisko nastepnyPrzystanek;


    public Lotnisko getNastepnyPrzystanek() {
        return nastepnyPrzystanek;
    }

    public void setNastepnyPrzystanek(Lotnisko nastepnyPrzystanek) {
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
}
