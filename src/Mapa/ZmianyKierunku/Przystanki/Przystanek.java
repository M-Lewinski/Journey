package Mapa.ZmianyKierunku.Przystanki;

import Gui.ShowLabel;
import Mapa.Swiat;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.control.Control;
import javafx.scene.shape.Circle;

import java.util.*;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Przystanek extends MiejsceZmianyKierunku {
    private List<Pojazd> listaPojazdowZaparkowanych = new ArrayList<Pojazd>();
    private List<Pasazer> listaPasazerowOczekujacych = new ArrayList<Pasazer>();
    private List<Pojazd> listaPojazdowPrzyjezdzajacych = new ArrayList<Pojazd>();
    private List<Pasazer> listaPasazerowPrzyjezdzajacych = new ArrayList<Pasazer>();
    private double maksymalnaPojemnosc=8;

//    public Pojazd getOstatnioOdwiedzil() {
//        return ostatnioOdwiedzil;
//    }
//
//    public void setOstatnioOdwiedzil(Pojazd ostatnioOdwiedzil) {
//        this.ostatnioOdwiedzil = ostatnioOdwiedzil;
//    }
//
//    private Pojazd ostatnioOdwiedzil;

    public UUID getOstatnioOdwiedzil() {
        return ostatnioOdwiedzil;
    }

    public void setOstatnioOdwiedzil(UUID ostatnioOdwiedzil) {
        this.ostatnioOdwiedzil = ostatnioOdwiedzil;
    }

    private UUID ostatnioOdwiedzil;



    public void setMaksymalnaPojemnosc(double maksymalnaPojemnosc) {
        this.maksymalnaPojemnosc = maksymalnaPojemnosc;
    }

    public double getMaksymalnaPojemnosc() {

        return maksymalnaPojemnosc;
    }

    public void poinformujPasazerow(List<Pasazer> listaOznajmionychPasazerow,boolean rezygnacja){
        List<Pasazer> listaTymczasowa = new LinkedList<Pasazer>();
        listaTymczasowa.addAll(this.listaPasazerowOczekujacych);
        odpowiedniePoinformowanie(listaTymczasowa,listaOznajmionychPasazerow, rezygnacja);
        listaTymczasowa.clear();
        listaTymczasowa.addAll(this.listaPasazerowPrzyjezdzajacych);
        odpowiedniePoinformowanie(this.listaPasazerowPrzyjezdzajacych,listaOznajmionychPasazerow,rezygnacja);
        listaTymczasowa.clear();
//
//        for (int i = 0; i < this.listaPasazerowPrzyjezdzajacych.size() ; i++) {
//            if(!listaOznajmionychPasazerow.contains(this.listaPasazerowPrzyjezdzajacych.get(i))) {
//                this.listaPasazerowPrzyjezdzajacych.get(i).setDoszloDoZmiany(true);
//                listaOznajmionychPasazerow.add(this.listaPasazerowPrzyjezdzajacych.get(i));
////                System.out.println("wie");
//            }
//        }
    }

    private void odpowiedniePoinformowanie(List<Pasazer> listaPasazerowDoOznajmienia,List<Pasazer> listaOznajmionychPasazerow, boolean rezygnacja) {
        if(listaPasazerowDoOznajmienia == null){
            return;
        }
        for (int i = 0; i < listaPasazerowDoOznajmienia.size(); i++) {
//            if(!listaOznajmionychPasazerow.contains(listaPasazerowDoOznajmienia.get(i))) {
            if(listaOznajmionychPasazerow.contains(listaPasazerowDoOznajmienia.get(i))==false) {
//                if(listaPasazerowDoOznajmienia.get(i).getPozostalaTrasa()==null){

                if(listaPasazerowDoOznajmienia.get(i)==null){
                    continue;
                }
//                if(listaPasazerowDoOznajmienia.get(i).getPozostalaTrasa().isEmpty()){
//                    continue;
//                }
                if(rezygnacja==true) {
//                    if(listaPasazerowDoOznajmienia.get(i).getPozostalaTrasa()==null){
//                        continue;
//                    }
//                    if(!listaPasazerowDoOznajmienia.get(i).getPozostalaTrasa().isEmpty())
                    if(!listaPasazerowDoOznajmienia.get(i).getListaPrzystankow().isEmpty())
                    listaPasazerowDoOznajmienia.get(i).setDoszloDoZmiany(true);
                    listaOznajmionychPasazerow.add(listaPasazerowDoOznajmienia.get(i));
//                System.out.println("wie");
                }
                else{

//                    if(listaPasazerowDoOznajmienia.get(i).getPozostalaTrasa().isEmpty()) {
                    if(listaPasazerowDoOznajmienia.get(i).getListaPrzystankow().isEmpty()) {
                        listaPasazerowDoOznajmienia.get(i).setDoszloDoZmiany(true);
                        listaOznajmionychPasazerow.add(listaPasazerowDoOznajmienia.get(i));
                    }
                }
            }
        }
    }

    //
//    public void poinformujPasazerow(){
//        for (int i = 0; i < this.listaPasazerowOczekujacych.size(); i++) {
//            this.listaPasazerowOczekujacych.get(i).setDoszloDoZmiany(true);
//        }
//
//        for (int i = 0; i < this.listaPasazerowPrzyjezdzajacych.size() ; i++) {
//            this.listaPasazerowPrzyjezdzajacych.get(i).setDoszloDoZmiany(true);
//        }
//
    public void rezygnacjaPrzylotu(){
        for (int i = 0; i < this.listaPasazerowOczekujacych.size(); i++) {
            if(this.listaPasazerowOczekujacych.get(i).getPozostalaTrasa()!=null){
                System.out.println("Znalazlem nowa trase " +listaPasazerowOczekujacych.get(i).getImie());
                this.listaPasazerowOczekujacych.get(i).setDoszloDoZmiany(true);
            }
        }
        for (int i = 0; i < this.listaPasazerowPrzyjezdzajacych.size(); i++) {
            if(this.listaPasazerowPrzyjezdzajacych.get(i).getPozostalaTrasa()!=null){
                System.out.println("Znalazlem nowa trase " +listaPasazerowOczekujacych.get(i).getImie());
                this.listaPasazerowPrzyjezdzajacych.get(i).setDoszloDoZmiany(true);
            }
        }
    }

    public void zamiarPrzylotu(){
        for (int i = 0; i < this.listaPasazerowOczekujacych.size(); i++) {
            if(listaPasazerowOczekujacych.get(i).getPozostalaTrasa()==null){
                System.out.println("Znalazlem nowa trase " +listaPasazerowOczekujacych.get(i).getImie());
                this.listaPasazerowOczekujacych.get(i).setDoszloDoZmiany(true);
            }
        }
        for (int i = 0; i < this.listaPasazerowPrzyjezdzajacych.size(); i++) {
            if(listaPasazerowPrzyjezdzajacych.get(i).getPozostalaTrasa()==null){
                System.out.println("Znalazlem nowa trase " +listaPasazerowOczekujacych.get(i).getImie());
                this.listaPasazerowPrzyjezdzajacych.get(i).setDoszloDoZmiany(true);

            }

        }
    }

//    public void poinformujPasazerow(Pojazd pojazd) {
//        poinformowanie(this.listaPasazerowOczekujacych, pojazd);
//        poinformowanie(this.listaPasazerowPrzyjezdzajacych,pojazd);
//    }
//
//    private void poinformowanie(List<Pasazer> listaPasazerow,Pojazd pojazd) {
//        boolean wymaganaJestZmiana;
//        for (int i = 0; i < listaPasazerow.size(); i++) {
//            wymaganaJestZmiana=false;
//            List<Przystanek> pozostalaTrasa = listaPasazerow.get(i).getPozostalaTrasa();
// //
//            if(pozostalaTrasa!=null) {
//                for (int j = 0; j < pozostalaTrasa.size(); j++) {
//                    if (pojazd.getTrasa().contains(pozostalaTrasa.get(j))) {
//                        wymaganaJestZmiana = true;
//                        break;
//                    }
//                }
//            }
//            else{
//                wymaganaJestZ//miana=true;
//            }
//            if(wymaganaJestZmi//ana==true){
//                listaPasazerow.get(i).setDoszloDoZm//iany(true);
//            //}
//        }
//    }

    public List<Pojazd> getListaPojazdowZaparkowanych() {
        return listaPojazdowZaparkowanych;
    }

    public void setListaPojazdowZaparkowanych(List<Pojazd> listaPojazdowZaparkowanych) {
        this.listaPojazdowZaparkowanych = listaPojazdowZaparkowanych;
    }

    public List<Pasazer> getListaPasazerowOczekujacych() {
        return listaPasazerowOczekujacych;
    }

    public void setListaPasazerowOczekujacych(List<Pasazer> listaPasazerowOczekujacych) {
        this.listaPasazerowOczekujacych = listaPasazerowOczekujacych;
    }

    public List<Pojazd> getListaPojazdowPrzyjezdzajacych() {
        return listaPojazdowPrzyjezdzajacych;
    }

    public void setListaPojazdowPrzyjezdzajacych(List<Pojazd> listaPojazdowPrzyjezdzajacych) {
        this.listaPojazdowPrzyjezdzajacych = listaPojazdowPrzyjezdzajacych;
    }

    public List<Pasazer> getListaPasazerowPrzyjezdzajacych() {
        return listaPasazerowPrzyjezdzajacych;
    }

    public void setListaPasazerowPrzyjezdzajacych(List<Pasazer> listaPasazerowPrzyjezdzajacych) {
        this.listaPasazerowPrzyjezdzajacych = listaPasazerowPrzyjezdzajacych;
    }

    public synchronized void addPojazdZaparkowany(Pojazd pojazd){
        this.setOstatnioOdwiedzil(pojazd.getIdentyfikator());
//        this.setOstatnioOdwiedzil(pojazd);
        this.listaPojazdowZaparkowanych.add(pojazd);
        this.notifyAll();
    }

    public void addPojazdPrzyjezdzajacy(Pojazd pojazd){
        this.listaPojazdowPrzyjezdzajacych.add(pojazd);
    }

    public void addPasazerOczekujacy(Pasazer pasazer){
        this.listaPasazerowOczekujacych.add(pasazer);
    }

    public void addPasazerPrzyjezdzajacy(Pasazer pasazer){
        this.listaPasazerowPrzyjezdzajacych.add(pasazer);
    }

    public synchronized void  removePojazdZaparkowany(Pojazd pojazd){
        this.listaPojazdowZaparkowanych.remove(pojazd);
    }

    public void removePojazdPrzyjezdzajacy(Pojazd pojazd){
        this.listaPasazerowPrzyjezdzajacych.remove(pojazd);
    }

    public void removePasazerOczekujacy(Pasazer pasazer){
        this.listaPasazerowOczekujacych.remove(pasazer);
    }

    public void removePasazerPrzyjezdzajacy(Pasazer pasazer){
        this.listaPasazerowPrzyjezdzajacych.remove(pasazer);
    }


    public Przystanek(double dlugosc, double szerokosc, double polozenieX, double polozenieY, String nazwa,boolean czyIstniejeWSwiecie) {
        super(dlugosc, szerokosc, polozenieX, polozenieY,nazwa,czyIstniejeWSwiecie);
        if(czyIstniejeWSwiecie==true) {
            Swiat.getInstance().addPrzystanek(this);
        }
//        this.setPromien(15);
    }
    public Przystanek(){

    }




    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = super.potrzebneInformacje();
        ShowLabel showLabel1 = new ShowLabel("Ilosc miejsc wolnych:");
        listaNodow.add(showLabel1);
        ShowLabel showLabel2 = new ShowLabel(Double.toString(this.maksymalnaPojemnosc));
        listaNodow.add(showLabel2);
//        ShowLabel label20 = new ShowLabel("Lista pojazdow przyjezdzajacych:");
//        listaNodow.add(label20);
//        for (int i = 0; i < this.listaPojazdowPrzyjezdzajacych.size(); i++) {
//            ShowLabel label21 = new ShowLabel(this.getListaPojazdowPrzyjezdzajacych().get(i).getIdentyfikator().toString(),this.getListaPojazdowPrzyjezdzajacych().get(i));
//            listaNodow.add(label21);
//        }
        ShowLabel showLabel4 = new ShowLabel("Lista Pasazerow:");
        listaNodow.add(showLabel4);
        List<Pasazer> listaTymczasowa = new ArrayList<Pasazer>();
        listaTymczasowa.addAll(this.listaPasazerowOczekujacych);
        for (int i = 0; i < listaTymczasowa.size(); i++) {
            Pasazer pasazer = listaTymczasowa.get(i);
            if(pasazer!=null) {
                ShowLabel showLabel = new ShowLabel(pasazer.getImie() + " " + pasazer.getNazwisko(), pasazer);
                listaNodow.add(showLabel);
            }
        }

//        for (int i = 0; i < this.listaPasazerowOczekujacych.size(); i++) {
//            Pasazer pasazer = this.listaPasazerowOczekujacych.get(i);
//            ShowLabel showLabel = new ShowLabel(pasazer.getImie()+ " " + pasazer.getNazwisko(),pasazer);
//            listaNodow.add(showLabel);
//        }


        ShowLabel showLabel3 = new ShowLabel("Lista pojazdow:");
        listaNodow.add(showLabel3);
        List<Pojazd> listaTymczasowaPojazdow = new ArrayList<Pojazd>();
        listaTymczasowaPojazdow.addAll(this.getListaPojazdowZaparkowanych());
        for (int i = 0; i < listaTymczasowaPojazdow.size(); i++) {
            Pojazd pojazd = listaTymczasowaPojazdow.get(i);
            ShowLabel showLabel = new ShowLabel(pojazd.getIdentyfikator().toString(),pojazd);
            listaNodow.add(showLabel);
        }

//        for (int i = 0; i < this.getListaPojazdowZaparkowanych().size(); i++) {
//            Pojazd pojazd = this.getListaPojazdowZaparkowanych().get(i);
//            ShowLabel showLabel = new ShowLabel(pojazd.getIdentyfikator().toString(),pojazd);
//            listaNodow.add(showLabel);
//        }
        return  listaNodow;
    }
}
