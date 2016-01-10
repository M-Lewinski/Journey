package Mapa;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.io.Serializable;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class ObiektGraficzny implements Rysowanie,Serializable {
    private static final long serialVersionUID = -3751186235992662489L;
    private double wysokosc;
    private double szerokosc;
    private transient Shape imageNode;
    private transient Color color;


    @Override
    public void rysuj(Group group) {
    }


    @Override
    public void naprawRysunki(Group group, Color color) {
        this.color=color;
        rysuj(group);
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
