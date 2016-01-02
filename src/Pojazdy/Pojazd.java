package Pojazdy;

import java.lang.reflect.Array;
import java.util.*;

import Drogi.Droga;
import Drogi.DrogaPowietrzna;
import Gui.Controller;
import Gui.Informacja;
import Gui.MainPanel;
import Gui.ShowLabel;
import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Powietrzne.Samolot;
import com.sun.istack.internal.localization.NullLocalizable;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sun.applet.Main;
import sun.awt.image.ImageWatched;
import sun.misc.resources.Messages_it;


/**
 *  Klasa Pojazd implementujaca abstrakcje obiektu pojazd.
 *  @param
 *
 */
public abstract class Pojazd extends PunktNaMapie implements Runnable,Filtrowanie, ShowInfo {
    private double oczekiwanie=0.0;
    private int fps=30;
    private double imagePromien;
    private double odlegloscDoKonca=0.0;
    private Thread thread;
    private boolean threadIsAlive=false;
    /**
     * unikalny identyfikator pojazdu.
     */
    private UUID identyfikator;
    /**
     * lista miejsce, ktore znajduja sie na trasie pojazdu.
     */
    private List<MiejsceZmianyKierunku> trasa = new ArrayList<MiejsceZmianyKierunku>();
//    private  static abstract List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
    //private double angle=0;
    private Droga drogaTeraz=null;
    private double steps=0.0;
//    private int statusPrzelotu=-1;
    private int statusPrzelotu=0;
    private boolean pozwolenie=false;
    private List<MiejsceZmianyKierunku> pozostalaTrasa = new ArrayList<MiejsceZmianyKierunku>();

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getStatusPrzelotu() {
        return statusPrzelotu;
    }

    public void setStatusPrzelotu(int statusPrzelotu) {
        this.statusPrzelotu = statusPrzelotu;
    }

    public boolean isPozwolenie() {
        return pozwolenie;
    }

    public void setPozwolenie(boolean pozwolenie) {
        this.pozwolenie = pozwolenie;
    }

    /**
     * predkosc z jaka porusza sie pojazd.
     */
    private double maksymalnaPredkosc;
    /**
     * typ ladunku jaki posiada pojazd.
     */
    private TypLadunku Ladunek;
    /**
     * obecne polozenie pojazdu.
     */
    private MiejsceZmianyKierunku obecnePolozenie;
    /**
     * przystanek poczatkowy.
     */
    private Przystanek przystanekPoczatkowy;
    /**
     * przystanek docelowy.
     */
    private Przystanek przystanekDocelowy;

    private Color tymczasowyKolor;

    public Color getTymczasowyKolor() {
        return tymczasowyKolor;
    }

    public void setTymczasowyKolor(Color tymczasowyKolor) {
        this.tymczasowyKolor = tymczasowyKolor;
    }

    public double getSteps() {
        return steps;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }

    public void setMaksymalnaPredkosc(double maksymalnaPredkosc) {
        this.maksymalnaPredkosc = maksymalnaPredkosc;
    }

    public double getOdlegloscDoKonca() {
        return odlegloscDoKonca;
    }

    public void setOdlegloscDoKonca(double odlegloscDoKonca) {
        this.odlegloscDoKonca = odlegloscDoKonca;
    }

    /**
     * Getter
     * @return ladunek pojazdu.
     */

    public TypLadunku getLadunek() {
        return Ladunek;
    }

//    public Przystanek najblizszyPrzystanek(List<MiejsceZmianyKierunku> trasa){
//
////        return nastepneMozliweLadowanie(trasa,)
//    }
    private MiejsceZmianyKierunku nastepnyPrzystanek;

    public double getOczekiwanie() {
        return oczekiwanie;
    }

    public void setOczekiwanie(double oczekiwanie) {
        this.oczekiwanie = oczekiwanie;
    }

    public List<MiejsceZmianyKierunku> getPozostalaTrasa() {
        return pozostalaTrasa;
    }

    public void setPozostalaTrasa(List<MiejsceZmianyKierunku> pozostalaTrasa) {
        this.pozostalaTrasa = pozostalaTrasa;
    }

    public void addPozostalaTrasa(MiejsceZmianyKierunku pozostalaTrasa){
        this.pozostalaTrasa.add(pozostalaTrasa);
    }

