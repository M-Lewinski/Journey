package Drogi;

import Gui.MainPanel;
import Mapa.Rysowanie;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

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
    private double angle;
    private double sinDrogi;
    private double cosDrogi;
    private double pozycjaPoczatkowaX;
    private double PozycjaPoczatkowaY;
    private double odlegloscPoczatek;
    private double katPoczatkowy;
    private double pozycjaKoncowaX;
    private double pozycjaKoncowaY;
    private double odlegloscKoniec;
    private double katKoncowy;
    private double odlegloscMiedzyMiejscamiZmianyKierunku;
    private Shape imageNode;
    private Color color;

    public void setKoniec(MiejsceZmianyKierunku koniec) {
        this.koniec = koniec;
    }

    public void setPoczatek(MiejsceZmianyKierunku poczatek) {

        this.poczatek = poczatek;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public MiejsceZmianyKierunku getKoniec() {

        return koniec;
    }

    public MiejsceZmianyKierunku getPoczatek() {

        return poczatek;
    }

    public double getCosDrogi() {
        return cosDrogi;
    }

    public void setCosDrogi(double cosDrogi) {
        this.cosDrogi = cosDrogi;
    }

    public double getSinDrogi() {
        return sinDrogi;
    }

    public void setSinDrogi(double sinDrogi) {
        this.sinDrogi = sinDrogi;
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
    public Droga(MiejsceZmianyKierunku poczatek, MiejsceZmianyKierunku koniec,double poprawkaX, double poprawkaY) {
        this.poczatek = poczatek;
        this.koniec = koniec;
        this.odleglosc = Math.sqrt(Math.pow(poczatek.getPolozenieX()-koniec.getPolozenieX(),2.0) + Math.pow(poczatek.getPolozenieY()-koniec.getPolozenieY(),2.0));
        this.odleglosc = odleglosc;
        Swiat.getInstance().addDroga(this);
        this.poczatek.addListaDrog(this);
        this.okreslKat();
//        System.out.println("Dlugosc drogi: " + this.odleglosc);
    }

    public Shape getImageNode() {
        return imageNode;
    }

    public void setImageNode(Shape imageNode) {
        this.imageNode = imageNode;
    }

    /**
     * Pusty konstruktor drogi.
     */
    public Droga(){

    }
    @Override
    public void rysuj(Group group) {
        imageNode = new Line(poczatek.getPolozenieX(),poczatek.getPolozenieY(),koniec.getPolozenieX(),koniec.getPolozenieY());
        imageNode.setStroke(this.color);
        group.getChildren().add(imageNode);
        proba(group);
//        line.setStroke(Color.ORANGE);
//        panel.getChildren().add(line);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void okreslKat(){
        this.angle=Math.atan2(-poczatek.getPolozenieX()+koniec.getPolozenieX(),-poczatek.getPolozenieY()+koniec.getPolozenieY());
        this.sinDrogi = Math.sin(this.angle);
        this.cosDrogi = Math.cos(this.angle);
    }

    public void proba(Group group){
        double zmianaXP=0.0;
        double zmianaYP=0.0;
        double zmianaXK=0.0;
        double zmianaYK=0.0;
        double kat = this.angle;
        double przerwa=10.0;
        double promienP = this.getPoczatek().getPromienOuterRing();
        double promienK = this.getKoniec().getPromienOuterRing();
        double poczatekX = this.getPoczatek().getPolozenieX();
        double poczatekY = this.getPoczatek().getPolozenieY();
        double koniecX = this.getKoniec().getPolozenieX();
        double koniecY = this.getKoniec().getPolozenieY();
        double gamma;
        double beta;
        double betaPRIM;
        if((Math.toDegrees(angle)>0) && (Math.toDegrees(angle)<=90)) {
            gamma = Math.asin(przerwa / promienP);
            beta = kat - gamma;
            zmianaXP = Math.sin(beta) * promienP;
            zmianaYP = Math.cos(beta) * promienP;
            betaPRIM = Math.toRadians(90) - kat - gamma;
            zmianaXK = -Math.cos(betaPRIM) * promienP;
            zmianaYK = -Math.sin(betaPRIM) * promienP;
            if(this instanceof DrogaPowietrzna){
                this.color=Color.ORANGE;
            }
            else{
                this.color=Color.BLUE;
            }
        }
        else if((Math.toDegrees(angle)>=-180) && (Math.toDegrees(angle)<=-90)){
            kat = angle - Math.toRadians(180);
            gamma = Math.asin(-przerwa / promienP);
            beta = kat - gamma;
            zmianaXK = Math.sin(beta) * promienP;
            zmianaYK = Math.cos(beta) * promienP;
            betaPRIM = Math.toRadians(90) - kat - gamma;
            zmianaXP = -Math.cos(betaPRIM) * promienP;
            zmianaYP = -Math.sin(betaPRIM) * promienP;
            if(this instanceof DrogaPowietrzna){
                this.color = Color.FIREBRICK;
            }
            else{
                this.color = Color.PURPLE;
            }

        }
        else if ((Math.toDegrees(angle)>=90)&&(Math.toDegrees(angle)<=180)){
            gamma = Math.asin(przerwa / promienP);
            beta = kat - Math.toRadians(90) - gamma;
            zmianaXP = Math.cos(beta)*promienP;
            zmianaYP = -Math.sin(beta)*promienP;
            betaPRIM = beta + 2*gamma;
            zmianaXK = -promienK*Math.cos(betaPRIM);
            zmianaYK = promienK*Math.sin(betaPRIM);
            if(this instanceof DrogaPowietrzna){
                this.color=Color.ORANGE;
            }
            else{
                this.color=Color.BLUE;
            }
        }
        else if((Math.toDegrees(angle)<=0)&&(Math.toDegrees(angle)>=-90)){
            kat = angle - Math.toRadians(180);
            gamma = Math.asin(-przerwa / promienP);
            beta = kat - Math.toRadians(90) - gamma;
            zmianaXK = Math.cos(beta)*promienP;
            zmianaYK = -Math.sin(beta)*promienP;
            betaPRIM = beta + 2*gamma;
            zmianaXP = -promienK*Math.cos(betaPRIM);
            zmianaYP = promienK*Math.sin(betaPRIM);
            if(this instanceof DrogaPowietrzna){
                this.color = Color.FIREBRICK;
            }
            else{
                this.color = Color.PURPLE;
            }
        }
        else{
            return;
        }
        this.katPoczatkowy=beta;
        this.katKoncowy=betaPRIM;
        Line linia = new Line(poczatekX+zmianaXP,poczatekY+zmianaYP,koniecX+zmianaXK,koniecY+zmianaYK);
        Line odMiasta = new Line(poczatekX,poczatekY,poczatekX+zmianaXP,poczatekY+zmianaYP);
        Line doMiasta = new Line(koniecX,koniecY,koniecX+zmianaXK,koniecY+zmianaYK);
        linia.setStroke(this.color);
        odMiasta.setStroke(this.color);
        doMiasta.setStroke(this.color);
        group.getChildren().add(linia);
        group.getChildren().add(odMiasta);
        group.getChildren().add(doMiasta);

    }
}
