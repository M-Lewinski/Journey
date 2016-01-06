package Drogi;

import Mapa.Monitoring;
import Mapa.Rysowanie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Pojazdy.Pojazd;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/** Klasa droga implementujaca abstrakcje obiektu droga.
 * Created by Lewin on 2015-10-18.
 */
public abstract class Droga implements Rysowanie {
    private Monitoring hulkDrogi = new Monitoring();
    /**
     * poczatek drogi.
     */
    private MiejsceZmianyKierunku poczatek;
    /**
     * koniec drogi.
     */
    private MiejsceZmianyKierunku koniec;
    /**
     *  odleglosc miedzy punktem poczatkowym a koncowym.
     */
    private ArrayList<Pojazd> listaPojazdow = new ArrayList<Pojazd>();
    private double odleglosc;
    private double angle;
//    private double sinDrogi;
//    private double cosDrogi;
    private Odcinek wylot;
    private Odcinek przelot;
    private Odcinek ladowanie;
//    private double pozycjaPoczatkowaX;
//    private double PozycjaPoczatkowaY;
//    private double odlegloscPoczatek;
//    private double katPoczatkowy;
//    private double pozycjaKoncowaX;
//    private double pozycjaKoncowaY;
//    private double odlegloscKoniec;
//    private double katKoncowy;
//    private double odlegloscMiedzyMiejscamiZmianyKierunku;
//    private Shape imageNode;
    private Color color;

    public void setKoniec(MiejsceZmianyKierunku koniec) {
        this.koniec = koniec;
    }

