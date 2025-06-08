import game.constans.GameConstants;

public class Game {
    private Player playerOne;
    private Player playerOpponent;
    private Player currentPlayer;
    private final ConsoleInputReader consoleInputReader;
    private final InputValidator inputValidator;



    public Game() {
        this.consoleInputReader = new ConsoleInputReader();
        this.inputValidator = new InputValidator();
    }

    public void welcome() {
        System.out.println(GameConstants.Messages.WELCOME);
        setupPlayer();
        System.out.printf(GameConstants.Templates.GREETINGS_TEMPLATE, playerOne.getName(), playerOpponent.getName());
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
            System.out.printf(GameConstants.Templates.NAME_PLAYER_TEMPLATE,playerNumber);
            String namePlayer = consoleInputReader.readLine();
            if (!namePlayer.isEmpty()) {
                return new Player(namePlayer);
            } else {
                System.out.println(GameConstants.Messages.INPUT_EMPTY);
            }
        }
    }
    private void printShipInstallationConditions(Player player){
        System.out.printf(GameConstants.Templates.INSTALL_THE_SHIPS_TEMPLATE, player.getName());
        System.out.println(GameConstants.Messages.INSTALLATION_CONDITIONS);
        System.out.println(GameConstants.Prompts.SIZE_SHIP);
        System.out.println(GameConstants.Prompts.COORDINATE_HEAD_SHIP_X);
        System.out.println(GameConstants.Prompts.COORDINATE_HEAD_SHIP_Y);
        System.out.println(GameConstants.Prompts.DIRECTIONS_SHIP);
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
        System.out.printf(GameConstants.Templates.PLAYER_WIN_TEMPLATE, currentPlayer.getName());
    }

    private int inputSizeShip(){
        while (true) {
            System.out.printf(GameConstants.Templates.ENTER_TEMPLATE, GameConstants.Prompts.SIZE_SHIP + "\n");
            String sizeShip = consoleInputReader.readLine();
            try {
                int size = Integer.parseInt(sizeShip);
                if (size >= 1 && size <= 4) {
                    return size;
                }
                else {
                    System.out.println(GameConstants.Prompts.SIZE_SHIP);
                }
            } catch (NumberFormatException e) {
                System.out.println(GameConstants.Errors.INVALID_INPUT_SIZE);
            }
        }


    }

    private int inputCoordinateShipHead(String COORDINATE_HEAD_SHIP){
        while (true) {
            System.out.printf(GameConstants.Templates.ENTER_TEMPLATE, COORDINATE_HEAD_SHIP + "\n");
            String coordinateShip = consoleInputReader.readLine();
            try {
                int coordinate = Integer.parseInt(coordinateShip);
                if(coordinate >= 1 && coordinate <= 10){
                    return coordinate;
                }
                else {
                    System.out.println(GameConstants.Errors.INVALID_INPUT_COORDINATE);
                }
            }catch (NumberFormatException e){
                System.out.println(GameConstants.Errors.INVALID_INPUT_COORDINATE);
            }

        }
    }
    private Direction inputDirection(){
        while (true){
            System.out.printf(GameConstants.Templates.ENTER_TEMPLATE,GameConstants.Prompts.DIRECTIONS_SHIP + "\n");
            String directionShip = consoleInputReader.readLine();
            if(directionShip.isEmpty() || inputValidator.validateInputLength(directionShip) || inputValidator.isAllLetterHOrV(directionShip)){
                System.out.println(GameConstants.Errors.INVALID_INPUT_DIRECTION);
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
        System.out.printf(GameConstants.Templates.PLAYER_MOVE_TEMPLATE, currentPlayer.getName());
        System.out.printf(GameConstants.Templates.REMAINING_SHIPS_TEMPLATE, currentPlayer.getName(), currentPlayer.getOwnField().getShipCounts());
        System.out.printf(GameConstants.Templates.REMAINING_SHIPS_TEMPLATE, playerOpponent.getName(), playerOpponent.getOwnField().getShipCounts());
    }

}
