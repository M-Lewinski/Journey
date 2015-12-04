package Mapa;

public abstract class PunktNaMapie extends ObiektGraficzny implements ShowInfo {
    private double polozenieX;
    private double polozenieY;

    public double getPolozenieX() {
        return polozenieX;
    }

    public void setPolozenieX(double polozenieX) {
        this.polozenieX = polozenieX;
    }

    public double getPolozenieY() {
        return polozenieY;
    }

    public void setPolozenieY(double polozenieY) {
        this.polozenieY = polozenieY;
    }

    public PunktNaMapie(double dlugosc, double szerokosc) {
        super(dlugosc,szerokosc);

    }
    public PunktNaMapie(){
    }

    @Override
    public void showInfo() {

    }
}
