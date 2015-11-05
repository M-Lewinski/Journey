package Mapa;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Mapa.ZmianyKierunku.Skrzyzowanie;
import Pasazerowie.GeneratorPasazerow;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.SamolotPasazerski;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * Klasa swiat, ktora implementuje obiekt calego swiata. Przechowuje on wszystkie listy obiektow oraz tworzy mape.
 *
 */
public class Swiat extends ObiektGraficzny {
    /**
     * instancja klasy swiat.
     */
    private static Swiat instance = null;
    /**
     * lista wszystkich pasazerow.
     */
    private List<Pasazer> listaPasazerow = new ArrayList<Pasazer>();
    /**
     * lista wszystkich drog.
     */
    private List<Droga> listaDrog = new ArrayList<Droga>();
    /**
     * lista wszystkich pojazdow.
     */
    private List<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    /**
     * lista wszystkich miejsc, w ktorych mozemy zmienic kierunek poruszania sie.
     */
    private List<MiejsceZmianyKierunku> listaMiejscZmianyKierunku = new ArrayList<MiejsceZmianyKierunku>();
    /**
     * lista wszystkich przystankow.
     */
    private List<Przystanek> listaPrzystankow = new ArrayList<Przystanek>();
    /**
     * lista wszystkich pasazerow bez trasy.
     */
    private List<Pasazer> listaPasazerowBezTrasy = new ArrayList<Pasazer>();

    /**
     * Konstruktor klasy swiat, ktory tworzy mape poprzez stworzenie drog, przystankow i skrzyzowan.
     */
    public Swiat() {
        System.out.println("\nPoczatek Swiata!\n");
//        LotniskoCywilne lotniskoCywilne1 = new LotniskoCywilne("A",100,100,72,101,false,10);
////        LotniskoCywilne lotniskoCywilne2 = new LotniskoCywilne("B",100,100,280,80,false,10);
//        LotniskoCywilne lotniskoCywilne3 = new LotniskoCywilne("C",100,100,164,130,false,10);
////        LotniskoCywilne lotniskoCywilne4 = new LotniskoCywilne("D",100,100,254,190,false,10);
//        LotniskoCywilne lotniskoCywilne5 = new LotniskoCywilne("E",100,100,66,270,false,10);
////        LotniskoCywilne lotniskoCywilne6 = new LotniskoCywilne("F",100,100,288,292,false,10);
////        LotniskoCywilne lotniskoCywilne7 = new LotniskoCywilne("G",100,100,120,431,false,10);
//        Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie("S1",50,50,67,185,false);
//        Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie("S2",50,50,122,169,false);
////        Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie("S3",50,50,162,232,false);
////        Skrzyzowanie skrzyzowanie4 = new Skrzyzowanie("S4",50,50,295,188,false);
////        Skrzyzowanie skrzyzowanie5 = new Skrzyzowanie("S5",50,50,214,277,false);
//        DrogaPowietrzna drogaPowietrzna1 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie1);
//        DrogaPowietrzna drogaPowietrzna2 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne1);
//        DrogaPowietrzna drogaPowietrzna3 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie2);
//        DrogaPowietrzna drogaPowietrzna4 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne1);
//        DrogaPowietrzna drogaPowietrzna5 = new DrogaPowietrzna(skrzyzowanie1,skrzyzowanie2);
//        DrogaPowietrzna drogaPowietrzna6 = new DrogaPowietrzna(skrzyzowanie2,skrzyzowanie1);
//        DrogaPowietrzna drogaPowietrzna7 = new DrogaPowietrzna(lotniskoCywilne3,skrzyzowanie2);
//        DrogaPowietrzna drogaPowietrzna8 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne3);
//        DrogaPowietrzna drogaPowietrzna9 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne5);
//        DrogaPowietrzna drogaPowietrzna10 = new DrogaPowietrzna(lotniskoCywilne5,skrzyzowanie1);


//        LotniskoCywilne miasto1 = new LotniskoCywilne("Lawica",100,100,100,100,false,10);
//        listaPrzystankow.add(miasto1);
//        Pasazer pasazer1 = new Pasazer();
//        System.out.println("Imie pasazera:"+pasazer1.getImie());
    }

    /**
     * tworzenie instancji swiata.
     * @return instancja swiata.
     */
    public synchronized static Swiat getInstance(){
        if(instance == null){
            instance = new Swiat();
        }
        return instance;
    }

    public List<Przystanek> getListaPrzystankow() {
        return listaPrzystankow;
    }

    public void setListaPrzystankow(List<Przystanek> listaPrzystankow) {
        this.listaPrzystankow = listaPrzystankow;
    }

    public void addPrzystanek(Przystanek przystanek){
        this.listaPrzystankow.add(przystanek);
    }

    public void removePrzystanek(Przystanek przystanek){
        this.listaPrzystankow.remove(przystanek);
    }

    public List<Pasazer> getListaPasazerow() {
        return listaPasazerow;
    }

    public void setListaPasazerow(List<Pasazer> listaPasazerow) {
        this.listaPasazerow = listaPasazerow;
    }

    public List<Droga> getListaDrog() {
        return listaDrog;
    }

