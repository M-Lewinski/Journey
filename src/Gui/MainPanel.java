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
    private static Stage primaryStage;
    private static AnchorPane rootLayout;
    private static Group  grupaMiejscZmianyKierunku = new Group();
    private static Group grupaDrog = new Group();
    private static Group grupaPojazdow = new Group();
    public static boolean beginning = false;
    //    private static MainPanel instance = null;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mapa");
        this.primaryStage.setResizable(true);
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

    public static AnchorPane getRootLayout() {
        return rootLayout;
    }

    public static void setRootLayout(AnchorPane rootLayout) {
        MainPanel.rootLayout = rootLayout;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainPanel.primaryStage = primaryStage;
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPanel.class.getResource("Interfejs.fxml"));
            rootLayout = (AnchorPane) loader.load();
//            for (int i = 0; i < Swiat.getInstance().getListaMiejscZmianyKierunku().size(); i++) {
//                Swiat.getInstance().getListaMiejscZmianyKierunku().get(i).rysuj(MainPanel.grupaMiejscZmianyKierunku);
//            }
            System.out.println("grupa miejsc: "+grupaMiejscZmianyKierunku.getChildren().size());
            this.grupaMiejscZmianyKierunku.setEffect(new BoxBlur(2, 2, 1));
//            for (int i = 0; i < Swiat.getInstance().getListaDrog().size(); i++) {
//                Swiat.getInstance().getListaDrog().get(i).rysuj(this.grupaDrog);
//            }
            rootLayout.getChildren().add(MainPanel.grupaMiejscZmianyKierunku);
            rootLayout.getChildren().add(MainPanel.grupaDrog);
            rootLayout.getChildren().add(MainPanel.grupaPojazdow);
            //Group group = new Group();
            // Show the scene containing the root layout.
            MainPanel.beginning=true;
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        Swiat.getInstance();
        Swiat.getInstance().stworzSwiat();
        SamolotPasazerski samolot1 = new SamolotPasazerski(100, 100, 20, 10, 10, 10);
        SamolotPasazerski samolot2 = new SamolotPasazerski(100, 100, 4, 10, 10, 10);
        SamolotPasazerski samolot3 = new SamolotPasazerski(100, 100, 5, 10, 10, 10);
        SamolotPasazerski samolot4 = new SamolotPasazerski(100, 100, 6, 10, 10, 10);
        SamolotPasazerski samolot5 = new SamolotPasazerski(100, 100, 3, 10, 10, 10);
        SamolotPasazerski samolot6 = new SamolotPasazerski(100, 100, 4, 10, 10, 10);
        SamolotPasazerski samolot7 = new SamolotPasazerski(100, 100, 8, 10, 10, 10);
        SamolotPasazerski samolot8 = new SamolotPasazerski(100, 100, 2, 10, 10, 10);
        launch(args);
    }

}
