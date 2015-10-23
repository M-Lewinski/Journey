package Mapa;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class ObiektGraficzny implements Rysowanie {
    private int dlugosc;
    private int szerokosc;

    @Override
    public void rysuj() {

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
}
