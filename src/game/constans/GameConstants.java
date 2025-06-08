package game.constans;

public class GameConstants {
    private GameConstants(){}

    public static final class Messages{
        public static final String WELCOME = "Добро пожаловать в игру \"Морской бой\"";
        public static final String INPUT_EMPTY = "Вы ничего не ввели! Повторите попытку.";
        public static final String INSTALLATION_CONDITIONS = "Для установки коробля необходимо ввести в правильном порядке следующие данные:";
        public static final String MISS = "Мимо";
        public static final String HIT_TARGET = "Попадание!";
        public static final String SHIP_DESTROYED = "Корабль уничтожен!!!";
        public static final String SHIP_OUT_OF_BOUNDS = "Корабль выходит за пределы поля. Измените координаты!";
    }

    public static final class Templates {
        public static final String GREETINGS_TEMPLATE = "Приветствую %s и %s, Игра начинается! \n";
        public static final String NAME_PLAYER_TEMPLATE = "Введите имя Игрока №%d:\n";
        public static final String INSTALL_THE_SHIPS_TEMPLATE = "Игрок %s расставь свои корабли! \n";
        public static final String ENTER_TEMPLATE = "Введите %s";
        public static final String PLAYER_MOVE_TEMPLATE = "Ход игрока: %s \n";
        public static final String REMAINING_SHIPS_TEMPLATE = "У игрока %s осталось кораблей - %d \n";
        public static final String PLAYER_WIN_TEMPLATE = "Победил игрок %s \n";
        public static final String COORDINATE_SHOOT_X_TEMPLATE = " %s введите координату выстрела по оси X \n";
        public static final String COORDINATE_SHOOT_Y_TEMPLATE = " %s введите координату выстрела по оси Y \n";
        public static final String SHIP_SETUP_TEMPLATE = "Корабль установлен! осталось %s кораблей \n";
        public static final String SHIP_LIMITS_TEMPLATE = "Количество - %d палубных кораблей достигло лимита! Лимит - %d\n";
        public static final String ALL_SHIPS_DESTROYER = "Все корабли игрока - %s уничтожены!\n";
    }

    public static final class Prompts {
        public static final String SIZE_SHIP = "Размер коробля (Цифры от 1 до 4)";
        public static final String COORDINATE_HEAD_SHIP_X = "Координату головы коробля по оси Х (цифры от 1 до 10)";
        public static final String COORDINATE_HEAD_SHIP_Y = "Координату головы коробля по оси Y (цифры от 1 до 10)";
        public static final String DIRECTIONS_SHIP = "Направление горизонтальное или вертикальное (Буквы \"В\" или \"Г\")";
    }

    public static final class Errors {
        public static final String INVALID_INPUT_SIZE = "Неверный ввод, введите цифру от 1 до 4";
        public static final String INVALID_INPUT_COORDINATE = "Неверный ввод, введите цифру от 1 до 10";
        public static final String INVALID_INPUT_DIRECTION = "Неверный ввод, введите букву В или Г";
        public static final String INVALID_ERROR = "Некорректный ввод";
        public static final String SHIP_NOT_SETUP = "Корабль не установлен";
        public static final String SHIPS_NEAR = "Рядом с кораблем уже есть корабль или новый корабль пересекает старый. Измените координаты!";

    }

}
