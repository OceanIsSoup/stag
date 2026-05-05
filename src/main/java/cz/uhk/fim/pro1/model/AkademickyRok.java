package cz.uhk.fim.pro1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AkademickyRok {
    int rok;
    LocalDate zacatek;
    LocalDate zacatekLS;
    LocalDate konec;

    List<RozvrhovaAkce> rozvrhoveAkce;
    HashMap<String,Rozvrh> [] rozvrhy;

    public Rozvrh rozvrhOsoby(Semestr semestr, String id) {
        return rozvrhy[semestr.ordinal()].get(id);
    }

    public Rozvrh rozvrhMistnosti(Semestr semestr, String zkratka) {
        return rozvrhy[semestr.ordinal()].get(zkratka);
    }

    public Rozvrh rozvrhPredmetu(Semestr semestr, String zkratka) {
        return rozvrhy[semestr.ordinal()].get(zkratka);
    }

    public AkademickyRok(int rok) {
        this.rok = rok;
        zacatek = LocalDate.of(rok,9,1);
        zacatekLS = LocalDate.of(rok+1, 2, 9 );
        konec = LocalDate.of(rok+1,8,31);
        this.rozvrhoveAkce = new ArrayList<>();
        this.rozvrhy = new HashMap[2];
        this.rozvrhy[0] = new HashMap<>();
        this.rozvrhy[1] = new HashMap<>();
    }

    void pridejAkci(RozvrhovaAkce akce){
        Ucitel ucitel = akce.getUcitel();
        Ucebna ucebna = akce.getUcebna();
        Predmet predmet = akce.getPredmet();
        Semestr semestr = akce.getSemestr();

        if (rozvrhPredmetu(semestr, predmet.zkratka) == null)
            rozvrhy[semestr.ordinal()].put(predmet.getZkratka(), new Rozvrh());

        if (rozvrhOsoby(semestr, ucitel.id) == null)
            rozvrhy[semestr.ordinal()].put(ucitel.getId(), new Rozvrh());

        if (rozvrhMistnosti(semestr, ucebna.zkratka) == null)
            rozvrhy[semestr.ordinal()].put(ucebna.getZkratka(), new Rozvrh());

        Rozvrh rop = rozvrhPredmetu(semestr, predmet.zkratka);
        Rozvrh rou = rozvrhOsoby(semestr, ucitel.id);
        Rozvrh rom = rozvrhMistnosti(semestr, ucebna.zkratka);

        int irop = rop.index(akce);
        int irou = rou.index(akce);
        int irom = rom.index(akce);

        if (irop >= 0 ||
            irou >= 0 ||
            irom >= 0 )
        {
            rozvrhoveAkce.add(akce);
            rop.vlozAkci(irop, akce);
            rou.vlozAkci(irou, akce);
            rom.vlozAkci(irom, akce);
        }
    }

    @Override
    public String toString() {
        return rok + ", RA: " + rozvrhoveAkce;
    }
}
