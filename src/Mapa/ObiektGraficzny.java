package Mapa;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class ObiektGraficzny implements Rysowanie {
    private int dlugosc;
    private int szerokosc;
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

    public int getDlugosc() {
        return dlugosc;
    }

    public int getSzerokosc() {
        return szerokosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    public void setSzerokosc(int szerokosc) {
        this.szerokosc = szerokosc;
    }

    public ObiektGraficzny(int dlugosc, int szerokosc) {
        this.dlugosc = dlugosc;
        this.szerokosc = szerokosc;
    }
    public ObiektGraficzny(){

    }

}
