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
    private boolean rodzajPodrozy;
    private List<Bilet> listaBiletow = new ArrayList<Bilet>();
    private boolean powrot;

    public Pasazer() {
        Random random = new Random();
        this.identyfikator = UUID.randomUUID();
//        System.out.println(identyfikator);
        this.imie = GeneratorPasazerow.getInstance().getImie();
//        System.out.println(this.imie);
        this.nazwisko = GeneratorPasazerow.getInstance().getNazwisko();
//        System.out.println(this.nazwisko);
//        this.wiek = random.nextInt() %(100-18)+18;
        GregorianCalendar date = new GregorianCalendar();
        date.set(date.YEAR,random.nextInt(100)+1900);
        date.set(date.DAY_OF_YEAR, random.nextInt(date.getActualMaximum(date.DAY_OF_YEAR) - 1) + 1);
//        System.out.println(date.get(date.YEAR) + "-" + date.get(date.MONTH) + "-" + date.get(date.DAY_OF_MONTH));
        this.pesel=peselNextNumber(date.get(date.YEAR)%100)+peselNextNumber(date.get(date.MONTH))+peselNextNumber(date.get(date.DAY_OF_MONTH));
        for(int i=0;i<5;i++){
            this.pesel=this.pesel+Integer.toString(random.nextInt(10));
        }
//        System.out.println(pesel);
        GregorianCalendar dateNow = new GregorianCalendar();
//          System.out.println(date.get(date.YEAR) + "-" + date.get(date.MONTH) + "-" + date.get(date.DAY_OF_MONTH));
//        System.out.println(dateNow.get(date.YEAR) + "-" + dateNow.get(date.MONTH) + "-" + dateNow.get(date.DAY_OF_MONTH));
        /**********************!!!!!!!!!!!!!!!!!!!!!!PATRZ!!!!!!!!!!!!!!!!!!*/
        this.wiek=dateNow.get(date.YEAR)-date.get(date.YEAR);
//        System.out.println(this.wiek);
        this.rodzajPodrozy = random.nextBoolean();
//        System.out.println(this.rodzajPodrozy);
        this.powrot = false;
        this.przystanekPoczatkowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
        this.przystanekDocelowy = Swiat.getInstance().getListaPrzystankow().get(random.nextInt(Swiat.getInstance().getListaPrzystankow().size()));
        this.obecnePolozenie = this.przystanekPoczatkowy;
//        System.out.println(Swiat.getInstance().getListaPrzystankow().get(0));
//        System.out.println(this.przystanekPoczatkowy);
//        System.out.println(this.przystanekDocelowy);
//        System.out.println(this.obecnePolozenie);
    }

    private String peselNextNumber(int a){
        String result="";
        if(a<10)
            result="0";
        result+=a;
        return result;
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

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
