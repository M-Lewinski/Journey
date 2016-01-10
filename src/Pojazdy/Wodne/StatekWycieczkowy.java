package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Gui.MainPanel;
import Gui.ShowLabel;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pasazerowie.Pasazer;
import Pojazdy.Ladunki.Pasazerski;
import Pojazdy.TransportowiecCywilny;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public class StatekWycieczkowy extends Statek implements TransportowiecCywilny,Serializable {
    private static final long serialVersionUID = -6211877081383929112L;
    private Firma firma;
    private Pasazerski ladunek;
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;
    //    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
//    private static Droga typDrogi;

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public StatekWycieczkowy(double dlugosc, double szerokosc) {
        super(dlugosc, szerokosc);
        Random random = new Random();
        firma=Firma.values()[random.nextInt(Firma.values().length)];
        this.ladunek = new Pasazerski();
//        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow()+90);
        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow());
        this.firma = firma;
        this.setColor(Color.LIGHTBLUE);
        this.rysuj(MainPanel.getGrupaPojazdow());
        this.runMe();
    }

    public Pasazerski getLadunek() {
        return ladunek;
    }

    public void setLadunek(Pasazerski ladunek) {
        this.ladunek = ladunek;
    }

    public static List<MiejsceZmianyKierunku> getListaGdzieMozeLadowac() {
        return listaGdzieMozeLadowac;
    }

    public static void setListaGdzieMozeLadowac(List<MiejsceZmianyKierunku> listaGdzieMozeLadowac) {
        StatekWycieczkowy.listaGdzieMozeLadowac = listaGdzieMozeLadowac;
    }

    public static void setTypDrogi(Droga typDrogi) {
        StatekWycieczkowy.typDrogi = typDrogi;
    }

    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, this.getMozliweLadowania());
    }


    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = super.potrzebneInformacje();
        ShowLabel showLabel = new ShowLabel("Nazwa firmy: " + this.firma.toString());
        listaNodow.add(4,showLabel);
        List<Control> listaTymczasowa = this.ladunek.potrzebneInformacje();
        for (int i = 0; i < listaTymczasowa.size(); i++) {
            listaNodow.add(listaTymczasowa.get(i));
        }
        return listaNodow;
    }

    @Override
    public void usuwanie() {
        super.usuwanie();
        this.ladunek.usuwanie(this);
    }

    @Override
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
        if(StatekWycieczkowy.listaGdzieMozeLadowac.isEmpty()){
            StatekWycieczkowy.listaGdzieMozeLadowac.add(new Miasto());
            StatekWycieczkowy.listaGdzieMozeLadowac.add(new Port());
        }
        return StatekWycieczkowy.listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        if (StatekWycieczkowy.typDrogi==null){
            StatekWycieczkowy.typDrogi = new DrogaMorska();
        }
        return StatekWycieczkowy.typDrogi;
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
                return false;
            }
            if(this.ladunek.czyWciazJestNaPrzystanku(pasazer,this)==false){
                return false;
            }
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
    public boolean przedStartowaniem() {
//        return super.przedStartowaniem(miejsceZmianyKierunku);
        return this.ladunek.czyWszystcyWysiedli();
    }

    @Override
    public void wysiadanie(Pasazer pasazer) {
        synchronized (this.getHulkPojazdu()){
            this.ladunek.removePasazer(pasazer);
            this.ladunek.removeWysiadajacyPasazer(pasazer);
//            this.ladunek.setObecnaLiczbaPasazerow(this.ladunek.getObecnaLiczbaPasazerow()-1);
        }
    }


    @Override
    public void obslugaLadunku(List<Pasazer> listaOznajmionychPasazerow) {
        super.obslugaLadunku(listaOznajmionychPasazerow);

    }


}
