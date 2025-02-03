import java.util.Scanner;

public class EigenerScanner {
    public Integer int_abfragen(String frage){
        Scanner myObj = new Scanner(System.in);
        System.out.println(frage);
        return myObj.nextInt();
    }

    public String string_abfragen(String frage){
        Scanner myObj = new Scanner(System.in);
        System.out.println(frage);
        return myObj.nextLine();
    }
}
