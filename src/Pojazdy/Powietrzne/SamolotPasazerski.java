package Pojazdy.Powietrzne;

import Drogi.Droga;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pasazerowie.Pasazer;
import Pojazdy.Ladunki.Pasazerski;
import Pojazdy.TransportowiecCywilny;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot implements TransportowiecCywilny,Serializable {

    private static final long serialVersionUID = -7186764832278829889L;
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;
    private Pasazerski ladunek;

    public SamolotPasazerski(double dlugosc, double szerokosc) {
        super( dlugosc, szerokosc);
        this.ladunek = new Pasazerski();
//        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow()+90);
        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow());
        this.setColor(Color.YELLOW);
        this.rysuj(MainPanel.getGrupaPojazdow());
        this.runMe();
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
//        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, SamolotPasazerski.getListaGdzieMozeLadowac());
        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, this.getMozliweLadowania());
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

    @Override
    public void usuwanie() {
        super.usuwanie();
        this.ladunek.usuwanie(this);
    }

    @Override
    public boolean wsiadanie(Pasazer pasazer) {
        synchronized (this.getHulkPojazdu()){
//            if(pasazer.getObecnePolozenie() instanceof Przystanek){
//                Przystanek przystanek = (Przystanek) pasazer.getObecnePolozenie();
//                if(!przystanek.getListaPojazdowZaparkowanych().contains(this)){
//                    return false;
//                }
//            }
            if(this.ladunek==null){
                return  false;
            }

            if(this.ladunek.czyWciazJestNaPrzystanku(pasazer,this)==false){
                return false;
            }
//            System.out.println("wsidama fjasklfjlsadfjlj");
            return this.ladunek.czyJestWolneMiejsce(pasazer);
        }
    }

    @Override
         public void ladowanie(Przystanek przystanek) {
        synchronized (this.getHulkPojazdu()) {
            super.ladowanie(przystanek);
            this.ladunek.znalezienieOsobWysiadajacych(this,przystanek);
            for (Pasazer p: this.ladunek.getListaWysiadajacychPasazerow()) {
                synchronized (p){
                    p.notify();
                }
            }
            this.ladunek.setObecnaLiczbaPasazerow(this.ladunek.getObecnaLiczbaPasazerow()-this.ladunek.getsizeListaWysiadajacychPasazerow());
        }
    }

    @Override
    public void wysiadanie(Pasazer pasazer) {
        synchronized (this.getHulkPojazdu()){
            this.ladunek.removePasazer(pasazer);
            this.ladunek.removeWysiadajacyPasazer(pasazer);
        }
    }



//    @Override
//    public void wysiadanie() {
//        synchronized (this.getHulkPojazdu()){
//            this.ladunek.znalezienieOsobWysiadajacych(this.getNastepnyPrzystanek());
//        }
//    }

    public Pasazerski getLadunek() {
        return ladunek;
    }

    public void setLadunek(Pasazerski ladunek) {
        this.ladunek = ladunek;
    }

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = super.potrzebneInformacje();
        List<Control> listaTymczasowa = this.ladunek.potrzebneInformacje();
//        int j = 8;
        for (int i = 0; i < listaTymczasowa.size(); i++) {
            listaNodow.add(listaTymczasowa.get(i));
        }
//        for (int i = 0; i < listaTymczasowa.size(); i++) {
//            listaNodow.add(listaTymczasowa.get(i));
////            j++;
//        }
        return listaNodow;
    }

    @Override
    public boolean przedStartowaniem() {
//        return super.przedStartowaniem(miejsceZmianyKierunku);
        return this.ladunek.czyWszystcyWysiedli();
    }

    @Override
    public void obslugaLadunku(List<Pasazer> listaOznajmionychPasazerow) {
        super.obslugaLadunku(listaOznajmionychPasazerow);

    }
}
