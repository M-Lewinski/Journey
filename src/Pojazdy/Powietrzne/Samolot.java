package Pojazdy.Powietrzne;

import Drogi.DrogaPowietrzna;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Pojazd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu;
    private double maksymalnaIloscPaliwa;
    private double aktualnaIloscPaliwa;
//    private MiejsceZmianyKierunku nastepnyPrzystanek;

//    public Samolot(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
    public Samolot(double dlugosc, double szerokosc, double maksymalnaPredkosc, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, maksymalnaPredkosc);
        this.liczbaPersonelu = liczbaPersonelu;
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }

    public Samolot(){

    }

//    public MiejsceZmianyKierunku getNastepnyPrzystanek() {
//        return nastepnyPrzystanek;
//    }
//
//    public void setNastepnyPrzystanek(MiejsceZmianyKierunku nastepnyPrzystanek) {
//        this.nastepnyPrzystanek = nastepnyPrzystanek;
//    }

    public int getLiczbaPersonelu() {
        return liczbaPersonelu;
    }

    public double getAktualnaIloscPaliwa() {

        return aktualnaIloscPaliwa;
    }

    public void setAktualnaIloscPaliwa(double aktualnaIloscPaliwa) {
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }


    public double getMaksymalnaIloscPaliwa() {

        return maksymalnaIloscPaliwa;
    }

    public void setMaksymalnaIloscPaliwa(double maksymalnaIloscPaliwa) {
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
    }

    public void awaryjneLadowanie(){

    }

//    public void poinformujMiejsceZmianyKierunku(){
//
//    }
//    public void tworzenieTrasy(){
//        this.setTrasa(szukanieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),new DrogaPowietrzna()));
////        this.addPunktTrasy(Swiat.getInstance().getListaLotniskWojskowych().get(0));
////        List<Object> listaMozliwychLadowan = new ArrayList<Object>();
////        listaMozliwychLadowan.add(new Miasto());
////        listaMozliwychLadowan.add(new LotniskoCywilne());
//        poinformujOPrzyjezdzie(Pojazd.getListaGdzieMozeLadowac());
//        this.setNastepnyPrzystanek(nastepnyPrzystanekZTrasy(this.getTrasa(),this.getTrasa().get(0),Pojazd.getListaGdzieMozeLadowac()));
//    }

}
