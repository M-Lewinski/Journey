package Gui;

import Mapa.Swiat;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Powietrzne.SamolotWojskowy;
import Pojazdy.Wodne.StatekWycieczkowy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

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
        for (int i = 0; i < 10; i++) {
            SamolotPasazerski samolotPasazerski = new SamolotPasazerski(14,14);
        }
        System.out.println("Stworzono nowy samolot pasazerski");
    }

    @FXML
    public void handleStworzSamolotWojskowy(){
//        SamolotWojskowy samolotWojskowy = new SamolotWojskowy(14,14);
        System.out.println("Sworzono nowy samolot wojskowy");
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

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
