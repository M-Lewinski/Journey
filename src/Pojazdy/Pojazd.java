package Pojazdy;

import java.util.ArrayList;
import java.util.UUID;

import Mapa.PunktNaMapie;
import Pojazdy.Ladunki.TypLadunku;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

public abstract class Pojazd extends PunktNaMapie implements Runnable {
    private UUID identyfikator;
    private ArrayList<MiejsceZmianyKierunku> trasa;
    private int maksymalnaPredkosc;
    private TypLadunku Ladunek;

    public TypLadunku getLadunek() {
        return Ladunek;
    }

    public void zmianaTrasy(){

    }
    public ArrayList<MiejsceZmianyKierunku> getTrasa() {
        return trasa;
    }

    public int getMaksymalnaPredkosc() {
        return maksymalnaPredkosc;
    }

    public UUID getIdentyfikator() {
        return identyfikator;
    }


    public void setTrasa(ArrayList<MiejsceZmianyKierunku> trasa) {
        this.trasa = trasa;
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
