package Mapa;

import Gui.Controller;
import Gui.MainPanel;
import Gui.ShowLabel;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PunktNaMapie extends ObiektGraficzny implements ShowInfo,Serializable {
    private static final long serialVersionUID = -5033774608682335773L;
    private double polozenieX;
    private double polozenieY;

    public double getPolozenieX() {
        return polozenieX;
    }

    public void setPolozenieX(double polozenieX) {
        this.polozenieX = polozenieX;
    }

    public double getPolozenieY() {
        return polozenieY;
    }

    public void setPolozenieY(double polozenieY) {
        this.polozenieY = polozenieY;
    }

    public PunktNaMapie(double dlugosc, double szerokosc) {
        super(dlugosc,szerokosc);
    }
    public PunktNaMapie(){
    }

    public abstract List<Control> potrzebneInformacje();

    @Override
    public int showInfo(int rowCount) {
        List<Control> listaNodow = new ArrayList<Control>();
        listaNodow.addAll(this.potrzebneInformacje());
        Controller controller = MainPanel.getLoader().getController();
//        if(controller.getGrid().getChildren().size()==listaNodow.size()){
//            listaNodow.clear();
//            return rowCount;
//        }

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boolean rozne =false;
                if(controller.getGrid().getChildren().size()!= listaNodow.size()){
                    controller.getGrid().getChildren().clear();
                    rozne=true;
                }
                if(rozne==false){
                    for (int i = 0; i < controller.getGrid().getChildren().size(); i++) {
                        int row = controller.getGrid().getRowIndex(controller.getGrid().getChildren().get(i));
                        if(controller.getGrid().getChildren().get(i) instanceof Labeled && listaNodow.get(row) instanceof Labeled){
                            if(((Labeled) controller.getGrid().getChildren().get(i)).getText().equals(((Labeled) listaNodow.get(row)).getText())){
                                continue;
                            }

                        }
//                        if(controller.getGrid().getChildren().get(i) instanceof ShowLabel && listaNodow.get(row) instanceof ShowLabel) {
//                            if(((ShowLabel) controller.getGrid().getChildren().get(i)).getText().equals(((ShowLabel) listaNodow.get(row)).getText())){
//                                continue;
//                            }
//                        }
//                        if(controller.getGrid().getChildren().get(i) instanceof Button && listaNodow.get(row) instanceof Button){
//                            if(((Button) controller.getGrid().getChildren().get(i)).getText().equals())
//                        }
                        controller.getGrid().getChildren().remove(i);
                        i--;
                        controller.getGrid().add(listaNodow.get(row),0,row);
                    }
                }
                else{
                    for (int i = 0; i < listaNodow.size(); i++) {
                        controller.getGrid().add(listaNodow.get(i),0,i);
                    }
                }
            }
//                for (int i = 0; i < listaNodow.size(); i++) {
//                    int row=i;
//                    if(rozne==false) {
////                        row = controller.getGrid().getRowIndex(controller.getGrid().getChildren().get(i));
//                        if(controller.getGrid().getChildren().get(i) instanceof ShowLabel && listaNodow.get(i) instanceof ShowLabel) {
//                            if(((ShowLabel) controller.getGrid().getChildren().get(i)).getText().equals(((ShowLabel) listaNodow.get(i)).getText())){
//                                continue;
//                            }
//                        }
//                            controller.getGrid().getChildren().remove(i);
//                    }
//                    controller.getGrid().add(listaNodow.get(row), 0, row);
//                }
        });
        return listaNodow.size();
    }
}
