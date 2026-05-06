package cz.uhk.fim.pro1;

import cz.uhk.fim.pro1.model.Fakulta;
import cz.uhk.fim.pro1.model.Predmet;
import cz.uhk.fim.pro1.model.Student;
import cz.uhk.fim.pro1.model.Ucitel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println("Vitejte na FIM!");

//        List<String> list = new ArrayList<>();
//        Arrays.asList(Semestr.values()).forEach((s) -> { list.add(s.toString()); });

        Fakulta fakulta = new Fakulta("Fakulta informatiky a managementu", "FIM");
        print_help();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while(running){
            String input = sc.nextLine();
            switch (input){
                case "1":
                    print_teacher(sc,fakulta);
                    break;
                case "2":
                    System.out.println(fakulta);
                    break;
                case "3":
                    print_help();
                    break;
                case "4":
                    print_predmet(sc,fakulta);
                    break;
                case "5":
                    print_students(sc,fakulta);
                    break;
                case "6":
                    running = false;
                    break;
                default:
            }
        }

    }

    void print_help() {
        System.out.println("""
                print ucitele: 1
                print fakulta: 2
                help: 3
                print predmet: 4
                print student: 5
                exit: 6""");
    }

    void print_teacher(Scanner sc, Fakulta fk){
        System.out.println("""
                        all teacher from fakulta: 1
                        teacher by id: 2
                        """
                );
        String input = sc.nextLine();
        String tmp;
        switch(input){
            case "1":
                for (Map.Entry<String, Ucitel> entry:fk.getUcitele().entrySet()){
                    System.out.println(entry.getValue().toTable());
                    System.out.println("|---------|--------------------|-------|------------------|");
                }
                break;
            case "2":
                System.out.println("enter id:");
                tmp = sc.nextLine();
                if (fk.getUcitele().get(tmp) == null){
                    System.out.println("does not exist");
                    return;
                }
                System.out.println("|---------|--------------------|-------|------------------|");
                System.out.println(fk.getUcitele().get(tmp).toTable());
                System.out.println("|---------|--------------------|-------|------------------|");
                break;
            default:
        }
    }

    void print_students(Scanner sc,Fakulta fk){
        System.out.println("all students from fakulta: 1\n"
                //+"teacher by id: 2\n"
        );
        String input = sc.nextLine();
        if (input.equals("1")) {
            System.out.println("|---------|--------------------|-------|----|");
            for (Map.Entry<String, Student> entry : fk.getStudenti().entrySet()) {
                System.out.println(entry.getValue().toTable());
                System.out.println("|---------|--------------------|-------|----|");
            }
            //students are not assigned id
            //case "2":
            //   System.out.println("enter id:");
            //    tmp = sc.nextLine();
            //    System.out.println("|---------|--------------------|-------|----|");
            //   System.out.println(fk.getUcitele().get(tmp).toTable());
            //   System.out.println("|---------|--------------------|-------|----|");
            // break;
        }
    }
    void print_predmet(Scanner sc, Fakulta fk){

        System.out.println("vsechny predmety: 1\n" +
                "podle zkratky: 2");
        switch (sc.nextLine()){
            case "1":
                for (Map.Entry<String, Predmet> entry:fk.getPredmety().entrySet()){
                    System.out.println(entry.getValue().toTable());
                    System.out.println("|---------|--------------------|-------|--|");
                }
                break;
            case "2":
                HashMap<String,Predmet> h_tmp = fk.getPredmety();
                System.out.println(h_tmp.get(sc.nextLine()));
                break;
            default:
        }
    }
}
