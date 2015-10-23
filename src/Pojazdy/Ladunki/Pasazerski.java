package Pojazdy.Ladunki;

import Pasazerowie.Pasazer;

import java.util.ArrayList;

/**
 * Created by Lewin on 2015-10-19.
 */
public class Pasazerski extends TypLadunku {
    private int maksymalnaLiczbaPasazerow;
    private int obecnaLiczbaPasazerow;
    private ArrayList<Pasazer> listaPasazerow;

    public ArrayList<Pasazer> getListaPasazerow() {
        return listaPasazerow;
    }

    public void setListaPasazerow(ArrayList<Pasazer> listaPasazerow) {
        this.listaPasazerow = listaPasazerow;
    }

    public void setObecnaLiczbaPasazerow(int obecnaLiczbaPasazerow) {
        this.obecnaLiczbaPasazerow = obecnaLiczbaPasazerow;
    }

    public int getObecnaLiczbaPasazerow() {

        return obecnaLiczbaPasazerow;
    }

    public int getMaksymalnaLiczbaPasazerow() {

        return maksymalnaLiczbaPasazerow;
    }

}
