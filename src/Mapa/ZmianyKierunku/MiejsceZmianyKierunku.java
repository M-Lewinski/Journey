package Mapa.ZmianyKierunku;

import Drogi.Droga;
import Mapa.PunktNaMapie;
import Mapa.Swiat;
import Pojazdy.Pojazd;
import com.sun.javafx.geom.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.omg.PortableServer.IMPLICIT_ACTIVATION_POLICY_ID;

import java.awt.*;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class MiejsceZmianyKierunku extends PunktNaMapie {
    private String nazwa;
    private List<Pojazd> listaPojazdowOczekujacych= new ArrayList<Pojazd>();
    private List<Droga> listaDrog = new ArrayList<Droga>();
    private boolean zajetaPrzestrzen;
    private int promien;
//    private int promien=10;
//    private javafx.scene.shape.Shape imageNode;
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPromien() {
        return promien;
    }

    public void setPromien(int promien) {
        this.promien = promien;
    }

    public List<Droga> getListaDrog() {
        return listaDrog;
    }

    public void setListaDrog(List<Droga> listaDrog) {
        this.listaDrog = listaDrog;
    }

    public void addListaDrog(Droga droga){
        listaDrog.add(droga);
    }

    public void removeListaDrog(Droga droga){
        listaDrog.remove(droga);
    }

    public List<Pojazd> getListaPojazdowOczekujacych() {
        return listaPojazdowOczekujacych;
    }

    public void setListaPojazdowOczekujacych(List<Pojazd> listaPojazdowOczekujacych) {
        this.listaPojazdowOczekujacych = listaPojazdowOczekujacych;
    }

    public boolean isZajetaPrzestrzen() {
        return zajetaPrzestrzen;
    }

    public void setZajetaPrzestrzen(boolean zajetaPrzestrzen) {
        this.zajetaPrzestrzen = zajetaPrzestrzen;
    }

    public void addPojazdOczekujacy(Pojazd pojazd){
        this.listaPojazdowOczekujacych.add(pojazd);
    }
    public void removePojazdOczekujacy(Pojazd pojazd){
        this.listaPojazdowOczekujacych.remove(pojazd);
    }


//    public javafx.scene.shape.Shape getImageNode() {
//        return imageNode;
//    }
//
//    public void setImageNode(javafx.scene.shape.Shape imageNode) {
//        this.imageNode = imageNode;
//    }

    public void zajmij(){

    }
    public void zwolnij(){

    }
    public void poinformuj(){

    }

    public MiejsceZmianyKierunku(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, String nazwa) {
        super(dlugosc, szerokosc);
        this.setPolozenieX(polozenieX);
        this.setPolozenieY(polozenieY);
        this.zajetaPrzestrzen = zajetaPrzestrzen;
        this.nazwa = nazwa;
        Swiat.getInstance().addMiejsceZmianyKierunku(this);
    }
    public MiejsceZmianyKierunku(){

    }

    @Override
    public void rysuj(Pane panel) {
//        Circle circle = new Circle(this.getPolozenieX(),this.getPolozenieY(),this.getPromien());
        this.setImageNode(new Circle(this.getPolozenieX(),this.getPolozenieY(),this.getPromien()));
    }
}
