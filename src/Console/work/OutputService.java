package Console.work;

public interface OutputService {
    void print(String message);
    void printf(String template, Object... args);
    void println(String message);
    void printlnEmpty();
}
