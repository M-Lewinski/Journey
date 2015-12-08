package Pojazdy;

import Drogi.Droga;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;

import java.util.List;

/**
 * Created by Lewin on 2015-12-08.
 */
public interface Filtrowanie {
    public List<MiejsceZmianyKierunku> getMozliweLadowania();
    public Droga getTypDrogi();
}
