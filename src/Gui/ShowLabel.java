package Gui;

import Mapa.ShowInfo;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;

/**
 * Created by Lewin on 2016-01-01.
 */
public class ShowLabel extends Label {
    private ShowInfo informacjaObiektu=null;
    private transient Color wejscie = Color.GREEN;
    private transient Color wyjscie = Color.BLACK;
    public void obsluga(){
        this.setFont(new Font(15.0));
        this.setOnMouseClicked(event -> {
            if (this.informacjaObiektu != null) {
                Informacja.getInstance().setObecnaInformacja(this.informacjaObiektu);
            }
        });
        this.setOnMouseEntered(event -> {
            if (this.informacjaObiektu != null) {
                this.setTextFill(this.wejscie);
            }
        });
        this.setOnMouseExited(event -> {
            if (this.informacjaObiektu != null) {
                this.setTextFill(this.wyjscie);
            }
        });
    }
    public ShowLabel(String  s, ShowInfo node){
        super();
        this.setText(s);
        this.informacjaObiektu=node;
        obsluga();
    }

    public ShowLabel(String s){
        super();
        this.setText(s);
        obsluga();
    }
    public ShowLabel(){
        super();
        obsluga();
    }
}
