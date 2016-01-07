package Pasazerowie;

import Drogi.Droga;
import Gui.Controller;
import Gui.Informacja;
import Gui.MainPanel;
import Gui.ShowLabel;
import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Filtrowanie;
import Pojazdy.Pojazd;
import Pojazdy.TransportowiecCywilny;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;

import java.util.*;

/**
 * Klasa pasazer, ktora implementuje obiekt pasazer.
 * Created by Lewin on 2015-10-18.
 */
public class Pasazer implements ShowInfo,Runnable, Filtrowanie {
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
    /**
     * lista wszystkich miejsc zmiany kierunku i pojazdow za pomoca, ktorych bedzie przemieszczal sie pasazer.
     */
    private List<Bilet> listaBiletow = new ArrayList<Bilet>();

//    private List<Przystanek> listaPrzystankow = new ArrayList<Przystanek>();
    private List<Przystanek> listaPrzystankow;

    private int fps=30;

    /**
     * okresla czy pasazer wraca z podrozy.
     */
    private boolean powrot;
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
//    private List<Przystanek> pozostalaTrasa = new ArrayList<Przystanek>();
    private List<Przystanek> pozostalaTrasa;

    private Przystanek nastepnyPrzystanek;
    private Thread thread;
    private boolean threadIsAlive = false;
    private boolean moznaWysiadac = false;
    private boolean moznaWsiadac = false;
    private boolean doszloDoZmiany = false;
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
//        if (this.podrozSluzbowa == true){
//            this.czasPostoju = random.nextInt(15)+5;
//        }
//        else{
//            this.czasPostoju = random.nextInt(30)+15;
//        }

//        this.czasPostoju = random.nextInt(30)+30;
//        this.przystanekPoczatkowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
//        this.przystanekDocelowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
        listaPrzystankow = new ArrayList<Przystanek>();
        pozostalaTrasa = new ArrayList<Przystanek>();
        okreslaniePolozen();
//        this.przystanekPoczatkowy = Swiat.getInstance().getListaPojazdow().get(0).getPrzystanekPoczatkowy();
//        this.przystanekDocelowy = Swiat.getInstance().getListaPojazdow().get(0).getPrzystanekDocelowy();
        Swiat.getInstance().addPasazer(this);
//        this.przystanekPoczatkowy.addPasazerOczekujacy(this);
        tworzenieTrasy(this.przystanekPoczatkowy,this.przystanekDocelowy);
//        this.nastepnyPrzystanek=kolejnyPrzystanek();
        Runnable runner = this;
        thread = new Thread(runner);
//        threadIsAlive=true;
        thread.start();
//        Swiat.getInstance().getListaRunnable().add(runner);
        Swiat.getInstance().getListaThread().add(thread);
    }

    public Przystanek kolejnyPrzystanek(List<Przystanek> trasa){
        if(trasa.isEmpty()){
            return null;
        }
        else if(trasa.size()>1){
            return this.pozostalaTrasa.get(1);
        }
        else if(trasa.size()==0){
            return this.pozostalaTrasa.get(0);
        }
        return null;
    }
//public Przystanek kolejnyPrzystanek(List<Przystanek> trasa){
//        if(this.pozostalaTrasa.isEmpty()){
//            return null;
//        }
//        else if(this.pozostalaTrasa.size()>1){
//            return this.pozostalaTrasa.get(1);
//        }
//        else if(this.pozostalaTrasa.size()==0){
//            return this.pozostalaTrasa.get(0);
//        }
//        return null;
//    }

    private void okreslaniePolozen() {
        Random random = new Random();
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
//        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaPrzystankow());
        List<Przystanek> listaPrzystankow = Swiat.getInstance().getListaPrzystankow();
        for (int i = 0; i < listaPrzystankow.size(); i++) {
            if(czyMozeTutajLadowac(listaPrzystankow.get(i))==true){
                listaMozliwychPrzystankow.add(listaPrzystankow.get(i));
            }
        }
        this.przystanekPoczatkowy = listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size()));
//        this.przystanekPoczatkowy = Swiat.getInstance().getListaLotniskCywilnych().get(0);
        this.przystanekPoczatkowy.addPasazerOczekujacy(this);
        this.obecnePolozenie = this.przystanekPoczatkowy;
        listaMozliwychPrzystankow.remove(this.przystanekPoczatkowy);
        this.przystanekDocelowy = listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size()));
