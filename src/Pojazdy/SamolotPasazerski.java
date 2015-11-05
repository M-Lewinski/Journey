package Pojazdy;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotPasazerski extends Samolot {
    public SamolotPasazerski(int dlugosc, int szerokosc, int polozenieX, int polozenieY, int maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super( dlugosc, szerokosc, polozenieX, polozenieY, maksymalnaPredkosc, ladunek, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        LinkedList<Przystanek> listaLotniskCywilnych = null;
        for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
            if(Swiat.getInstance().getListaMiejscZmianyKierunku().get(i) instanceof LotniskoCywilne){
                listaLotniskCywilnych.add(Swiat.getInstance().getListaPrzystankow().get(i));
            }
        }
        Random random = new Random();
        int temp;
        temp=random.nextInt(listaLotniskCywilnych.size());
        this.setObecnePolozenie(listaLotniskCywilnych.get(temp));
    }
}
