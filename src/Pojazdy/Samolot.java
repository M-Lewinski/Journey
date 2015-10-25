package Pojazdy;

import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Pojazdy.Ladunki.TypLadunku;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu;
    private int maksymalnaIloscPaliwa;
    private int aktualnaIloscPaliwa;
    private Lotnisko nastepnyPrzystanek;

    public Samolot(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek);
        this.liczbaPersonelu = liczbaPersonelu;
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }

    public Samolot(){

    }

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
