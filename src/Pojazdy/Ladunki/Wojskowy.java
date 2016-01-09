package Pojazdy.Ladunki;


import java.util.Random;

/**
 * Created by Lewin on 2015-10-19.
 */
public class Wojskowy extends TypLadunku {
    private Uzbrojenie uzbrojenie;

    public Uzbrojenie getUzbrojenie() {
        return uzbrojenie;
    }

    public Wojskowy(Uzbrojenie uzbrojenie) {
        this.uzbrojenie = uzbrojenie;
    }

    public Wojskowy(){
        Random random = new Random();
        uzbrojenie = Uzbrojenie.values()[random.nextInt(Uzbrojenie.values().length)];
    }
}
