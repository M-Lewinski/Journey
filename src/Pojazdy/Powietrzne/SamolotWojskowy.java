package Pojazdy.Powietrzne;

import Drogi.Droga;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotWojskowy extends Samolot {
    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    private static Droga typDrogi;
//    public SamolotWojskowy(double dlugosc, double szerokosc, double polozenieX, double polozenieY, double maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
    public SamolotWojskowy(double dlugosc, double szerokosc, double maksymalnaPredkosc, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        if(listaGdzieMozeLadowac.isEmpty()){
//            listaGdzieMozeLadowac.add(new Miasto());
            listaGdzieMozeLadowac.add(new LotniskoWojskowe());
        }
        if (typDrogi==null){
            typDrogi = new DrogaPowietrzna();
        }
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskWojskowych());
        this.setTymczasowyKolor(Color.DARKORANGE);
        okreslNowePolozenie(listaGdzieMozeLadowac);
        tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(),typDrogi);
        wypisywanieTrasy(this.getTrasa());
        this.getObecnePolozenie().addPojazdOczekujacy(this);
        this.rysuj(MainPanel.getGrupaPojazdow());
        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));
        this.nastepnaDroga();

//        okreslNowePolozenie(listaMozliwychPrzystankow);
//        tworzenieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy());
//        tworzenieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),typDrogi);
//        this.setNastepnyPrzystanek(nastepnyPrzystanekZTrasy(this.getTrasa()));
    }

    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, SamolotWojskowy.listaGdzieMozeLadowac);
    }

//    @Override
//    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {
//
//    }
}
