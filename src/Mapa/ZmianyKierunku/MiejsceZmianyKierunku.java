package Mapa.ZmianyKierunku;

import Gui.Informacja;
import Gui.ShowLabel;
import Mapa.Monitoring;
import Drogi.Droga;
import Mapa.PunktNaMapie;
import Mapa.ShowInfo;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;
import Pojazdy.Powietrzne.Samolot;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class MiejsceZmianyKierunku extends PunktNaMapie implements ShowInfo {
    private String nazwa;
    private List<Pojazd> listaPojazdowOczekujacych= new ArrayList<Pojazd>();
    private List<Droga> listaDrog = new ArrayList<Droga>();
    private boolean zajetaPrzestrzen=false;
    private Pojazd obecnieZajmuje = null;
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

//    public void poinformujSkrzyzowanie(){
//
//    }
//
//    public void zajmij(Pojazd pojazd){
//        this.zajetaPrzestrzen=true;
//        this.listaPojazdowOczekujacych.remove(pojazd);
//    }
//    public void zwolnij(){
//        this.zajetaPrzestrzen=false;
//        poinformujPojazdy();
//    }
//
//    public void poinformujPojazdy(){
//        Pojazd pojazd = this.getListaPojazdowOczekujacych().get(0);
//    }


    public Pojazd getObecnieZajmuje() {
        return obecnieZajmuje;
    }

    public void setObecnieZajmuje(Pojazd obecnieZajmuje) {
        this.obecnieZajmuje = obecnieZajmuje;
    }

    public void ladowanie(Pojazd pojazd){
        Boolean czyToJestMiejsceDoLadowania=false;
        synchronized (kontrolaLotow) {
            synchronized (pojazd.getHulkPojazdu()) {
//               if(pojazd.przedLadowaniem(this)==false){
//                   return;
//               }
                this.obecnieZajmuje = pojazd;
//                if (this instanceof Przystanek) {
                    if (pojazd.czyMozeTutajLadowac(this) == true) {
                        Przystanek przystanek = (Przystanek) this;
                        if (pojazd instanceof Samolot) {
                            if (przystanek.getMaksymalnaPojemnosc() < 1) {
                                return;
                            }
                            przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc() - 1);
                        }
                        czyToJestMiejsceDoLadowania = true;
                    }
//                }
                pojazd.poruszSie();
                pojazd.setObecnePolozenie(this);
                pojazd.getDrogaTeraz().removeListaPojazdow(pojazd);
                pojazd.getPozostalaTrasa().remove(0);
                pojazd.setDrogaTeraz(null);
                if (czyToJestMiejsceDoLadowania == true) {
                    pojazd.setWidocznosc(false);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            pojazd.getImageNode().visibleProperty().setValue(false);
                        }
                    });
                    Przystanek przystanek = (Przystanek) this;
//                    pojazd.setNastepnyPrzystanek(pojazd.nastepneMozliweLadowanie(pojazd.getTrasa(), pojazd.getObecnePolozenie()));
                    pojazd.ladowanie(przystanek);

                    pojazd.setNastepnyPrzystanek(pojazd.nastepneMozliweLadowanie(pojazd.getPozostalaTrasa(), pojazd.getObecnePolozenie()));
                    przystanek.addPojazdZaparkowany(pojazd);


                } else {
                    this.startowanie(pojazd);
                }
                this.obecnieZajmuje=null;
            }
        }
    }

    public void startowanie(Pojazd pojazd){
        synchronized (kontrolaLotow) {
            synchronized (pojazd.getHulkPojazdu()) {
                if(pojazd.przedStartowaniem()==false){
                    return;
                }
                this.obecnieZajmuje = pojazd;
//            pojazd.getDrogaTeraz().addListaPojazdow(pojazd);
                pojazd.nastepnaDroga();
                if (this instanceof Przystanek) {
                    Przystanek przystanek = (Przystanek) this;
                    if (przystanek.getListaPojazdowZaparkowanych().contains(pojazd)) {
                        if (pojazd instanceof Samolot) {
                            ((Samolot) pojazd).setMaksymalnaIloscPaliwa(pojazd.okreslanieDlugosciTrasy(pojazd.getObecnePolozenie(),pojazd.getNastepnyPrzystanek(),pojazd.getPozostalaTrasa())+400);
                            przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc() + 1);
                        }
                        pojazd.setWidocznosc(true);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                pojazd.getImageNode().visibleProperty().setValue(true);
                            }
                        });
                        przystanek.removePojazdZaparkowany(pojazd);
                    }
                }
//                pojazd.nastepnaDroga();
            }
                pojazd.poruszSie();
                this.obecnieZajmuje = null;
        }
    }

//
//    public void startowanie(Pojazd pojazd){
//        synchronized (kontrolaLotow) {
//            synchronized (pojazd.getHulkPojazdu()) {
//                this.obecnieZajmuje = pojazd;
////            pojazd.getDrogaTeraz().addListaPojazdow(pojazd);
//                if (this instanceof Przystanek) {
//                    Przystanek przystanek = (Przystanek) this;
//                    if (przystanek.getListaPojazdowZaparkowanych().contains(pojazd)) {
//                        if (pojazd instanceof Samolot) {
//                            przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc() + 1);
//                        }
//                        pojazd.setWidocznosc(true);
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                pojazd.getImageNode().visibleProperty().setValue(true);
//                            }
//                        });
//                        przystanek.removePojazdZaparkowany(pojazd);
//                    }
//                }
//                pojazd.nastepnaDroga();
//                pojazd.poruszSie();
//                this.obecnieZajmuje = null;
//            }
//        }
//    }
//
    public MiejsceZmianyKierunku(double dlugosc, double szerokosc, double polozenieX, double polozenieY, String nazwa,boolean istniejeWSwiecie) {
        super(dlugosc, szerokosc);
        this.setPolozenieX(polozenieX);
        this.setPolozenieY(polozenieY);
//        this.zajetaPrzestrzen = zajetaPrzestrzen;
//        if(istniejeWSwiecie==true) {
            this.nazwa = nazwa;
        if(istniejeWSwiecie==true) {
            Swiat.getInstance().addMiejsceZmianyKierunku(this);
        }
        this.setPromien(10);
        this.promienOuterRing = this.promien * 2 + 15;
//        }
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
        this.getOutRing().setOnMouseClicked(event -> {
            Informacja.getInstance().setObecnaInformacja(this);
        });
        group.getChildren().add(this.getImageNode());
        group.getChildren().add(this.getOutRing());
        Label label = new Label(this.getNazwa());
        label.setLayoutX(this.getPolozenieX()-this.promienOuterRing/2);
        label.setLayoutY(this.getPolozenieY() - this.promienOuterRing - 15);
        group.getChildren().add(label);
    }

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = new ArrayList<Control>();
        ShowLabel showLabel1 = new ShowLabel("Nazwa: " + this.getNazwa());
        listaNodow.add(showLabel1);
//        ShowLabel showLabel2 = new ShowLabel(this.getNazwa(),this);
//        listaNodow.add(showLabel2);
        ShowLabel showLabel3 = new ShowLabel("Obecnie zajmowane przez:");
        listaNodow.add(showLabel3);
        if(this.obecnieZajmuje!=null){
            ShowLabel showLabel4 = new ShowLabel(this.obecnieZajmuje.getIdentyfikator().toString(),this.obecnieZajmuje);
            listaNodow.add(showLabel4);
        }
        else{
            ShowLabel showLabel4 = new ShowLabel("Nikt");
            listaNodow.add(showLabel4);
        }
        return listaNodow;
    }

}
