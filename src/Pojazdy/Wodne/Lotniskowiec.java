package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Gui.MainPanel;
import Gui.ShowLabel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;
import Pojazdy.Ladunki.Wojskowy;
import Pojazdy.Powietrzne.SamolotWojskowy;
import Pojazdy.TworzeniePojazdu;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/** Klasa lotniskowca, ktora implementuje obiekt lotniskowiec.
 * Created by Lewin on 2015-10-18.
 */
public class Lotniskowiec extends Statek implements TworzeniePojazdu {
    private static List<MiejsceZmianyKierunku> listaGdzieMozeLadowac = new ArrayList<MiejsceZmianyKierunku>();
    private static Droga typDrogi;
    private Wojskowy ladunek;

    /**
     * Konstruktor klasy lotniskowiec, ktory wykorzystuje konstruktor z odziedziczonej klasy.
     * @param dlugosc
     * @param szerokosc
     */
    public Lotniskowiec(double dlugosc, double szerokosc) {
        super(dlugosc, szerokosc);
        ladunek = new Wojskowy();
        Swiat.getInstance().addLotniskowiec(this);
        this.setColor(Color.DEEPPINK);
        this.rysuj(MainPanel.getGrupaPojazdow());
        this.runMe();
    }

    @Override
    public void stworz() {

    }


    public void stworzSamolotWojskowy(){
        SamolotWojskowy samolotWojskowy = new SamolotWojskowy(14,14,this.ladunek.getUzbrojenie(),this.getPolozenieX(),this.getPolozenieY());
    }


    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listNodow = super.potrzebneInformacje();
        Button button = new Button();
        ShowLabel showLabel = new ShowLabel("Uzbrojenie: " + this.ladunek.getUzbrojenie().toString());
        listNodow.add(showLabel);
        return listNodow;
    }

    @Override
    public List<MiejsceZmianyKierunku> getMozliweLadowania() {
//        if(Lotniskowiec.listaGdzieMozeLadowac.isEmpty()){
//            Lotniskowiec.listaGdzieMozeLadowac.add(new Miasto());
//            Lotniskowiec.listaGdzieMozeLadowac.add(new Port());
//        }
        return Lotniskowiec.listaGdzieMozeLadowac;
    }

    @Override
    public Droga getTypDrogi() {
        if (Lotniskowiec.typDrogi==null){
            Lotniskowiec.typDrogi = new DrogaMorska();
        }
        return Lotniskowiec.typDrogi;
    }

    @Override
    public void usuwanie() {
        super.usuwanie();
        Swiat.getInstance().removeLotniskowiec(this);
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