    public void removePozostalaTrasa(MiejsceZmianyKierunku pozostalaTrasa){
        this.pozostalaTrasa.remove(pozostalaTrasa);
    }

//    public void zmianaTrasy(List<Przystanek> listaMozliwychPrzystankow){
    public void zmianaTrasy(List<Przystanek> listaMozliwychPrzystankow,Droga typDrogi){
        //int x;
//        MiejsceZmianyKierunku nastepneMiejsceZmianyKierunku = null;
        Przystanek nastepneMiejsceZmianyKierunku = null;
        List<MiejsceZmianyKierunku> staraTrasa = new LinkedList<MiejsceZmianyKierunku>();
        staraTrasa.addAll(this.getPozostalaTrasa());
        System.out.println(this.obecnePolozenie.getNazwa());
        if (czyWyladowal(this.obecnePolozenie) == true){
//            System.out.println("jest");
            nastepneMiejsceZmianyKierunku=(Przystanek)this.obecnePolozenie;
        }
        else{
//            System.out.println("nie ma");
            nastepneMiejsceZmianyKierunku=nastepneMozliweLadowanie(this.getTrasa(),this.obecnePolozenie);
        }
//        System.out.println("Nastepne miejsce: " + nastepneMiejsceZmianyKierunku.getNazwa());
        Random random = new Random();
        listaMozliwychPrzystankow.remove(nastepneMiejsceZmianyKierunku);
        listaMozliwychPrzystankow.remove(this.getPrzystanekPoczatkowy());
        listaMozliwychPrzystankow.remove(this.getPrzystanekDocelowy());
        int x=random.nextInt(listaMozliwychPrzystankow.size());
        System.out.println(x);
        this.setPrzystanekDocelowy(listaMozliwychPrzystankow.get(x));
        this.setPrzystanekPoczatkowy(nastepneMiejsceZmianyKierunku);
//        this.tworzenieTrasy(nastepneMiejsceZmianyKierunku,this.getPrzystanekDocelowy());
//        this.tworzenieTrasy(nastepneMiejsceZmianyKierunku,this.getPrzystanekDocelowy(),typDrogi);
        this.tworzenieTrasy(nastepneMiejsceZmianyKierunku,this.getPrzystanekDocelowy(),typDrogi);
        for (int i = 0;staraTrasa.get(i)!=nastepneMiejsceZmianyKierunku; i++) {
            this.pozostalaTrasa.add(i,staraTrasa.get(i));
        }
    }

    public void zmienDotychczasowaTrase(){
        List<Przystanek> listaMozliwychPrzystankow = new ArrayList<Przystanek>();
        List<Przystanek> listaTymczasowa = new ArrayList<Przystanek>();
        listaTymczasowa.addAll(Swiat.getInstance().getListaPrzystankow());
        for (int i = 0; i < listaTymczasowa.size(); i++) {
            if(this.czyMozeTutajLadowac(listaTymczasowa.get(i))==true){
                listaMozliwychPrzystankow.add(listaTymczasowa.get(i));
            }
        }
        zmianaTrasy(listaMozliwychPrzystankow,this.getTypDrogi());
    }

    public boolean czyWyladowal(MiejsceZmianyKierunku obecnePolozenieNaMapie){
        if (obecnePolozenieNaMapie instanceof Przystanek){
//            if((obecnePolozenieNaMapie.getPolozenieX() == this.getPolozenieX()) && (obecnePolozenieNaMapie.getPolozenieY() == this.getPolozenieY())){
             if(((Przystanek) obecnePolozenieNaMapie).getListaPojazdowZaparkowanych().contains(this)){
                return true;
            }
        }
            return false;
    }




//    public List<MiejsceZmianyKierunku> szukanieTrasy(Przystanek poczatekTrasy,Przystanek koniecTrasy, Object typDrogi){
    public List<MiejsceZmianyKierunku> szukanieTrasy(MiejsceZmianyKierunku poczatekTrasy,MiejsceZmianyKierunku koniecTrasy, Object typDrogi){
        ObservableList<Trasowanie> listaTras = FXCollections.observableArrayList();
        SortedList<Trasowanie> posortowanaListaTras =  new SortedList<Trasowanie>(listaTras,new Trasowanie());
//        for (int i = 0; i < poczatekTrasy.getListaDrog().size(); i++) {
//            Trasowanie nowyElement = new Trasowanie();
//            nowyElement.addListaPunktowNaMapie(poczatekTrasy.getListaDrog().get(i).getKoniec(),poczatekTrasy.getListaDrog().get(i).getOdleglosc());
//            listaTras.add(nowyElement);
//        }
        if (poczatekTrasy == koniecTrasy){
            return null;
        }
        Trasowanie nowyElement = new Trasowanie();
        nowyElement.addCopyListaPunktowNaMapie(poczatekTrasy);
        listaTras.add(nowyElement);
//        System.out.println("Poczatek szukania trasy");
//        while (posortowanaListaTras.size()!=0){
        while (!posortowanaListaTras.isEmpty()){
//        while(posortowanaListaTras !=null){
            //if (koniecTrasy.equals(posortowanaListaTras.get(0).getListaPunktowNaMapie().get(posortowanaListaTras.get(0).getListaPunktowNaMapie().size()-1))) {
            LinkedList<MiejsceZmianyKierunku> badanyElement = posortowanaListaTras.get(0).getListaPunktowNaMapie();
            if (uzyskiwanieListTrasBezPowtorzeniaElementu(koniecTrasy, typDrogi, listaTras, posortowanaListaTras, badanyElement)) {
//                System.out.println("Dlugosc " + posortowanaListaTras.get(0).getDlugosc());
                return badanyElement;
            }
            listaTras.remove(posortowanaListaTras.get(0));
        }
//        System.out.println("Nie znaleziono trasy");
        return null;
    }

