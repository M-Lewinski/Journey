package Pojazdy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.UUID;

import Mapa.PunktNaMapie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;


/**
 *  Klasa Pojazd implementujaca abstrakcje obiektu pojazd.
 *  @param
 *
 */
public abstract class Pojazd extends PunktNaMapie implements Runnable {
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

    public void wybieranieTrasyRekurencyjnie(MiejsceZmianyKierunku obecneMiejsce, ObservableList<Trasowanie> lista){
        for (int i = 0; i < obecneMiejsce.getListaDrog().size() ; i++) {

        }
    }

    public void wybieranieTrasy(){
        ObservableList<Trasowanie> lista = FXCollections.observableArrayList();
        SortedList<Trasowanie> listaTras =  new SortedList<Trasowanie>(lista,new Trasowanie());

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
    public MiejsceZmianyKierunku getPrzystanekDocelowy() {
        return przystanekDocelowy;
    }

    public Przystanek getPrzystanekPoczatkowy() {
        return przystanekPoczatkowy;
    }

    public void setPrzystanekPoczatkowy(Przystanek przystanekPoczatkowy) {
        this.przystanekPoczatkowy = przystanekPoczatkowy;
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
     * @param polozenieX okresla polozenie obiektu w poziomie.
     * @param polozenieY okresla polozenie obiektu w pionie.
     * @param maksymalnaPredkosc okresla maksymalna predkosc, z ktora porusza sie pojazd.
     * @param ladunek okresla ladunek jaki posiada dany pojazd.
     */
    public Pojazd(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek) {
        super(dlugosc, szerokosc, polozenieX, polozenieY);
        this.identyfikator = UUID.randomUUID();
        this.maksymalnaPredkosc = maksymalnaPredkosc;
        this.Ladunek = ladunek;
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

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
