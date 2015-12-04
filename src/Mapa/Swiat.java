package Mapa;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Mapa.ZmianyKierunku.Przystanki.*;
import Mapa.ZmianyKierunku.Skrzyzowanie;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Wodne.Lotniskowiec;
import com.sun.media.sound.MidiInDeviceProvider;

import java.util.*;

import static javafx.application.Application.launch;

/**
 * Klasa swiat, ktora implementuje obiekt calego swiata. Przechowuje on wszystkie listy obiektow oraz tworzy mape.
 *
 */
//public class Swiat extends ObiektGraficzny {
public class Swiat extends ObiektGraficzny {
    /**
     * instancja klasy swiat.
     */
    private static Swiat instance = null;
    /**
     * lista wszystkich pasazerow.
     */
    private List<Runnable> listaRunnable = new LinkedList<Runnable>();
    private List<Thread> listaThread = new LinkedList<Thread>();
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

    private List<Miasto> listaMiast = new ArrayList<Miasto>();

    private List<LotniskoCywilne> listaLotniskCywilnych = new ArrayList<LotniskoCywilne>();

    private List<LotniskoWojskowe> listaLotniskWojskowych = new ArrayList<LotniskoWojskowe>();

    private List<Port> listaPortow = new ArrayList<Port>();

    private  List<Lotniskowiec> listaLotniskowcow = new ArrayList<Lotniskowiec>();
    /**
     * Konstruktor klasy swiat, ktory tworzy mape poprzez stworzenie drog, przystankow i skrzyzowan.
     */
    public Swiat() {
        System.out.println("\nPoczatek Swiata!\n");
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

    public List<Thread> getListaThread() {
        return listaThread;
    }

    public void setListaThread(List<Thread> listaThread) {
        this.listaThread = listaThread;
    }

    public List<Runnable> getListaRunnable() {
        return listaRunnable;
    }

    public void setListaRunnable(List<Runnable> listaRunnable) {
        this.listaRunnable = listaRunnable;
    }

    public void addMiasto(Miasto miasto){
        this.listaMiast.add(miasto);
    }

    public void removeMiasto(Miasto miasto){
        this.listaMiast.remove(miasto);
    }

    public void addLotniskoCywilne(LotniskoCywilne lotniskoCywilne){
        this.listaLotniskCywilnych.add(lotniskoCywilne);
    }

    public List<Lotniskowiec> getListaLotniskowcow() {
        return listaLotniskowcow;
    }

    public void addLotniskowiec(Lotniskowiec lotniskowiec){
        listaLotniskowcow.add(lotniskowiec);
    }

    public void  removeLotniskowiec(Lotniskowiec lotniskowiec){
        listaLotniskowcow.remove(lotniskowiec);
    }

    public void setListaLotniskowcow(List<Lotniskowiec> listaLotniskowcow) {
        this.listaLotniskowcow = listaLotniskowcow;
    }

    public void removeLotniskoCywilne(LotniskoCywilne lotniskoCywilne){
        this.listaLotniskCywilnych.remove(lotniskoCywilne);
    }

    public void addLotniskoWojskowe(LotniskoWojskowe lotniskoWojskowe){
        this.listaLotniskWojskowych.add(lotniskoWojskowe);
    }

    public void removeLotniskoWojskowe(LotniskoWojskowe lotniskoWojskowe){
        this.listaLotniskWojskowych.remove(lotniskoWojskowe);
    }

    public void addPort(Port port){
        this.listaPortow.add(port);
    }

    public void removePort(Port port){
        this.listaPortow.remove(port);
    }

    public List<Miasto> getListaMiast() {
        return listaMiast;
    }

    public void setListaMiast(List<Miasto> listaMiast) {
        this.listaMiast = listaMiast;
    }

    public List<LotniskoCywilne> getListaLotniskCywilnych() {
        return listaLotniskCywilnych;
    }

    public void setListaLotniskCywilnych(List<LotniskoCywilne> listaLotniskCywilnych) {
        this.listaLotniskCywilnych = listaLotniskCywilnych;
    }

    public List<LotniskoWojskowe> getListaLotniskWojskowych() {
        return listaLotniskWojskowych;
    }

    public void setListaLotniskWojskowych(List<LotniskoWojskowe> listaLotniskWojskowych) {
        this.listaLotniskWojskowych = listaLotniskWojskowych;
    }

    public List<Port> getListaPortow() {
        return listaPortow;
    }

    public void setListaPortow(List<Port> listaPortow) {
        this.listaPortow = listaPortow;
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


    public void stworzSwiat(){
        //Swiat.getInstance();
        Miasto warszawa = new Miasto("Warszawa", 30,30, 50, 300, false, 10);
        Miasto berlin = new Miasto("Berlin", 30, 30, 560, 300, false, 10);
        Miasto topGun = new Miasto("Top gun",30,30,800,600,false,10);
        LotniskoCywilne airlineTycoon = new LotniskoCywilne("Airline Tycoon",30,30,120,440,false,10);
        LotniskoCywilne paryz = new LotniskoCywilne("Paryz", 30, 30, 700, 50, false, 10);
        LotniskoWojskowe sop = new LotniskoWojskowe("SOP",30,30,300,50,false,10);
        LotniskoWojskowe po = new LotniskoWojskowe("PO",30,30,250,560,false,10);
        LotniskoWojskowe pa = new LotniskoWojskowe("PA",30,30,800,300,false,10);
        LotniskoWojskowe wsi = new LotniskoWojskowe("WSI",30,30,520,50,false,10);
        LotniskoWojskowe cia = new LotniskoWojskowe("CIA",30,30,100,100,false,10);
        Port port1 = new Port("nike",30,30,360,550,false);
        Port port2 = new Port("addidas",30,30,800,400,false);
        Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie("S:Warszawa-Berlin", 30, 30, 300, 245, false);
        Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie("S:Berlin-Paryz", 30, 30, 660, 200, false);
        Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie("S:Berlin-Top gun",30,30,600,400,false);
        Skrzyzowanie skrzyzowanie4 = new Skrzyzowanie("S:PO-WSI",30,30,300,440,false);
        Skrzyzowanie skrzyzowanie5 = new Skrzyzowanie("S:PO-Airlne Tycoon",30,30,450,130,false);
        Skrzyzowanie skrzyzowanie6 = new Skrzyzowanie("S:nike-berlin",30,30,420,385,false);
        Skrzyzowanie skrzyzowanie7 = new Skrzyzowanie("S:nike-top gun",30,30,570,575,false);
        DrogaPowietrzna drogaPowietrzna1 = new DrogaPowietrzna(warszawa,skrzyzowanie1,0,0);
        DrogaPowietrzna drogaPowietrzna2 = new DrogaPowietrzna(skrzyzowanie1,warszawa,0,0);
        DrogaPowietrzna drogaPowietrzna5 = new DrogaPowietrzna(berlin,skrzyzowanie2,0,0);
        DrogaPowietrzna drogaPowietrzna6 = new DrogaPowietrzna(skrzyzowanie2,berlin,0,0);
        DrogaPowietrzna drogaPowietrzna7 = new DrogaPowietrzna(skrzyzowanie2,paryz,0,0);
        DrogaPowietrzna drogaPowietrzna8 = new DrogaPowietrzna(paryz,skrzyzowanie2,0,0);
        DrogaPowietrzna drogaPowietrzna9 = new DrogaPowietrzna(sop,skrzyzowanie5,0,0);
        DrogaPowietrzna drogaPowietrzna10 = new DrogaPowietrzna(skrzyzowanie5,sop,0,0);
        DrogaPowietrzna drogaPowietrzna11 = new DrogaPowietrzna(skrzyzowanie2,pa,0,0);
        DrogaPowietrzna drogaPowietrzna23 = new DrogaPowietrzna(pa,skrzyzowanie2,0,0);
        DrogaPowietrzna drogaPowietrzna12 = new DrogaPowietrzna(pa,skrzyzowanie3,0,0);
        DrogaPowietrzna drogaPowietrzna13 = new DrogaPowietrzna(skrzyzowanie3,pa,0,0);
        DrogaPowietrzna drogaPowietrzna14 = new DrogaPowietrzna(berlin,skrzyzowanie3,0,0);
        DrogaPowietrzna drogaPowietrzna15 = new DrogaPowietrzna(skrzyzowanie3,berlin,0,0);
        DrogaPowietrzna drogaPowietrzna16 = new DrogaPowietrzna(skrzyzowanie3,topGun,0,0);
        DrogaPowietrzna drogaPowietrzna17 = new DrogaPowietrzna(topGun,skrzyzowanie3,0,0);
        DrogaPowietrzna drogaPowietrzna18 = new DrogaPowietrzna(skrzyzowanie2,skrzyzowanie5,0,0);
        DrogaPowietrzna drogaPowietrzna19 = new DrogaPowietrzna(skrzyzowanie5,skrzyzowanie2,0,0);
        DrogaPowietrzna drogaPowietrzna20 = new DrogaPowietrzna(sop,skrzyzowanie1,0,0);
        DrogaPowietrzna drogaPowietrzna21 = new DrogaPowietrzna(skrzyzowanie1,sop,0,0);
        DrogaPowietrzna drogaPowietrzna22 = new DrogaPowietrzna(skrzyzowanie1,skrzyzowanie4,0,0);
        DrogaPowietrzna drogaPowietrzna35 = new DrogaPowietrzna(skrzyzowanie4,skrzyzowanie1,0,0);
        DrogaPowietrzna drogaPowietrzna24 = new DrogaPowietrzna(skrzyzowanie4,po,0,0);
        DrogaPowietrzna drogaPowietrzna25 = new DrogaPowietrzna(po,skrzyzowanie4,0,0);
        DrogaPowietrzna drogaPowietrzna26 = new DrogaPowietrzna(skrzyzowanie1,cia,0,0);
        DrogaPowietrzna drogaPowietrzna27 = new DrogaPowietrzna(cia,skrzyzowanie1,0,0);
        DrogaPowietrzna drogaPowietrzna28 = new DrogaPowietrzna(skrzyzowanie5,wsi,0,0);
        DrogaPowietrzna drogaPowietrzna29 = new DrogaPowietrzna(wsi,skrzyzowanie5,0,0);
        DrogaPowietrzna drogaPowietrzna30 = new DrogaPowietrzna(airlineTycoon,skrzyzowanie4,0,0);
        DrogaPowietrzna drogaPowietrzna31 = new DrogaPowietrzna(skrzyzowanie4,airlineTycoon,0,0);
        DrogaMorska drogaMorska1 = new DrogaMorska(port1,skrzyzowanie6,0,0);
        DrogaMorska drogaMorska2 = new DrogaMorska(skrzyzowanie6,port1,0,0);
        DrogaMorska drogaMorska11 = new DrogaMorska(berlin,skrzyzowanie6,0,0);
        DrogaMorska drogaMorska12 = new DrogaMorska(skrzyzowanie6,berlin,0,0);
        DrogaMorska drogaMorska3 = new DrogaMorska(port1,skrzyzowanie7,0,0);
        DrogaMorska drogaMorska4 = new DrogaMorska(skrzyzowanie7,port1,0,0);
        DrogaMorska drogaMorska13 = new DrogaMorska(skrzyzowanie7,topGun,0,0);
        DrogaMorska drogaMorska14 = new DrogaMorska(topGun,skrzyzowanie7,0,0);
        DrogaMorska drogaMorska5 = new DrogaMorska(warszawa,skrzyzowanie6,0,0);
        DrogaMorska drogaMorska6 = new DrogaMorska(skrzyzowanie6,warszawa,0,0);
        DrogaMorska drogaMorska7 = new DrogaMorska(port2,skrzyzowanie7,0,0);
        DrogaMorska drogaMorska8 = new DrogaMorska(skrzyzowanie7,port2,0,0);
        DrogaMorska drogaMorska9 = new DrogaMorska(skrzyzowanie6,skrzyzowanie7,0,0);
        DrogaMorska drogaMorska10 = new DrogaMorska(skrzyzowanie7,skrzyzowanie6,0,0);
    }

}
