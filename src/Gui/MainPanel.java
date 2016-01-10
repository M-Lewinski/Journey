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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sun.applet.Main;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import java.io.*;
import java.security.PrivateKey;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lewin on 2015-10-23.
 */
public class MainPanel extends Application implements Serializable {
    private static final long serialVersionUID = -1993738385689327868L;
    private static Stage primaryStage;
    private static AnchorPane rootLayout;
    private static Group  grupaMiejscZmianyKierunku = new Group();
    private static Group grupaDrog = new Group();
    private static Group grupaPojazdow = new Group();
    private Swiat swiat;
    //    public static boolean beginning = false;
//    private static Controller controller;
    //    private static MainPanel instance = null;
    private static FXMLLoader  loader = new FXMLLoader();
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Mapa");
        this.primaryStage.setResizable(true);
        initRootLayout();

//        MainPanel.beginning=true;
    }

//    public synchronized static MainPanel getInstance(){
//        if(instance == null){
//            instance = new MainPanel();
//        }
//        return instance;
//    }


//    public static Controller getController() {
//        return controller;
//    }
//
//    public static void setController(Controller controller) {
//        MainPanel.controller = controller;
//    }


    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void setLoader(FXMLLoader loader) {
        MainPanel.loader = loader;
    }

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
//            FXMLLoader loader = new FXMLLoader();
            this.loader.setLocation(MainPanel.class.getResource("Interfejs.fxml"));
            Controller controller = this.loader.getController();
//            this.controller.getGrid()
            rootLayout = (AnchorPane) this.loader.load();
//            System.out.println("grupa miejsc: "+grupaMiejscZmianyKierunku.getChildren().size());
            rootLayout.getChildren().add(MainPanel.grupaMiejscZmianyKierunku);
            rootLayout.getChildren().add(MainPanel.grupaDrog);
            rootLayout.getChildren().add(MainPanel.grupaPojazdow);
            this.swiat=Swiat.getInstance();
            Swiat.getInstance().stworzSwiat();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
//            controller.getScrollPanel().setStyle("edge-to-edge;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Swiat getSwiat() {
        if(this.swiat==null){
            this.swiat=Swiat.getInstance();
        }
        return this.swiat;
    }

    public static void main(String[] args) {

        launch(args);
    }

    public static void serializacjaAplikacji(){
        ObjectOutputStream wyjscie;
        try {
            for (int i = 0; i < Swiat.getInstance().getListaThread().size(); i++) {
                Swiat.getInstance().getListaThread().get(i).suspend();
            }
            wyjscie = new ObjectOutputStream(new FileOutputStream("PlikSerializacyjny.serial"));
            wyjscie.writeObject(Swiat.getInstance());
            wyjscie.close();
            System.out.println("Wykonano serializacje");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Nie udalo sie zapisac pliku");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Nie udalo sie dokonac serializacji");
        }
        for (int i = 0; i < Swiat.getInstance().getListaThread().size(); i++) {
            Swiat.getInstance().getListaThread().get(i).resume();
        }
    }
}
