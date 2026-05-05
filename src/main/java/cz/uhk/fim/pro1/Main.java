package cz.uhk.fim.pro1;

import cz.uhk.fim.pro1.model.Fakulta;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Vitejte na FIM!"));

//        List<String> list = new ArrayList<>();
//        Arrays.asList(Semestr.values()).forEach((s) -> { list.add(s.toString()); });

        Fakulta fakulta = new Fakulta("Fakulta informatiky a managementu", "FIM");
        System.out.println(fakulta);

    }
}
