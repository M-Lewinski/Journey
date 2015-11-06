package Mapa;

public abstract class PunktNaMapie extends ObiektGraficzny implements ShowInfo {
    private int polozenieX;
    private int polozenieY;

    public int getPolozenieX() {
        return polozenieX;
    }

    public void setPolozenieX(int polozenieX) {
        this.polozenieX = polozenieX;
    }

    public int getPolozenieY() {
        return polozenieY;
    }

    public void setPolozenieY(int polozenieY) {
        this.polozenieY = polozenieY;
    }

    public PunktNaMapie(int dlugosc, int szerokosc) {
        super(dlugosc,szerokosc);

    }
    public PunktNaMapie(){

    }

    @Override
    public void showInfo() {

    }
}
