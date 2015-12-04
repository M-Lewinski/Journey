package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Pojazd;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Statek extends Pojazd {
    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    private static Droga typDrogi;
    /**
     * Konstruktor klasy statek, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     * @param maksymalnaPredkosc
     * @param ladunek
     */
    public Statek(double dlugosc, double szerokosc, double maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, maksymalnaPredkosc);
        if(listaGdzieMozeLadowac.isEmpty()){
            listaGdzieMozeLadowac.add(new Miasto());
            listaGdzieMozeLadowac.add(new Port());
        }
        if (typDrogi==null){
            typDrogi = new DrogaMorska();
        }
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskWojskowych());
        this.setTymczasowyKolor(Color.LIGHTBLUE);
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

    /**
     * Pusty konstruktor klasy statek.
     */
    public Statek(){

    }
    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return null;
    }
}
