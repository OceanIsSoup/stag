package cz.uhk.fim.pro1.model;

import cz.uhk.fim.pro1.fileOperations.FileOperations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cz.uhk.fim.pro1.model.RozvrhovaAkce.vytvorRA;

public class Fakulta {

    public static final int AR_0 = 2025;

    public enum Obor {
        AI2, AI3, IM2, IM3, EAM, ISB3;
    }

    public enum FormaStudia {
        PREZ, KOMB;
    }

    String nazev;
    String zkratka;
    int aktualniAR;

    Semestr aktualniSemestr() {
        return (LocalDate.now().isBefore(ar().zacatekLS) ) ? Semestr.ZS : Semestr.LS;
    }

    protected List<AkademickyRok> archiv;

    HashMap<String,Student> studenti;
    HashMap<String,Ucitel> ucitele;
    HashMap<String,Predmet> predmety;
    HashMap<String,Ucebna> ucebny;

    public Fakulta(String nazev, String zkratka) {
        this.nazev = nazev;
        this.zkratka = zkratka;
        studenti = new HashMap<>();
        ucitele = new HashMap<>();
        predmety = new HashMap<>();
        ucebny = new HashMap<>();
        archiv = new ArrayList<>();
        init();
    }

    public void init() {
        load();
        pridejStudenty();
        upravUcitele();
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        for (int y = AR_0; y <= thisYear; y++) {
            archiv.add(new AkademickyRok(y));
        }
        if (today.isBefore(ar(thisYear).zacatek))
            aktualniAR = thisYear-1;
        else
            aktualniAR = thisYear;
        pridejRA();
    }

    public AkademickyRok ar(int rok) {
        return archiv.get(rok-AR_0);
    }

    public AkademickyRok ar() {
        return ar(aktualniAR);
    }

    public void pridejStudenty(){
        studenti.put("dolenja1", new Student("Jakub", "Dolének", "jakub.dolenek@uhk.cz", "dolenja1", Obor.AI3, FormaStudia.PREZ));
        studenti.put("mrskoma1", new Student("Marek", "Mrskoč", "marek.mrskoc@uhk.cz", "mrskoma1", Obor.AI3, FormaStudia.PREZ));
    }

    public void upravUcitele() {
        Ucitel bau = ucitele.get("246023");
        bau.username = "bauerpe1";
        System.out.println(bau);
    }

    public void pridejRA() {
        RozvrhovaAkce pro1cv0 = vytvorRA(
                Semestr.LS,
                RozvrhovaAkce.TypAkce.cviceni,
                Den.Po,
                LocalTime.of(14,55),
                LocalTime.of(16,30),
                predmety.get("PRO1"),
                ucebny.get("J20"),
                ucitele.get("246023")
                );
        RozvrhovaAkce pro1cv1 = vytvorRA(
                Semestr.LS,
                RozvrhovaAkce.TypAkce.cviceni,
                Den.Ut,
                LocalTime.of(10,45),
                LocalTime.of(12,20),
                predmety.get("PRO1"),
                ucebny.get("J23"),
                ucitele.get("246023")
                );

                RozvrhovaAkce pro1cv2 = vytvorRA(
                Semestr.LS,
                RozvrhovaAkce.TypAkce.cviceni,
                Den.Ut,
                LocalTime.of(12,25),
                LocalTime.of(14,00),
                predmety.get("PRO1"),
                ucebny.get("J23"),
                ucitele.get("246023")
                );

                RozvrhovaAkce pro1kolize = vytvorRA(
                Semestr.LS,
                RozvrhovaAkce.TypAkce.cviceni,
                Den.Ut,
                LocalTime.of(13,55),
                LocalTime.of(19,00),
                predmety.get("PRO1"),
                ucebny.get("J22"),
                ucitele.get("1388")
                );

        ar().pridejAkci(pro1cv0);
        ar().pridejAkci(pro1cv1);
        ar().pridejAkci(pro1cv2);
        ar().pridejAkci(pro1kolize);
    }

    public void pridejAkci(RozvrhovaAkce akce, int rok) {
        ar(rok).pridejAkci(akce);
    }

    public void pridejAkci(RozvrhovaAkce akce) {
        pridejAkci(akce, aktualniAR);
    }

    public void load() {
        FileOperations.loadPredmetyMap("predmety.csv", this.predmety);
//        FileOperations.loadUcitele("ucitele.csv", this.ucitele);
        FileOperations.loadUciteleMap("ucitele.csv", this.ucitele);
//        FileOperations.loadUcebny("mistnosti.csv", this.ucebny);
        FileOperations.loadUcebnyMap("mistnosti.csv", this.ucebny);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Nazev: ").append(nazev).append("\n");
        b.append("Zkratka: ").append(zkratka).append("\n");
        b.append("Studenti: ").append(studenti.values()).append("\n");
        b.append("Ucitele: ").append(ucitele.values()).append("\n");
        b.append("Predmety: ").append(predmety.values()).append("\n");
        b.append("Ucebny: ").append(ucebny.values()).append("\n");
        b.append("Akademicky rok: ").append(aktualniAR).append(" " + aktualniSemestr()).append("\n");
        b.append("Rozvrhove akce: ").append(ar().rozvrhoveAkce).append("\n");
//        b.append("Archiv: ").append("\n");
//        b.append("RA: ").append(archiv).append("\n");

        return b.toString();
    }
}
