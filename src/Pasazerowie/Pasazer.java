package Pasazerowie;

import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Pasazer implements ShowInfo {

    private UUID Identyfikator;
    private String imie;
    private String nazwisko;
    private long pesel;
    private int wiek;
    private Przystanek przystanekPoczatkowy;
    private Przystanek przystanekDocelowy;
    private PunktNaMapie obecnePolozenie;
    private boolean rodzajPodrozy;
    private List<Bilet> listaBiletow = new ArrayList<Bilet>();
    private boolean powrot;
    public Pasazer() {
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

    public boolean isRodzajPodrozy() {

        return rodzajPodrozy;
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

    public long getPesel() {

        return pesel;
    }

    public String getNazwisko() {

        return nazwisko;
    }

    public String getImie() {

        return imie;
    }

    public UUID getIdentyfikator() {

        return Identyfikator;
    }

    public void setObecnePolozenie(PunktNaMapie obecnePolozenie) {
        this.obecnePolozenie = obecnePolozenie;
    }

    @Override
    public void showInfo() {

    }

    public void znajdzTrase(){

    }
    public void zmianaTrasy(){

    }
    public void czekaj(){

    }
    public void poinformujPrzystanki(){

    }
}
