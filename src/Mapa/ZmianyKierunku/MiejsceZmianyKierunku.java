package Mapa.ZmianyKierunku;

import Drogi.Droga;
import Gui.MainPanel;
import Mapa.PunktNaMapie;
import Mapa.Swiat;
import Pojazdy.Pojazd;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

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
    private double promien;
    private Shape outRing;
    private Color color;
    private double promienOuterRing;
    //    private int promien=10;
//    private javafx.scene.shape.Shape imageNode;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getPromien() {
        return promien;
    }

    public void setPromien(double promien) {
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

    public double getPromienOuterRing() {
        return promienOuterRing;
    }

    public void setPromienOuterRing(double promienOuterRing) {
        this.promienOuterRing = promienOuterRing;
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

    public MiejsceZmianyKierunku(double dlugosc, double szerokosc, double polozenieX, double polozenieY, boolean zajetaPrzestrzen, String nazwa) {
        super(dlugosc, szerokosc);
        this.setPolozenieX(polozenieX);
        this.setPolozenieY(polozenieY);
        this.zajetaPrzestrzen = zajetaPrzestrzen;
        this.nazwa = nazwa;
        Swiat.getInstance().addMiejsceZmianyKierunku(this);
        this.setPromien(10);
        this.promienOuterRing = this.promien*2 + 15;
    }
    public MiejsceZmianyKierunku(){

    }

    public Shape getOutRing() {
        return outRing;
    }

    public void setOutRing(Shape outRing) {
        this.outRing = outRing;
    }

    @Override
    public void rysuj(Group group) {
//        Circle circle = new Circle(this.getPolozenieX(),this.getPolozenieY(),this.getPromien());
        this.setImageNode(new Circle(this.getPolozenieX(),this.getPolozenieY(),this.getPromien()));
        this.setOutRing(new Circle(this.getPolozenieX(),this.getPolozenieY(),this.promienOuterRing));
//        this.setImageNode(innerCircle);
//        this.setOutRing(outterCicle);
        this.getOutRing().setStrokeWidth(2);
        this.getOutRing().setFill(Color.TRANSPARENT);
        this.getImageNode().setFill(this.color);
        this.getImageNode().setStroke(this.color);
        this.getOutRing().setStroke(this.color);
        group.getChildren().add(this.getImageNode());
        group.getChildren().add(this.getOutRing());
        Label label = new Label(this.getNazwa());
        label.setLayoutX(this.getPolozenieX()-this.promienOuterRing/2);
        label.setLayoutY(this.getPolozenieY()-this.promienOuterRing-15);
        group.getChildren().add(label);
    }
}