//        this.przystanekDocelowy = Swiat.getInstance().getListaLotniskCywilnych().get(1);
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

    public Przystanek getNastepnyPrzystanek() {
        return nastepnyPrzystanek;
    }

    public synchronized void setNastepnyPrzystanek(Przystanek nastepnyPrzystanek) {
        this.nastepnyPrzystanek = nastepnyPrzystanek;
    }

    public boolean isMoznaWsiadac() {
        return moznaWsiadac;
    }

    public void setMoznaWsiadac(boolean moznaWsiadac) {
        this.moznaWsiadac = moznaWsiadac;
    }

    public boolean isThreadIsAlive() {
        return threadIsAlive;
    }

    public void setThreadIsAlive(boolean threadIsAlive) {
        this.threadIsAlive = threadIsAlive;
    }

    public boolean isMoznaWysiadac() {
        return moznaWysiadac;
    }

    public void setMoznaWysiadac(boolean moznaWysiadac) {
        this.moznaWysiadac = moznaWysiadac;
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

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = new ArrayList<Control>();
        ShowLabel label1 = new ShowLabel("Identyfikator:");
        listaNodow.add(label1);
        ShowLabel label2 = new ShowLabel(this.identyfikator.toString());
        listaNodow.add(label2);
        ShowLabel label15 = new ShowLabel("Imie i nazwisko:");
        listaNodow.add(label15);
        ShowLabel label16 = new ShowLabel(this.getImie() + " " +this.getNazwisko());
        listaNodow.add(label16);
        ShowLabel label18 = new ShowLabel("Pesel: " +this.pesel);
        listaNodow.add(label18);
        ShowLabel label17 = new ShowLabel("Wiek: " + this.wiek);
        listaNodow.add(label17);
        if(this.podrozSluzbowa==true){
            ShowLabel label19 = new ShowLabel("Podroz sluzbowa: TAK");
            listaNodow.add(label19);
        }
        else{
            ShowLabel label19 = new ShowLabel("Podroz sluzbowa: Nie");
            listaNodow.add(label19);
        }
        ShowLabel label5 = new ShowLabel("Przystanek poczatkowy: " + this.przystanekPoczatkowy.getNazwa(),this.getPrzystanekPoczatkowy());
        listaNodow.add(label5);
//        ShowLabel label6 = new ShowLabel("  " +this.getPrzystanekPoczatkowy().getNazwa(),this.getPrzystanekPoczatkowy());
//        listaNodow.add(label6);
//        ShowLabel label11 = new ShowLabel("Obecne polozenie:");
//        listaNodow.add(label11);
        if(this.obecnePolozenie instanceof Pojazd){
//            ShowLabel label12 = new ShowLabel("Obecne polozenie:"+((Pojazd) this.obecnePolozenie).getIdentyfikator(),this.getObecnePolozenie());
            ShowLabel label12 = new ShowLabel("Obecne polozenie:");
            listaNodow.add(label12);
            ShowLabel label = new ShowLabel(((Pojazd) this.obecnePolozenie).getIdentyfikator().toString(),this.getObecnePolozenie());
            listaNodow.add(label);
        }
        else{
            ShowLabel label12 = new ShowLabel("Obecne polozenie: "+((Przystanek) this.obecnePolozenie).getNazwa(),this.getObecnePolozenie());
            listaNodow.add(label12);
        }
        if(this.nastepnyPrzystanek!=null){
            ShowLabel label = new ShowLabel("Nastepny przystanek: "+ this.nastepnyPrzystanek.getNazwa(),this.nastepnyPrzystanek);
            listaNodow.add(label);
        }
        else{
            ShowLabel label = new ShowLabel("Nastepny przystanek: Brak");
            listaNodow.add(label);
        }
        ShowLabel label7 = new ShowLabel("Przystanek koncowy: "+this.getPrzystanekDocelowy().getNazwa(),this.getPrzystanekDocelowy());
        listaNodow.add(label7);
//        ShowLabel label8 = new ShowLabel("  " + this.getPrzystanekDocelowy().getNazwa(),this.getPrzystanekDocelowy());
//        listaNodow.add(label8);
//        ShowLabel label13 = new ShowLabel("Koniec obecnej drogi:");
//        listaNodow.add(label13);
//        ShowLabel label14 = new ShowLabel(" " + this.getDrogaTeraz().getKoniec().getNazwa());
//        listaNodow.add(label14);
        ShowLabel label9 = new ShowLabel("Obecna Trasa:");
        listaNodow.add(label9);
//        if(this.listaPrzystankow!=null && this.listaPrzystankow.size()>1){
        if(!this.pozostalaTrasa.isEmpty() && this.pozostalaTrasa.size()>1){
//            for (int i = 1; i < this.pozostalaTrasa.size(); i++) {
            for (int i = 0; i < this.pozostalaTrasa.size(); i++) {
                ShowLabel label = new ShowLabel("   " + this.pozostalaTrasa.get(i).getNazwa(),this.pozostalaTrasa.get(i));
                listaNodow.add(label);
            }
        }
        return listaNodow;
    }

    /**
     * Pokazanie informacji na panelu informacyjnym.
     */



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