    public void setPoczatek(MiejsceZmianyKierunku poczatek) {

        this.poczatek = poczatek;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public MiejsceZmianyKierunku getKoniec() {

        return koniec;
    }

    public MiejsceZmianyKierunku getPoczatek() {

        return poczatek;
    }

    public Monitoring getHulkDrogi() {
        return hulkDrogi;
    }

    public void setHulkDrogi(Monitoring hulkDrogi) {
        this.hulkDrogi = hulkDrogi;
    }

    public ArrayList<Pojazd> getListaPojazdow() {
        return listaPojazdow;
    }

    public void setListaPojazdow(ArrayList<Pojazd> listaPojazdow) {
        this.listaPojazdow = listaPojazdow;
    }

    public void addListaPojazdow(Pojazd pojazd){
        this.listaPojazdow.add(pojazd);
    }
    public void removeListaPojazdow(Pojazd pojazd){
        this.listaPojazdow.remove(pojazd);
    }
    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Odcinek getWylot() {
        return wylot;
    }

    public void setWylot(Odcinek wylot) {
        this.wylot = wylot;
    }

    public Odcinek getPrzelot() {
        return przelot;
    }

    public void setPrzelot(Odcinek przelot) {
        this.przelot = przelot;
    }

    public Odcinek getLadowanie() {
        return ladowanie;
    }

    public void setLadowanie(Odcinek ladowanie) {
        this.ladowanie = ladowanie;
    }

    //    public double getCosDrogi() {
//        return cosDrogi;
//    }
//
//    public void setCosDrogi(double cosDrogi) {
//        this.cosDrogi = cosDrogi;
//    }
//
//    public double getSinDrogi() {
//        return sinDrogi;
//    }
//
//    public void setSinDrogi(double sinDrogi) {
//        this.sinDrogi = sinDrogi;
//    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    /**
     * Konstruktor klasy droga, ktort wykorzystuje odziedziczony konstryktor.
     * @param poczatek poczatek drogi.
     * @param koniec koniec drogi.
     */
    public Droga(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.odleglosc = Math.sqrt(Math.pow(poczatek.getPolozenieX()-koniec.getPolozenieX(),2.0) + Math.pow(poczatek.getPolozenieY()-koniec.getPolozenieY(),2.0));
        this.odleglosc = odleglosc;
        Swiat.getInstance().addDroga(this);
        this.poczatek.addListaDrog(this);
        this.okreslKat();
//        System.out.println("Dlugosc drogi: " + this.odleglosc);
    }

//    public Shape getImageNode() {
//        return imageNode;
//    }
//
//    public void setImageNode(Shape imageNode) {
//        this.imageNode = imageNode;
//    }

    /**
     * Pusty konstruktor drogi.
     */
    public Droga(){

    }
    @Override
    public void rysuj(Group group) {
//        imageNode = new Line(poczatek.getPolozenieX(),poczatek.getPolozenieY(),koniec.getPolozenieX(),koniec.getPolozenieY());
//        imageNode.setStroke(this.color);
//        group.getChildren().add(imageNode);
        proba(group);
//        line.setStroke(Color.ORANGE);
//        panel.getChildren().add(line);
    }

//    public double getAngle() {
//        return angle;
//    }
//
//    public void setAngle(double angle) {
//        this.angle = angle;
//    }

    public void okreslKat(){
        this.angle=Math.atan2(-poczatek.getPolozenieX()+koniec.getPolozenieX(),-poczatek.getPolozenieY()+koniec.getPolozenieY());
//        this.sinDrogi = Math.sin(this.angle);
//        this.cosDrogi = Math.cos(this.angle);
    }

    public void proba(Group group){
        double zmianaXP=0.0;
        double zmianaYP=0.0;
        double zmianaXK=0.0;
        double zmianaYK=0.0;
        double kat = this.angle;
        double przerwa=13.0;
        double promienP = this.getPoczatek().getPromienOuterRing();
        double promienK = this.getKoniec().getPromienOuterRing();
        double poczatekX = this.getPoczatek().getPolozenieX();
        double poczatekY = this.getPoczatek().getPolozenieY();
        double koniecX = this.getKoniec().getPolozenieX();
        double koniecY = this.getKoniec().getPolozenieY();
        double gamma;
        double beta;
        double betaPRIM;
        if((Math.toDegrees(angle)>0) && (Math.toDegrees(angle)<=90)) {
            gamma = Math.asin(przerwa / promienP);
            beta = kat - gamma;
            zmianaXP = Math.sin(beta) * promienP;
            zmianaYP = Math.cos(beta) * promienP;
            betaPRIM = Math.toRadians(90) - kat - gamma;
            zmianaXK = -Math.cos(betaPRIM) * promienP;
            zmianaYK = -Math.sin(betaPRIM) * promienP;
            if(this instanceof DrogaPowietrzna){
                this.color=Color.RED;
            }
            else{
                this.color=Color.BLUE;
            }
        }
        else if((Math.toDegrees(angle)>=-180) && (Math.toDegrees(angle)<=-90)){
            kat = angle - Math.toRadians(180);
            gamma = Math.asin(-przerwa / promienP);
            beta = kat - gamma;
            zmianaXK = Math.sin(beta) * promienP;
            zmianaYK = Math.cos(beta) * promienP;
            betaPRIM = Math.toRadians(90) - kat - gamma;
            zmianaXP = -Math.cos(betaPRIM) * promienP;
            zmianaYP = -Math.sin(betaPRIM) * promienP;
            if(this instanceof DrogaPowietrzna){
                this.color = Color.ORANGE;
            }
            else{
                this.color = Color.CYAN;
            }

        }
        else if ((Math.toDegrees(angle)>=90)&&(Math.toDegrees(angle)<=180)){
            gamma = Math.asin(przerwa / promienP);
            beta = kat - Math.toRadians(90) - gamma;
            zmianaXP = Math.cos(beta)*promienP;
            zmianaYP = -Math.sin(beta)*promienP;
            betaPRIM = beta + 2*gamma;
            zmianaXK = -promienK*Math.cos(betaPRIM);
            zmianaYK = promienK*Math.sin(betaPRIM);
            if(this instanceof DrogaPowietrzna){
                this.color=Color.RED;
            }
            else{
                this.color=Color.BLUE;
            }
        }
        else if((Math.toDegrees(angle)<=0)&&(Math.toDegrees(angle)>=-90)){
            kat = angle - Math.toRadians(180);
            gamma = Math.asin(-przerwa / promienP);
            beta = kat - Math.toRadians(90) - gamma;
            zmianaXK = Math.cos(beta)*promienP;
            zmianaYK = -Math.sin(beta)*promienP;
            betaPRIM = beta + 2*gamma;
            zmianaXP = -promienK*Math.cos(betaPRIM);
            zmianaYP = promienK*Math.sin(betaPRIM);
            if(this instanceof DrogaPowietrzna){
                this.color = Color.ORANGE;
            }
            else{
                this.color = Color.CYAN;
            }
        }
        else{
            return;
        }
//        this.katPoczatkowy=beta;
//        this.katKoncowy=betaPRIM;
//        System.out.println(this.poczatek.getNazwa()+" kat normalny " + Math.toDegrees(this.angle));
        double katMiedzy = Math.atan2(zmianaXP,zmianaYP);
//        System.out.println(this.poczatek.getNazwa()+" kat wylotu "+Math.toDegrees(katMiedzy));
//        double katMiedzy = Math.atan2(zmianaYP,zmianaXP);
        this.wylot = new Odcinek(katMiedzy,poczatekX,poczatekY,poczatekX+zmianaXP,poczatekY+zmianaYP,this.color);
        katMiedzy = Math.atan2(koniecX+zmianaXK-(poczatekX+zmianaXP),koniecY+zmianaYK-(poczatekY+zmianaYP));
//        katMiedzy = Math.atan2(koniecY+zmianaYK-(poczatekY+zmianaYP),koniecX+zmianaXK-(poczatekX+zmianaXP));
//        System.out.println(this.poczatek.getNazwa()+" kat przelatu "+Math.toDegrees(katMiedzy));
        this.przelot = new Odcinek(katMiedzy,poczatekX+zmianaXP,poczatekY+zmianaYP,koniecX+zmianaXK,koniecY+zmianaYK,this.color);
        katMiedzy = Math.atan2(-zmianaXK,-zmianaYK);
//        katMiedzy = Math.atan2(zmianaYK,zmianaXK);
//        System.out.println(this.poczatek.getNazwa()+" kat ladowania "+Math.toDegrees(katMiedzy));
        this.ladowanie = new Odcinek(katMiedzy,koniecX+zmianaXK,koniecY+zmianaYK,koniecX,koniecY,this.color);
//        System.out.println(this.poczatek.getNazwa() + " odcinek normalny: "+this.odleglosc);
//        System.out.println(this.poczatek.getNazwa() + " odcinek wylotu: " + this.wylot.getDlugosc());
//        System.out.println(this.poczatek.getNazwa() + " odcinek przelotu: " + this.przelot.getDlugosc());
//        System.out.println(this.poczatek.getNazwa() + " odcinek ladowania: "+ this.ladowanie.getDlugosc());
    }

    public double iloscKrokow(int statusPodrozy){
        double steps=0.0;
        switch (statusPodrozy){
            case 0:
//                steps=this.wylot.getDlugosc()/predkoscPojazdu;
                steps=this.wylot.getDlugosc();
                break;
            case 1:
//                steps=this.przelot.getDlugosc()/predkoscPojazdu;
                steps=this.przelot.getDlugosc();
                break;
            case 2:
//                steps=this.ladowanie.getDlugosc()/predkoscPojazdu;
                steps=this.ladowanie.getDlugosc();
                break;
        }
        return steps;
    }

    public double sinusDrogi(int statusPodrozy){
        double sin=0.0;
        switch (statusPodrozy){
            case 0:
                sin=this.wylot.getSinOdcinka();
                break;
            case 1:
                sin=this.przelot.getSinOdcinka();
                break;
            case 2:
                sin=this.ladowanie.getSinOdcinka();
                break;
        }
        return sin;
    }

    public double cosinusDrogi(int statusPodrozy){
        double cos=0.0;
        switch (statusPodrozy){
            case 0:
                cos=this.wylot.getCosOdcinka();
                break;
            case 1:
                cos=this.przelot.getCosOdcinka();
                break;
            case 2:
                cos=this.ladowanie.getCosOdcinka();
                break;
        }
        return cos;
    }

    public boolean czyDojdzieDoZderzenia(Pojazd pojazd, double przesuniecie){
        synchronized (hulkDrogi){
//            List<Pojazd> listaPojazdowNaDrodze = new ArrayList<>(this.getListaPojazdow());
            List<Pojazd> listaPojazdowNaDrodze = new ArrayList<>();
            listaPojazdowNaDrodze.addAll(this.listaPojazdow);
//            for (int i = 0; i < this.getListaPojazdow().size(); i++) {
            for (int i = 0; i < listaPojazdowNaDrodze.size(); i++) {
//                Pojazd pojazdNaDrodze = this.getListaPojazdow().get(i);
                Pojazd pojazdNaDrodze = listaPojazdowNaDrodze.get(i);
                if (pojazdNaDrodze == pojazd) {
                        continue;
                }
//                if(pojazdNaDrodze.getImageNode().visibleProperty().get() == true){
                    if (pojazdNaDrodze.isWidocznosc() == true) {
                        double odleglosc1 = pojazd.getOdlegloscDoKonca();
                        double odleglosc2 = pojazdNaDrodze.getOdlegloscDoKonca();
                        double odlegloscMiedzyPojazdami = Math.abs(odleglosc1 - odleglosc2);
                        if (odlegloscMiedzyPojazdami < pojazd.getImagePromien() + pojazdNaDrodze.getImagePromien() + przesuniecie) {
                            if (odleglosc1 > odleglosc2) {
                                try {
                                    hulkDrogi.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                        }
                    }

            }
//            for (int i = 0; i < Swiat.getInstance().getListaWolnychPojazdow().size(); i++) {
//                if()
//            }
//            hulkDrogi.notify();
            hulkDrogi.notifyAll();
            return false;
        }
    }

    public void notifyHulkaDrogi(){
        synchronized (hulkDrogi) {
            hulkDrogi.notifyAll();
        }
    }

    public double calaOdlegloscDrogi(){
        return this.wylot.getDlugosc() + this.przelot.getDlugosc() + this.ladowanie.getDlugosc();
    }

}
