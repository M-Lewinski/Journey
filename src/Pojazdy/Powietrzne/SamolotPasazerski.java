package Pojazdy.Powietrzne;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot {
    public SamolotPasazerski(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskCywilnych());
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaMiast());
        okreslaniePolozenia(listaMozliwychPrzystankow);
        tworzenieTrasy();
        wypisywanieTrasy();
        this.getObecnePolozenie().addPojazdOczekujacy(this);
//        LinkedList<MiejsceZmianyKierunku> testList = new LinkedList<MiejsceZmianyKierunku>();
//        for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
//            testList.add(Swiat.getInstance().getListaMiejscZmianyKierunku().get(i));
//        }
//        this.setTrasa(testList);
    }
}
