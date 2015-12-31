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
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;
    public SamolotPasazerski(double dlugosc, double szerokosc, double maksymalnaPredkosc, int liczbaPersonelu, double maksymalnaIloscPaliwa, double aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);


//        okreslNowePolozenie(listaGdzieMozeLadowac);
//        tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(),typDrogi);
//        wypisywanieTrasy(this.getTrasa());
//        this.getObecnePolozenie().addPojazdOczekujacy(this);
//        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));
//        this.nastepnaDroga();
        this.setTymczasowyKolor(Color.YELLOW);
        this.rysuj(MainPanel.getGrupaPojazdow());

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
    public static List<MiejsceZmianyKierunku> getListaGdzieMozeLadowac() {
        return listaGdzieMozeLadowac;
    }

    public static void setListaGdzieMozeLadowac(List<MiejsceZmianyKierunku> listaLadowisk) {
        SamolotPasazerski.listaGdzieMozeLadowac = listaLadowisk;
    }

    public static void addListaGdzieMozeLadowac(MiejsceZmianyKierunku listaLadowisk){
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
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
        if(SamolotPasazerski.getListaGdzieMozeLadowac().isEmpty()){
            SamolotPasazerski.addListaGdzieMozeLadowac(new Miasto());
            SamolotPasazerski.addListaGdzieMozeLadowac(new LotniskoCywilne());
        }
        return SamolotPasazerski.listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        if(SamolotPasazerski.typDrogi==null){
            SamolotPasazerski.typDrogi = new DrogaPowietrzna();
        }
        return SamolotPasazerski.typDrogi;
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
