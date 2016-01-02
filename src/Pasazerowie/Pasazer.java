package Pasazerowie;

import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;
import Pojazdy.Trasowanie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.util.*;

/**
 * Klasa pasazer, ktora implementuje obiekt pasazer.
 * Created by Lewin on 2015-10-18.
 */
public class Pasazer implements ShowInfo,Runnable {
    /**
     * unikalny identyfikator
     */
    private UUID identyfikator;
    /**
     * imie pasazera.
     */
    private String imie;
    /**
     * nazwisko pasazera.
     */
    private String nazwisko;
    /**
     * pesel pasazera.
     */
    private String pesel;
    /**
     * wiek pasazera
     */
    private int wiek;
    /**
     * poczatkowy przystanek.
     */
    private Przystanek przystanekPoczatkowy;
    /**
     * przystanek docelowy.
     */
    private Przystanek przystanekDocelowy;
    /**
     * obecne polozenie.
     */
    private PunktNaMapie obecnePolozenie;
    /**
     * okresla czy pasazer jest w podrozy sluzbowej.
     */
    private boolean podrozSluzbowa;
    /**
     * czas postoju, ktory zalezy od rodzaju podrozy.
     */
    private int czasPostoju;
    /**
     * lista wszystkich miejsc zmiany kierunku i pojazdow za pomoca, ktorych bedzie przemieszczal sie pasazer.
     */
    private List<Bilet> listaBiletow = new ArrayList<Bilet>();

    private List<Przystanek> listaPrzystankow = new ArrayList<Przystanek>();

    /**
     * okresla czy pasazer wraca z podrozy.
     */
    private boolean powrot;

    private List<Przystanek> pozostalaTrasa = new ArrayList<Przystanek>();

    /**
     * Konstruktor klasy pasazer, ktory losowo generuje wartosci pol.
     */
    public Pasazer() {
        Random random = new Random();
        this.identyfikator = UUID.randomUUID();
        this.imie = GeneratorPasazerow.getInstance().getImie();
        this.nazwisko = GeneratorPasazerow.getInstance().getNazwisko();
        GregorianCalendar date = new GregorianCalendar();
        date.set(date.YEAR,random.nextInt(100)+1900);
        date.set(date.DAY_OF_YEAR, random.nextInt(date.getActualMaximum(date.DAY_OF_YEAR) - 1) + 1);
        this.pesel=peselNextNumber(date.get(date.YEAR)%100)+peselNextNumber(date.get(date.MONTH))+peselNextNumber(date.get(date.DAY_OF_MONTH));
        for(int i=0;i<5;i++){
            this.pesel=this.pesel+Integer.toString(random.nextInt(10));
        }
        GregorianCalendar dateNow = new GregorianCalendar();
        this.wiek=dateNow.get(date.YEAR)-date.get(date.YEAR);
        this.podrozSluzbowa = random.nextBoolean();
        this.powrot = false;
        if (this.podrozSluzbowa == true){
            this.czasPostoju = random.nextInt(15)+5;
        }
        else{
            this.czasPostoju = random.nextInt(30)+15;
        }
        this.czasPostoju = random.nextInt(30)+30;
//        this.przystanekPoczatkowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
//        this.przystanekDocelowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
//        okreslaniePolozen();
        this.przystanekPoczatkowy = Swiat.getInstance().getListaPojazdow().get(0).getPrzystanekPoczatkowy();
        this.przystanekDocelowy = Swiat.getInstance().getListaPojazdow().get(0).getPrzystanekDocelowy();
        Swiat.getInstance().addPasazer(this);
        this.przystanekPoczatkowy.addPasazerOczekujacy(this);
        tworzenieTrasy(this.przystanekPoczatkowy,this.przystanekDocelowy);
    }

