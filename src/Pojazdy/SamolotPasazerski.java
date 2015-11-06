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
        LinkedList<Przystanek> listaLotniskCywilnych = new LinkedList<Przystanek>();
        for (int i = 0; i < Swiat.getInstance().getListaPrzystankow().size(); i++) {
            if(Swiat.getInstance().getListaPrzystankow().get(i) instanceof LotniskoCywilne){
                listaLotniskCywilnych.add(Swiat.getInstance().getListaPrzystankow().get(i));
            }
        }
        Pasazerski ladunek = new Pasazerski();
        this.setLadunek(ladunek);
        Random random = new Random();
        int temp;
        temp=random.nextInt(listaLotniskCywilnych.size());
        this.setPrzystanekPoczatkowy(listaLotniskCywilnych.get(temp));
        this.setObecnePolozenie(listaLotniskCywilnych.get(temp));
        listaLotniskCywilnych.remove(temp);
        this.setPolozenieX(this.getObecnePolozenie().getPolozenieX());
        this.setPolozenieY(this.getObecnePolozenie().getPolozenieY());
        temp = random.nextInt(listaLotniskCywilnych.size());
        this.setPrzystanekDocelowy(listaLotniskCywilnych.get(temp));
    }
}