//    /**
//     * szukanie trasy.
//     */
//    public void znajdzTrase(){
//
//    }
//
//    /**
//     * zmiana trasy.
//     */
//    public void zmianaTrasy(){
//
//    }
//
//    /**
//     * czekanie, jezeli nie istnieje polaczenie miedzy przystankami.
//     */
//    public void czekaj(){
//
//    }
//
//    /**
//     * informuje przystanki, ze znajduje sie na trasie pasazera.
//     */
//    public void poinformujPrzystanki(){
//
//    }

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
//        System.out.println("Poczatek szukania trasy");
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
//            System.out.println(this.getImie() + " " + this.getNazwisko() +" znaleziono Trase jej dlugosc to: " + posortowanaListaTras.get(0).getDlugosc());
//            for (int i = 0; i <  badanyElement.size(); i++) {
//                System.out.println("Punkt " + i + " " +  badanyElement.get(i).getNazwa());
//            }
            return true;
        }
        ArrayList<Pojazd> listaMozliwychPojazdow = new ArrayList<Pojazd>();
//        if(!badanyElement.getLast().getListaPojazdowZaparkowanych().isEmpty()){
//            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowZaparkowanych());
//        }
        if(!badanyElement.getLast().getListaPojazdowPrzyjezdzajacych().isEmpty()){
            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowPrzyjezdzajacych());
        }
        for (int i = 0; i <  listaMozliwychPojazdow.size(); i++) {
//            System.out.println("Trasa ");
//            for (int j = 0; j <  badanyElement.size(); j++) {
//                System.out.printf( badanyElement.get(j).getNazwa()+ " ");
//            }
//            System.out.println("");
//            Przystanek nastepnyPrzystanek = listaMozliwychPojazdow.get(i).nastepneMozliweLadowanie(listaMozliwychPojazdow.get(i).getTrasa(), badanyElement.getLast());

//            ArrayList<Przystanek> listaPrzystankow = new ArrayList<> ();

//            if(listaMozliwychPojazdow.get(i).listaOdwiedzanychPrzystankow(listaMozliwychPojazdow.get(i).getTrasa())==null){
//                continue;
//            }
//            else{
            List<Przystanek> listaPrzystankowNaTrasie = new ArrayList<Przystanek>();
            List<Przystanek>   listaTymczasowa = listaMozliwychPojazdow.get(i).listaOdwiedzanychPrzystankow(listaMozliwychPojazdow.get(i).getTrasa());
//            }
            if(listaTymczasowa==null){
                continue;
            }
            else {
                listaPrzystankowNaTrasie.addAll(listaTymczasowa);
            }
            int index =listaPrzystankowNaTrasie.indexOf(badanyElement.getLast());
//            if(index==-1){
//                continue;
//            }
            if(index-1 >=0 && index -1 <listaPrzystankowNaTrasie.size()){
                if(!badanyElement.contains(listaPrzystankowNaTrasie.get(index-1))){
                    Przystanek nextPrzystanek = listaPrzystankowNaTrasie.get(index-1);
                    tworzenieNowegoElementu(listaTras, posortowanaListaTras, badanyElement, listaMozliwychPojazdow, i, nextPrzystanek,true);
                }
            }
            if(index+1 >=0 && index+1 <listaPrzystankowNaTrasie.size()){
                if(!badanyElement.contains(listaPrzystankowNaTrasie.get(index+1))){
                    Przystanek nextPrzystanek = listaPrzystankowNaTrasie.get(index+1);
                    tworzenieNowegoElementu(listaTras, posortowanaListaTras, badanyElement, listaMozliwychPojazdow, i, nextPrzystanek,false);
                }
            }
        }
        return false;
    }

    private void tworzenieNowegoElementu(ObservableList<TrasowaniePasazerow> listaTras, SortedList<TrasowaniePasazerow> posortowanaListaTras, LinkedList<Przystanek> badanyElement, ArrayList<Pojazd> listaMozliwychPojazdow, int i,Przystanek nextPrzystanek,boolean wDrugaStrone) {
        TrasowaniePasazerow nowyElement = new TrasowaniePasazerow();
//                    System.out.println(i + "punkt: " +"poczatek: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getPoczatek().getNazwa() + " koniec: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getKoniec().getNazwa());
        for (int j = 0; j <  badanyElement.size(); j++) {
            nowyElement.addCopyListaPunktowNaMapie( badanyElement.get(j));
        }
        nowyElement.setDlugosc(posortowanaListaTras.get(0).getDlugosc());
        ArrayList<MiejsceZmianyKierunku> listaTymczasowa = new ArrayList<>();
        listaTymczasowa.addAll(listaMozliwychPojazdow.get(i).getTrasa());
        if (wDrugaStrone){
            Collections.reverse(listaTymczasowa);
        }
        double odleglosc = listaMozliwychPojazdow.get(i).okreslanieDlugosciTrasy(badanyElement.getLast(), nextPrzystanek, listaTymczasowa);
        if(odleglosc!=0) {
            nowyElement.addListaPunktowNaMapie(nextPrzystanek, odleglosc);
            listaTras.add(nowyElement);
        }
    }

    //