    public void setListaDrog(List<Droga> listaDrog) {
        this.listaDrog = listaDrog;
    }

    public List<Pojazd> getListaPojazdow() {
        return listaPojazdow;
    }

    public void setListaPojazdow(List<Pojazd> listaPojazdow) {
        this.listaPojazdow = listaPojazdow;
    }

    public List<MiejsceZmianyKierunku> getListaMiejscZmianyKierunku() {
        return listaMiejscZmianyKierunku;
    }

    public void setListaMiejscZmianyKierunku(List<MiejsceZmianyKierunku> listaMiejscZmianyKierunku) {
        this.listaMiejscZmianyKierunku = listaMiejscZmianyKierunku;
    }

    public List<Pasazer> getListaPasazerowBezTrasy() {
        return listaPasazerowBezTrasy;
    }

    public void setListaPasazerowBezTrasy(List<Pasazer> listaPasazerowBezTrasy) {
        this.listaPasazerowBezTrasy = listaPasazerowBezTrasy;
    }

    /**
     * poinformowac pasazerow bez trasy, ze doszlo do zmiany w trasach, ktorymi poruszaja sie pojazdy.
     */
    public void poinformuj(){

    }

    public void addPasazerBezTrasy(Pasazer pasazer){
        this.listaPasazerowBezTrasy.add(pasazer);
    }
    public void addPasazer(Pasazer pasazer){
        this.listaPasazerow.add(pasazer);
    }

    public void addPojazd(Pojazd pojazd){
        this.listaPojazdow.add(pojazd);
    }
    public void addDroga(Droga droga){
        this.listaDrog.add(droga);
    }

    public void addMiejsceZmianyKierunku(MiejsceZmianyKierunku miejsceZmianyKierunku){
        this.listaMiejscZmianyKierunku.add(miejsceZmianyKierunku);
    }

    public void removePasazerBezTrasy(Pasazer pasazer){
        this.listaPasazerowBezTrasy.remove(pasazer);
    }

    public void removePasazer(Pasazer pasazer){
        this.listaPasazerow.remove(pasazer);
    }

    public void removePojazd(Pojazd pojazd){
        this.listaPojazdow.remove(pojazd);
    }

    public void removeDroga(Droga droga){
        this.listaDrog.remove(droga);
    }

    public void removeMiejsceZmianyKierunku(MiejsceZmianyKierunku miejsceZmianyKierunku){
        this.listaMiejscZmianyKierunku.remove(miejsceZmianyKierunku);
    }

    public static void main(String[] args) {
//        System.out.println("Poczatek Swiata!\n");
        Swiat.getInstance();
        LotniskoCywilne lotniskoCywilne1 = new LotniskoCywilne("A",100,100,72,101,false,10);
//        LotniskoCywilne lotniskoCywilne2 = new LotniskoCywilne("B",100,100,280,80,false,10);
        LotniskoCywilne lotniskoCywilne3 = new LotniskoCywilne("C",100,100,164,130,false,10);
//        LotniskoCywilne lotniskoCywilne4 = new LotniskoCywilne("D",100,100,254,190,false,10);
        LotniskoCywilne lotniskoCywilne5 = new LotniskoCywilne("E",100,100,66,270,false,10);
//        LotniskoCywilne lotniskoCywilne6 = new LotniskoCywilne("F",100,100,288,292,false,10);
//        LotniskoCywilne lotniskoCywilne7 = new LotniskoCywilne("G",100,100,120,431,false,10);
        Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie("S1",50,50,67,185,false);
        Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie("S2",50,50,122,169,false);
//        Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie("S3",50,50,162,232,false);
//        Skrzyzowanie skrzyzowanie4 = new Skrzyzowanie("S4",50,50,295,188,false);
//        Skrzyzowanie skrzyzowanie5 = new Skrzyzowanie("S5",50,50,214,277,false);
        DrogaPowietrzna drogaPowietrzna1 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie1);
        DrogaPowietrzna drogaPowietrzna2 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne1);
        DrogaPowietrzna drogaPowietrzna3 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna4 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne1);
        DrogaPowietrzna drogaPowietrzna5 = new DrogaPowietrzna(skrzyzowanie1,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna6 = new DrogaPowietrzna(skrzyzowanie2,skrzyzowanie1);
        DrogaPowietrzna drogaPowietrzna7 = new DrogaPowietrzna(lotniskoCywilne3,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna8 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne3);
        DrogaPowietrzna drogaPowietrzna9 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne5);
        DrogaPowietrzna drogaPowietrzna10 = new DrogaPowietrzna(lotniskoCywilne5,skrzyzowanie1);

        System.out.println("ilosc drog " + Swiat.getInstance().getListaDrog().size());
        for (int i = 0; i < Swiat.getInstance().getListaDrog().size() ; i++) {
            System.out.println(Swiat.getInstance().getListaDrog().get(i).getPoczatek().getNazwa());
        }
        SamolotPasazerski samolot1 = new SamolotPasazerski(100,100)
//        Pasazer pasazer = new Pasazer();
//        pasazer.outconsole();
//        double odleglosc = Math.sqrt(Math.pow(8,2) + Math.pow(6,2.0));
//        System.out.printf("%.2f",odleglosc);

    }
}
