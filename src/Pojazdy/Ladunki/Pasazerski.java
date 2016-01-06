package Pojazdy.Ladunki;

import Gui.ShowLabel;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-19.
 */
public class Pasazerski extends TypLadunku {
    private int maksymalnaLiczbaPasazerow;
    private int obecnaLiczbaPasazerow=0;
    private List<Pasazer> listaPasazerow = new ArrayList<Pasazer>();
    private List<Pasazer> listaWysiadajacychPasazerow = new ArrayList<Pasazer>();
    public void addPasazer(Pasazer pasazer){
        this.listaPasazerow.add(pasazer);
    }

    public List<Pasazer> getListaWysiadajacychPasazerow() {
        return listaWysiadajacychPasazerow;
    }

    public void removePasazer(Pasazer pasazer){
        this.listaPasazerow.remove(pasazer);
    }

    public void addWysiadajacyPasazer(Pasazer pasazer){
        this.listaPasazerow.add(pasazer);
    }

    public void removeWysiadajacyPasazer(Pasazer pasazer){
        this.listaWysiadajacychPasazerow.remove(pasazer);
    }
    public List<Pasazer> getListaPasazerow() {
        return listaPasazerow;
    }

    public void setListaPasazerow(List<Pasazer> listaPasazerow) {
        this.listaPasazerow = listaPasazerow;
    }

    public void setObecnaLiczbaPasazerow(int obecnaLiczbaPasazerow) {
        this.obecnaLiczbaPasazerow = obecnaLiczbaPasazerow;
    }

    public int getsizeListaWysiadajacychPasazerow() {
        return listaWysiadajacychPasazerow.size();
    }

    public void setListaWysiadajacychPasazerow(List<Pasazer> listaWysiadajacychPasazerow) {
        this.listaWysiadajacychPasazerow = listaWysiadajacychPasazerow;
    }

    public int getObecnaLiczbaPasazerow() {

        return obecnaLiczbaPasazerow;
    }

    public int getMaksymalnaLiczbaPasazerow() {

        return maksymalnaLiczbaPasazerow;
    }

    public void stworzNowychPasazerow(int liczba) {
        for (int i = 0; i < liczba; i++) {
            Pasazer pasazer = new Pasazer();
        }
    }


    public Pasazerski(int maksymalnaLiczbaPasazerow) {
        this.maksymalnaLiczbaPasazerow = maksymalnaLiczbaPasazerow;
    }

    public Pasazerski() {
        Random random = new Random();
//        this.maksymalnaLiczbaPasazerow=random.nextInt(6)+5;
        this.maksymalnaLiczbaPasazerow=5;
    }

    public void znalezienieOsobWysiadajacych(Przystanek przystanek){
        for (int i = 0; i < listaPasazerow.size(); i++) {
//            if(this.listaPasazerow.get(i).getNastepnyPrzystanek()==przystanek || this.listaPasazerow.get(i).getPozostalaTrasa().isEmpty()){
            if(this.listaPasazerow.get(i).getNastepnyPrzystanek()==przystanek){
                this.listaWysiadajacychPasazerow.add(this.listaPasazerow.get(i));
                this.listaPasazerow.get(i).setMoznaWysiadac(true);
            }
//            if(this.listaPasazerow.get(i).getPozostalaTrasa().isEmpty()){
//            this.listaWysiadajacychPasazerow.add(this.listaPasazerow.get(i));
//            this.listaPasazerow.get(i).setMoznaWysiadac(true);
//            }
        }
    }

    public void usuwanie(){
        for (int i = 0; i < this.listaPasazerow.size(); i++) {
            this.listaPasazerow.get(i).usuwanie();
            this.listaPasazerow.get(i).setObecnePolozenie(null);
            this.listaPasazerow.remove(i);
        }
        this.listaWysiadajacychPasazerow.clear();
    }

    public boolean czyJestWolneMiejsce(Pasazer pasazer){
        if(this.getMaksymalnaLiczbaPasazerow()-this.getObecnaLiczbaPasazerow()>0){
            this.addPasazer(pasazer);
//            this.setObecnaLiczbaPasazerow(this.getObecnaLiczbaPasazerow()+1);
            this.obecnaLiczbaPasazerow++;
            return true;
        }
        else {
            return false;
        }
    }

    public List<Control> potrzebneInformacje(){
        List<Control> listaNodow = new ArrayList<>();
        ShowLabel showLabel = new ShowLabel("Maksymalna liczba pasazerow: "+Integer.toString(this.getMaksymalnaLiczbaPasazerow()));
        listaNodow.add(showLabel);
        ShowLabel showLabel1 = new ShowLabel("Aktualna liczba pasazerow: " + Integer.toString(this.getObecnaLiczbaPasazerow()));
        listaNodow.add(showLabel1);
        ShowLabel showLabel4 = new ShowLabel("Liczba pasazerow wysiadajacych: " + Integer.toString(this.listaWysiadajacychPasazerow.size()));
        listaNodow.add(showLabel4);
        ShowLabel showLabel3 = new ShowLabel("Pasazerowie na pokladzie: ");
        listaNodow.add(showLabel3);
        for (int i = 0; i < this.listaPasazerow.size(); i++) {
            Pasazer pasazer = this.listaPasazerow.get(i);
            ShowLabel showLabel2 = new ShowLabel(pasazer.getImie() + " " + pasazer.getNazwisko(),this.getListaPasazerow().get(i));
            listaNodow.add(showLabel2);
        }
        return  listaNodow;
    }

    public boolean czyWciazJestNaPrzystanku(Pasazer pasazer, Pojazd pojazd){
        if(pasazer.getObecnePolozenie() instanceof Przystanek){
//            Przystanek przystanek = (Przystanek) pasazer.getNastepnyPrzystanek();
//            if(!przystanek.getListaPojazdowZaparkowanych().contains(this)){
//                return false;
//            }
            Przystanek przystanek = (Przystanek) pasazer.getObecnePolozenie();
            if(!przystanek.getListaPojazdowZaparkowanych().contains(pojazd)){
                return false;
            }
        }
        return true;
    }

    public boolean czyWszystcyWysiedli(){
        if(this.listaWysiadajacychPasazerow.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