//    private boolean uzyskiwanieListTrasBezPowtorzeniaElementu(Przystanek koniecTrasy, ObservableList<TrasowaniePasazerow> listaTras, SortedList<TrasowaniePasazerow> posortowanaListaTras, LinkedList<Przystanek> badanyElement) {
//        if ( badanyElement.getLast() == koniecTrasy) {
////            System.out.println("Znaleziono Trase jej dlugosc to: " + posortowanaListaTras.get(0).getDlugosc());
////            for (int i = 0; i <  badanyElement.size(); i++) {
//////                System.out.println("Punkt " + i + " " +  badanyElement.get(i).getNazwa());
////            }
//            return true;
//        }
//        LinkedList<Pojazd> listaMozliwychPojazdow = new LinkedList<Pojazd>();
//        if(!badanyElement.getLast().getListaPojazdowZaparkowanych().isEmpty()){
//            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowZaparkowanych());
//        }
//        if(!badanyElement.getLast().getListaPojazdowPrzyjezdzajacych().isEmpty()){
//            listaMozliwychPojazdow.addAll(badanyElement.getLast().getListaPojazdowPrzyjezdzajacych());
//        }
//        for (int i = 0; i <  listaMozliwychPojazdow.size(); i++) {
////            System.out.println("Trasa ");
////            for (int j = 0; j <  badanyElement.size(); j++) {
////                System.out.printf( badanyElement.get(j).getNazwa()+ " ");
////            }
////            System.out.println("");
//            Przystanek nastepnyPrzystanek = listaMozliwychPojazdow.get(i).nastepneMozliweLadowanie(listaMozliwychPojazdow.get(i).getTrasa(), badanyElement.getLast());
//            if (nastepnyPrzystanek == null){
////                System.out.println("HERE");
//                continue;
//            }
//            if ( badanyElement.contains(nastepnyPrzystanek)){
//                continue;
//            }
//                TrasowaniePasazerow nowyElement = new TrasowaniePasazerow();
////                    System.out.println(i + "punkt: " +"poczatek: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getPoczatek().getNazwa() + " koniec: " + posortowanaListaTras.get(0).getListaPunktowNaMapie().getLast().getListaDrog().get(i).getKoniec().getNazwa());
//                for (int j = 0; j <  badanyElement.size(); j++) {
//                    nowyElement.addCopyListaPunktowNaMapie( badanyElement.get(j));
//                }
//                nowyElement.setDlugosc(posortowanaListaTras.get(0).getDlugosc());
//            double odleglosc = listaMozliwychPojazdow.get(i).okreslanieDlugosciTrasy(badanyElement.getLast(), nastepnyPrzystanek, listaMozliwychPojazdow.get(i).getTrasa());
//            nowyElement.addListaPunktowNaMapie(nastepnyPrzystanek, odleglosc);
//                listaTras.add(nowyElement);
//        }
//        return false;
//    }
//
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
        this.poinformujORezygnacjiZPrzyjazdu();
        if(!this.listaPrzystankow.isEmpty()) {
            this.listaPrzystankow.clear();
        }
        if(!this.pozostalaTrasa.isEmpty()){
            this.pozostalaTrasa.clear();
        }
