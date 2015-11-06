package Pojazdy;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.Pasazerski;
import Pojazdy.Ladunki.TypLadunku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot {
    public SamolotPasazerski(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        Random random = new Random();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskCywilnych());
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaMiast());
//        if (listaMozliwychPrzystankow.size() !=0) {
//            this.setPrzystanekPoczatkowy(listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size() - 1)));
//            this.setObecnePolozenie(this.getPrzystanekPoczatkowy());
//            listaMozliwychPrzystankow.remove(this.getPrzystanekPoczatkowy());
//            this.setPrzystanekDocelowy(listaMozliwychPrzystankow.get(random.nextInt(listaMozliwychPrzystankow.size() - 1)));
//            tworzenieTrasy();
//        }
        okreslaniePolozenia(listaMozliwychPrzystankow);
        tworzenieTrasy();
        this.setNastepnyPrzystanek(nastepnyPrzystanekZTrasy(this.getTrasa()));
    }
}
