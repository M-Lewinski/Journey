package Pojazdy.Powietrzne;

import Drogi.Droga;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot {
    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    private static Droga typDrogi;
    public SamolotPasazerski(double dlugosc, double szerokosc, double maksymalnaPredkosc, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        if(SamolotPasazerski.getListaGdzieMozeLadowac().isEmpty()){
            SamolotPasazerski.addListaGdzieMozeLadowac(new Miasto());
            SamolotPasazerski.addListaGdzieMozeLadowac(new LotniskoCywilne());
        }
        if(SamolotPasazerski.typDrogi==null){
            SamolotPasazerski.typDrogi = new DrogaPowietrzna();
        }
        this.setTymczasowyKolor(Color.YELLOW);
        okreslNowePolozenie(listaGdzieMozeLadowac);
        tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(),typDrogi);
        wypisywanieTrasy(this.getTrasa());
        this.getObecnePolozenie().addPojazdOczekujacy(this);
        this.rysuj(MainPanel.getGrupaPojazdow());
        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));
        this.nastepnaDroga();
//        System.out.println("droga teraz: " + this.getDrogaTeraz().getKoniec().getNazwa());
        //        this.nastepnaDroga();
//        Rectangle rectangle = new Rectangle(30,30, Color.BLACK);
//        rectangle.setVisible(false);
//        this.setImageNode(rectangle);

//        System.out.println(this.getDrogaTeraz().getKoniec().getNazwa());
        //this.setDrogaTeraz(this.getPrzystanekPoczatkowy().ge);
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

//    @Override
//    public void tworzenieTrasy(MiejsceZmianyKierunku przystanekPoczatkowy, MiejsceZmianyKierunku przystanekDocelowy){
//        this.poinformujORezygnacjiPrzyjazdu(this.getTrasa());
//        this.getTrasa().clear();
//        this.getPozostalaTrasa().clear();
//        this.setTrasa(szukanieTrasy(przystanekPoczatkowy,przystanekDocelowy,new DrogaPowietrzna()));
//        if (this.getTrasa()==null){
//            return;
//        }
//        this.getPozostalaTrasa().addAll(this.getTrasa());
//        this.poinformujOZamiarzePrzyjazdu(this.getTrasa());
//        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));
//    }
}