    private boolean uzyskiwanieListTrasBezPowtorzeniaElementu(MiejsceZmianyKierunku koniecTrasy, Object typDrogi, ObservableList<Trasowanie> listaTras, SortedList<Trasowanie> posortowanaListaTras, LinkedList<MiejsceZmianyKierunku> badanyElement) {
        if ( badanyElement.getLast() == koniecTrasy) {
//            System.out.println("Znaleziono Trase jej dlugosc to: " + posortowanaListaTras.get(0).getDlugosc());
//            for (int i = 0; i <  badanyElement.size(); i++) {
//                System.out.println("Punkt " + i + " " +  badanyElement.get(i).getNazwa());
//            }
            return true;
        }
//        if( badanyElement.getLast() == null){
//            return false;
//        }
        for (int i = 0; i <  badanyElement.getLast().getListaDrog().size(); i++) {
//            System.out.println("Trasa ");
//            for (int j = 0; j <  badanyElement.size(); j++) {
//                System.out.printf( badanyElement.get(j).getNazwa()+ " ");
//            }
//            System.out.println("");
            if ( badanyElement.contains( badanyElement.getLast().getListaDrog().get(i).getKoniec())){
                continue;
            }
            if (typDrogi.getClass().isInstance( badanyElement.getLast().getListaDrog().get(i))){
                Trasowanie nowyElement = new Trasowanie();
//                    System.out.println(i + "punkt: " +"poczatek: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getPoczatek().getNazwa() + " koniec: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getKoniec().getNazwa());
                for (int j = 0; j <  badanyElement.size(); j++) {
                    nowyElement.addCopyListaPunktowNaMapie( badanyElement.get(j));
                }
                nowyElement.setDlugosc(posortowanaListaTras.get(0).getDlugosc());
                nowyElement.addListaPunktowNaMapie( badanyElement.getLast().getListaDrog().get(i).getKoniec(),  badanyElement.getLast().getListaDrog().get(i).getOdleglosc());
                listaTras.add(nowyElement);
            }
        }
        return false;
    }


    /**
     *
     * @return
     */
    public MiejsceZmianyKierunku getObecnePolozenie() {
        return obecnePolozenie;
    }

    public MiejsceZmianyKierunku getNastepnyPrzystanek() {
        return nastepnyPrzystanek;
    }

    public void setNastepnyPrzystanek(MiejsceZmianyKierunku nastepnyPrzystanek) {
        this.nastepnyPrzystanek = nastepnyPrzystanek;
    }

    /**
     * @return
     */
    public Przystanek getPrzystanekDocelowy() {
        return przystanekDocelowy;
    }

    public Przystanek getPrzystanekPoczatkowy() {
        return przystanekPoczatkowy;
    }

    public void setPrzystanekPoczatkowy(Przystanek przystanekPoczatkowy) {
        this.przystanekPoczatkowy = przystanekPoczatkowy;
    }

    public double getImagePromien() {
        return imagePromien;
    }

    public void setImagePromien(double imagePromien) {
        this.imagePromien = imagePromien;
    }

    public void setIdentyfikator(UUID identyfikator) {
        this.identyfikator = identyfikator;
    }

    public void setMaksymalnaPredkosc(int maksymalnaPredkosc) {
        this.maksymalnaPredkosc = maksymalnaPredkosc;
    }

    public void setObecnePolozenie(MiejsceZmianyKierunku obecnePolozenie) {
        this.obecnePolozenie = obecnePolozenie;
    }

    public void setLadunek(TypLadunku ladunek) {
        Ladunek = ladunek;
    }

    public void setPrzystanekDocelowy(Przystanek przystanekDocelowy) {
        this.przystanekDocelowy = przystanekDocelowy;
    }

    public void setObecnePolozenie(Przystanek obecnePolozenie) {
        this.obecnePolozenie = obecnePolozenie;
    }

