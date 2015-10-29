package Mapa;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pasazerowie.GeneratorPasazerow;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

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
        System.out.println("Poczatek Swiata");
        LotniskoCywilne miasto1 = new LotniskoCywilne("Lawica",100,100,100,100,false,10);
        listaPrzystankow.add(miasto1);
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
        System.out.println("Poczatek Swiata!\n");
        Swiat.getInstance();
        Pasazer pasazer = new Pasazer();
        pasazer.outconsole();
    }
}
