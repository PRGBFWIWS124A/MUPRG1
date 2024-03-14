package MUPRG1.Rekursion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tournament {

    static final String PROMPT = "Geben Sie den nächsten Teilnehmer ein (leere Eingabe zum Beenden) :";

    public static void main(final String[] args) {
        TournamentNode root = Tournament.readParticipants();
        while (!Tournament.finished(root)) {
            Tournament.showCurrentState(root);
            root = Tournament.readNextResult(root);
        }
        Tournament.showCurrentState(root);
    }   

    static boolean finished(final TournamentNode root) {
        return root.winner() != null;
    }

    static TournamentNode setPoints(final TournamentNode node, final int points) {
        return new TournamentNode(node.left(), node.right(), node.winner(), points);
    }

    static int powerOf2_2(final int nonNegativeNumber) {
        return (int) Math.round(Math.pow(2, nonNegativeNumber));
    }

    static int powerOf2(final int nonNegativeNumber) {
        return powerOf2Rek(nonNegativeNumber - 1, 2, 2);
    }

    static int powerOf2Rek(int round, int result, int original) {
        if (round <= 0)
            return result;
        else {
            result = result * original;
            return powerOf2Rek(round - 1, result, original);
        }
    }

    static int rowOffset(final int level, final int heigth) {
        return powerOf2(heigth) / powerOf2(level);
    }

    static int getHeight(final TournamentNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = getHeight(node.left());
        int rightHeight = getHeight(node.right());

        return Math.max(leftHeight, rightHeight) + 1;

    }

    static int countNames(final TournamentNode node) {
        if (node == null)
            return 0;

        int count = 0;
        if (finished(node)) {
            count = 1;
            if (node.left() != null)
                count = count + countNames(node.left());

            if (node.right() != null)
                count = count + countNames(node.right());
            return count;
        } else {
            if (node.left() != null)
                count = count + countNames(node.left());

            if (node.right() != null)
                count = count + countNames(node.right());
            return count;
        }
    }

    static int getNumberOfLeaves(final TournamentNode node) {
        int count = 1;
        if (node.left() != null)
            count = count + getNumberOfLeaves(node.left());

        if (node.right() != null)
            count = count + getNumberOfLeaves(node.right());

        return count;
    }

    static TournamentNode addParticipant(final String name, final TournamentNode node) {
        if (node == null) {
            return new TournamentNode(null, null, name, 0);
        }
        if (node.winner() != null) {
            return new TournamentNode(node, new TournamentNode(null, null, name, 0), null, 0);
        }
        int leavesLeft = getNumberOfLeaves(node.left());
        int leavesRight = getNumberOfLeaves(node.right());
        if (leavesLeft > leavesRight) {
            return new TournamentNode(node.left(), addParticipant(name, node.right()), null, 0);
        } else
            return new TournamentNode(addParticipant(name, node.left()), node.right(), null, 0);
    }

    static TournamentNode readParticipants() {
        TournamentNode node = null;

        System.out.println(PROMPT);
        String input = readFromConsole();

        while (!input.equals("")) {
            node = addParticipant(input, node);
            System.out.println(PROMPT);
            input = readFromConsole();
        }
        return node;
    }

    static int readPoints(final String name) {
        int result = 0;
        boolean ok = false;
        while (!ok) {
            System.out.println("Punktzahl " + name + ":");
            try {
                result = Integer.parseInt(readFromConsole());
                ok = true;
            } catch (NumberFormatException e) {
                ok = false;
            }
        }
        return result;
    }

    static TournamentNode readNextResult(final TournamentNode node) {
        if (node.left().winner() != null && node.right().winner() != null) {
            int leftPoints = readPoints(node.left().winner());
            int rightPoints = readPoints(node.right().winner());
            while (leftPoints == rightPoints) {
                System.out.println("Punktzahl darf nicht gleich sein.");
                leftPoints = readPoints(node.left().winner());
                rightPoints = readPoints(node.right().winner());
            }
            return new TournamentNode(
                    setPoints(node.left(), leftPoints),
                    setPoints(node.right(), rightPoints),
                    leftPoints > rightPoints ? node.left().winner() : node.right().winner(),
                    0);
        }

        if (node.left().winner() != null) {
            return new TournamentNode(node.left(), readNextResult(node.right()), null, 0);
        }

        if (node.right().winner() != null) {
            return new TournamentNode(readNextResult(node.left()), node.right(), null, 0);
        }

        int leftNames = countNames(node.left());
        int rightNames = countNames(node.right());
        return leftNames > rightNames ? new TournamentNode(node.left(), readNextResult(node.right()), null, 0)
                : new TournamentNode(readNextResult(node.left()), node.right(), null, 0);
    }

    static void fillDisplay(final TournamentNode node, final int level, final int row, final int columnLength,
            final int height, final char[][] display) {
        if (node == null) {
            return;
        }
        if (node.winner() != null) {
            int startcolumn = (height - level) * (columnLength + 1);
            for (int i = 0; i < node.winner().length(); i++) {
                display[row][startcolumn + i] = node.winner().charAt(i);
            }
            if (level > 0) {
                String points = String.valueOf(node.points());
                display[row][startcolumn + node.winner().length() + 1] = '(';
                for (int i = 0; i < points.length(); i++) {
                    display[row][startcolumn + node.winner().length() + 1 + i] = points.charAt(i);
                }
                display[row][startcolumn + node.winner().length() + 2 + points.length()] = ')';
            }
            fillDisplay(node.left(), level + 1, row, columnLength, height, display);
            fillDisplay(node.right(), level + 1, rowOffset(level, height) + row, columnLength, height, display);
        }
    }

    static int getLengthOfLongestColumn(final TournamentNode node) {
        if (node == null) {
            return 0;
        }
        int max = Math.max(getLengthOfLongestColumn(node.left()), getLengthOfLongestColumn(node.right()));

        return Math.max(max,
                node.winner() != null ? node.winner().length() + String.valueOf(node.points()).length() + 3 : 0);
    }

    static char[][] toDisplay(final TournamentNode root) {
        final int columnLength = Tournament.getLengthOfLongestColumn(root);
        final int height = Tournament.getHeight(root);
        final char[][] display =
            new char[Tournament.powerOf2(height) * 2][(columnLength + 1) * (height + 1)];
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[i].length; j++) {
                display[i][j] = ' ';
            }
        }
        Tournament.fillDisplay(root, 0, 0, columnLength, height, display);
        return display;
    }

    static void showCurrentState(final TournamentNode root) {
        final char[][] display = Tournament.toDisplay(root);
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[i].length; j++) {
                System.out.print(display[i][j]);
            }
            System.out.println();
        }
    } 

    static String readFromConsole() {
        String input = "";
        try {
            input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
        }
        return input;
    }

}