    private void okreslaniePolozen() {
        Random random = new Random();
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaPrzystankow());
        this.przystanekPoczatkowy = listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size()));
        this.obecnePolozenie = this.przystanekPoczatkowy;
        listaMozliwychPrzystankow.remove(this.przystanekPoczatkowy);
        this.przystanekDocelowy = listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size()));
    }

    /**
     * zamiana typu integer na String w zaleznosci od ilosci cyfr.
     * @param a liczba jedno lub dwu cyfrowa.
     * @return lancuch znakow.
     */
    private String peselNextNumber(int a){
        String result="";
        if(a<10)
            result="0";
        result+=a;
        return result;
    }

    public List<Przystanek> getPozostalaTrasa() {
        return pozostalaTrasa;
    }

    public void setPozostalaTrasa(List<Przystanek> pozostalaTrasa) {
        this.pozostalaTrasa = pozostalaTrasa;
    }

    public void addPozostalaTrasa(Przystanek przystanek){
        this.pozostalaTrasa.add(przystanek);
    }

    public void removePozostalaTrasa(Przystanek przystanek){
        this.pozostalaTrasa.remove(przystanek);
    }
    public int getCzasPostoju() {
        return czasPostoju;
    }

    public void setCzasPostoju(int czasPostoju) {
        this.czasPostoju = czasPostoju;
    }

    public boolean isPowrot() {
        return powrot;
    }

    public void setPowrot(boolean powrot) {
        this.powrot = powrot;
    }

    public List<Bilet> getListaBiletow() {
        return listaBiletow;
    }

    public void setListaBiletow(List<Bilet> listaBiletow) {
        this.listaBiletow = listaBiletow;
    }

    public void addBilet(Bilet bilet){
        this.listaBiletow.add(bilet);
    }

    public void removeBilet(Bilet bilet){
        this.listaBiletow.remove(bilet);
    }

    public boolean isPodrozSluzbowa() {

        return podrozSluzbowa;
    }

    public PunktNaMapie getObecnePolozenie() {

        return obecnePolozenie;
    }

    public Przystanek getPrzystanekDocelowy() {

        return przystanekDocelowy;
    }

    public Przystanek getPrzystanekPoczatkowy() {

        return przystanekPoczatkowy;
    }

    public int getWiek() {

        return wiek;
    }

    public String getNazwisko() {

        return nazwisko;
    }

    public String getImie() {

        return imie;
    }

    public UUID getIdentyfikator() {

        return identyfikator;
    }

    public void setObecnePolozenie(PunktNaMapie obecnePolozenie) {
        this.obecnePolozenie = obecnePolozenie;
    }

    /**
     * Pokazanie informacji na panelu informacyjnym.
     */


    @Override
    public int showInfo(int rowCount) {
        return rowCount;
    }

    /**
     * szukanie trasy.
     */
    public void znajdzTrase(){

    }

    /**
     * zmiana trasy.
     */
    public void zmianaTrasy(){

    }

    /**
     * czekanie, jezeli nie istnieje polaczenie miedzy przystankami.
     */
    public void czekaj(){

    }

    /**
     * informuje przystanki, ze znajduje sie na trasie pasazera.
     */
    public void poinformujPrzystanki(){

    }

    /**
     * wypisywanie informacji na konsoli (testowanie).
     */
    public void outconsole(){
        System.out.println("\nIdentyfikator: " + this.identyfikator);
        System.out.println("Imie: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Wiek: " + this.wiek);
        System.out.println("Pesel: " + this.pesel);
//        System.out.println(this.obecnePolozenie);
        System.out.println("Przystanek poczatkowy: " + this.przystanekPoczatkowy.getNazwa());
        System.out.println("Przystanek docelowy: " + this.przystanekDocelowy.getNazwa());
        System.out.println("Czy jest w podrozy sluzbowej? " + this.podrozSluzbowa);
        System.out.println("Czas postoju w punkcie docelowym: " + this.czasPostoju);
        System.out.println("Czy wraca: " + this.powrot);
    }

    public List<Przystanek> szukanieTrasy(Przystanek poczatekTrasy,Przystanek koniecTrasy){
        ObservableList<TrasowaniePasazerow> listaTras = FXCollections.observableArrayList();
        SortedList<TrasowaniePasazerow> posortowanaListaTras =  new SortedList<TrasowaniePasazerow>(listaTras,new TrasowaniePasazerow());
//        for (int i = 0; i < poczatekTrasy.getListaDrog().size(); i++) {
//            Trasowanie nowyElement = new Trasowanie();
//            nowyElement.addListaPunktowNaMapie(poczatekTrasy.getListaDrog().get(i).getKoniec(),poczatekTrasy.getListaDrog().get(i).getOdleglosc());
//            listaTras.add(nowyElement);
//        }
        TrasowaniePasazerow nowyElement = new TrasowaniePasazerow();
        nowyElement.addCopyListaPunktowNaMapie(poczatekTrasy);
        listaTras.add(nowyElement);
        System.out.println("Poczatek szukania trasy");
//        while (posortowanaListaTras.size()!=0){
        while (!posortowanaListaTras.isEmpty()){
            //if (koniecTrasy.equals(posortowanaListaTras.get(0).getListaPunktowNaMapie().get(posortowanaListaTras.get(0).getListaPunktowNaMapie().size()-1))) {
            LinkedList<Przystanek> badanyElement = posortowanaListaTras.get(0).getListaPunktowNaMapie();
            if (uzyskiwanieListTrasBezPowtorzeniaElementu(koniecTrasy, listaTras, posortowanaListaTras, badanyElement))
                return badanyElement;
            listaTras.remove(posortowanaListaTras.get(0));
        }
//        System.out.println("Nie znaleziono trasy");
        return null;
    }

    private boolean uzyskiwanieListTrasBezPowtorzeniaElementu(Przystanek koniecTrasy, ObservableList<TrasowaniePasazerow> listaTras, SortedList<TrasowaniePasazerow> posortowanaListaTras, LinkedList<Przystanek> badanyElement) {
        if ( badanyElement.getLast() == koniecTrasy) {
            System.out.println("Znaleziono Trase jej dlugosc to: " + posortowanaListaTras.get(0).getDlugosc());
            for (int i = 0; i <  badanyElement.size(); i++) {
                System.out.println("Punkt " + i + " " +  badanyElement.get(i).getNazwa());
            }
            return true;
        }
        LinkedList<Pojazd> listaMozliwychPojazdow = new LinkedList<Pojazd>();
        if(!badanyElement.getLast().getListaPojazdowZaparkowanych().isEmpty()){
            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowZaparkowanych());
        }
        if(!badanyElement.getLast().getListaPojazdowPrzyjezdzajacych().isEmpty()){
            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowPrzyjezdzajacych());
        }
        for (int i = 0; i <  listaMozliwychPojazdow.size(); i++) {
            System.out.println("Trasa ");
            for (int j = 0; j <  badanyElement.size(); j++) {
                System.out.printf( badanyElement.get(j).getNazwa()+ " ");
            }
            System.out.println("");
            Przystanek nastepnyPrzystanek = listaMozliwychPojazdow.get(i).nastepneMozliweLadowanie(listaMozliwychPojazdow.get(i).getTrasa(), badanyElement.getLast());
            if (nastepnyPrzystanek == null){
                System.out.println("HERE");
                continue;
            }
            if ( badanyElement.contains(nastepnyPrzystanek)){
                continue;
            }
                TrasowaniePasazerow nowyElement = new TrasowaniePasazerow();
//                    System.out.println(i + "punkt: " +"poczatek: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getPoczatek().getNazwa() + " koniec: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getKoniec().getNazwa());
                for (int j = 0; j <  badanyElement.size(); j++) {
                    nowyElement.addCopyListaPunktowNaMapie( badanyElement.get(j));
                }
                nowyElement.setDlugosc(posortowanaListaTras.get(0).getDlugosc());
            double odleglosc = listaMozliwychPojazdow.get(i).okreslanieDlugosciTrasy(badanyElement.getLast(), nastepnyPrzystanek, listaMozliwychPojazdow.get(i).getTrasa());
            nowyElement.addListaPunktowNaMapie(nastepnyPrzystanek, odleglosc);
                listaTras.add(nowyElement);
        }
        return false;
    }

    public void setPodrozSluzbowa(boolean podrozSluzbowa) {
        this.podrozSluzbowa = podrozSluzbowa;
    }

    public void setPrzystanekDocelowy(Przystanek przystanekDocelowy) {
        this.przystanekDocelowy = przystanekDocelowy;
    }

    public void setPrzystanekPoczatkowy(Przystanek przystanekPoczatkowy) {
        this.przystanekPoczatkowy = przystanekPoczatkowy;
    }

    public List<Przystanek> getListaPrzystankow() {
        return listaPrzystankow;
    }

    public void setListaPrzystankow(List<Przystanek> listaPrzystankow) {
        this.listaPrzystankow = listaPrzystankow;
    }

    public void addListaPrzystankow(Przystanek przystanek){
        this.listaPrzystankow.add(przystanek);
    }

    public void removeListaPrzystankow(Przystanek przystanek){
        this.listaPrzystankow.remove(przystanek);
    }

    public void tworzenieTrasy(Przystanek punktPoczatkowy, Przystanek punktKoncowy){
        this.setListaPrzystankow(szukanieTrasy(punktPoczatkowy, punktKoncowy));
        if (this.listaPrzystankow == null){
            System.out.println("Nie ma takiej trasy");
            return;
//            listaPrzystankow.remove(0);
        }
        this.setPozostalaTrasa(this.getListaPrzystankow());
        System.out.println("Lista przystankow do przejechania:");
        for (int i = 0; i < this.listaPrzystankow.size(); i++) {
            System.out.printf(" " + this.listaPrzystankow.get(i).getNazwa());
        }
        System.out.println("");
    }

    /**
     * czynnosci wykonywane przez nowy watek pasazera.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
