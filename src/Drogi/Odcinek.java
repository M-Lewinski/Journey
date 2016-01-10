package Drogi;

import Gui.MainPanel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.io.Serializable;

/**
 * Created by Lewin on 2015-12-08.
 */
public class Odcinek implements Serializable{
    private static final long serialVersionUID = 2481289876531997373L;
    private double angle;
    private double dlugosc;
    private double poczatekX;
    private double poczatekY;
    private double koniecX;
    private double koniecY;
    private double sinOdcinka;
    private double cosOdcinka;
    private transient Shape imageNode;
    private transient Color kolor;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(double dlugosc) {
        this.dlugosc = dlugosc;
    }

    public double getPoczatekX() {
        return poczatekX;
    }

    public void setPoczatekX(double poczatekX) {
        this.poczatekX = poczatekX;
    }

    public double getPoczatekY() {
        return poczatekY;
    }

    public void setPoczatekY(double poczatekY) {
        this.poczatekY = poczatekY;
    }

    public double getKoniecX() {
        return koniecX;
    }

    public void setKoniecX(double koniecX) {
        this.koniecX = koniecX;
    }

    public double getKoniecY() {
        return koniecY;
    }

    public void setKoniecY(double koniecY) {
        this.koniecY = koniecY;
    }

    public Color getKolor() {
        return kolor;
    }

    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }

    public double getSinOdcinka() {
        return sinOdcinka;
    }

    public void setSinOdcinka(double sinOdcinka) {
        this.sinOdcinka = sinOdcinka;
    }

    public double getCosOdcinka() {
        return cosOdcinka;
    }

    public void setCosOdcinka(double cosOdcinka) {
        this.cosOdcinka = cosOdcinka;
    }

    public Shape getImageNode() {
        return imageNode;
    }

    public void setImageNode(Shape imageNode) {
        this.imageNode = imageNode;
    }
    public Odcinek(double kat, double pX, double pY,double kX,double kY, Color color,boolean czyIstnieje){
        this.angle=kat;
        this.poczatekX=pX;
        this.poczatekY=pY;
        this.koniecX=kX;
        this.koniecY=kY;
        this.dlugosc = Math.sqrt(Math.pow(this.poczatekX-this.koniecX,2.0) + Math.pow(poczatekY-koniecY,2.0));
        this.sinOdcinka = Math.sin(this.angle);
        this.cosOdcinka = Math.cos(this.angle);
        this.kolor = color;
        if(czyIstnieje==true) {
            Line linia = new Line(poczatekX, poczatekY, koniecX, koniecY);
            linia.setStroke(this.kolor);
            linia.setStrokeWidth(2);
            imageNode = linia;
            MainPanel.getGrupaDrog().getChildren().add(imageNode);
        }
    }

}
