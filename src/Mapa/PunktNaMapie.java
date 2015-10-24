package Mapa;

public class PunktNaMapie extends ObiektGraficzny implements ShowInfo {
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

    public PunktNaMapie(int dlugosc, int szerokosc, int polozenieX,int polozenieY) {
        super(dlugosc,szerokosc);
        this.polozenieX = polozenieX;
        this.polozenieY = polozenieY;
    }
    public PunktNaMapie(){

    }

    @Override
    public void showInfo() {

    }
}
