package Mapa;

import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Skrzyzowanie;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Powietrzne.SamolotWojskowy;
import Pojazdy.Wodne.Lotniskowiec;
import Pojazdy.Wodne.StatekWycieczkowy;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.function.LongToDoubleFunction;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class ObiektGraficzny implements Rysowanie,Serializable {
    private static final long serialVersionUID = -4030344064594210323L;
    private double wysokosc;
    private double szerokosc;
    private transient Shape imageNode;
    private transient Color color;


    @Override
    public void rysuj(Group group) {
    }


    @Override
    public void naprawRysunki(Group group) {
        if(this instanceof Skrzyzowanie){
            this.color=Color.GREEN;
        }
        else if(this instanceof LotniskoCywilne){
            this.color=Color.BROWN;
        }
        else if(this instanceof LotniskoWojskowe){
            this.color=Color.RED;
        }
        else if(this instanceof Miasto){
            this.color=Color.PURPLE;
        }
        else if(this instanceof Port){
            this.color=Color.BLUE;
        }
        else if(this instanceof SamolotPasazerski){
            this.color=Color.YELLOW;
        }
        else if(this instanceof SamolotWojskowy){
            this.color=Color.ORANGE;
        }
        else if(this instanceof Lotniskowiec){
            this.color=Color.BLACK;
        }
        else if(this instanceof StatekWycieczkowy){
            this.color=Color.LIGHTBLUE;
        }
        rysuj(group);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getImageNode().setVisible(true);
            }
        });
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getImageNode() {
        return imageNode;
    }

    public void setImageNode(Shape imageNode) {
        this.imageNode = imageNode;
    }

    public double getWysokosc() {
        return wysokosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public void setWysokosc(double dlugosc) {
        this.wysokosc = dlugosc;
    }

    public void setSzerokosc(double szerokosc) {
        this.szerokosc = szerokosc;
    }

    public ObiektGraficzny(double dlugosc, double szerokosc) {
        this.wysokosc = dlugosc;
        this.szerokosc = szerokosc;
    }
    public ObiektGraficzny(){

    }

}
