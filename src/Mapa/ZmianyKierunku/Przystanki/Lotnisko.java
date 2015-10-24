package Mapa.ZmianyKierunku.Przystanki;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Lotnisko extends Przystanek {
    private int maksymalnaPojemnosc;

    public void setMaksymalnaPojemnosc(int maksymalnaPojemnosc) {
        this.maksymalnaPojemnosc = maksymalnaPojemnosc;
    }

    public int getMaksymalnaPojemnosc() {

        return maksymalnaPojemnosc;
    }

    public Lotnisko(int dlugosc, int szerokosc, int polozenieX, int polozenieY, boolean zajetaPrzestrzen, int maksymalnaPojemnosc) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen);
        this.maksymalnaPojemnosc = maksymalnaPojemnosc;
    }
    public Lotnisko(){

    }
}
