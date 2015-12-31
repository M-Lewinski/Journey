package Mapa.ZmianyKierunku.Przystanki;

/** Klasa lotniska, ktora implementuje obiekt lotnisko.
 * Created by Lewin on 2015-10-18.
 */
public abstract class Lotnisko extends Przystanek {
    /**
     * maksymalna liczba samolotow, ktore znajduja sie na lotnisku.
     */

    /**
     * Konstruktor klasy lotnisko, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc odlugosc obrazu zwiazanego z obiektem.
     * @param szerokosc szerokosc obrazu zwiazanego z obiektem.
     * @param polozenieX polozenie obiektu w poziomie.
     * @param polozenieY polozenie obiektu w pionie.
     * @param zajetaPrzestrzen czy przestrzen powietrzna nad lotniskiem jest zajeta.
     * @param maksymalnaPojemnosc maksymalna liczba samolotow znajdujacych sie na lotnisku.
     */
    public Lotnisko(String nazwa,double dlugosc, double szerokosc, double polozenieX, double polozenieY, boolean zajetaPrzestrzen, double maksymalnaPojemnosc) {
        super(dlugosc, szerokosc, polozenieX, polozenieY, zajetaPrzestrzen,nazwa);
//        this.maksymalnaPojemnosc = maksymalnaPojemnosc;
    }

    /**
     * Pusty konstruktor klasy lotnisko.
     */
    public Lotnisko(){

    }
}
