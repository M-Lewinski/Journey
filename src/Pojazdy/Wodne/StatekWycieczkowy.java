package Pojazdy.Wodne;

import Drogi.Droga;
import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Gui.MainPanel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Miasto;
import Mapa.ZmianyKierunku.Przystanki.Port;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.Pasazerski;
import Pojazdy.Ladunki.TypLadunku;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class StatekWycieczkowy extends Statek {
    private String firma;
    private Pasazerski ladunek;
    //    private static List<Object> listaGdzieMozeLadowac = new ArrayList<Object>();
//    private static Droga typDrogi;
    public String getFirma() {
        return firma;
    }

    public StatekWycieczkowy(double dlugosc, double szerokosc, double maksymalnaPredkosc, String firma) {
        super(dlugosc, szerokosc, maksymalnaPredkosc,null);
        ladunek = new Pasazerski();
//        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow()+90);
        this.ladunek.stworzNowychPasazerow(this.ladunek.getMaksymalnaLiczbaPasazerow());
        this.firma = firma;
        this.setTymczasowyKolor(Color.LIGHTBLUE);
        this.rysuj(MainPanel.getGrupaPojazdow());
    }


}
