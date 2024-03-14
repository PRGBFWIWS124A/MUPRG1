package MUPRG1.Rekursion;

public class Tournament {
    
    public static void main(String args[]){
        System.out.println(powerOf2(4));
    }



    static boolean finished(final TournamentNode root){
        return root.winner() != null;
    }

    static TournamentNode setPoints(final TournamentNode node, final int points){
        return new TournamentNode(node.left(), node.right(), node.winner(), points);
    }

    static int powerOf2_2(final int nonNegativeNumber){
        return (int) Math.round(Math.pow(2, nonNegativeNumber));
    }

    static int powerOf2(final int nonNegativeNumber){
        return powerOf2Rek(nonNegativeNumber - 1, 2, 2);
    }

    static int powerOf2Rek(int round, int result, int original){
        if(round <= 0)return result;
        else{
            result = result * original;
            return powerOf2Rek(round - 1, result, original);
        }
    }

    static int rowOffset(final int level, final int heigth){
        return powerOf2(heigth)/powerOf2(level);
    }

    static int getHeight(final TournamentNode node){
        if(node.left() == null && node.right() == null)return 0;

        if(node.left() == null){
            return getHeight(node.right()) + 1;
        }
        return getHeight(node.right()) + 1;
    }

    static int countNames(final TournamentNode node){
        int count = 0;
        if(finished(node)){
            if(node.left() != null) count = count + countNames(node.left()) + 1;

            if(node.right() != null) count = count + countNames(node.right()) + 1;
            return count;
        }else{
            if(node.left() != null) count = count + countNames(node.left());

            if(node.right() != null) count = count + countNames(node.right());
            return count;
        }
    }

    static int getNumberOfLeaves(final TournamentNode node){
        int count = 1;
        if(node.left() != null) count = count + getNumberOfLeaves(node.left());

        if(node.right() != null) count = count + getNumberOfLeaves(node.right());

        return count;
    }

    static TournamentNode addParticipant(final String name, final TournamentNode node){
        
    }
}
