package Mapa.ZmianyKierunku.Przystanki;

import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.ArrayList;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Przystanek extends MiejsceZmianyKierunku {
    private ArrayList<Pojazd> listaPojazdow;
    private ArrayList<Pasazer> listaPasazerow;
    private ArrayList<Pojazd> listaPrzyjezdzajacychPojazdow;
    private ArrayList<Pasazer> listaPrzyjezdzajacychPasazerow;
    public ArrayList<Pojazd> getListaPrzyjezdzajacychPojazdow() {
        return listaPrzyjezdzajacychPojazdow;
    }

    public void dodajPojazd(){

    }
    public void usunPojazd(){

    }
    public void poinformujPasazerow(){

    }

    public ArrayList<Pasazer> getListaPrzyjezdzajacychPasazerow() {
        return listaPrzyjezdzajacychPasazerow;
    }

    public void setListaPrzyjezdzajacychPasazerow(ArrayList<Pasazer> listaPrzyjezdzajacychPasazerow) {
        this.listaPrzyjezdzajacychPasazerow = listaPrzyjezdzajacychPasazerow;
    }

    public void setListaPrzyjezdzajacychPojazdow(ArrayList<Pojazd> listaPrzyjezdzajacychPojazdow) {
        this.listaPrzyjezdzajacychPojazdow = listaPrzyjezdzajacychPojazdow;
    }

    public ArrayList<Pasazer> getListaPasazerow() {
        return listaPasazerow;
    }

    public void setListaPasazerow(ArrayList<Pasazer> listaPasazerow) {
        this.listaPasazerow = listaPasazerow;
    }

    public void setListaPojazdow(ArrayList<Pojazd> listaPojazdow) {
        this.listaPojazdow = listaPojazdow;
    }

    public ArrayList<Pojazd> getListaPojazdow() {

        return listaPojazdow;
    }

}
