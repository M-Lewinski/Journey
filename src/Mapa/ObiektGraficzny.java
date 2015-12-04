package Mapa;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class ObiektGraficzny implements Rysowanie {
    private double wysokosc;
    private double szerokosc;
    private Shape imageNode;

    @Override
    public void rysuj(Group group) {
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
