package Mapa.ZmianyKierunku.Przystanki;

import Pasazerowie.Pasazer;
import Pojazdy.Pojazd;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Przystanek extends MiejsceZmianyKierunku {
    private List<Pojazd> listaPojazdowZaparkowanych = new ArrayList<Pojazd>();
    private List<Pasazer> listaPasazerowOczekujacych = new ArrayList<Pasazer>();
    private List<Pojazd> listaPojazdowPrzyjezdzajacych = new ArrayList<Pojazd>();
    private List<Pasazer> listaPasazerowPrzyjezdzajacych = new ArrayList<Pasazer>();
    public void poinformujPasazerow(){

    }

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

    public void addPojazdZaparkowany(Pojazd pojazd){
        this.listaPojazdowZaparkowanych.add(pojazd);
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

    public void removePojazdZaparkowany(Pojazd pojazd){
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

    public Przystanek(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen);
    }
    public Przystanek(){

    }
}
