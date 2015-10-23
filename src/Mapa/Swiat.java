package Mapa;

import Drogi.Droga;
import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.ArrayList;

public class Swiat extends ObiektGraficzny {
    private ArrayList<Pasazer> listaPasazerow;
    private ArrayList<Droga> listadrog;
    private ArrayList<Pojazd> listaPojazdow;
    private ArrayList<MiejsceZmianyKierunku> miejsceZmianyKierunku;
    private ArrayList<Pasazer> listaPasazerowBezTrasy;
    private ArrayList<Droga> listaDrog;
    public Swiat() {
    }

    public ArrayList<Droga> getListaDrog() {
        return listaDrog;
    }

    public void setListaDrog(ArrayList<Droga> listaDrog) {
        this.listaDrog = listaDrog;
    }

    public ArrayList<Pasazer> getListaPasazerowBezTrasy() {
        return listaPasazerowBezTrasy;
    }

    public void setListaPasazerowBezTrasy(ArrayList<Pasazer> listaPasazerowBezTrasy) {
        this.listaPasazerowBezTrasy = listaPasazerowBezTrasy;
    }

    public void setListaPasazerow(ArrayList<Pasazer> listaPasazerow) {
        this.listaPasazerow = listaPasazerow;
    }

    public ArrayList<Pasazer> getListaPasazerow() {

        return listaPasazerow;
    }

    public ArrayList<Droga> getListadrog() {
        return listadrog;
    }

    public void setListadrog(ArrayList<Droga> listadrog) {
        this.listadrog = listadrog;
    }

    public ArrayList<Pojazd> getListaPojazdow() {
        return listaPojazdow;
    }

    public void setListaPojazdow(ArrayList<Pojazd> listaPojazdow) {
        this.listaPojazdow = listaPojazdow;
    }

    public ArrayList<MiejsceZmianyKierunku> getMiejsceZmianyKierunku() {
        return miejsceZmianyKierunku;
    }

    public void setMiejsceZmianyKierunku(ArrayList<MiejsceZmianyKierunku> miejsceZmianyKierunku) {
        this.miejsceZmianyKierunku = miejsceZmianyKierunku;
    }
    public void poinformuj(){

    }
    public static void main(String[] args) {

    }
}
