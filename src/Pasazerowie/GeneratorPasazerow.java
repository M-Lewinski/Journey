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
 * Created by Lewin on 2015-10-24.
 */
public class GeneratorPasazerow {
    private static GeneratorPasazerow instance = null;
    private List<String> listaImion = new ArrayList<String>();
    private List<String> listaNazwisk = new ArrayList<String>();
    private GeneratorPasazerow() {
        InputStream in = getClass().getResourceAsStream("/NameList.txt");
        BufferedReader imieReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("NameList.txt")));
        String s;
        try {
            while ((s = imieReader.readLine()) != null)
                listaImion.add(s);
        }catch (IOException e){

        }
        BufferedReader nazwiskoReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("SurnameList.txt")));
        try {
            while ((s = nazwiskoReader.readLine())!=null){
                listaNazwisk.add(s);
            }
        }catch (IOException e){

        }
    }

    public synchronized static GeneratorPasazerow getInstance(){
        if (instance == null){
            instance = new GeneratorPasazerow();
        }
        return instance;
    }

    public String getImie(){
        Random rn = new Random();
        int i = rn.nextInt(this.listaImion.size());
        return this.listaImion.get(i);
    }

    public String getNazwisko(){
        Random rn = new Random();
        int i = rn.nextInt(this.listaNazwisk.size());
        return this.listaNazwisk.get(i);
    }
}
