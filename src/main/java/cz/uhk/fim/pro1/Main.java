package cz.uhk.fim.pro1;

import cz.uhk.fim.pro1.model.Fakulta;

import java.io.InputStream;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Vitejte na FIM!"));

//        List<String> list = new ArrayList<>();
//        Arrays.asList(Semestr.values()).forEach((s) -> { list.add(s.toString()); });

        Fakulta fakulta = new Fakulta("Fakulta informatiky a managementu", "FIM");
        print_help();
        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input){
                case "1":
                    System.out.println(fakulta);
                    break;
                case "2":
                    System.out.println(fakulta);
                    break;
                case "3":
                    print_help();
                    break;
                default:
            }
        }

    }

    void print_help(){
        System.out.println("statistika ucitele: 1\n" +
                "print fakulta: 2\n" +
                "help: 3");
    }
}
