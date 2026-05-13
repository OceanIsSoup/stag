package cz.uhk.fim.pro1.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {

    Fakulta.Obor obor;
    int rok;

    List<Predmet> zapsanePredmety;
    Rozvrh rozvrh;

    //vysledky studia

    public Student (String jmeno, String prijmeni) {
        super(jmeno,prijmeni);
        this.rozvrh = new Rozvrh();
        zapsanePredmety = new ArrayList<>();
        this.rok = 1;
    }

    public Student(String jmeno, String prijmeni, String email, String username, Fakulta.Obor obor, Fakulta.FormaStudia formaStudia) {
        super(jmeno, prijmeni, email);
        this.username = username;
        this.obor = obor;
        this.rozvrh = new Rozvrh();
        zapsanePredmety = new ArrayList<>();
        this.rok = 1;
    }

    public void add_subject(RozvrhovaAkce ra) {
        rozvrh.vlozAkci(0,ra);
    }

    public Rozvrh getRozvrh() {
        return rozvrh;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += ", " + username + ", rocnik: " + rok + ", obor: " + obor;
        return s;
    }
    public String toTable(){
        return "|"+String.format("%-8s | %-18s | %-5s | %-17s",username,jmeno+" "+prijmeni, rok, obor+"|");
    }

}
