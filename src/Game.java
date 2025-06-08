import Console.work.ConsoleInputReader;
import Console.work.OutputService;
import game.constans.GameConstants;

public class Game {
    private Player playerOne;
    private Player playerOpponent;
    private Player currentPlayer;
    private final ConsoleInputReader consoleInputReader;
    private final InputValidator inputValidator;
    private final OutputService output;



    public Game(OutputService output) {
        this.consoleInputReader = new ConsoleInputReader();
        this.inputValidator = new InputValidator();
        this.output = output;
    }

    public void welcome() {
        output.println(GameConstants.Messages.WELCOME);
        setupPlayer();
        output.printf(GameConstants.Templates.GREETINGS_TEMPLATE, playerOne.getName(), playerOpponent.getName());
        startGame();
    }

    private void startGame(){
        printShipInstallationConditions(playerOne);
        shipInstallationProcess(playerOne);
        printShipInstallationConditions(playerOpponent);
        shipInstallationProcess(playerOpponent);
        startWarProcess();

    }
    private void setupPlayer(){
        this.playerOne = createPlayer(1);
        this.playerOpponent = createPlayer(2);
        this.currentPlayer = playerOne;
    }
    private Player createPlayer(int playerNumber){
        while (true) {
            output.printf(GameConstants.Templates.NAME_PLAYER_TEMPLATE,playerNumber);
            String namePlayer = consoleInputReader.readLine();
            if (!namePlayer.isEmpty()) {
                return new Player(namePlayer);
            } else {
                System.out.println(GameConstants.Messages.INPUT_EMPTY);
            }
        }
    }
    private void printShipInstallationConditions(Player player){
        output.printf(GameConstants.Templates.INSTALL_THE_SHIPS_TEMPLATE, player.getName());
        output.println(GameConstants.Messages.INSTALLATION_CONDITIONS);
        output.println(GameConstants.Prompts.SIZE_SHIP);
        output.println(GameConstants.Prompts.COORDINATE_HEAD_SHIP_X);
        output.println(GameConstants.Prompts.COORDINATE_HEAD_SHIP_Y);
        output.println(GameConstants.Prompts.DIRECTIONS_SHIP);

    }

    private void shipInstallationProcess(Player player){
       while (player.getOwnField().getShipCounts() < PlayingField.MAX_TOTAL_COUNT_SHIPS){
           int sizeShip = inputSizeShip();
           int coordinateShipX = inputCoordinateShipHead(GameConstants.Prompts.COORDINATE_HEAD_SHIP_X);
           int coordinateShipY = inputCoordinateShipHead(GameConstants.Prompts.COORDINATE_HEAD_SHIP_Y);
           Direction direction = inputDirection();
           player.placeShip(new Ship(sizeShip,new Coordinate(coordinateShipX ,coordinateShipY),direction));
           player.getOwnField().printField(FieldType.PLAYER);
       }
    }
    private void startWarProcess(){
        while (notLose(playerOne) && notLose(playerOpponent)) {
            displayCurrentTurnInfo();
            currentPlayer.getEnemyField().printField(FieldType.OPPONENT);
            Coordinate shotCoordinate = currentPlayer.makeMove();
            boolean isHit = playerOpponent.getOwnField().shot(shotCoordinate);
            currentPlayer.getEnemyField().getMapShots().put(shotCoordinate, isHit ? ShotResult.HIT : ShotResult.MISS);

            if(!isHit){
                switchPlayer();
            }

        }
        output.printf(GameConstants.Templates.ALL_SHIPS_DESTROYER, playerOpponent.getName());
        currentPlayer.getEnemyField().printField(FieldType.OPPONENT);
        output.printf(GameConstants.Templates.PLAYER_WIN_TEMPLATE, currentPlayer.getName());

    }

    private int inputSizeShip(){
        while (true) {
            output.printf(GameConstants.Templates.ENTER_TEMPLATE, GameConstants.Prompts.SIZE_SHIP + "\n");
            String sizeShip = consoleInputReader.readLine();
            try {
                int size = Integer.parseInt(sizeShip);
                if (size >= 1 && size <= 4) {
                    return size;
                }
                else {
                    output.println(GameConstants.Prompts.SIZE_SHIP);
                }
            } catch (NumberFormatException e) {
                output.println(GameConstants.Errors.INVALID_INPUT_SIZE);
            }
        }

    }

    private int inputCoordinateShipHead(String COORDINATE_HEAD_SHIP){
        while (true) {
            output.printf(GameConstants.Templates.ENTER_TEMPLATE, COORDINATE_HEAD_SHIP + "\n");
            String coordinateShip = consoleInputReader.readLine();
            try {
                int coordinate = Integer.parseInt(coordinateShip);
                if(coordinate >= 1 && coordinate <= 10){
                    return coordinate;
                }
                else {
                    output.println(GameConstants.Errors.INVALID_INPUT_COORDINATE);
                }
            }catch (NumberFormatException e){
                output.println(GameConstants.Errors.INVALID_INPUT_COORDINATE);
            }

        }
    }
    private Direction inputDirection(){
        while (true){
            output.printf(GameConstants.Templates.ENTER_TEMPLATE,GameConstants.Prompts.DIRECTIONS_SHIP + "\n");
            String directionShip = consoleInputReader.readLine();
            if(directionShip.isEmpty() || inputValidator.validateInputLength(directionShip) || inputValidator.isAllLetterHOrV(directionShip)){
                output.println(GameConstants.Errors.INVALID_INPUT_DIRECTION);
            }
            else {
                return directionShip.equalsIgnoreCase("г")? Direction.HORIZONTAL: Direction.VERTICAL;
            }
        }
    }

    private void switchPlayer() {
        Player temp = currentPlayer;
        currentPlayer = playerOpponent;
        playerOpponent = temp;
    }

    private boolean notLose(Player player){
        return player.getOwnField().getShipCounts() != 0;
    }

    private void displayCurrentTurnInfo(){
        output.printf(GameConstants.Templates.PLAYER_MOVE_TEMPLATE, currentPlayer.getName());
        output.printf(GameConstants.Templates.REMAINING_SHIPS_TEMPLATE, currentPlayer.getName(), currentPlayer.getOwnField().getShipCounts());
        output.printf(GameConstants.Templates.REMAINING_SHIPS_TEMPLATE, playerOpponent.getName(), playerOpponent.getOwnField().getShipCounts());
    }

}
