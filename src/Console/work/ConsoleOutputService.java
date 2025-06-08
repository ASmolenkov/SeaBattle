package Console.work;

public class ConsoleOutputService implements OutputService {

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void printf(String template, Object... args) {
        System.out.printf(template, args);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }
    @Override
    public void printlnEmpty() {
        System.out.println();
    }
}
