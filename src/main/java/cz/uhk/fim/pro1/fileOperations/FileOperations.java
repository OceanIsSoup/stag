package cz.uhk.fim.pro1.fileOperations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import cz.uhk.fim.pro1.model.Predmet;
import cz.uhk.fim.pro1.model.RozvrhovaAkce;
import cz.uhk.fim.pro1.model.Ucebna;
import cz.uhk.fim.pro1.model.Ucitel;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileOperations {
    private static String[] items(String line, String sep) {
//        return line.trim().replaceAll("\"","").split(";");
        return line.trim().replaceFirst("\"", "").replaceAll("(\")(?=$)", "").split("\""+sep+"\"");
    }

    public static void loadPredmetyMap(String file, HashMap<String, Predmet> predmety) {
//        List<String> fields = new ArrayList<>();
//        List<Predmet.Indexy> lst = Arrays.asList(Predmet.Indexy.values());
//        lst.forEach(index -> fields.add(index.toString()));

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            String [] keys = items(line, ";");

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = items(line, ";");
                assert (values.length == keys.length);

                HashMap<String, String> mapa = new HashMap<>();
                for (int j = 0; j < values.length; j++)
                    mapa.put(keys[j], values[j]);

                Predmet predmet = new Predmet(mapa);
                predmety.put(predmet.getZkratka(), predmet);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadUciteleMap(String file, HashMap<String, Ucitel> ucitele) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            String [] keys = items(line, ";");

            while ((line = bufferedReader.readLine()) != null) {
                String [] values = items(line, ";");
                assert (values.length == keys.length);

                HashMap<String, String> mapa = new HashMap<>();
                for (int j = 0; j < values.length; j++)
                    mapa.put(keys[j], values[j]);

                Ucitel ucitel = Ucitel.fromHashMap(mapa);
                ucitele.put(ucitel.getId(), ucitel);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadUcebnyMap(String file, HashMap<String, Ucebna> ucebny) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            String [] keys = items(line, ";");

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = items(line, ";");
                assert (values.length == keys.length);

                HashMap<String, String> mapa = new HashMap<>();
                for (int j = 0; j < values.length; j++)
                    mapa.put(keys[j], values[j]);

                Ucebna ucebna = Ucebna.vytvor(mapa);
                ucebny.put(ucebna.getZkratka(), ucebna);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Predmet> readPredmetyFromJson(String file) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(
                    fileReader,
                    new TypeToken<List<Predmet>>() {
                    }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<RozvrhovaAkce> readRAFromJson(String file) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(
                    fileReader,
                    new TypeToken<List<RozvrhovaAkce>>() {
                    }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}