package Pojazdy.Ladunki;

import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pasazerowie.Pasazer;

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

    public void removePasazer(Pasazer pasazer){
        this.listaPasazerow.remove(pasazer);
    }

    public void addWysiadajacyPasazer(Pasazer pasazer){
        this.listaPasazerow.add(pasazer);
    }

    public void removeWysiadajacyPasazer(Pasazer pasazer){
        this.listaPasazerow.remove(pasazer);
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

    public int getObecnaLiczbaPasazerow() {

        return obecnaLiczbaPasazerow;
    }

    public int getMaksymalnaLiczbaPasazerow() {

        return maksymalnaLiczbaPasazerow;
    }

    public void stworzNowychPasazerow() {
        for (int i = 0; i < this.getMaksymalnaLiczbaPasazerow(); i++) {
            Pasazer pasazer = new Pasazer();
        }
    }


    public Pasazerski(int maksymalnaLiczbaPasazerow) {
        this.maksymalnaLiczbaPasazerow = maksymalnaLiczbaPasazerow;
        stworzNowychPasazerow();
    }

    public Pasazerski() {
        Random random = new Random();
//        this.maksymalnaLiczbaPasazerow=random.nextInt(6)+5;
        this.maksymalnaLiczbaPasazerow=100;
        stworzNowychPasazerow();
    }

    public void znalezienieOsobWysiadajacych(Przystanek przystanek){
        for (int i = 0; i < listaPasazerow.size(); i++) {
            if(this.listaPasazerow.get(i).getNastepnyPrzystanek()==przystanek){
                this.listaWysiadajacychPasazerow.add(this.listaPasazerow.get(i));
                this.listaPasazerow.get(i).setMoznaWysiadac(true);
            }
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
}