//        this.setListaPrzystankow(szukanieTrasy(punktPoczatkowy, punktKoncowy));
        List<Przystanek> listaTymczasowa = szukanieTrasy(punktPoczatkowy,punktKoncowy);
//        nastepnyPrzystanek=null;
        if(listaTymczasowa==null){
            this.setNastepnyPrzystanek(null);
            return;
        }
        else if(listaTymczasowa.size()==1){
            return;
        }
        else{
            this.listaPrzystankow.addAll(listaTymczasowa);
        }
//        if (this.listaPrzystankow == null){
//            System.out.println("Nie ma takiej trasy");
////            return;
////            listaPrzystankow.remove(0);
//        }
//        if(listaPrzystankow==null){
//            return;
//        }
        this.pozostalaTrasa.addAll(this.listaPrzystankow);
        this.setNastepnyPrzystanek(kolejnyPrzystanek(this.pozostalaTrasa));
//        System.out.println("Lista przystankow do przejechania:");
//        for (int i = 0; i < this.listaPrzystankow.size(); i++) {
//            System.out.printf(" " + this.listaPrzystankow.get(i).getNazwa());
//        }
//        System.out.println("");
        this.poinformujOZamiarzePrzyjazdu();
    }

    public void wysiadanie(){
            if (this.obecnePolozenie instanceof TransportowiecCywilny) {
                TransportowiecCywilny pojazd = (TransportowiecCywilny) this.obecnePolozenie;
//                    System.out.println("wysiadam!!!!!!!!!");
                    this.nastepnyPrzystanek.addPasazerOczekujacy(this);
                    if(this.pozostalaTrasa.size()>0) {
                        this.pozostalaTrasa.remove(0);
                    }

                    this.obecnePolozenie=this.nastepnyPrzystanek;
//                    this.nastepnyPrzystanek = this.kolejnyPrzystanek(this.pozostalaTrasa);
                    this.setNastepnyPrzystanek(this.kolejnyPrzystanek(this.pozostalaTrasa));
                    this.moznaWysiadac = false;
                pojazd.wysiadanie(this);

            }
    }

    public void wsiadanie(Pojazd pojazd){
//            System.out.println("Wsiadam");
            if(pojazd instanceof TransportowiecCywilny){
//                System.out.println("Jestem!!!!!!!!");
                TransportowiecCywilny transportowiecCywilny = (TransportowiecCywilny) pojazd;
//                if(transportowiecCywilny.getLadunek().czyWszystcyWysiedli()==false){
//                    return;
//                }
//                while(transportowiecCywilny.getLadunek().czyWszystcyWysiedli()==false){
//
//                }
                synchronized (pojazd.getHulkPojazdu()){
                    if(transportowiecCywilny.wsiadanie(this)){
//                    System.out.println("wsiadlem");
                    if(this.getObecnePolozenie() instanceof  Przystanek) {
                        Przystanek przystanek = (Przystanek) this.getObecnePolozenie();
                        przystanek.removePasazerOczekujacy(this);
                    }
                    this.obecnePolozenie=pojazd;
                }
            }
        }
    }

    @Override
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
        if(Pasazer.listaGdzieMozeLadowac.isEmpty()){
            Pasazer.listaGdzieMozeLadowac.add(new LotniskoCywilne());
            Pasazer.listaGdzieMozeLadowac.add(new Miasto());
            Pasazer.listaGdzieMozeLadowac.add(new Port());
        }
        return listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        return null;
    }

    @Override
    public boolean czyMozeTutajLadowac(Object doSprawdzenia) {
        List<MiejsceZmianyKierunku> mozliweLadowania = this.getMozliweLadowania();
        for (int i = 0; i < mozliweLadowania.size(); i++) {
            if(mozliweLadowania.get(i).getClass().isInstance(doSprawdzenia)){
                return true;
            }
        }
        return false;
    }

    public boolean isDoszloDoZmiany() {
        return doszloDoZmiany;
    }

    public synchronized void setDoszloDoZmiany(boolean doszloDoZmiany) {
        this.doszloDoZmiany = doszloDoZmiany;
        this.notify();
    }

