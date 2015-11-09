package Pojazdy.Powietrzne;

import Drogi.DrogaPowietrzna;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot {
    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    public SamolotPasazerski(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        if(SamolotPasazerski.getListaGdzieMozeLadowac().isEmpty()){
            SamolotPasazerski.addListaGdzieMozeLadowac(new Miasto());
            SamolotPasazerski.addListaGdzieMozeLadowac(new LotniskoCywilne());
        }
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskCywilnych());
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaMiast());
        System.out.println("dlugosc listy przystankow " + listaMozliwychPrzystankow.size());
        okreslaniePolozenia(listaMozliwychPrzystankow);
        tworzenieTrasy();
        wypisywanieTrasy();
        this.getObecnePolozenie().addPojazdOczekujacy(this);
//        LinkedList<MiejsceZmianyKierunku> testList = new LinkedList<MiejsceZmianyKierunku>();
//        for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
//            testList.add(Swiat.getInstance().getListaMiejscZmianyKierunku().get(i));
//        }
//        this.setTrasa(testList);
    }
    public static List<Object> getListaGdzieMozeLadowac() {
        return listaGdzieMozeLadowac;
    }

    public static void setListaGdzieMozeLadowac(List<Object> listaLadowisk) {
        SamolotPasazerski.listaGdzieMozeLadowac = listaLadowisk;
    }

    public static void addListaGdzieMozeLadowac(Object listaLadowisk){
        SamolotPasazerski.listaGdzieMozeLadowac.add(listaLadowisk);
    }

    public static void removeListaGdzieMozeLadowac(Object listaLadowisk){
        SamolotPasazerski.listaGdzieMozeLadowac.remove(listaLadowisk);
    }

    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, SamolotPasazerski.getListaGdzieMozeLadowac());
    }

    @Override
    public void tworzenieTrasy(){
        this.setTrasa(szukanieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),new DrogaPowietrzna()));
        if (this.getTrasa()==null){
            return;
        }
//        this.addPunktTrasy(Swiat.getInstance().getListaLotniskWojskowych().get(0));
        poinformujOPrzyjezdzie(SamolotPasazerski.getListaGdzieMozeLadowac());
        this.setNastepnyPrzystanek(nastepnyPrzystanekZTrasy(this.getTrasa(),this.getTrasa().get(0),SamolotPasazerski.getListaGdzieMozeLadowac()));
    }
}
