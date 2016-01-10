package Mapa;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Gui.Controller;
import Gui.MainPanel;
import Gui.ShowLabel;
import Mapa.ZmianyKierunku.Przystanki.*;
import Mapa.ZmianyKierunku.Skrzyzowanie;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Wodne.Lotniskowiec;
import com.sun.media.sound.MidiInDeviceProvider;
import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;

import java.util.*;

import static javafx.application.Application.launch;

/**
 * Klasa swiat, ktora implementuje obiekt calego swiata. Przechowuje on wszystkie listy obiektow oraz tworzy mape.
 *
 */
//public class Swiat extends ObiektGraficzny {
public class Swiat extends ObiektGraficzny implements ShowInfo{
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
    private List<Pojazd> listaWolnychPojazdow = new ArrayList<Pojazd>();
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


    public List<Pojazd> getListaWolnychPojazdow() {
        return listaWolnychPojazdow;
    }

    public void setListaWolnychPojazdow(List<Pojazd> listaWolnychPojazdow) {
        this.listaWolnychPojazdow = listaWolnychPojazdow;
    }
    public void removeWolnyPojazd(Pojazd pojazd){
        this.listaWolnychPojazdow.remove(pojazd);
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
        Miasto warszawa = new Miasto("Warszawa", 30,30, 50, 280, true);
        Miasto berlin = new Miasto("Berlin", 30, 30, 590, 300, true);
        Miasto topGun = new Miasto("Las Vegas",30,30,800,600, true);
//        LotniskoCywilne airlineTycoon = new LotniskoCywilne("Airline Tycoon",30,30,120,440,false,10);
        LotniskoWojskowe airlineTycoon = new LotniskoWojskowe("SOP",30,30,120,440,true);
        LotniskoCywilne paryz = new LotniskoCywilne("Paryz", 30, 30, 700, 50,true);
//        LotniskoWojskowe sop = new LotniskoWojskowe("SOP",30,30,300,50,false,10);
        LotniskoCywilne sop = new LotniskoCywilne("AirLine Tycoon",30,30,300,50,true);
        LotniskoWojskowe po = new LotniskoWojskowe("PO",30,30,250,560,true);
        LotniskoWojskowe pa = new LotniskoWojskowe("PA",30,30,800,300,true);
        LotniskoWojskowe wsi = new LotniskoWojskowe("WSI",30,30,410,210,true);
//        LotniskoWojskowe cia = new LotniskoWojskowe("CIA",30,30,100,100,false,10);
        LotniskoWojskowe cia = new LotniskoWojskowe("CIA",30,30,450,280,true);
        Port port1 = new Port("Green Gun",30,30,360,550,true);
        Port port2 = new Port("Blue Gun",30,30,600,400,true);
        Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie("S:A", 30, 30, 300, 245, true);
        Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie("S:B", 30, 30, 660, 200, true);
        Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie("S:C",30,30,780,400,true);
        Skrzyzowanie skrzyzowanie4 = new Skrzyzowanie("S:D",30,30,300,440,true);
        Skrzyzowanie skrzyzowanie5 = new Skrzyzowanie("S:E",30,30,450,50,true);
        Skrzyzowanie skrzyzowanie6 = new Skrzyzowanie("S:F",30,30,445,370,true);
        Skrzyzowanie skrzyzowanie7 = new Skrzyzowanie("S:G",30,30,570,575,true);
        DrogaPowietrzna drogaPowietrzna1 = new DrogaPowietrzna(warszawa,skrzyzowanie1,true);
        DrogaPowietrzna drogaPowietrzna2 = new DrogaPowietrzna(skrzyzowanie1,warszawa,true);
        DrogaPowietrzna drogaPowietrzna5 = new DrogaPowietrzna(berlin,skrzyzowanie2,true);
        DrogaPowietrzna drogaPowietrzna6 = new DrogaPowietrzna(skrzyzowanie2,berlin,true);
        DrogaPowietrzna drogaPowietrzna7 = new DrogaPowietrzna(skrzyzowanie2,paryz,true);
        DrogaPowietrzna drogaPowietrzna8 = new DrogaPowietrzna(paryz,skrzyzowanie2,true);
        DrogaPowietrzna drogaPowietrzna9 = new DrogaPowietrzna(sop,skrzyzowanie5,true);
        DrogaPowietrzna drogaPowietrzna10 = new DrogaPowietrzna(skrzyzowanie5,sop,true);
        DrogaPowietrzna drogaPowietrzna11 = new DrogaPowietrzna(skrzyzowanie2,pa,true);
        DrogaPowietrzna drogaPowietrzna23 = new DrogaPowietrzna(pa,skrzyzowanie2,true);
        DrogaPowietrzna drogaPowietrzna12 = new DrogaPowietrzna(pa,skrzyzowanie3,true);
        DrogaPowietrzna drogaPowietrzna13 = new DrogaPowietrzna(skrzyzowanie3,pa,true);
        DrogaPowietrzna drogaPowietrzna14 = new DrogaPowietrzna(berlin,skrzyzowanie3,true);
        DrogaPowietrzna drogaPowietrzna15 = new DrogaPowietrzna(skrzyzowanie3,berlin,true);
        DrogaPowietrzna drogaPowietrzna16 = new DrogaPowietrzna(skrzyzowanie3,topGun,true);
        DrogaPowietrzna drogaPowietrzna17 = new DrogaPowietrzna(topGun,skrzyzowanie3,true);
        DrogaPowietrzna drogaPowietrzna18 = new DrogaPowietrzna(skrzyzowanie2,skrzyzowanie5,true);
        DrogaPowietrzna drogaPowietrzna19 = new DrogaPowietrzna(skrzyzowanie5,skrzyzowanie2,true);
        DrogaPowietrzna drogaPowietrzna20 = new DrogaPowietrzna(sop,skrzyzowanie1,true);
        DrogaPowietrzna drogaPowietrzna21 = new DrogaPowietrzna(skrzyzowanie1,sop,true);
        DrogaPowietrzna drogaPowietrzna22 = new DrogaPowietrzna(skrzyzowanie1,skrzyzowanie4,true);
        DrogaPowietrzna drogaPowietrzna35 = new DrogaPowietrzna(skrzyzowanie4,skrzyzowanie1,true);
        DrogaPowietrzna drogaPowietrzna24 = new DrogaPowietrzna(skrzyzowanie4,po,true);
        DrogaPowietrzna drogaPowietrzna25 = new DrogaPowietrzna(po,skrzyzowanie4,true);
        DrogaPowietrzna drogaPowietrzna26 = new DrogaPowietrzna(skrzyzowanie1,cia,true);
        DrogaPowietrzna drogaPowietrzna27 = new DrogaPowietrzna(cia,skrzyzowanie1,true);
        DrogaPowietrzna drogaPowietrzna28 = new DrogaPowietrzna(skrzyzowanie5,wsi,true);
        DrogaPowietrzna drogaPowietrzna29 = new DrogaPowietrzna(wsi,skrzyzowanie5,true);
        DrogaPowietrzna drogaPowietrzna30 = new DrogaPowietrzna(airlineTycoon,skrzyzowanie4,true);
        DrogaPowietrzna drogaPowietrzna31 = new DrogaPowietrzna(skrzyzowanie4,airlineTycoon,true);
        DrogaMorska drogaMorska1 = new DrogaMorska(port1,skrzyzowanie6,true);
        DrogaMorska drogaMorska2 = new DrogaMorska(skrzyzowanie6,port1,true);
        DrogaMorska drogaMorska11 = new DrogaMorska(berlin,skrzyzowanie6,true);
        DrogaMorska drogaMorska12 = new DrogaMorska(skrzyzowanie6,berlin,true);
        DrogaMorska drogaMorska3 = new DrogaMorska(port1,skrzyzowanie7,true);
        DrogaMorska drogaMorska4 = new DrogaMorska(skrzyzowanie7,port1,true);
        DrogaMorska drogaMorska13 = new DrogaMorska(skrzyzowanie7,topGun,true);
        DrogaMorska drogaMorska14 = new DrogaMorska(topGun,skrzyzowanie7,true);
        DrogaMorska drogaMorska5 = new DrogaMorska(warszawa,skrzyzowanie6,true);
        DrogaMorska drogaMorska6 = new DrogaMorska(skrzyzowanie6,warszawa,true);
        DrogaMorska drogaMorska7 = new DrogaMorska(port2,skrzyzowanie7,true);
        DrogaMorska drogaMorska8 = new DrogaMorska(skrzyzowanie7,port2,true);
//        DrogaMorska drogaMorska9 = new DrogaMorska(skrzyzowanie6,skrzyzowanie7,0,0);
//        DrogaMorska drogaMorska10 = new DrogaMorska(skrzyzowanie7,skrzyzowanie6,0,0);
    }

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = new ArrayList<Control>();
        ShowLabel showLabel = new ShowLabel("Wszyscy Pasazerowie: ");
        listaNodow.add(showLabel);
        ShowLabel showLabel1 = new ShowLabel("Liczba wszystkich pasazerow: " + this.listaPasazerow.size());
        listaNodow.add(showLabel1);
        for (int i = 0; i < this.listaPasazerow.size(); i++) {
            Pasazer pasazer = this.listaPasazerow.get(i);
            ShowLabel showLabel2 = new ShowLabel("  " + pasazer.getImie() + " " + pasazer.getNazwisko(),pasazer);
            listaNodow.add(showLabel2);
        }
        return  listaNodow;
    }

    @Override
    public int showInfo(int rowCount) {
        List<Control> listaNodow = new ArrayList<Control>();
        listaNodow.addAll(this.potrzebneInformacje());
        Controller controller = MainPanel.getLoader().getController();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boolean rozne = false;
                if (controller.getGrid().getChildren().size() != listaNodow.size()) {
                    controller.getGrid().getChildren().clear();
                    rozne = true;
                }
                if (rozne == false) {
                    for (int i = 0; i < controller.getGrid().getChildren().size(); i++) {
                        int row = controller.getGrid().getRowIndex(controller.getGrid().getChildren().get(i));
                        if (controller.getGrid().getChildren().get(i) instanceof Labeled && listaNodow.get(row) instanceof Labeled) {
                            if (((Labeled) controller.getGrid().getChildren().get(i)).getText().equals(((Labeled) listaNodow.get(row)).getText())) {
                                continue;
                            }
                        }
                        controller.getGrid().getChildren().remove(i);
                        i--;
                        controller.getGrid().add(listaNodow.get(row), 0, row);
                    }
                } else {
                    for (int i = 0; i < listaNodow.size(); i++) {
                        controller.getGrid().add(listaNodow.get(i), 0, i);
                    }
                }
            }
        });
        return listaNodow.size();
    }
}
