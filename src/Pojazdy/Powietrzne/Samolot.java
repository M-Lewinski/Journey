package Pojazdy.Powietrzne;

import Drogi.DrogaPowietrzna;
import Gui.ShowLabel;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Pojazd;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu=0;
    private double maksymalnaIloscPaliwa=0.0;
    private double aktualnaIloscPaliwa=0.0;
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

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = super.potrzebneInformacje();
        ShowLabel label1 = new ShowLabel("Liczba za³ogi:");
        listaNodow.add(4,label1);
        ShowLabel label2 = new ShowLabel(Integer.toString(this.liczbaPersonelu));
        listaNodow.add(5,label2);
        ShowLabel label3 = new ShowLabel("Maksymalna ilosc paliwa:");
        listaNodow.add(6,label3);
        ShowLabel label4 = new ShowLabel(Double.toString(this.maksymalnaIloscPaliwa));
        listaNodow.add(7,label4);
        ShowLabel label5 = new ShowLabel("Aktualna ilosc paliwa:");
        listaNodow.add(8,label5);
        ShowLabel label6 = new ShowLabel(Double.toString(this.aktualnaIloscPaliwa));
        listaNodow.add(9,label6);
        return  listaNodow;
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


    @Override
    public void usuwanie() {
        super.usuwanie();
        if(this.getObecnePolozenie() instanceof Przystanek){
            Przystanek przystanek = (Przystanek) this.getObecnePolozenie();
            if(przystanek.getListaPojazdowZaparkowanych().contains(this)){
                przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc()+1);
            }
        }
    }
}
