package Gui;

import Mapa.Swiat;
import Pasazerowie.Pasazer;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Powietrzne.SamolotWojskowy;
import Pojazdy.Wodne.Lotniskowiec;
import Pojazdy.Wodne.StatekWycieczkowy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.Random;

/**
 * Created by Lewin on 2015-11-28.
 */
public class Controller {
    @FXML
    private Button buttonStworzSamolotPasazerski;
    @FXML
    private Button getButtonStworzSamolotWojskowy1;
    @FXML
    private Button buttonStworzSamolotWojskowy;
    @FXML
    private Button buttonWszyscyPasazerowie;
    @FXML
    private Button buttonStworzLotniskowiec;
    @FXML
    private Button buttonDodajPasazera;
    @FXML
    private Button buttonZapiszSerializacje;
    @FXML
    private Button buttonWczytajSerializacje;

    @FXML
    private void initialize() {
    }

    @FXML
    private GridPane grid;
    @FXML
    private MainPanel mainPanel;

    @FXML
    private ScrollPane scrollPanel;

    public Controller(){
    }

    public GridPane getGrid() {
        return grid;
    }

    public void setGrid(GridPane grid) {
        this.grid = grid;
    }

    public ScrollPane getScrollPanel() {
        return scrollPanel;
    }

    @FXML
    public void handleStworzSamolotPasazerski(){
        for (int i = 0; i < 1; i++) {
            new SamolotPasazerski(14,14);
        }
        System.out.println("Stworzono nowy samolot pasazerski");
    }

    @FXML
    public void handleStworzSamolotWojskowy(){
        Random random = new Random();
        if(!Swiat.getInstance().getListaLotniskowcow().isEmpty()) {
            Lotniskowiec lotniskowiec = Swiat.getInstance().getListaLotniskowcow().get(random.nextInt(Swiat.getInstance().getListaLotniskowcow().size()));
//        SamolotWojskowy samolotWojskowy = new SamolotWojskowy(14,14);
            lotniskowiec.stworzSamolotWojskowy();
            System.out.println("Sworzono nowy samolot wojskowy");
        }
        else{
            System.out.println("Nie ma lotniskowcow");
        }
    }

    @FXML
    public  void handleStworzStatekWycieczkowy(){
        StatekWycieczkowy statekWycieczkowy = new StatekWycieczkowy(14,14);
        System.out.println("Stworzono nowy statek wycieczkowy");
    }

    @FXML
    public void handleWyswietlWszystkichPasazerow(){
        Informacja.getInstance().setObecnaInformacja(Swiat.getInstance());
    }

    @FXML
    public void handleStworzLotniskowiec(){
        new Lotniskowiec(14,14);
        System.out.println("Stworzono nowy lotniskowiec");
    }

    @FXML
    public void handleDodajPasazera(){
        for (int i = 0; i < 5; i++) {
            new Pasazer();
        }
        System.out.println("Stworzono 5 pasazerow");
    }

    @FXML
    public void handleZapiszSerializacje(){
        System.out.println("Zapisywanie Serializacji do pliku");
        MainPanel.serializacjaAplikacji();
    }

    @FXML
    public void handleWczytajSerializacje(){
        System.out.println("Wczytywanie Serializacji z pliku");
        MainPanel.wczytywanieSerializacji();
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
