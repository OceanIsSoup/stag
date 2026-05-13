package cz.uhk.fim.pro1;

import cz.uhk.fim.pro1.model.*;

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
                    print_ra(fakulta);
                    break;
                case "7":
                    print_rozvrh(sc,fakulta);
                    break;
                case"8":
                    add_ra_to_student(sc,fakulta);
                    break;
                case "9":
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
                print ra: 6
                print rozvrh: 7
                add subject to student: 8
                exit: 9""");
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
        switch (input) {
            case "1":
            System.out.println("|---------|--------------------|-------|----|");
            for (Map.Entry<String, Student> entry : fk.getStudenti().entrySet()) {
                System.out.println(entry.getValue().toTable());
                System.out.println("|---------|--------------------|-------|----|");
            }
            break;
            //students are not assigned id
            case "2":
               System.out.println("enter name:");
                input = sc.nextLine();
                System.out.println("|---------|--------------------|-------|----|");
               System.out.println(fk.getStudenti().get(input).toTable());
               System.out.println("|---------|--------------------|-------|----|");
             break;
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

    void print_rozvrh(Scanner sc,Fakulta fk){
        String input;

        System.out.println("enter name:");
        input = sc.nextLine();
        Student st = fk.getStudenti().get(input);
        System.out.println("|---------|--------------------|-------|----|");
        System.out.println(st.toTable());
        System.out.println("|---------|--------------------|-------|----|");
        Rozvrh rz = st.getRozvrh();
        if (rz==null){return;}
        System.out.println(rz);
        }

        void print_ra(Fakulta fk){
            System.out.println(fk.ar());
        }

        void add_ra_to_student(Scanner sc, Fakulta fk){

            System.out.println("which semester: ");
            String input = sc.nextLine();
            Semestr sm = Semestr.valueOf(input);
            System.out.println("subject name: ");
            input = sc.nextLine();
            System.out.println(fk.ar().rozvrhPredmetu(sm,input).toString());
            System.out.println("which one:");
            input = sc.nextLine();
            RozvrhovaAkce ra_tmp = fk.ar().getRa(Integer.parseInt(input));
            System.out.println("which student");
            input = sc.nextLine();
            Student st = fk.getStudenti().get(input);
            if (ra_tmp.pridatStudenta(st)){
                st.add_subject(ra_tmp);
            }

            System.out.println(st.getRozvrh().to_table());
        }
    }
