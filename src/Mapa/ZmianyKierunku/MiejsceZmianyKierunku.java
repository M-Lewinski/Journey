package Mapa.ZmianyKierunku;

import Mapa.Monitoring;
import Drogi.Droga;
import Mapa.PunktNaMapie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;
import Pojazdy.Powietrzne.Samolot;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Label;
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
    private boolean zajetaPrzestrzen=false;
    private double promien;
    private Shape outRing;
    private Color color;
    private double promienOuterRing;
    private Monitoring kontrolaLotow = new Monitoring();
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

    public void poinformujSkrzyzowanie(){

    }

    public void zajmij(Pojazd pojazd){
        this.zajetaPrzestrzen=true;
        this.listaPojazdowOczekujacych.remove(pojazd);
    }
    public void zwolnij(){
        this.zajetaPrzestrzen=false;
        poinformujPojazdy();
    }
    public void poinformujPojazdy(){
        Pojazd pojazd = this.getListaPojazdowOczekujacych().get(0);
    }

    public void ladowanie(Pojazd pojazd){
        synchronized (kontrolaLotow){
            Boolean czyToJestMiejsceDoLadowania=false;
            if(this instanceof Przystanek){
                Przystanek przystanek = (Przystanek) this;
                if (pojazd.czyMozeTutajLadowac(this)==true){
                    if(pojazd instanceof Samolot){
                        if(przystanek.getMaksymalnaPojemnosc()==0){
                            return;
                        }
                        przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc()-1);
                    }
                    czyToJestMiejsceDoLadowania=true;
                    przystanek.addPojazdZaparkowany(pojazd);
                }
            }
            pojazd.poruszSie();
            pojazd.setObecnePolozenie(this);
            pojazd.getDrogaTeraz().removeListaPojazdow(pojazd);
            pojazd.getPozostalaTrasa().remove(0);
            if(czyToJestMiejsceDoLadowania==true){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        pojazd.getImageNode().visibleProperty().setValue(false);
                    }
                });
                pojazd.ladowanie();
            }
            else{
                this.startowanie(pojazd);
            }
        }
    }

    public void startowanie(Pojazd pojazd){
        synchronized (kontrolaLotow){
            pojazd.nastepnaDroga();
//            pojazd.getDrogaTeraz().addListaPojazdow(pojazd);
            if(this instanceof Przystanek){
                Przystanek przystanek = (Przystanek) this;
                if(przystanek.getListaPojazdowZaparkowanych().contains(pojazd)){
                    if(pojazd instanceof Samolot){
                        przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc()+1);
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            pojazd.getImageNode().visibleProperty().setValue(true);
                        }
                    });
                    przystanek.removePojazdZaparkowany(pojazd);
                }
            }
            pojazd.poruszSie();
        }
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