    /**
     * Konstruktor klasy pojazd, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc okresla dlugosc obrazu zwiazanego z obiektem.
     * @param szerokosc okresla szerokosc obrazu zwiazanego z obiektem.
     * @param maksymalnaPredkosc okresla maksymalna predkosc, z ktora porusza sie pojazd.
     */
    public Pojazd(double dlugosc, double szerokosc, double maksymalnaPredkosc) {
        super(dlugosc, szerokosc);
        this.imagePromien=this.pitagoras(this.getSzerokosc()/2,this.getWysokosc()/2);
        this.identyfikator = UUID.randomUUID();
        this.maksymalnaPredkosc = maksymalnaPredkosc;
        Random random = new Random();
        this.maksymalnaPredkosc=random.nextInt(5)+2;
        Swiat.getInstance().addPojazd(this);
        Runnable runner = this;
        thread = new Thread(runner);
//        threadIsAlive=true;
        thread.start();
//        Swiat.getInstance().getListaRunnable().add(runner);
        Swiat.getInstance().getListaThread().add(thread);
        okreslNowePolozenie(this.getMozliweLadowania());
        tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(),this.getTypDrogi());
//        wypisywanieTrasy(this.getTrasa());
//        System.out.println("Pozostala trasa od poczatku");
//        wypisywanieTrasy(this.getPozostalaTrasa());
//        this.getObecnePolozenie().addPojazdOczekujacy(this);
        this.przystanekPoczatkowy.addPojazdZaparkowany(this);
        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));
