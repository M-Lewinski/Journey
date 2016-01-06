package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Gui.ShowLabel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.TworzeniePojazdu;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.List;

/** Klasa lotniskowca, ktora implementuje obiekt lotniskowiec.
 * Created by Lewin on 2015-10-18.
 */
public class Lotniskowiec extends Statek implements TworzeniePojazdu {
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;

    /**
     * Konstruktor klasy lotniskowiec, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     * @param maksymalnaPredkosc
     */
    public Lotniskowiec(double dlugosc, double szerokosc, double maksymalnaPredkosc) {
        super(dlugosc, szerokosc);
        Swiat.getInstance().addLotniskowiec(this);
        this.runMe();
    }

    @Override
    public void stworz() {

    }



    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listNodow = super.potrzebneInformacje();
        ShowLabel showLabel = new ShowLabel();
        return listNodow;
    }

    @Override
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
        if(Lotniskowiec.listaGdzieMozeLadowac.isEmpty()){
//            Lotniskowiec.listaGdzieMozeLadowac.add(new Miasto());
//            Lotniskowiec.listaGdzieMozeLadowac.add(new Port());
        }
        return Lotniskowiec.listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        if (Lotniskowiec.typDrogi==null){
            Lotniskowiec.typDrogi = new DrogaMorska();
        }
        return Lotniskowiec.typDrogi;
    }

    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
//        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, SamolotPasazerski.getListaGdzieMozeLadowac());
        return this.nastepnyPrzystanekZTrasy(trasa,obecnePolozenie, this.getMozliweLadowania());
    }


//    @Override
//    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {
//
//    }
}
