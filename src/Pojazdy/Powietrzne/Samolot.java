package Pojazdy.Powietrzne;

import Gui.ShowLabel;
import Mapa.Swiat;
import Mapa.ZmianyKierunku.MiejsceZmianyKierunku;
import Mapa.ZmianyKierunku.Przystanki.Przystanek;
import Pojazdy.Pojazd;
import Pojazdy.Trasowanie;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lewin on 2015-10-18.
 */
public abstract class Samolot extends Pojazd {
    private int liczbaPersonelu=0;
    private double maksymalnaIloscPaliwa=0.0;
    private double aktualnaIloscPaliwa=0.0;
    private boolean awaria=false;
    private boolean wymuszoneAwaryjneLadowanie =false;
//    private MiejsceZmianyKierunku nastepnyPrzystanek;

//    public Samolot(int dlugosc, int szerokosc, int maksymalnaPredkosc, int liczbaPersonelu, int maksymalnaIloscPaliwa, int aktualnaIloscPaliwa) {
    public Samolot(double dlugosc, double szerokosc) {
        super(dlugosc, szerokosc);
        Random random = new Random();
        this.liczbaPersonelu = random.nextInt(10)+4;
    }

    public Samolot(){

    }

    @Override
    public List<Control> potrzebneInformacje() {
        List<Control> listaNodow = super.potrzebneInformacje();
        if(!Swiat.getInstance().getListaWolnychPojazdow().contains(this)) {
            Button button = new Button("Awaryjne ladowanie!");
            button.setOnMouseClicked(event -> {
//            this.awaryjneLadowanie();
                this.wymuszoneAwaryjneLadowanie = true;
            });
            listaNodow.add(0, button);
        }
        ShowLabel label1 = new ShowLabel("Liczba zalogi: "+ Integer.toString(this.liczbaPersonelu));
        listaNodow.add(5,label1);
//        ShowLabel label2 = new ShowLabel(Integer.toString(this.liczbaPersonelu));
//        listaNodow.add(4,label2);
        ShowLabel label3 = new ShowLabel("Maksymalna ilosc paliwa: " + Double.toString(this.maksymalnaIloscPaliwa) + " litrow");
        listaNodow.add(6,label3);
//        ShowLabel label4 = new ShowLabel(Double.toString(this.maksymalnaIloscPaliwa) + " litrow");
//        listaNodow.add(6,label4);
        ShowLabel label5 = new ShowLabel("Aktualna ilosc paliwa: " + Double.toString(this.aktualnaIloscPaliwa) + " litrow");
        listaNodow.add(7,label5);
//        ShowLabel label6 = new ShowLabel(Double.toString(this.aktualnaIloscPaliwa) + " litrow");
//        listaNodow.add(9,label6);
        return  listaNodow;
    }

    //    public MiejsceZmianyKierunku getNastepnyPrzystanek() {
//        return nastepnyPrzystanek;
//    }
//
//    public void setNastepnyPrzystanek(MiejsceZmianyKierunku nastepnyPrzystanek) {
//        this.nastepnyPrzystanek = nastepnyPrzystanek;
//    }

    public int getLiczbaPersonelu() {
        return liczbaPersonelu;
    }

    public double getAktualnaIloscPaliwa() {

        return aktualnaIloscPaliwa;
    }

    public void setAktualnaIloscPaliwa(double aktualnaIloscPaliwa) {
        this.aktualnaIloscPaliwa = aktualnaIloscPaliwa;
    }


    public double getMaksymalnaIloscPaliwa() {

        return maksymalnaIloscPaliwa;
    }

    public boolean isWymuszoneAwaryjneLadowanie() {
        return wymuszoneAwaryjneLadowanie;
    }

    public void setWymuszoneAwaryjneLadowanie(boolean wymuszoneAwaryjneLadowanie) {
        this.wymuszoneAwaryjneLadowanie = wymuszoneAwaryjneLadowanie;
    }

    public boolean isAwaria() {
        return awaria;
    }

    public void setAwaria(boolean awaria) {
        this.awaria = awaria;
    }

    public void setLiczbaPersonelu(int liczbaPersonelu) {
        this.liczbaPersonelu = liczbaPersonelu;
    }

    public void setMaksymalnaIloscPaliwa(double maksymalnaIloscPaliwa) {
        this.maksymalnaIloscPaliwa = maksymalnaIloscPaliwa;
        this.aktualnaIloscPaliwa = this.maksymalnaIloscPaliwa;
    }