//        this.nastepnaDroga();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getImageNode().visibleProperty().setValue(false);
            }
        });
    }

    /**
     * Pusty konstruktor klasy pojazd.
     */
    public Pojazd(){

    }

    public List<MiejsceZmianyKierunku> getTrasa() {
        return trasa;
    }

    public void setTrasa(List<MiejsceZmianyKierunku> trasa) {
        this.trasa = trasa;
    }

    public void addPunktTrasy(MiejsceZmianyKierunku miejsceZmianyKierunku){
        this.trasa.add(miejsceZmianyKierunku);
    }

    public void removePunktTrasy(MiejsceZmianyKierunku miejsceZmianyKierunku){
        this.trasa.remove(miejsceZmianyKierunku);
    }

    public double getMaksymalnaPredkosc() {
        return maksymalnaPredkosc;
    }

    public UUID getIdentyfikator() {
        return identyfikator;
    }

    public Droga getDrogaTeraz() {
        return drogaTeraz;
    }

    public void setDrogaTeraz(Droga drogaTeraz) {
        this.drogaTeraz = drogaTeraz;
    }

    public void nastepnaDroga(){
        if( this.pozostalaTrasa == null || this.pozostalaTrasa.size()<2){
            return;
        }
        for (int i = 0; i < this.pozostalaTrasa.get(0).getListaDrog().size(); i++) {
            if(this.pozostalaTrasa.get(0).getListaDrog().get(i).getKoniec()==this.pozostalaTrasa.get(1)){
                this.drogaTeraz=this.pozostalaTrasa.get(0).getListaDrog().get(i);
//                this.drogaTeraz.addListaPojazdow(this);
                this.steps=drogaTeraz.iloscKrokow(this.statusPrzelotu);
                this.getDrogaTeraz().addListaPojazdow(this);
                return;
            }
        }
//        this.steps = this.drogaTeraz.getOdleglosc()/ this.maksymalnaPredkosc;
//        this.steps=this.drogaTeraz.getOdleglosc();
    }

    public void ladowanie(){
//        Random random = new Random();
        this.oczekiwanie=5*fps;
    }

    public void obslugaRuchu(){
        switch (this.statusPrzelotu){
            case 0:
//                System.out.println("TUTAJ");
//                this.nastepnaDroga();
//                this.steps=drogaTeraz.iloscKrokow(this.statusPrzelotu);
                this.obecnePolozenie.startowanie(this);
                break;
            case 1:
                this.steps=drogaTeraz.iloscKrokow(this.statusPrzelotu);
                poruszSie();
                break;
            case 2:
                this.steps=drogaTeraz.iloscKrokow(this.statusPrzelotu);
                this.pozostalaTrasa.get(1).ladowanie(this);
                break;
        }

    }

    public double pitagoras(double a, double b){
        return Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
    }

    public void poruszSie(){
        double sinDrogi = this.drogaTeraz.sinusDrogi(this.statusPrzelotu);
        double cosDrogi = this.drogaTeraz.cosinusDrogi(this.statusPrzelotu);
        double moveX=0.0;
        double moveY=0.0;
        if(this.pozostalaTrasa.size()>1){
            this.odlegloscDoKonca = this.pitagoras(this.getPolozenieX()-this.pozostalaTrasa.get(1).getPolozenieX(),this.getPolozenieY()-this.pozostalaTrasa.get(1).getPolozenieY());
        }
        else{
            this.odlegloscDoKonca = this.pitagoras(this.getPolozenieX()-this.pozostalaTrasa.get(0).getPolozenieX(),this.getPolozenieY()-this.pozostalaTrasa.get(0).getPolozenieY());
        }
        while(this.steps!=0.0) {
            try {
                if(threadIsAlive==false){
                    return;
                }
                Thread.sleep(1000 / fps);

                if (this.steps < this.getMaksymalnaPredkosc()) {
                    moveX = this.steps * sinDrogi;
                    moveY = this.steps * cosDrogi;
//                    this.steps = 0.0;
                } else {
                    moveX = this.getMaksymalnaPredkosc() * sinDrogi;
                    moveY = this.getMaksymalnaPredkosc() * cosDrogi;
//                    this.steps -= this.getMaksymalnaPredkosc();
                }
                double przesuniecie = this.pitagoras(moveX,moveY);
                if(this.drogaTeraz.czyDojdzieDoZderzenia(this,przesuniecie)==true){
                    continue;
                }
                if(this.steps < this.getMaksymalnaPredkosc()){
                    this.steps=0.0;
                }else{
                    this.steps-=this.getMaksymalnaPredkosc();
                }
                this.setPolozenieX(this.getPolozenieX() + moveX);
                this.setPolozenieY(this.getPolozenieY() + moveY);
                this.odlegloscDoKonca=this.odlegloscDoKonca - przesuniecie;
                if(this.odlegloscDoKonca < 0.0){
                    this.odlegloscDoKonca = 0.0;
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        getImageNode().setLayoutX(getPolozenieX()-getSzerokosc()/2);
                        getImageNode().setLayoutY(getPolozenieY()-getWysokosc()/2);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.statusPrzelotu++;
        this.statusPrzelotu=this.statusPrzelotu % 3;
    }
// public void ruszSie(){
////        if(this.getImageNode()!=null){
//        if(this.steps==0.0){
//            if(this.statusPrzelotu<3){
//                this.statusPrzelotu+=1;
//                if(this.statusPrzelotu==2){
//
//                }
//                this.steps=drogaTeraz.iloscKrokow(this.statusPrzelotu);
//            }
//            else{
//                this.statusPrzelotu=-1;
//                this.zmienTor();
//            }
//            return;
//        }
//        double moveX;
//        double moveY;
//        double sinDrogi = this.drogaTeraz.sinusDrogi(this.statusPrzelotu);
//        double cosDrogi = this.drogaTeraz.cosinusDrogi(this.statusPrzelotu);
//        if(this.steps<this.getMaksymalnaPredkosc()){
//            moveX = this.steps* sinDrogi;
//            moveY = this.steps* cosDrogi;
//            this.steps=0.0;
//        }
//        else {
//            moveX = this.getMaksymalnaPredkosc() * sinDrogi;
//            moveY = this.getMaksymalnaPredkosc() * cosDrogi;
////            this.steps--;
//            this.steps-=this.getMaksymalnaPredkosc();
//        }
//        this.setPolozenieX(this.getPolozenieX() + moveX);
//        this.setPolozenieY(this.getPolozenieY() + moveY);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                getImageNode().setLayoutX(getPolozenieX());
//                getImageNode().setLayoutY(getPolozenieY());
//            }
//        });
//    }
//
//    public void zmienTor(){
//        this.setObecnePolozenie(this.getPozostalaTrasa().get(1));
//        this.getPozostalaTrasa().remove(0);
//        this.nastepnaDroga();
//    }


//    public void okreslNowePolozenie(List<Przystanek> listaMozliwychPrzystankow){
    public void okreslNowePolozenie(List<MiejsceZmianyKierunku> listaMozliwychPrzystankow){
        if (listaMozliwychPrzystankow.size() !=0) {
            List<Przystanek> listaLokalizacji = new LinkedList<Przystanek>();
            List<Przystanek> listaPrzystankow = Swiat.getInstance().getListaPrzystankow();
            for (int i = 0; i < listaPrzystankow.size(); i++) {
                if(czyMozeLadowac(listaPrzystankow.get(i), listaMozliwychPrzystankow)){
                    listaLokalizacji.add(listaPrzystankow.get(i));
                }
            }

//            System.out.println("lista lokalizacji: "+listaLokalizacji.size());
            Random random = new Random();
            this.setPrzystanekPoczatkowy(listaLokalizacji.get(random.nextInt(listaLokalizacji.size())));
            this.setObecnePolozenie(this.getPrzystanekPoczatkowy());
            listaLokalizacji.remove(this.getPrzystanekPoczatkowy());
//            for (int i = 0; i < listaMozliwychPrzystankow.size(); i++) {
//                System.out.println("przystanek " + i + ": " +listaMozliwychPrzystankow.get(i).getNazwa());
//            }
            this.setPrzystanekDocelowy(listaLokalizacji.get(random.nextInt(listaLokalizacji.size())));
            this.getPrzystanekPoczatkowy().addPojazdZaparkowany(this);
            this.setPolozenieX(this.getObecnePolozenie().getPolozenieX());
            this.setPolozenieY(this.getObecnePolozenie().getPolozenieY());
            this.getObecnePolozenie().addPojazdOczekujacy(this);
//            this.nastepnaDroga();
        }
    }


//    public Przystanek nastepnyPrzystanekZTrasy(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie, List<MiejsceZmianyKierunku> listaGdzieMozeLadowac) {
//        if(trasa.size()>1){
//            for (int i = 1; i < trasa.size(); i++) {
//                if(trasa.get(i) instanceof Przystanek) {
//                    if (czyMozeLadowac(trasa.get(i), listaGdzieMozeLadowac) == true) {
//                        return (Przystanek) trasa.get(i);
//                    }
//                }
//            }
//        }
//        return (Przystanek) trasa.get(0);
//    }

    public Przystanek nastepnyPrzystanekZTrasy(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie, List<MiejsceZmianyKierunku> ListaGdzieMozeLadowac) {
        int temp;
//        for (temp = 0;(temp<trasa.size()) &&  (trasa.get(temp) != obecnePolozenie); temp++) {
////            System.out.println("Nie ma takiego miejsca na trasie");
//        }
        if((temp=trasa.indexOf(obecnePolozenie))== -1){
//            return null;
            return (Przystanek) trasa.get(0);
        }
        for (int i = temp+1; i < trasa.size(); i++) {
            if (trasa.get(i) instanceof Przystanek) {
                if(czyMozeLadowac(trasa.get(i),ListaGdzieMozeLadowac)) {
                    return (Przystanek) trasa.get(i);
                }
            }
        }
        return null;
    }

    public abstract Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie);


    public List<Przystanek> listaOdwiedzanychPrzystankow(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie){
//        System.out.println("here");
//        LinkedList<MiejsceZmianyKierunku> listaTymczasowaTras = new LinkedList<MiejsceZmianyKierunku>();
//        listaTymczasowaTras.addAll(this.trasa);
        Przystanek obecnyPrzystanek = null;
        if (trasa.isEmpty()){
            return null;
        }
        LinkedList<Przystanek> listaPrzystankow = new LinkedList<Przystanek>();
        if(obecnePolozenie instanceof Przystanek){
            obecnyPrzystanek = (Przystanek) obecnePolozenie;
        }
        else{
            obecnyPrzystanek = nastepneMozliweLadowanie(trasa,obecnePolozenie);
        }
        while(obecnyPrzystanek != null) {
            listaPrzystankow.add(obecnyPrzystanek);
//            obecnyPrzystanek=nastepnyPrzystanekZTrasy(trasa,obecnyPrzystanek,listaObjektow);
            obecnyPrzystanek = nastepneMozliweLadowanie(trasa,obecnyPrzystanek);
        }
        return listaPrzystankow;
    }

    public void rezygnacjaPrzyjazdu(List<Przystanek> listaPrzystankow){
        if(listaPrzystankow == null){
            return;
        }
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            listaPrzystankow.get(i).removePojazdPrzyjezdzajacy(this);
        }
//        System.out.println("Przystanki " + listaPrzystankow.size());
//        for (int i = 0; i < listaPrzystankow.size(); i++) {
//            System.out.printf(listaPrzystankow.get(i).getNazwa() + " ");
//        }
//        System.out.println("");
    }

    public void poinformujORezygnacjiPrzyjazdu(List<MiejsceZmianyKierunku> wczesniejszaTrasa){
        rezygnacjaPrzyjazdu(this.listaOdwiedzanychPrzystankow(wczesniejszaTrasa, this.getObecnePolozenie()));
    }


    public void zamiarPrzyjezdzu(List<Przystanek> listaPrzystankow){
//        LinkedList<Przystanek> listaPrzystankow = new LinkedList<Przystanek>();
//        listaPrzystankow.addAll(listaOdwiedzanychPrzystankow(this.trasa,(Przystanek) this.trasa.get(0),listaObjektow));
//        listaPrzystankow.remove(0);
        if(listaPrzystankow == null){
            return;
        }
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            listaPrzystankow.get(i).addPojazdPrzyjezdzajacy(this);
        }
        System.out.println("Przystanki " + listaPrzystankow.size());
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            System.out.printf(listaPrzystankow.get(i).getNazwa() + " ");
        }
        System.out.println("");
    }

    public void poinformujOZamiarzePrzyjazdu(List<MiejsceZmianyKierunku> obecnaTrasa){
        this.zamiarPrzyjezdzu(listaOdwiedzanychPrzystankow(obecnaTrasa,this.getObecnePolozenie()));
    }


    public boolean czyMozeLadowac(Object doSprawdzenia,List<MiejsceZmianyKierunku> listaGdzieMozeLadowac){
        for (int i = 0; i < listaGdzieMozeLadowac.size(); i++) {
            if(listaGdzieMozeLadowac.get(i).getClass().isInstance(doSprawdzenia)){
                return true;
            }
        }
        return false;
    }
    public boolean czyMozeTutajLadowac(Object doSprawdzenia){
        return czyMozeLadowac(doSprawdzenia,this.getMozliweLadowania());
    }

    public double okreslanieDlugosciTrasy(MiejsceZmianyKierunku obecnePolozenieNaTrasie, MiejsceZmianyKierunku ostatecznePolozenieNaTrasie, List<MiejsceZmianyKierunku> trasaMiedzyDwomaPunktami){
        double dlugosc=0.0;
        int temp;
//        if (!(trasaMiedzyDwomaPunktami.contains(obecnePolozenieNaTrasie)) || (!trasaMiedzyDwomaPunktami.contains(ostatecznePolozenieNaTrasie))){
//            System.out.println("Bledne polozenia");
//            return 0;
//        }
//        for (temp = 0;( temp < trasaMiedzyDwomaPunktami.size() && (trasaMiedzyDwomaPunktami.get(temp) != obecnePolozenieNaTrasie)); temp++) {
//            System.out.println("Nie ten punkt");
//        }
        temp = trasaMiedzyDwomaPunktami.indexOf(obecnePolozenieNaTrasie);
        for (int i = temp+1; i < trasaMiedzyDwomaPunktami.size() ; i++) {
            dlugosc=dlugosc + Math.sqrt(Math.pow(obecnePolozenieNaTrasie.getPolozenieX()-trasaMiedzyDwomaPunktami.get(i).getPolozenieX(),2.0) + Math.pow(obecnePolozenieNaTrasie.getPolozenieY()-trasaMiedzyDwomaPunktami.get(i).getPolozenieY(),2.0));
            obecnePolozenieNaTrasie=trasaMiedzyDwomaPunktami.get(i);
            if (trasaMiedzyDwomaPunktami.get(i)==ostatecznePolozenieNaTrasie){
                return dlugosc;
            }
        }
        return 0;
    }

    public void wypisywanieTrasy(List<MiejsceZmianyKierunku> trasa){
        System.out.println("Trasa:");
        for (int i = 0; i < trasa.size(); i++) {
            System.out.printf(" " + trasa.get(i).getNazwa());
        }
        System.out.println("");
    }


    public void odwrocTrase(){
        Przystanek poczatek = this.getPrzystanekDocelowy();
        this.pozostalaTrasa.clear();
        this.przystanekDocelowy=this.getPrzystanekPoczatkowy();
        this.przystanekPoczatkowy=poczatek;
        Collections.reverse(this.trasa);
        this.pozostalaTrasa.addAll(this.trasa);
    }