//    public void poinformujORezygnacjiZPrzyjazdu(){
//        for (int i = 0; i < this.listaPrzystankow.size(); i++) {
//            this.listaPrzystankow.get(i).removePasazerPrzyjezdzajacy(this);
//        }
//    }
//
    public void poinformujORezygnacjiZPrzyjazdu(){
        List<Przystanek> listaTymczasowa = Swiat.getInstance().getListaPrzystankow();
        for (int i = 0; i < listaTymczasowa.size(); i++) {
            if(listaTymczasowa.get(i).getListaPasazerowPrzyjezdzajacych().contains(this)){
                listaTymczasowa.get(i).removePasazerPrzyjezdzajacy(this);
            }
        }
    }


    public void poinformujOZamiarzePrzyjazdu(){
        for (int i = 0; i < this.listaPrzystankow.size(); i++) {
            if(!this.listaPrzystankow.get(i).getListaPojazdowPrzyjezdzajacych().contains(this)) {
                this.listaPrzystankow.get(i).addPasazerPrzyjezdzajacy(this);
            }
        }
    }

    public void usuwanie(){
        this.poinformujORezygnacjiZPrzyjazdu();
//        List<Przystanek> listaTymczasowa = Swiat.getInstance().getListaPrzystankow();
//        for (int i = 0; i < listaTymczasowa.size(); i++) {
//            if(listaTymczasowa.get(i).getListaPasazerowPrzyjezdzajacych().contains(this)){
//                listaTymczasowa.get(i).getListaPojazdowZaparkowanych().remove(this);
//            }
//        }
        if(this.obecnePolozenie instanceof Przystanek){
            Przystanek przystanek = (Przystanek) this.obecnePolozenie;
            przystanek.removePasazerOczekujacy(this);
        }
        Swiat.getInstance().removePasazer(this);
        Swiat.getInstance().getListaThread().remove(thread);
        threadIsAlive=false;
        if(Informacja.getInstance().getObecnaInformacja()==this) {
            Informacja.getInstance().wyczysc();
        }
        System.out.println("USUNIETO PASAZERA!!!!!!!!!!!!!!!");
    }

    public void odwrocTrase(){
        if(this.isPowrot()==false) {
            int czasPostoju=0;
            Przystanek przystanek = this.getPrzystanekPoczatkowy();
            this.setPrzystanekPoczatkowy(this.getPrzystanekDocelowy());
            this.setPrzystanekDocelowy(przystanek);
            Random random = new Random();
            if (this.podrozSluzbowa == true) {
//                this.czasPostoju = random.nextInt(15) + 5;
                czasPostoju = fps * 10;
            } else {
//                this.czasPostoju = random.nextInt(30) + 15;
                czasPostoju = fps*20;
            }
            this.setPowrot(true);
            try {
                Thread.sleep(1000 / fps*czasPostoju);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            this.tworzenieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy());
        }
        else{
            this.usuwanie();
        }
    }

    /**
     * czynnosci wykonywane przez nowy watek pasazera.
     */
    @Override
    public void run() {
        try {
            this.threadIsAlive=true;
            while(this.threadIsAlive==true){

                        if (moznaWysiadac == true) {
                            this.wysiadanie();
                            if(this.getObecnePolozenie()==this.getPrzystanekDocelowy()){
                                odwrocTrase();
                            }
                        }
                        else {
                            if (this.nastepnyPrzystanek != null) {
                                if (obecnePolozenie instanceof Przystanek) {
                                    Przystanek przystanek = (Przystanek) obecnePolozenie;
                                    wsiadanie(przystanek);
                                }
                                //lecem
                                lot((Pojazd)this.obecnePolozenie);
                                continue;
                            }

                            synchronized (this) {
                                while (!doszloDoZmiany) {
                                    this.wait();
                                }
                                doszlodozmiany();
                            }
                        }
                    }

            } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
//            System.out.println("zabito pasazera");
//            thread.interrupt();
    }

    private void lot(Pojazd pojazd){

        while(true) {
            synchronized (this) {
                if (doszloDoZmiany == true) {
                    doszlodozmiany();
                }
                if(!moznaWysiadac) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    return;
                }
            }

        }
    }

    private void wsiadanie(Przystanek przystanek) throws InterruptedException {

        List<Pojazd> listapojazdow = new ArrayList<Pojazd>();
        List<Pojazd> poprzednialistapojazdow = new ArrayList<Pojazd>();
        UUID przylecialOstatni = null;
        while(true){
            synchronized (przystanek){
                przylecialOstatni = przystanek.getOstatnioOdwiedzil();
//                if(przystanek.getListaPojazdowZaparkowanych()==null){
                if(przystanek.getListaPojazdowZaparkowanych().isEmpty()){
//                    listapojazdow=null;
                    listapojazdow.clear();
//                    poprzednialistapojazdow=null;
                    poprzednialistapojazdow.clear();
                    przystanek.wait();
//                    System.out.println("koniec czekania");
                    continue;
                }

                for (Pojazd pojazd: przystanek.getListaPojazdowZaparkowanych()){
                    if(!listapojazdow.contains(pojazd)){
                        listapojazdow.add(pojazd);
                    }
                }
//                listapojazdow.addAll(przystanek.getListaPojazdowZaparkowanych());
                listapojazdow.removeAll(poprzednialistapojazdow);
            }
            for (int i = 0; i < listapojazdow.size(); i++) {
                synchronized (this) {
                    if (doszloDoZmiany == true) {
                        doszlodozmiany();
                    }
                }
                Pojazd pojazd = listapojazdow.get(i);
                if (pojazd.getNastepnyPrzystanek() == this.nastepnyPrzystanek) {
//                                            System.out.println("Proba wejscia");
                    this.wsiadanie(pojazd);
                    if(this.obecnePolozenie == pojazd) {
                        return;
                    }
                }
            }
//            poprzednialistapojazdow=listapojazdow;
            poprzednialistapojazdow.clear();
            poprzednialistapojazdow.addAll(listapojazdow);
            synchronized (przystanek){
//                if(przystanek.getOstatnioOdwiedzil()==null|| (przylecialOstatni==null && przystanek.getOstatnioOdwiedzil()!=null) || przylecialOstatni.equals(przystanek.getOstatnioOdwiedzil()))
                if(przystanek.getOstatnioOdwiedzil()==null|| (przylecialOstatni==null && przystanek.getOstatnioOdwiedzil()!=null) || przylecialOstatni.equals(przystanek.getOstatnioOdwiedzil()))
                przystanek.wait();
            }
        }
    }

    private void doszlodozmiany() {
        if(this.threadIsAlive==false) {
            Thread.currentThread().stop();
        }

        doszloDoZmiany = false;
        if (this.obecnePolozenie instanceof Przystanek) {
            zmianaTrasyWPrzystanku();
        } else {
            zmianaTrasyWPojezdzie();
        }
    }

    private void zmianaTrasyWPojezdzie() {
        Pojazd pojazd = (Pojazd) this.getObecnePolozenie();

//        if(pojazd.getObecnePolozenie() instanceof Przystanek) {
//            Przystanek obecne = (Przystanek) pojazd.getObecnePolozenie();
//            tworzenieTrasy(obecne, this.getPrzystanekDocelowy());
//        }
//        else{
            tworzenieTrasy(pojazd.getNastepnyPrzystanek(), this.getPrzystanekDocelowy());
//        }
        if(nastepnyPrzystanek==null){
            this.setNastepnyPrzystanek(pojazd.getNastepnyPrzystanek());
            if(this.nastepnyPrzystanek!=null) {
                if (!this.nastepnyPrzystanek.getListaPojazdowPrzyjezdzajacych().contains(this)) {
                    this.nastepnyPrzystanek.addPasazerPrzyjezdzajacy(this);
                }
            }
        }
    }

    private void zmianaTrasyWPrzystanku() {
        tworzenieTrasy((Przystanek) this.getObecnePolozenie(), this.getPrzystanekDocelowy());
    }


}
