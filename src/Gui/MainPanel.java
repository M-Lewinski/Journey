package Gui;

import Drogi.DrogaMorska;
import Drogi.DrogaPowietrzna;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.Przystanki.Lotnisko;
import Mapa.ZmianyKierunku.Przystanki.LotniskoCywilne;
import Mapa.ZmianyKierunku.Przystanki.LotniskoWojskowe;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Mapa.ZmianyKierunku.Skrzyzowanie;
import Pasazerowie.Pasazer;
import Pojazdy.Powietrzne.SamolotPasazerski;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sun.applet.Main;

import javax.swing.*;
import java.io.IOException;
import java.security.acl.Group;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-23.
 */
public class MainPanel extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mapa");
        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPanel.class.getResource("Interfejs.fxml"));
            rootLayout = (BorderPane) loader.load();
            for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
                Swiat.getInstance().getListaMiejscZmianyKierunku().get(i).rysuj(rootLayout);
            }
            for (int i = 0; i < Swiat.getInstance().getListaDrog().size(); i++) {
                Swiat.getInstance().getListaDrog().get(i).rysuj(rootLayout);
            }
            //Group group = new Group();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
//    public void showPersonOverview() {
//        try {
//            // Load person overview.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
//            AnchorPane personOverview = (AnchorPane) loader.load();
//
//            // Set person overview into the center of root layout.
//            rootLayout.setCenter(personOverview);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        Swiat.getInstance();
        Swiat.getInstance().stworzSwiat();
//        System.out.println("ilosc drog " + Swiat.getInstance().getListaDrog().size());
//        for (int i = 0; i < Swiat.getInstance().getListaDrog().size() ; i++) {
//            System.out.println(Swiat.getInstance().getListaDrog().get(i).getPoczatek().getNazwa());
//        }
        SamolotPasazerski samolot1 = new SamolotPasazerski(100, 100, 100, 10, 10, 10);
        System.out.println("Stworzono Samolot");
        System.out.println("Przystanek poczatkowy: " + samolot1.getPrzystanekPoczatkowy().getNazwa());
        System.out.println("Przystanek docelowy: " + samolot1.getPrzystanekDocelowy().getNazwa());
        System.out.println(samolot1.getNastepnyPrzystanek().getNazwa());
        System.out.println("dlugosc drogi miedzy punktami: " + samolot1.okreslanieDlugosciTrasy(samolot1.getPrzystanekPoczatkowy(), samolot1.getPrzystanekDocelowy(), samolot1.getTrasa()));
//        Pasazer pasazer1 = new Pasazer();
//        pasazer1.outconsole();
        //pasazer1.setPrzystanekPoczatkowy(lotniskoCywilne1);
        //pasazer1.setPrzystanekDocelowy(lotniskoCywilne2);
        //pasazer1.szukanieTrasy(pasazer1.getPrzystanekPoczatkowy(),pasazer1.getPrzystanekDocelowy());
//        pasazer1.tworzenieTrasy(pasazer1.getPrzystanekPoczatkowy(), pasazer1.getPrzystanekDocelowy());
//        List<MiejsceZmianyKierunku> listaprobna = new LinkedList<MiejsceZmianyKierunku>();

//        listaprobna.add(skrzyzowanie1);
//        listaprobna.add(skrzyzowanie2);
//        listaprobna.add(lotniskoCywilne2);
//        samolot1.setTrasa(listaprobna);
//        samolot1.setPozostalaTrasa(listaprobna);
        List<Przystanek> listaMiejsc = new LinkedList<Przystanek>();
        listaMiejsc.addAll(Swiat.getInstance().getListaLotniskCywilnych());
        listaMiejsc.addAll(Swiat.getInstance().getListaMiast());
        System.out.println("Poczatek zmiany trasy, obecne polozenie: " + samolot1.getObecnePolozenie().getNazwa());
        samolot1.wypisywanieTrasy(samolot1.getTrasa());
        samolot1.wypisywanieTrasy(samolot1.getPozostalaTrasa());
//        samolot1.setObecnePolozenie(skrzyzowanie1);
        samolot1.zmianaTrasy(listaMiejsc);;
//        samolot1.addPozostalaTrasa(skrzyzowanie1);
        System.out.println("Zmiana trasy");
        samolot1.wypisywanieTrasy(samolot1.getTrasa());
        System.out.println("pozostala trasa");
        samolot1.wypisywanieTrasy(samolot1.getPozostalaTrasa());

        listaMiejsc.add(null);
        if (listaMiejsc.isEmpty()) {
            System.out.println("jest pusta");
        }
        if (listaMiejsc.size() == 0) {
            System.out.println("dlugosc wynosi 0");
        }

        //samolot1.szukanieTrasy(samolot1.getPrzystanekPoczatkowy(), samolot1.getPrzystanekDocelowy(), new DrogaPowietrzna());
        //samolot1.szukanieTrasy(lotniskoCywilne1, lotniskoCywilne2, new DrogaPowietrzna());
//        Pasazer pasazer = new Pasazer();
//        pasazer.outconsole();
//        double odleglosc = Math.sqrt(Math.pow(8,2) + Math.pow(6,2.0));
//        System.out.printf("%.2f",odleglosc);

//


        launch(args);
    }

}
