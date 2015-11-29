package Drogi;

import Mapa.Rysowanie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/** Klasa droga implementujaca abstrakcje obiektu droga.
 * Created by Lewin on 2015-10-18.
 */
public abstract class Droga implements Rysowanie {
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
    private double odleglosc;
    private int poprawkaX;
    private int poprawkaY;
    public void setKoniec(MiejsceZmianyKierunku koniec) {
        this.koniec = koniec;
    }

    public void setPoczatek(MiejsceZmianyKierunku poczatek) {

        this.poczatek = poczatek;
    }

    public MiejsceZmianyKierunku getKoniec() {

        return koniec;
    }

    public MiejsceZmianyKierunku getPoczatek() {

        return poczatek;
    }

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
    public Droga(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec,int poprawkaX, int poprawkaY) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.odleglosc = Math.sqrt(Math.pow(poczatek.getPolozenieX()-koniec.getPolozenieX(),2.0) + Math.pow(poczatek.getPolozenieY()-koniec.getPolozenieY(),2.0));
        this.odleglosc = odleglosc;
        Swiat.getInstance().addDroga(this);
        this.poczatek.addListaDrog(this);
        this.poprawkaX=poprawkaX;
        this.poprawkaY=poprawkaY;
//        System.out.println("Dlugosc drogi: " + this.odleglosc);
    }

    /**
     * Pusty konstruktor drogi.
     */
    public Droga(){

    }
    @Override
    public void rysuj(Pane panel) {
        Line line = new Line(poczatek.getPolozenieX(),poczatek.getPolozenieY(),koniec.getPolozenieX(),koniec.getPolozenieY());
        line.setStroke(Color.ORANGE);
        panel.getChildren().add(line);
    }
}
