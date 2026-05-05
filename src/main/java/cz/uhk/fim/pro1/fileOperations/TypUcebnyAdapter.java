package cz.uhk.fim.pro1.fileOperations;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import cz.uhk.fim.pro1.model.Ucebna;

import java.io.IOException;

/**
 * Adapter pro praci s typem LocalDate v GSONu
 */
public class TypUcebnyAdapter extends TypeAdapter<Ucebna.TypUcebny> {
    @Override
    public void write(JsonWriter jsonWriter, Ucebna.TypUcebny typUcebny) throws IOException {
        jsonWriter.value(typUcebny.toString());
    }

    @Override
    public Ucebna.TypUcebny read(JsonReader jsonReader) throws IOException {
        String str = jsonReader.nextString();
        String typ = str.split(" ")[1].trim();
        if ("posluchárna".equals(typ))
            return Ucebna.TypUcebny.poslucharna;
        if ("počítačová".equals(typ))
            return Ucebna.TypUcebny.pocitacova;
        if ("seminární".equals(typ))
            return Ucebna.TypUcebny.seminarni;
        return Ucebna.TypUcebny.ostatni;
    }
}
