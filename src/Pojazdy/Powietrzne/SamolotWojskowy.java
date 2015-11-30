package Pojazdy.Powietrzne;

import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Ladunki.TypLadunku;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-18.
 */
public class SamolotWojskowy extends Samolot {
    public SamolotWojskowy(int dlugosc, int szerokosc, int polozenieX, int polozenieY, double maksymalnaPredkosc, TypLadunku ladunek, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
        super(dlugosc, szerokosc, maksymalnaPredkosc, liczbaPersonelu, maksymalnaIloscPaliwa, aktualnaIloscPaliwa);
        LinkedList<Przystanek> listaMozliwychPrzystankow = new LinkedList<Przystanek>();
        listaMozliwychPrzystankow.addAll(Swiat.getInstance().getListaLotniskWojskowych());
//        okreslNowePolozenie(listaMozliwychPrzystankow);
        tworzenieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy());
//        this.setNastepnyPrzystanek(nastepnyPrzystanekZTrasy(this.getTrasa()));
    }

    @Override
    public Przystanek nastepneMozliweLadowanie(List<MiejsceZmianyKierunku> trasa, MiejsceZmianyKierunku obecnePolozenie) {
        return null;
    }

    @Override
    public void tworzenieTrasy(MiejsceZmianyKierunku poczatekTrasy, MiejsceZmianyKierunku koniecTrasy) {

    }
}
