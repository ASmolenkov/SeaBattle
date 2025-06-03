public class InputValidator {


    public boolean validateInputShipSize(int shipSize){
        return  shipSize >= 1 && shipSize <= 4;
    }
    public boolean validateInputCoordinates(int coordinates){
        return coordinates >= 1 && coordinates <= 10;
    }
    public boolean validateInputLength(String direction){
        return direction.length() > 1;
    }
    public boolean isAllLetterHOrV(String direction){
        char first = direction.trim().toLowerCase().charAt(0);
        return first != 'г' && first != 'в';
    }





}

