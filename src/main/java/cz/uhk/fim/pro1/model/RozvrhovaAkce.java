package cz.uhk.fim.pro1.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import cz.uhk.fim.pro1.fileOperations.TypUcebnyAdapter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RozvrhovaAkce {
    public enum TypAkce {prednaska,cviceni;}
    TypAkce typAkce;
    Semestr semestr;
    @SerializedName("denZkr")
    Den den;

    @SerializedName("hodinaSkutOd")
    @JsonAdapter(TypUcebnyAdapter.class)
    LocalTime casOd;
    @SerializedName("hodinaSkutDo")
    @JsonAdapter(TypUcebnyAdapter.class)
    LocalTime casDo;

    Predmet predmet;
    String budova;
    @SerializedName("mistnost")
    Ucebna ucebna;
    @SerializedName("planObsazeni")
    int kapacita;
    Ucitel ucitel;
    List<Student>  studenti;

    private RozvrhovaAkce(Semestr s, TypAkce typAkce, Den den, LocalTime casOd, LocalTime casDo, Predmet predmet, Ucebna ucebna, Ucitel ucitel) {
        assert casOd.isBefore(casDo);

        this.semestr = s;
        this.typAkce = typAkce;
        this.den = den;
        this.casOd = casOd;
        this.casDo = casDo;
        this.predmet = predmet;
        this.ucebna = ucebna;
        this.ucitel = ucitel;
        this.studenti = new ArrayList<>();
    }

    public static RozvrhovaAkce vytvorRA(Semestr semestr, TypAkce typAkce, Den den, LocalTime casOd, LocalTime casDo, Predmet predmet, Ucebna ucebna, Ucitel ucitel){
        return new RozvrhovaAkce(semestr, typAkce, den, casOd, casDo, predmet, ucebna, ucitel);
    }

    public Ucitel getUcitel() {
        return ucitel;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public Ucebna getUcebna() {
        return ucebna;
    }

    public Semestr getSemestr() {
        return semestr;
    }

    public Den getDen() {
        return den;
    }

    public int prihlaseno() {
        return studenti.size();
    }

    private boolean lzeZapsat(Student student) {
        return !studenti.contains(student) && studenti.size() < this.kapacita;
    }

    public boolean pridatStudenta(Student student) {
        if (lzeZapsat(student))
            return studenti.add(student);
        return false;
    }

    @Override
    public String toString() {
        return predmet.zkratka + ", " + typAkce + ", " + ucebna.zkratka + ", " + ucitel.jmeno+" "+ucitel.prijmeni;

    }

}
