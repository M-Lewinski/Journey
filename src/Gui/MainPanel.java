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
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-23.
 */
public class MainPanel extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private static Group  grupaMiejscZmianyKierunku = new Group();
    private static Group grupaDrog = new Group();
    private static Group grupaPojazdow = new Group();
//    private static MainPanel instance = null;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mapa");
        initRootLayout();
    }

//    public synchronized static MainPanel getInstance(){
//        if(instance == null){
//            instance = new MainPanel();
//        }
//        return instance;
//    }


    public static Group getGrupaPojazdow() {
        return MainPanel.grupaPojazdow;
    }

    public static void setGrupaPojazdow(Group grupaPojazdow) {
        MainPanel.grupaPojazdow = grupaPojazdow;
    }

    public static Group getGrupaDrog() {
        return MainPanel.grupaDrog;
    }

    public static void setGrupaDrog(Group grupaDrog) {
        MainPanel.grupaDrog = grupaDrog;
    }

    public static Group getGrupaMiejscZmianyKierunku() {
        return MainPanel.grupaMiejscZmianyKierunku;
    }

    public static void setGrupaMiejscZmianyKierunku(Group grupaMiejscZmianyKierunku) {
        MainPanel.grupaMiejscZmianyKierunku = grupaMiejscZmianyKierunku;
    }

    public AnchorPane getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(AnchorPane rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPanel.class.getResource("Interfejs.fxml"));
            rootLayout = (AnchorPane) loader.load();
//            NumberBinding areaResize = Bindings.min(rootLayout.heightProperty(),rootLayout.widthProperty());
//            AnchorPane.setTopAnchor(rootLayout,0.0);
//            AnchorPane.setBottomAnchor(rootLayout,0.0);
//            AnchorPane.setLeftAnchor(rootLayout,0.0);
//            AnchorPane.setRightAnchor(rootLayout,0.0);
            //Group grupaMiejscZmianyKierunku = new Group();
            for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
                Swiat.getInstance().getListaMiejscZmianyKierunku().get(i).rysuj(MainPanel.grupaMiejscZmianyKierunku);
            }
            System.out.println("grupa miejsc: "+grupaMiejscZmianyKierunku.getChildren().size());
//            MainPanel.getInstance().getGrupaPojazdow().getChildren().add(Swiat.getInstance().getListaPojazdow().get(0).getImageNode());
            this.grupaMiejscZmianyKierunku.setEffect(new BoxBlur(2, 2, 1));
//            grupaMiejscZmianyKierunku.getChildren().get(i).layoutXProperty()
//            Group grupaDrog = new Group();
            for (int i = 0; i < Swiat.getInstance().getListaDrog().size(); i++) {
                Swiat.getInstance().getListaDrog().get(i).rysuj(this.grupaDrog);
            }
//            Rectangle rectangle = new Rectangle(200,200);
//            rectangle.xProperty().setValue(100);
//            rectangle.yProperty().setValue(100);
//            rectangle.setStroke(Color.BLACK);
//            rectangle.setFill(Color.BLACK);
//            rectangle.xProperty().bind(areaResize.multiply(0));
//            rectangle.yProperty().bind(areaResize.multiply(0));
//            rectangle.heightProperty().bind(areaResize.divide(0));
//            rectangle.widthProperty().bind(areaResize);
//            rootLayout.getChildren().add(rectangle);
//            Group grupaPojazdow = new Group();
            rootLayout.getChildren().add(this.grupaMiejscZmianyKierunku);
            rootLayout.getChildren().add(this.grupaDrog);
            rootLayout.getChildren().add(this.grupaPojazdow);
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
//        MainPanel.getInstance();
        Swiat.getInstance().stworzSwiat();
        SamolotPasazerski samolot1 = new SamolotPasazerski(100, 100, 1, 10, 10, 10);
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
//        List<Przystanek> listaMiejsc = new LinkedList<Przystanek>();
//        listaMiejsc.addAll(Swiat.getInstance().getListaLotniskCywilnych());
//        listaMiejsc.addAll(Swiat.getInstance().getListaMiast());
//        System.out.println("Poczatek zmiany trasy, obecne polozenie: " + samolot1.getObecnePolozenie().getNazwa());
//        samolot1.wypisywanieTrasy(samolot1.getTrasa());
//        samolot1.wypisywanieTrasy(samolot1.getPozostalaTrasa());
////        samolot1.setPrzystanekPoczatkowy(Swiat.getInstance().getListaPrzystankow().get(3));
////        samolot1.setPrzystanekDocelowy(Swiat.getInstance().getListaPrzystankow().get(0));
//        samolot1.tworzenieTrasy(Swiat.getInstance().getListaPrzystankow().get(3),Swiat.getInstance().getListaPrzystankow().get(0));
////        samolot1.zmianaTrasy(listaMiejsc);;
//        System.out.println("Zmiana trasy");
//        samolot1.wypisywanieTrasy(samolot1.getTrasa());
//        System.out.println("pozostala trasa");
//        samolot1.wypisywanieTrasy(samolot1.getPozostalaTrasa());
//        listaMiejsc.clear();
//        listaMiejsc.add(null);
//        System.out.println(samolot1.getPolozenieX());
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
