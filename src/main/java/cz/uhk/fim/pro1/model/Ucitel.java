package cz.uhk.fim.pro1.model;

import java.util.HashMap;

public class Ucitel extends Osoba {
    String katedra;
//    String pozice;
//vyucovanePredmety
//rozvrh

    String zkrBudovy;
    String cisloMistnosti;
    String telefon;

    public Ucitel(String jmeno, String prijmeni, String email, String katedra) {
        super(jmeno, prijmeni, email);
        this.katedra = katedra;
    }

    public Ucitel(String jmeno, String prijmeni, String email, String username, String katedra) {
        super(jmeno, prijmeni, email, username);
        this.katedra = katedra;
    }

    public static Ucitel fromHashMap(HashMap<String,String> mapa) {
        Ucitel ucitel = new Ucitel(mapa.get("jmeno"), mapa.get("prijmeni"), mapa.get("email"), mapa.get("katedra"));

        ucitel.id = mapa.get("ucitIdno");
        ucitel.telefon = mapa.get("telefon");
//        ucitel.zkrBudovy = buffer[14];
//        ucitel.cisloMistnosti = buffer[15];

        return ucitel;
    }

    @Override
    public String toString() {
        return "|"+String.format("%-6s : %-18s",id,jmeno+" "+prijmeni)+"|";
    }


    public String toTable(){
        return "|"+String.format("%-8s | %-18s | %-5s | %-17s",id,jmeno+" "+prijmeni, katedra, telefon)+"|";
    }
}
