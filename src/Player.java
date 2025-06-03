public class Player {
    private String name;
    private PlayingField ownField;
    private PlayingField enemyField;
    private ConsoleInputReader consoleInputReader;


    public Player(String name) {
        this.name = name;
        this.ownField = new PlayingField(10,10, true);
        this.enemyField = new PlayingField(10,10, false);
        consoleInputReader = new ConsoleInputReader();

    }

    public String getName() {
        return name;
    }

    public PlayingField getOwnField() {
        return ownField;
    }

    public PlayingField getEnemyField() {
        return enemyField;
    }

    public void placeShip(Ship ship){
        ownField.installShip(ship);
    }
    public Coordinate makeMove(){
       while (true) {
           try {
               System.out.println(this.name + " введите координату выстрела по оси X");
               int coordinateShotX = Integer.parseInt(consoleInputReader.readLine());
               System.out.println(this.name + " введите координату выстрела по оси Y");
               int coordinateShotY = Integer.parseInt(consoleInputReader.readLine());
               return new Coordinate(coordinateShotX, coordinateShotY);
           } catch (NumberFormatException e) {
               System.out.println("Некорректный ввод");
           }
       }

    }
}
