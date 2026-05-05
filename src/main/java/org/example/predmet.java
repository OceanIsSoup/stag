package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Stream;

public class predmet {
    private String nazev;
    private String zkratka;
    private String katedra;
    private String semestr;
    private String jazyk;

    private int doporuceny_rocnik;

    private String rozsah;
    private String typ_zk;
    private int kredity;
    private int hodin_prednasek;
    private int hodin_cviceni;

    public predmet(String s){
        String[] buffer = s.split(";");

    }

    public predmet(String nazev, String zkratka, String katedra, String semestr, int doporuceny_rocnik, String rozsah, String typ_zk, int kredity, int hodin_prednasek, int hodin_cviceni) {
        this.nazev = nazev;
        this.zkratka = zkratka;
        this.katedra = katedra;
        this.semestr = semestr;
        this.doporuceny_rocnik = doporuceny_rocnik;
        this.rozsah = rozsah;
        this.typ_zk = typ_zk;
        this.kredity = kredity;
        this.hodin_prednasek = hodin_prednasek;
        this.hodin_cviceni = hodin_cviceni;
    }

    private void zpracuj_rozsah(){
        String[] buffer = this.rozsah.trim().split("\\+");
        this.hodin_prednasek = Integer.parseInt(buffer[0]);
        this.hodin_cviceni = Integer.parseInt(buffer[1]);
    }

    public static void read_from_file(String path, Collection<predmet> predmets){
        predmets.clear();
        
    }


    public String toString(){
        return zkratka+", "+ "nazev";
    }

}
