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

import java.util.*;

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

    public static void main(String[] args) {
        Swiat.getInstance();
        LotniskoCywilne lotniskoCywilne1 = new LotniskoCywilne("A",100,100,70,100,false,10);
        LotniskoCywilne lotniskoCywilne2 = new LotniskoCywilne("C",100,100,200,200,false,10);
        LotniskoCywilne lotniskoCywilne3 = new LotniskoCywilne("E",100,100,70,300,false,10);
        Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie("S1",50,50,70,200,false);
        Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie("S2",50,50,120,200,false);
        DrogaPowietrzna drogaPowietrzna1 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie1);
        DrogaPowietrzna drogaPowietrzna2 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne1);
        DrogaPowietrzna drogaPowietrzna3 = new DrogaPowietrzna(lotniskoCywilne1,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna4 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne1);
        DrogaPowietrzna drogaPowietrzna5 = new DrogaPowietrzna(skrzyzowanie1,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna6 = new DrogaPowietrzna(skrzyzowanie2,skrzyzowanie1);
        DrogaPowietrzna drogaPowietrzna7 = new DrogaPowietrzna(lotniskoCywilne2,skrzyzowanie2);
        DrogaPowietrzna drogaPowietrzna8 = new DrogaPowietrzna(skrzyzowanie2,lotniskoCywilne2);
        DrogaPowietrzna drogaPowietrzna9 = new DrogaPowietrzna(skrzyzowanie1,lotniskoCywilne3);
        DrogaPowietrzna drogaPowietrzna10 = new DrogaPowietrzna(lotniskoCywilne3,skrzyzowanie1);
        DrogaMorska drogaMorska1 = new DrogaMorska(lotniskoCywilne1,lotniskoCywilne2);
        DrogaMorska drogaMorska2 = new DrogaMorska(lotniskoCywilne2,lotniskoCywilne1);
        DrogaMorska drogaMorska3 = new DrogaMorska(lotniskoCywilne2,lotniskoCywilne3);
        DrogaMorska drogaMorska4 = new DrogaMorska(lotniskoCywilne3,lotniskoCywilne2);
        LotniskoWojskowe lotniskoWojskowe1 = new LotniskoWojskowe("B",100,100,70,100,false,10);
//        System.out.println("ilosc drog " + Swiat.getInstance().getListaDrog().size());
//        for (int i = 0; i < Swiat.getInstance().getListaDrog().size() ; i++) {
//            System.out.println(Swiat.getInstance().getListaDrog().get(i).getPoczatek().getNazwa());
//        }
        SamolotPasazerski samolot1 = new SamolotPasazerski(100,100,100,10,10,10);
        System.out.println("Stworzono Samolot");
        System.out.println("Przystanek poczatkowy: " + samolot1.getPrzystanekPoczatkowy().getNazwa());
        System.out.println("Przystanek docelowy: " + samolot1.getPrzystanekDocelowy().getNazwa());
        System.out.println(samolot1.getNastepnyPrzystanek().getNazwa());
        System.out.println("dlugosc drogi miedzy punktami: " +samolot1.okreslanieDlugosciTrasy(samolot1.getPrzystanekPoczatkowy(),samolot1.getPrzystanekDocelowy(),samolot1.getTrasa()));
        Pasazer pasazer1 = new Pasazer();
        pasazer1.outconsole();
        pasazer1.setPrzystanekPoczatkowy(lotniskoCywilne1);
        pasazer1.setPrzystanekDocelowy(lotniskoCywilne2);
        //pasazer1.szukanieTrasy(pasazer1.getPrzystanekPoczatkowy(),pasazer1.getPrzystanekDocelowy());
        pasazer1.tworzenieTrasy();
        //samolot1.szukanieTrasy(samolot1.getPrzystanekPoczatkowy(), samolot1.getPrzystanekDocelowy(), new DrogaPowietrzna());
        //samolot1.szukanieTrasy(lotniskoCywilne1, lotniskoCywilne2, new DrogaPowietrzna());
//        Pasazer pasazer = new Pasazer();
//        pasazer.outconsole();
//        double odleglosc = Math.sqrt(Math.pow(8,2) + Math.pow(6,2.0));
//        System.out.printf("%.2f",odleglosc);

    }
}
