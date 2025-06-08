import Console.work.ConsoleOutputService;

public class SeaBattle {
    public static void main(String[] args) {
        Game game = new Game(new ConsoleOutputService());
        game.welcome();
    }

}