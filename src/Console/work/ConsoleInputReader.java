package Console.work;

import java.util.Scanner;

public class ConsoleInputReader {
    private final Scanner scanner;

    public ConsoleInputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine(){
        return scanner.nextLine();
    }
}