    public void awaryjneLadowanie(){
        if(awaria==true){
            return;
        }
        if(!czyWyladowal(this.getObecnePolozenie())) {
            if(this.getPozostalaTrasa().size()<2 || czyMozeTutajLadowac(this.getPozostalaTrasa().get(1)) ){
                List<MiejsceZmianyKierunku> staraTrasa = new ArrayList<>();
                List<MiejsceZmianyKierunku> staraPozostalaTrasa =new ArrayList<>();
//                Przystanek nastepny = this.getNastepnyPrzystanek();
                staraTrasa.addAll(this.getTrasa());
                staraPozostalaTrasa.addAll(this.getPozostalaTrasa());
                synchronized (this) {
                    this.setPrzystanekDocelowy((Przystanek) this.getPozostalaTrasa().get(1));
                    if(this.getPrzystanekPoczatkowy()==this.getPrzystanekDocelowy()){
                        Random random = new Random();
                        List<Przystanek> listaLokalizacji = filtrowaniePrzystankow();
                        listaLokalizacji.remove(this.getPrzystanekDocelowy());
                        this.setPrzystanekPoczatkowy(listaLokalizacji.get(random.nextInt(listaLokalizacji.size())));
                    }
//                    System.out.println("XXXXXXXXXXXXXXXX");
                    tworzenieTrasy(this.getPrzystanekPoczatkowy(), this.getPrzystanekDocelowy(), this.getTypDrogi());
                    this.getPozostalaTrasa().clear();
//                    this.getPozostalaTrasa().add(this.getObecnePolozenie());
                    this.getPozostalaTrasa().addAll(staraPozostalaTrasa);
//                    this.setNastepnyPrzystanek(nastepny);
                    this.poinformujPasazerow(this.listaOdwiedzanychPrzystankow(staraTrasa),this.listaOdwiedzanychPrzystankow(this.getTrasa()));
                }
            }
            else {
                List<Przystanek> listaLokalizacji = filtrowaniePrzystankow();
                List<MiejsceZmianyKierunku> trasaAwaryjna = new LinkedList<MiejsceZmianyKierunku>();
                double odleglosc = 0.0;
                Trasowanie temp = new Trasowanie();
                for (int i = 0; i < listaLokalizacji.size(); i++) {
                    List<MiejsceZmianyKierunku> listaTymczasowa = szukanieTrasy(this.getPozostalaTrasa().get(1), listaLokalizacji.get(i), this.getTypDrogi(),temp);
                    if (listaTymczasowa == null) {
                        continue;
                    } else {
//                        double temp = okreslanieDlugosciTrasy(this.getPozostalaTrasa().get(1), listaLokalizacji.get(i), listaTymczasowa);
                        if ((odleglosc == 0.0) || (temp.getDlugosc() < odleglosc && temp.getDlugosc() != 0.0)) {
                            odleglosc = temp.getDlugosc();
                            trasaAwaryjna.clear();
                            trasaAwaryjna.add(this.getObecnePolozenie());
                            trasaAwaryjna.addAll(listaTymczasowa);
                        }
                    }
                }
//            System.out.println("Awaria: " +odleglosc);
                synchronized (this) {
                    List<Przystanek> staraTrasaPrzystankow = new ArrayList<Przystanek>();
                    staraTrasaPrzystankow.addAll(this.listaOdwiedzanychPrzystankow(this.getTrasa()));
                    this.setPrzystanekDocelowy((Przystanek)trasaAwaryjna.get(trasaAwaryjna.size()-1));
                    if(this.getPrzystanekPoczatkowy()==this.getPrzystanekDocelowy()){
                        Random random = new Random();
                        listaLokalizacji.remove(this.getPrzystanekDocelowy());
                        this.setPrzystanekPoczatkowy(listaLokalizacji.get(random.nextInt(listaLokalizacji.size())));
                    }
//                    this.getTrasa().clear();
                    this.tworzenieTrasy(this.getPrzystanekPoczatkowy(),this.getPrzystanekDocelowy(),this.getTypDrogi());
                    this.getPozostalaTrasa().clear();
//                    this.getPozostalaTrasa().add(this.getObecnePolozenie());
                    this.getPozostalaTrasa().addAll(trasaAwaryjna);
                    this.setNastepnyPrzystanek(this.getPrzystanekDocelowy());
                    this.poinformujPasazerow(staraTrasaPrzystankow, this.listaOdwiedzanychPrzystankow(this.getTrasa()));
                }
            }
            this.awaria=true;
        }
    }

    @Override
    public void wtrakciePoruszaniaSie(double przesuniecie) {
        super.wtrakciePoruszaniaSie(przesuniecie);
        this.aktualnaIloscPaliwa = this.aktualnaIloscPaliwa-przesuniecie;
        if(aktualnaIloscPaliwa<0.0){
            System.out.println("ALARM BRAK PALIWA");
        }
        if(this.wymuszoneAwaryjneLadowanie ==true){
            this.wymuszoneAwaryjneLadowanie=false;
            this.awaryjneLadowanie();
        }
    }

    @Override
    public void ladowanie(Przystanek przystanek) {
        super.ladowanie(przystanek);
        if(this.awaria==true){
            System.out.println("koniec awarii");
            this.awaria=false;
            this.setOczekiwanie(this.getOczekiwanie()*2);
        }
    }

    @Override
    public void usuwanie() {
        super.usuwanie();
        if(this.getObecnePolozenie() instanceof Przystanek){
            Przystanek przystanek = (Przystanek) this.getObecnePolozenie();
            if(przystanek.getListaPojazdowZaparkowanych().contains(this)){
                przystanek.setMaksymalnaPojemnosc(przystanek.getMaksymalnaPojemnosc()+1);
            }
        }
    }

    @Override
    public void zmienDotychczasowaTrase() {
        if(this.awaria==false) {
            super.zmienDotychczasowaTrase();
        }
    }
}
