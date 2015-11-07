package Pojazdy;

import java.lang.reflect.Array;
import java.util.*;

import Drogi.Droga;
import Mapa.PunktNaMapie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import com.sun.istack.internal.localization.NullLocalizable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import sun.awt.image.ImageWatched;


/**
 *  Klasa Pojazd implementujaca abstrakcje obiektu pojazd.
 *  @param
 *
 */
public abstract class Pojazd extends PunktNaMapie {
    /**
     * unikalny identyfikator pojazdu.
     */
    private UUID identyfikator;
    /**
     * lista miejsce, ktore znajduja sie na trasie pojazdu.
     */
    private List<MiejsceZmianyKierunku> trasa = new ArrayList<MiejsceZmianyKierunku>();
    /**
     * predkosc z jaka porusza sie pojazd.
     */
    private int maksymalnaPredkosc;
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

    /**
     * Getter
     * @return ladunek pojazdu.
     */
    public TypLadunku getLadunek() {
        return Ladunek;
    }

    public void zmianaTrasy(){

    }

    public List<MiejsceZmianyKierunku> szukanieTrasy(Przystanek poczatekTrasy,Przystanek koniecTrasy, Object typDrogi){
        ObservableList<Trasowanie> listaTras = FXCollections.observableArrayList();
        SortedList<Trasowanie> posortowanaListaTras =  new SortedList<Trasowanie>(listaTras,new Trasowanie());
//        for (int i = 0; i < poczatekTrasy.getListaDrog().size(); i++) {
//            Trasowanie nowyElement = new Trasowanie();
//            nowyElement.addListaPunktowNaMapie(poczatekTrasy.getListaDrog().get(i).getKoniec(),poczatekTrasy.getListaDrog().get(i).getOdleglosc());
//            listaTras.add(nowyElement);
//        }
        Trasowanie nowyElement = new Trasowanie();
        nowyElement.addCopyListaPunktowNaMapie(poczatekTrasy);
        listaTras.add(nowyElement);
        System.out.println("Poczatek szukania trasy");
//        while (posortowanaListaTras.size()!=0){
        while (!posortowanaListaTras.isEmpty()){
            //if (koniecTrasy.equals(posortowanaListaTras.get(0).getListaPunktowNaMapie().get(posortowanaListaTras.get(0).getListaPunktowNaMapie().size()-1))) {
            LinkedList<MiejsceZmianyKierunku> badanyElement = posortowanaListaTras.get(0).getListaPunktowNaMapie();
            if (uzyskiwanieListTrasBezPowtorzeniaElementu(koniecTrasy, typDrogi, listaTras, posortowanaListaTras, badanyElement))
                return badanyElement;
            listaTras.remove(posortowanaListaTras.get(0));
        }
//        System.out.println("Nie znaleziono trasy");
        return null;
    }

    private boolean uzyskiwanieListTrasBezPowtorzeniaElementu(Przystanek koniecTrasy, Object typDrogi, ObservableList<Trasowanie> listaTras, SortedList<Trasowanie> posortowanaListaTras, LinkedList<MiejsceZmianyKierunku> badanyElement) {
        if ( badanyElement.getLast() == koniecTrasy) {
//            System.out.println("Znaleziono Trase jej dlugosc to: " + posortowanaListaTras.get(0).getDlugosc());
//            for (int i = 0; i <  badanyElement.size(); i++) {
//                System.out.println("Punkt " + i + " " +  badanyElement.get(i).getNazwa());
//            }
            return true;
        }
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
    public Pojazd(int dlugosc, int szerokosc, int maksymalnaPredkosc) {
        super(dlugosc, szerokosc);
        this.identyfikator = UUID.randomUUID();
        this.maksymalnaPredkosc = maksymalnaPredkosc;
        Swiat.getInstance().addPojazd(this);
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

    public int getMaksymalnaPredkosc() {
        return maksymalnaPredkosc;
    }

    public UUID getIdentyfikator() {
        return identyfikator;
    }


    public void poruszanie(){

    }

    public void postuj(){

    }

    public void przyjazd(){

    }

    public void wyjazd(){

    }
    public void usunPojazd(){

    }

    public void okreslaniePolozenia(List<Przystanek> listaMozliwychPrzystankow){
        if (listaMozliwychPrzystankow.size() !=0) {
            Random random = new Random();
            this.setPrzystanekPoczatkowy(listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size() - 1)));
            this.setObecnePolozenie(this.getPrzystanekPoczatkowy());
            listaMozliwychPrzystankow.remove(this.getPrzystanekPoczatkowy());
            this.setPrzystanekDocelowy(listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size() - 1)));
        }
    }


    public Przystanek nastepnyPrzystanekZTrasy(List<MiejsceZmianyKierunku> trasa,List<Object> listaObjektow) {
        for (int i = 0; i < trasa.size(); i++) {
            if (trasa.get(i) instanceof Przystanek) {
                if(czyMozeLadowac(trasa.get(i),listaObjektow)) {
                    return (Przystanek) trasa.get(i);
                }
            }
        }
        return null;
    }

    public List<Przystanek> listaOdwiedzanychPrzystankow(List<Object> listaObjektow){
//        System.out.println("here");
        LinkedList<MiejsceZmianyKierunku> listaTymczasowaTras = new LinkedList<MiejsceZmianyKierunku>();
        listaTymczasowaTras.addAll(this.trasa);
        LinkedList<Przystanek> listaPrzystankow = new LinkedList<Przystanek>();
        while(!listaTymczasowaTras.isEmpty()) {
//            System.out.println("here");
            if (nastepnyPrzystanekZTrasy(listaTymczasowaTras,listaObjektow)!=null) {
                listaPrzystankow.add(nastepnyPrzystanekZTrasy(listaTymczasowaTras,listaObjektow));
            }
            for (int i = 0; !listaTymczasowaTras.isEmpty() && (listaTymczasowaTras.get(0) != nastepnyPrzystanekZTrasy(listaTymczasowaTras,listaObjektow)); i++) {
                listaTymczasowaTras.remove(0);
            }
            if (!listaTymczasowaTras.isEmpty()) {
                listaTymczasowaTras.remove(0);
            }
        }
        return listaPrzystankow;
    }

    public void poinformujOPrzyjezdzie(List<Object> listaObjektow){
        LinkedList<Przystanek> listaPrzystankow = new LinkedList<Przystanek>();
        listaPrzystankow.addAll(listaOdwiedzanychPrzystankow(listaObjektow));
//        listaPrzystankow.remove(0);
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            listaPrzystankow.get(i).addPojazdPrzyjezdzajacy(this);
        }
        System.out.println("Przystanki " + listaPrzystankow.size());
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            System.out.printf(listaPrzystankow.get(i).getNazwa() + " ");
        }
        System.out.println("");
    }

    public boolean czyMozeLadowac(Object doSprawdzenia,List<Object> listaObiektow){
        for (int i = 0; i < listaObiektow.size(); i++) {
            if(listaObiektow.get(i).getClass().isInstance(doSprawdzenia)){
                return true;
            }
        }
        return false;
    }

    public void wypisywanieTrasy(){
        System.out.println("Trasa:");
        for (int i = 0; i < this.getTrasa().size(); i++) {
            System.out.printf(" " + this.getTrasa().get(i).getNazwa());
        }
    }

//    @Override
//    public void run() {
//        while(true){
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
