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
    private int rowCount=0;
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

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void wyczysc(){
        this.rowCount=0;
        this.obecnaInformacja=null;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Controller controller = MainPanel.getLoader().getController();
                controller.getGrid().getChildren().clear();
            }
        });
    }

    @Override
    public void run() {
        try {
//            Controller controller = MainPanel.getLoader().getController();
            while(true) {
                if (obecnaInformacja != null) {
                    rowCount=obecnaInformacja.showInfo(rowCount);
                }
                Thread.sleep(1000 / 30);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
