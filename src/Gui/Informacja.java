package Gui;

import Mapa.ShowInfo;
import javafx.application.Platform;
import sun.applet.Main;

/**
 * Created by Lewin on 2015-12-31.
 */
public class Informacja implements Runnable {
    private ShowInfo obecnaInformacja=null;
    private static Informacja instance = null;
    public Informacja(){
        Runnable runner = this;
        Thread thread = new Thread(runner);
        thread.start();
    }

    public synchronized static Informacja getInstance(){
        if(instance == null){
            instance = new Informacja();
        }
        return instance;
    }

    public ShowInfo getObecnaInformacja() {
        return obecnaInformacja;
    }

    public void setObecnaInformacja(ShowInfo obecnaInformacja) {
        this.obecnaInformacja = obecnaInformacja;
    }

    @Override
    public void run() {
        try {
//            Controller controller = MainPanel.getLoader().getController();
            while(true) {
                if (obecnaInformacja != null) {
                    obecnaInformacja.showInfo();
                }
                Thread.sleep(1000 / 30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
