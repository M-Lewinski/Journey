package Mapa;

import java.io.Serializable;

/**
 * Created by Lewin on 2015-12-29.
 */
public class Monitoring implements Serializable{
    private static final long serialVersionUID = -9191200608500873962L;
    private PunktNaMapie punktNaMapie =null;
    //    private Object  monitor = new Object();
//    public Monitoring() {
//
//    }


    public PunktNaMapie getPunktNaMapie() {
        return punktNaMapie;
    }

    public void setPunktNaMapie(PunktNaMapie punktNaMapie) {
        this.punktNaMapie = punktNaMapie;
    }
}
