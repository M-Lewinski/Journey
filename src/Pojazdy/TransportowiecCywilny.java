package Pojazdy;

import Mapa.Monitoring;
import Pasazerowie.Pasazer;
import Pojazdy.Ladunki.Pasazerski;

import java.util.List;

/**
 * Created by Lewin on 2016-01-02.
 */
public interface TransportowiecCywilny {
    public boolean wsiadanie(Pasazer pasazer);
//    public void wysiadanie();
    public void wysiadanie(Pasazer pasazer);
    public Monitoring getHulkPojazdu();
    public Pasazerski getLadunek();
//    public void stworzNowychPasazerow();
}
