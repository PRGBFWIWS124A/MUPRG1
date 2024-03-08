import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Battleship {

    static final int SIZE = 10;
    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für ein Schiff der Länge %d ein: ";
    static final String ENTER_SHOT_COORDINATE_PROMPT = "Wo soll geschossen werden?";
    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    static final int ALL_HIT = 14;

    // Merhere static in richtiger und wichtiger reihenfolge
    /*
     * static {
     * SIZE = 10;
     * }
     */

    public static void main(String[] args) {
        while (true) {
            String test = getRandomCoordinate().toString();
            System.out.println(test);
        }
    }

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(((start.column() - end.column()))) + Math.abs((start.row() - end.row()));
    }

    static Coordinate getRandomCoordinate() {
        return new Coordinate(new Random().nextInt(SIZE), new Random().nextInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return (start.column() == end.column() || start.row() == end.row());

    }

    static void showSeperatorLine() {
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.column(), end.column()) + 1, SIZE - 1);
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.row(), end.row()) + 1, SIZE - 1);
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.column(), end.column()) - 1, SIZE - SIZE);
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.row(), end.row()) - 1, SIZE - SIZE);
    }

    /*
     * static Coordinate toCoordinate(final String input){
     * return new Coordinate(input.toLowerCase().charAt(0) - 'a',
     * Character.getNumericValue(input.charAt(1)));
     * //return new Coordinate(input.toLowerCase().charAt(0) - 97,
     * Integer.parseInt(String.valueOf(input.charAt(1))) -1);
     * }
     */

    static Coordinate toCoordinate(final String input) {
        int column = input.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(column, row);
    }

    static boolean isValidCoordinate(final String input) {
        return input.toLowerCase().matches("[a-j](10|[1-9])");
    }

    static String getStartCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Start", length);
    }

    static String getEndCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "End", length);
    }

    static void isValid() {
        // regex = regular expression
        String doubleChar = "[A-Z]{2}";
        String tor = "To+r!";
        String variableValid = "[A-Z|a-z|_]+[0-9|A-Z|a-z|_]";
        String variableValid2 = "[a-zA-Z_$][a-zA-Z_$0-9]*";
        String age = "[1-9]+[0-9]*[m|w|d]";
        String age2 = "[1-9][0-9]*[mwd]";

        String test = "Toooor!";
        System.out.println(test.matches(doubleChar));
        System.out.println(test.matches(tor));
        System.out.println(test.matches(variableValid));
        System.out.println(test.matches(variableValid2));
        System.out.println(test.matches(age));
        System.out.println(test.matches(age2));
    }

    static void showRowNumber(final int row) {
        if (row + 1 < 10)
            System.out.print(" ");
        System.out.print(row + 1);
    }

    static String gerade(final int points) {
        if (points > 0 || points < 100) {
            return "Ungültige Punktzahl";
        }
        if (points >= 97) {
            return "1,0";
        }
        if (points >= 92) {
            return "1,3";
        }
        if (points >= 89) {
            return "1,7";
        }
        if (points >= 85) {
            return "2,0";
        }
        if (points >= 81) {
            return "2,3";
        }
        if (points >= 77) {
            return "2,7";
        }
        if (points >= 72) {
            return "3,0";
        }
        if (points >= 67) {
            return "3,3";
        }
        if (points >= 59) {
            return "3,7";
        }
        if (points >= 50) {
            return "4,0";
        }

        return "5,0";
    }

    static Coordinate getRandomCoordinate(final Coordinate start, final int distance) {
        int choices = 0;
        if (start.column() - distance >= 0) {
            choices++;
        }
        if (start.column() + distance <= SIZE) {
            choices++;
        }
        if (start.row() - distance >= 0) {
            choices++;
        }
        if (start.row() + distance <= SIZE) {
            choices++;
        }

        int choice = new Random().nextInt(choices);

        if (start.column() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() - distance, start.row());
            }
        }
        if (start.column() + distance <= SIZE) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() + distance, start.row());
            }
        }
        if (start.row() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column(), start.row() - distance);
            }
        }
        return new Coordinate(start.column(), start.row() + distance);
    }

    static void showField(final Field field, final boolean showShips) {

        switch (field) {
            case SHIP:
                System.out.print(showShips == true ? "O" : " ");
                break;

            case FREE:
                System.out.print(" ");
                break;

            case WATER_HIT:
                System.out.print("X");
                break;

            case SHIP_HIT:
                System.out.print("*");
                break;
        }
    }

    static void shot(final Coordinate shot, final Field[][] field) {
        switch (field[shot.row()][shot.column()]) {
            case SHIP:
                field[shot.row()][shot.column()] = Field.SHIP_HIT;
                if(shipSunk(shot, field)) fillWaterHits(shot, field);
                break;

            case FREE:
                field[shot.row()][shot.column()] = Field.WATER_HIT;
                break;

            case WATER_HIT:
                // NICHTS (ICH WEISS DIESER CASE IST UNNOETIG)
                break;

            case SHIP_HIT:
                // NICHTS (ICH WEISS DIESER CASE IST UNNOETIG)
                break;
        }
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field) {
        for (int i = Math.min(start.row(), end.row()); i < Math.max(start.row(), end.row()); i++) {
            for (int j = Math.min(start.column(), end.column()); i < Math.max(start.column(), end.column()); i++) {
                field[i][j] = Field.SHIP;
            }
        }
    }

    static void showRow(final int row, final Field[][] ownField, final Field[][] otherField) {
        showRowNumber(row);
        System.out.print(" |");
        for (int i = 0; i < SIZE; i++) {
            showField(ownField[row][i], true);
            System.out.print("|");
        }
        System.out.print("   ");
        showRowNumber(row);
        System.out.print(" |");

        for (int j = 0; j < SIZE; j++) {
            showField(otherField[row][j], false);
            System.out.print("|");
        }

        System.out.println();
    }

    static void showFields(final Field[][] ownField, final Field[][] otherField) {
        System.out.print("    A B C D E F G H I J        A B C D E F G H J");
        showSeperatorLine();
        for (int i = 0; i < SIZE; i++) {
            showRow(i, ownField, otherField);
            showSeperatorLine();
        }
        System.out.println();
    }

    static boolean shipSunk(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        return true;
    }

    static void setAllFree(final Field[][] field) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                field[row][column] = Field.FREE;
            }
        }
    }

    static int countHits(final Field[][] field) {
        int res = 0;
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                if (field[row][column] == Field.SHIP_HIT) {
                    res++;
                }
            }
        }
        return res;
    }

    static void fillWaterHits(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        int minRow = row;
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        int maxRow = row;
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        int maxColumn = column;
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        int minColumn = column;
        for (row = minRow; row < maxRow; row++) {
            for (column = minColumn; column < maxColumn; column++) {
                if (field[row][column] == Field.FREE) {
                    field[row][column] = Field.WATER_HIT;
                }
            }
        }
    }

    static boolean noConflict(final Coordinate start, final Coordinate end, final Field[][] field) {
        for (int column = getMinSurroundingColumn(start, end); column <= getMaxSurroundingColumn(start,
                end); column++) {
            for (int row = getMinSurroundingRow(start, end); row <= getMaxSurroundingRow(start, end); row++) {
                if (field[column][row] != Field.FREE) {
                    return false;
                }
            }
        }
        return true;
    }

    static Coordinate readCoordinate(final String prompt) {

        String input = "";
        while (!isValidCoordinate(input)) {
            System.out.println(prompt);
            try {
                input = Battleship.READER.readLine();
            } catch (IOException e) {
            }
        }
        return toCoordinate(input);
    }

    static Coordinate getRandomUnshotCoordinate(final Field[][] field) {
        int chances = 0;
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    chances++;
                }
            }
        }

        int random_Skip = new Random().nextInt(chances);
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    random_Skip--;
                    if (random_Skip < 0)
                        return new Coordinate(column, row);
                }
            }
        }
        throw new IllegalStateException();
    }

    static Coordinate readStartCoordinate(final int length){
        return readCoordinate(getStartCoordinatePrompt(length));
    }

    static Coordinate readEndCoordinate(final int length){
        return readCoordinate(getEndCoordinatePrompt(length));
    }

    static boolean allHit(final Field[][] field){
        return countHits(field) == ALL_HIT;
    }

    static boolean endCondition(final Field[][] ownField, final Field[][] otherField){
        return allHit(ownField) || allHit(otherField);
    }

    static boolean validPosition(
        final Coordinate start,
        final Coordinate end,
        final int length,
        final Field[][] field
        ){
        return distance(start, end)  == length -1 && noConflict(start, end, field) && onOneLine(start, end);
    }

    static void turn(final Field[][] ownField, final Field[][] otherField){
        showFields(ownField, otherField);
        shot(readCoordinate(ENTER_SHOT_COORDINATE_PROMPT), otherField);
        shot(getRandomUnshotCoordinate(ownField), ownField);
    }

    static void outputWinner(final Field[][] ownField, final Field[][] otherField){
        showFields(ownField, otherField);
            if (allHit(otherField)) System.out.println("Du hast gewonnen!");
            else System.out.println("Der Computer hat gewonnen!"); 
    }

    static Field[][] iniOtherField(){
        Field[][] field = new Field[SIZE][SIZE];
        setAllFree(field);

        for(int ship_size = 5; ship_size >= 2; ship_size--){
            Coordinate start = getRandomCoordinate();
            placeShip(start, getRandomCoordinate(start, ship_size), field);
        }

        return field;
    }

    

    // Uebungen
    static int max(final int[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("Kein Array vorhanden");
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(array[i], max);
        }
        return max;
    }
}