package Pasazerowie;


import org.w3c.dom.NameList;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Klasa generujaca imiona i nazwiska pasazerow, ktore sa wczytywane z dolaczonego pliku.
 * Created by Lewin on 2015-10-24.
 */
public class GeneratorPasazerow implements Serializable {
    private static final long serialVersionUID = -1121403815523156626L;
    /**
     * instancja klasy generatora pasazerow.
     */
    private static GeneratorPasazerow instance = null;
    /**
     * lista wszystkich imion.
     */
    private List<String> listaImion = new ArrayList<String>();
    /**
     * lista wszystkich nazwisk.
     */
    private List<String> listaNazwisk = new ArrayList<String>();

    /**
     * Konstruktor klasy generatora pasazerow, ktory wczytuje imiona i nazwiska z pliku do list.
     */
    private GeneratorPasazerow() {
        InputStream in = getClass().getResourceAsStream("/NameList.txt");
        BufferedReader imieReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("NameList.txt")));
        String s;
        try {
            while ((s = imieReader.readLine()) != null) {
                listaImion.add(s);
            }
        }catch (IOException e){

        }finally {
            try {
                imieReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedReader nazwiskoReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("SurnameList.txt")));
        try {
            while ((s = nazwiskoReader.readLine())!=null){
                listaNazwisk.add(s);
            }
        }catch (IOException e){

        }finally {
            try {
                nazwiskoReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * tworzenie instancji generatora pasazerow.
     * @return instancja generatora pasazerow.
     */
    public synchronized static GeneratorPasazerow getInstance(){
        if (instance == null){
            instance = new GeneratorPasazerow();
        }
        return instance;
    }

    /**
     * zwraca wylosowane imie.
     * @return
     */
    public String getImie(){
        Random rn = new Random();
        int i = rn.nextInt(this.listaImion.size());
        return this.listaImion.get(i);
    }

    /**
     * zwraca wylosowane nazwisko.
     * @return
     */
    public String getNazwisko(){
        Random rn = new Random();
        int i = rn.nextInt(this.listaNazwisk.size());
        return this.listaNazwisk.get(i);
    }
}
