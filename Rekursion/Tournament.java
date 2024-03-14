package MUPRG1.Rekursion;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tournament {

    static final String PROMPT = "Geben Sie den nächsten Teilnehmer ein (leere Eingabe zum Beenden) :";

    public static void main(String args[]) {
        System.out.println(powerOf2(4));
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
        
        while(!input.equals("")){
            node = addParticipant(input, node);
            System.out.println(PROMPT);
            input = readFromConsole();
        }

        return node;
    }

    static String readFromConsole(){
        String input = "";
        try {
            input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {}
        return input;
    }

}
