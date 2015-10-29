package Pasazerowie;

import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;

import java.util.*;

/**
 * Created by Lewin on 2015-10-18.
 */
public class Pasazer implements ShowInfo,Runnable {

    private UUID identyfikator;
    private String imie;
    private String nazwisko;
    private String pesel;
    private int wiek;
    private Przystanek przystanekPoczatkowy;
    private Przystanek przystanekDocelowy;
    private PunktNaMapie obecnePolozenie;
    private boolean podrozSluzbowa;
    private int czasPostoju;
    private List<Bilet> listaBiletow = new ArrayList<Bilet>();
    private boolean powrot;

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
        if (this.podrozSluzbowa == true){
            this.czasPostoju = random.nextInt(15)+5;
        }
        else{
            this.czasPostoju = random.nextInt(30)+15;
        }
        this.czasPostoju = random.nextInt(30)+30;
        this.przystanekPoczatkowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
        this.przystanekDocelowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
        this.obecnePolozenie = this.przystanekPoczatkowy;
    }

    private String peselNextNumber(int a){
        String result="";
        if(a<10)
            result="0";
        result+=a;
        return result;
    }

    public int getCzasPostoju() {
        return czasPostoju;
    }

    public void setCzasPostoju(int czasPostoju) {
        this.czasPostoju = czasPostoju;
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
    public void outconsole(){
        System.out.println("Identyfikator: " + this.identyfikator);
        System.out.println("Imie: " + this.imie);
        System.out.println("Nazwisko: " + this.nazwisko);
        System.out.println("Wiek: " + this.wiek);
        System.out.println("Pesel: " + this.pesel);
//        System.out.println(this.obecnePolozenie);
        System.out.println("Przystanek poczatkowy: " + this.przystanekPoczatkowy.getNazwa());
        System.out.println("Przystanek docelowy: " + this.przystanekDocelowy.getNazwa());
        System.out.println("Czy jest w podrozy sluzbowej? " + this.podrozSluzbowa);
        System.out.println("Czas postoju w punkcie docelowym: " + this.czasPostoju);
        System.out.println("Czy wraca: " + this.powrot);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
