package cz.uhk.fim.pro1.model;

import javax.security.auth.Destroyable;
import java.sql.Array;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Rozvrh {
    private final int N_DAYS = 7;
    private final int N_SLOTS = 16;
    static List<LocalTime> casy;
    static final int DELKA_LEKCE = 50;

    List<RozvrhovaAkce>[] data;

    public Rozvrh() {
        data = new LinkedList[N_DAYS];
        for (int i = 0; i < N_DAYS; i++) {
            data[i] = new LinkedList<>();
        }
    }

    public int index(RozvrhovaAkce akce) {
        int den = akce.den.ordinal();
        int index = 0;
        while (index < data[den].size() && data[den].get(index).casDo.isBefore(akce.casOd))
             index++;
        if (index < data[den].size())
            if (!akce.casDo.isBefore(data[den].get(index).casOd))
            {
                System.err.println("Vkladana akce " + akce + " koliduje s akci " + data[den].get(index));
                index = -1;
            }

        return index;
    }
    public int indexPredmetu(RozvrhovaAkce akce) {
        return 0;
    }

    public void vlozAkci(int index, RozvrhovaAkce akce) {
        int den = akce.den.ordinal();
        if (index >= 0  && index < data[den].size())
            data[den].add(index,akce);
        else
            data[den].add(akce);
    }
}
