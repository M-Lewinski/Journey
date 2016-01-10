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
import Pojazdy.Ladunki.Uzbrojenie;
import Pojazdy.Ladunki.Wojskowy;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotWojskowy extends Samolot {
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;
    private Wojskowy ladunek;
//    public SamolotWojskowy(double dlugosc, double szerokosc, double polozenieX, double polozenieY, double maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
    public SamolotWojskowy(double dlugosc, double szerokosc, Uzbrojenie uzbrojenie, double polozenieX, double polozenieY) {
        super(dlugosc, szerokosc);
        ladunek = new Wojskowy(uzbrojenie);
        this.setTymczasowyKolor(Color.DARKORANGE);
        this.rysuj(MainPanel.getGrupaPojazdow());
        Swiat.getInstance().getListaWolnychPojazdow().add(this);
//        okreslaniePierwszegoPolozenia(polozenieX, polozenieY);
        this.setPolozenieX(polozenieX);
        this.setPolozenieY(polozenieY);
//        System.out.println("HERE1");
//        synchronized (this) {
//            tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(), this.getTypDrogi());
//            System.out.println("nazwa nastepnego miejsca: " + this.getTrasa().get(1));
//            this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getPozostalaTrasa(), this.getObecnePolozenie()));
//        }
//        this.getPrzystanekPoczatkowy().addPojazdZaparkowany(this);
//        this.poinformujPasazerow(null,this.listaOdwiedzanychPrzystankow(this.getTrasa()));
        this.runMe();
    }

    @Override
    public void okreslNowePolozenie(List<MiejsceZmianyKierunku> listaMozliwychPrzystankow) {
        LotniskoWojskowe poczatek = new LotniskoWojskowe(null,30,30,this.getPolozenieX(),this.getPolozenieY(),false);
        this.setObecnePolozenie(poczatek);
        this.setPrzystanekPoczatkowy(poczatek);
//        this.setPolozenieX(polozenieX);
//        this.setPolozenieY(polozenieY);
        List<Przystanek> listaLokalizcji = filtrowaniePrzystankow();
        listaLokalizcji.remove(poczatek);
        double odleglosc=0.0;
        Przystanek koniec=null;
        for (int i = 0; i < listaLokalizcji.size(); i++) {
            Przystanek przystanek = listaLokalizcji.get(i);
//            if(przystanek == poczatek){
//                continue;
//            }
            double temp = pitagoras(poczatek.getPolozenieX()-przystanek.getPolozenieX(),poczatek.getPolozenieY()-przystanek.getPolozenieY());
            if(temp == 0.0){
                continue;
            }
            if(odleglosc==0.0){
                odleglosc=temp;
                koniec=przystanek;
                continue;
            }
            if(temp <= odleglosc){
                odleglosc=temp;
                koniec=przystanek;
            }
        }
//        System.out.println(temp);
//        System.out.println(koniec.getNazwa());
        Droga droga = new DrogaPowietrzna(poczatek,koniec,false);
        this.setPrzystanekDocelowy(koniec);
    }


    //    public void okreslaniePierwszegoPolozenia(double polozenieX,double polozenieY){
//        LotniskoWojskowe poczatek = new LotniskoWojskowe(null,30,30,polozenieX,polozenieY,false);
//        this.setObecnePolozenie(poczatek);
//        this.setPrzystanekPoczatkowy(poczatek);
//        this.setPolozenieX(polozenieX);
//        this.setPolozenieY(polozenieY);
//        List<Przystanek> listaLokalizcji = filtrowaniePrzystankow();
//        listaLokalizcji.remove(poczatek);
//        double odleglosc=0.0;
//        Przystanek koniec=null;
//        double temp=0.0;
//        for (int i = 0; i < listaLokalizcji.size(); i++) {
//            Przystanek przystanek = listaLokalizcji.get(i);
////            if(przystanek == poczatek){
////                continue;
////            }
//           temp = pitagoras(poczatek.getPolozenieX()-przystanek.getPolozenieX(),poczatek.getPolozenieY()-przystanek.getPolozenieY());
//            if(temp == 0.0){
//                continue;
//            }
//            if(odleglosc==0.0){
//                odleglosc=temp;
//                koniec=przystanek;
//                continue;
//            }
//            if(temp <= odleglosc){
//                odleglosc=temp;
//                koniec=przystanek;
//            }
//        }
//        System.out.println(temp);
//        System.out.println(koniec.getNazwa());
//        Droga droga = new DrogaPowietrzna(poczatek,koniec,false);
//        this.setPrzystanekDocelowy(koniec);
//    }
//
    @Override
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
        if(SamolotWojskowy.listaGdzieMozeLadowac.isEmpty()){
//            listaGdzieMozeLadowac.add(new Miasto());
            SamolotWojskowy.listaGdzieMozeLadowac.add(new LotniskoWojskowe());
        }
        return SamolotWojskowy.listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        if (SamolotWojskowy.typDrogi==null){
            SamolotWojskowy.typDrogi = new DrogaPowietrzna();
        }
        return SamolotWojskowy.typDrogi;
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
