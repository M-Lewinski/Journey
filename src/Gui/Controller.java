package Gui;

import Mapa.Swiat;
import Pojazdy.Powietrzne.SamolotPasazerski;
import Pojazdy.Powietrzne.SamolotWojskowy;
import Pojazdy.Wodne.StatekWycieczkowy;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Lewin on 2015-11-28.
 */
public class Controller {
    @FXML
    private Button buttonStworzSamolotPasazerski;

    @FXML
    private Button buttonStworzSamolotWojskowy;
    @FXML
    private void initialize() {
    }

    @FXML
    private MainPanel mainPanel;

    public Controller(){
    }
    @FXML
    public void handleStworzSamolotPasazerski(){
        SamolotPasazerski samolotPasazerski = new SamolotPasazerski(15,15,15,10,10,10);
        System.out.println("Stworzono nowy samolot pasazerski");
    }

    @FXML
    public void handleStworzSamolotWojskowy(){
        SamolotWojskowy samolotWojskowy = new SamolotWojskowy(15,15,15,10,10,10);
        System.out.println("Sworzono nowy samolot wojskowy");
    }

    @FXML
    public  void handleStworzStatekWycieczkowy(){
        StatekWycieczkowy statekWycieczkowy = new StatekWycieczkowy(15,15,15,"nowy");
        System.out.println("Stworzono nowy statek wycieczkowy");
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