//    public abstract void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy);
    public void tworzenieTrasy(MiejsceZmianyKierunku przystanekPoczatkowy, MiejsceZmianyKierunku przystanekDocelowy, Droga typDrogi){
        this.poinformujORezygnacjiPrzyjazdu(this.getTrasa());
        this.getTrasa().clear();
        this.getPozostalaTrasa().clear();
        this.setTrasa(szukanieTrasy(przystanekPoczatkowy,przystanekDocelowy,typDrogi));
        if (this.getTrasa()==null){
            return;
        }
        this.getPozostalaTrasa().addAll(this.getTrasa());
        this.poinformujOZamiarzePrzyjazdu(this.getTrasa());
        this.setNastepnyPrzystanek(this.nastepneMozliweLadowanie(this.getTrasa(),this.getObecnePolozenie()));

    }
    @Override
    public void rysuj(Group group) {
        Rectangle rectangle = new Rectangle(this.getSzerokosc(),this.getWysokosc());
        rectangle.setStroke(this.tymczasowyKolor);
        rectangle.setFill(this.tymczasowyKolor);
        rectangle.setLayoutX(this.getPolozenieX()-this.getSzerokosc()/2);
        rectangle.setLayoutY(this.getPolozenieY()-this.getWysokosc()/2);
        rectangle.setOnMouseClicked(event -> {
            Informacja.getInstance().setObecnaInformacja(this);
        });
        this.setImageNode(rectangle);
        group.getChildren().add(this.getImageNode());
    }

    @Override
    public abstract List<MiejsceZmianyKierunku> getMozliweLadowania();

    @Override
    public abstract Droga getTypDrogi();


    @Override
    public void run() {
        threadIsAlive=true;
        while (threadIsAlive==true) {
            try {
//                System.out.println("WLASNIE TUTAJ");
//                if(MainPanel.beginning==true) {
//                if(this.oczekiwanie!=0) {
                    if (!this.pozostalaTrasa.isEmpty()) {
                        if (this.getObecnePolozenie() == this.getPrzystanekDocelowy()) {
                            this.odwrocTrase();
                        }
//                        System.out.println("HELLO");
                        if(this.oczekiwanie==0.0) {
//                            System.out.println("HEY");
                            this.obslugaRuchu();
//                        this.poruszSie();
                        }
                        else{
                            this.oczekiwanie--;
                        }

                }
                    Thread.sleep(1000 / fps);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int showInfo(int rowCount){
        List<Control> listaNodow = new ArrayList<Control>();
        listaNodow.addAll(this.potrzebneInformacje());
        Controller controller = MainPanel.getLoader().getController();
        if(controller.getGrid().getChildren().size()==listaNodow.size()){
            listaNodow.clear();
            return rowCount;
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.getGrid().getChildren().clear();
                for (int i = 0; i < listaNodow.size(); i++) {
                    controller.getGrid().add(listaNodow.get(i), 0, i);
                }
            }
        });
        return listaNodow.size();
    }

    public void usuwanie(){
        this.poinformujORezygnacjiPrzyjazdu(this.trasa);
        if(this.obecnePolozenie instanceof Przystanek){
            Przystanek przystanek = (Przystanek) this.obecnePolozenie;
            if(przystanek.getListaPojazdowZaparkowanych().contains(this)){
                przystanek.getListaPojazdowZaparkowanych().remove(this);
            }
        }
        Swiat.getInstance().getListaPojazdow().remove(this);
        MainPanel.getGrupaPojazdow().getChildren().remove(this.getImageNode());
        if(this.drogaTeraz!=null){
            this.drogaTeraz.removeListaPojazdow(this);
        }
        Informacja.getInstance().wyczysc();
        Swiat.getInstance().getListaThread().remove(thread);
        threadIsAlive=false;
    }
    
    
    public List<Control> potrzebneInformacje(){
        List<Control> listaNodow = new ArrayList<Control>();
        Button buttonZmianaTrasy = new Button("Zmien trase");
        buttonZmianaTrasy.setOnMouseClicked(event -> {
            this.zmienDotychczasowaTrase();
        });
        listaNodow.add(buttonZmianaTrasy);
        Button  buttonUsunPojazd = new Button("Usun pojazd");
        buttonUsunPojazd.setOnMouseClicked(event -> {
            this.usuwanie();
        });
        listaNodow.add(buttonUsunPojazd);
        ShowLabel label1 = new ShowLabel("Identyfikator:");
        listaNodow.add(label1);
        ShowLabel label2 = new ShowLabel("  " + this.identyfikator.toString(),this);
        listaNodow.add(label2);
        ShowLabel label5 = new ShowLabel("Przystanek poczatkowy:");
        listaNodow.add(label5);
        ShowLabel label6 = new ShowLabel("  " +this.getPrzystanekPoczatkowy().getNazwa(),this.getNastepnyPrzystanek());
        listaNodow.add(label6);
        ShowLabel label11 = new ShowLabel("Obecne polozenie:");
        listaNodow.add(label11);
        ShowLabel label12 = new ShowLabel(" "+this.obecnePolozenie.getNazwa(),this.getObecnePolozenie());
        listaNodow.add(label12);
        ShowLabel label7 = new ShowLabel("Przystanek koncowy:");
        listaNodow.add(label7);
        ShowLabel label8 = new ShowLabel("  " + this.getPrzystanekDocelowy().getNazwa(),this.getPrzystanekDocelowy());
        listaNodow.add(label8);
        ShowLabel label13 = new ShowLabel("Koniec obecnej drogi:");
        listaNodow.add(label13);
        ShowLabel label14 = new ShowLabel(" " + this.getDrogaTeraz().getKoniec().getNazwa());
        listaNodow.add(label14);
        ShowLabel label9 = new ShowLabel("Obecna Trasa:");
        listaNodow.add(label9);
        for (int i = 1; i < this.pozostalaTrasa.size(); i++) {
            ShowLabel label = new ShowLabel("   " + this.pozostalaTrasa.get(i).getNazwa(),this.pozostalaTrasa.get(i));
            listaNodow.add(label);
        }
//        ShowLabel label10 = new ShowLabel("");
//        listaLabeli.add(label10);
        return listaNodow;
    }
}
