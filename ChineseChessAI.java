import java.util.ArrayList;
import java.util.Arrays;

public class ChineseChessAI {
    // ----------type define-----------
    static enum Group {
        RED, BLACK, EMPTY
    }

    static enum Piece {
        KING, ADVISOR, ELEPHANT, CHARIOT, HORSE, CANNON, SOLDIER, EMPTY
    }
    // ----------end type define----------

    // ----------evaluate arrays : static for main test----------
    static int[] pieceEvaluate = { 0, 250, 250, 500, 300, 300, 80};

    static int[][][] kingPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0,-9,-9,-9, 0, 0, 0 },
                                                { 0, 0, 0,-8,-8,-8, 0, 0, 0 },
                                                { 0, 0, 0, 1, 5, 1, 0, 0, 0 } },

                                              { { 0, 0, 0, 1, 5, 1, 0, 0, 0 },
                                                { 0, 0, 0,-8,-8,-8, 0, 0, 0 },
                                                { 0, 0, 0,-9,-9,-9, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] advisorPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 3, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                 { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 3, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] elephantPositionEvaluate = { { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    {-2, 0, 0, 0, 3, 0, 0, 0,-2 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                  { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    {-2, 0, 0, 0, 3, 0, 0, 0,-2 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

    static int[][][] chariotPositionEvaluate = { { { 6, 8, 7,13,14,13, 7, 8, 6 },
                                                   { 6,12, 9,16,33,16, 9,12, 6 },
                                                   { 6, 8, 7,14,16,14, 7, 8, 6 },
                                                   { 6,13,13,16,16,16,13,13, 6 },
                                                   { 8,11,11,14,15,14,11,11, 8 },
                                                   { 8,12,12,14,15,14,12,12, 8 },
                                                   { 4, 9, 4,12,14,12, 4, 9, 4 },
                                                   {-2, 8, 4,12,12,12, 4, 8,-2 },
                                                   { 5, 8, 6,12, 0,12, 6, 8, 5 },
                                                   {-6, 6, 4,12, 0,12, 4, 6,-6 } },

                                                 { {-6, 6, 4,12, 0,12, 4, 6,-6 },
                                                   { 5, 8, 6,12, 0,12, 6, 8, 5 },
                                                   {-2, 8, 4,12,12,12, 4, 8,-2 },
                                                   { 4, 9, 4,12,14,12, 4, 9, 4 },
                                                   { 8,12,12,14,15,14,12,12, 8 },
                                                   { 8,11,11,14,15,14,11,11, 8 },
                                                   { 6,13,13,16,16,16,13,13, 6 },
                                                   { 6, 8, 7,14,16,14, 7, 8, 6 },
                                                   { 6,12, 9,16,33,16, 9,12, 6 },
                                                   { 6, 8, 7,13,14,13, 7, 8, 6 } } };

    static int[][][] horsePositionEvaluate = { { { 2, 2, 2, 8, 2, 8, 2, 2, 2 },
                                                 { 2, 8,15, 9, 6, 9,15, 8, 2 },
                                                 { 4,10,11,15,11,15,11,10, 4 },
                                                 { 5,20,12,19,12,19,12,20, 5 },
                                                 { 2,12,11,15,16,15,11,12, 2 },
                                                 { 2,10,13,14,15,14,13,10, 2 },
                                                 { 4, 6,10, 7,10, 7,10, 6, 4 },
                                                 { 5, 4, 6, 7, 4, 7, 6, 4, 5 },
                                                 {-3, 2, 4, 5,-10,5, 4, 2,-3 },
                                                 { 0,-3, 2, 0, 2, 0, 2,-3, 0 } },

                                               { { 0,-3, 2, 0, 2, 0, 2,-3, 0 },
                                                 {-3, 2, 4, 5,-10,5, 4, 2,-3 },
                                                 { 5, 4, 6, 7, 4, 7, 6, 4, 5 },
                                                 { 4, 6,10, 7,10, 7,10, 6, 4 },
                                                 { 2,10,13,14,15,14,13,10, 2 },
                                                 { 2,12,11,15,16,15,11,12, 2 },
                                                 { 5,20,12,19,12,19,12,20, 5 },
                                                 { 4,10,11,15,11,15,11,10, 4 },
                                                 { 2, 8,15, 9, 6, 9,15, 8, 2 },
                                                 { 2, 2, 2, 8, 2, 8, 2, 2, 2 } } };

    static int[][][] cannonPositionEvaluate = { { { 4, 4, 0,-5,-6,-5, 0, 4, 4 },
                                                  { 2, 2, 0,-4,-7,-4, 0, 2, 2 },
                                                  { 1, 1, 0,-5,-4,-5, 0, 1, 1 },
                                                  { 0, 3, 3, 2, 4, 2, 3, 3, 0 },
                                                  { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
                                                  {-1, 0, 3, 0, 4, 0, 3, 0,-1 },
                                                  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                  { 1, 0, 4, 3, 5, 3, 4, 0, 1 },
                                                  { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
                                                  { 0, 0, 1, 3, 3, 3, 1, 0, 0 } },

                                                { { 0, 0, 1, 3, 3, 3, 1, 0, 0 },
                                                  { 0, 1, 2, 2, 2, 2, 2, 1, 0 },
                                                  { 1, 0, 4, 3, 5, 3, 4, 0, 1 },
                                                  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                  {-1, 0, 3, 0, 4, 0, 3, 0,-1 },
                                                  { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
                                                  { 0, 3, 3, 2, 4, 2, 3, 3, 0 },
                                                  { 1, 1, 0,-5,-4,-5, 0, 1, 1 },
                                                  { 2, 2, 0,-4,-7,-4, 0, 2, 2 },
                                                  { 4, 4, 0,-5,-6,-5, 0, 4, 4 } } };

    static int[][][] soldierPositionEvaluate = { { { 0, 0, 0, 2, 4, 2, 0, 0, 0 },
                                                   {20,30,50,65,70,65,50,30,20 },
                                                   {20,30,45,55,55,55,45,30,20 },
                                                   {20,27,30,40,42,40,30,27,20 },
                                                   {10,18,22,35,40,35,22,18,10 },
                                                   { 3, 0, 4, 0, 7, 0, 4, 0, 3 },
                                                   {-2, 0,-2, 0, 6, 0,-2, 0,-2 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } },

                                                 { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                                                   {-2, 0,-2, 0, 6, 0,-2, 0,-2 },
                                                   { 3, 0, 4, 0, 7, 0, 4, 0, 3 },
                                                   {10,18,22,35,40,35,22,18,10 },
                                                   {20,27,30,40,42,40,30,27,20 },
                                                   {20,30,45,55,55,55,45,30,20 },
                                                   {20,30,50,65,70,65,50,30,20 },
                                                   { 0, 0, 0, 2, 4, 2, 0, 0, 0 } } };

    static int[][][][] positionEvaluate = { kingPositionEvaluate, advisorPositionEvaluate, elephantPositionEvaluate, chariotPositionEvaluate, horsePositionEvaluate, cannonPositionEvaluate, soldierPositionEvaluate };

    static int[] maxMove = { 4, 4, 4, 17, 8, 17, 4 }; // temp
    // ----------end evaluate arrays----------

    // ----------
    private static boolean inBound(int i, int j) {
        return (0 <= i && i < 10 && 0 <= j && j < 9);
    }
    // ----------

    // ----------generate move // only for method "generateMove"----------
    int[] kingMoveI = { 0, 0, 1, -1 };
    int[] kingMoveJ = { 1, -1, 0, 0 };
    int[] advisorMoveI = { 1, 1, -1, -1 };
    int[] advisorMoveJ = { 1, -1, 1, -1 };
    int[] elephantMoveI = { 2, 2, -2, -2 };
    int[] elephantMoveJ = { 2, -2, 2, -2 };
    // special method for chariot move
    int[] horseMoveI = { 1, 1, -1, -1, 2, 2, -2, -2 };
    int[] horseMoveJ = { 2, -2, 2, -2, 1, -1, 1, -1 };
    // special method for cannon move // special method for cannon eat
    // special method for horse move

    // for all move (including self group : to evaluate protect score)
    private static ArrayList<int[]> generateMove(int[][] boardPiece, int[][] boardGroup, int i, int j, boolean underDKing) {
        if (underDKing) return null; // todo
        else return null; // todo
    }

    // for only valid move
    private static ArrayList<int[]> generateAvailableMove(int[][] boardPiece, int[][] boardGroup, Group turn) {
        return null; // todo generate all move { i1, j1, i2, j2 };
    }
    // ----------end generate move---------

    // ----------check if under dKing----------
    // todo can be used in generateMove...
    private static boolean checkUnderDKing(int[][] boardPiece, int[][] boardGroup, Group turn) {
        return false; // todo
    }
    // ----------end check----------

    public static int evaluate(int[][] boardPiece, int[][] boardGroup, Group turn) {

        // piece position & type evaluate
        int posTotalRed = 0, posTotalBlack = 0;
        int typeTotalRed = 0, typeTotalBlack = 0;
        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[i].length; j++) {
                if (boardGroup[i][j] == Group.RED.ordinal() && boardGroup[i][j] != Piece.EMPTY.ordinal()) {
                    posTotalRed += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j];
                    typeTotalRed += pieceEvaluate[boardPiece[i][j]];
                } else if (boardGroup[i][j] == Group.BLACK.ordinal()) {
                    posTotalBlack += positionEvaluate[boardPiece[i][j]][boardGroup[i][j]][i][j];
                    typeTotalBlack += pieceEvaluate[boardPiece[i][j]];
                }
            }
        }
        System.out.println("position evaluate:");
        System.out.println("RED:" + posTotalRed + "BLACK:" + posTotalBlack);

        System.out.println("type evaluate:");
        System.out.println("RED:" + typeTotalRed + "BLACK:" + typeTotalBlack);
        // end piece position & type evaluate

        // piece mobility restrict
        // under attack
        // under protect
        // end the game : eva = -inf or inf
        int mobilityRed = 0, mobilityBlack = 0;
        boolean underDKing = checkUnderDKing(boardPiece, boardGroup, turn);
        // ifUnderDKing
        for (int i = 0; i < boardPiece.length; i++) {
            for (int j = 0; j < boardPiece[i].length; j++) {
                ArrayList<int[]> moves = generateMove(boardPiece, boardGroup, i, j, underDKing);
                for (int[] move : moves) {
                    // type evaluate
                    if (boardGroup[move[0]][move[1]] == Group.EMPTY.ordinal()) {
                        // todo add to mobility evaluate
                    } else if (boardGroup[move[0]][move[1]] != boardGroup[i][j]) {
                        // todo add extra attacking evaluate
                    } else { // self group
                        // todo add extra protecting evaluate
                    }

                    // total move evaluate
                    // todo maxMove[boardGroup[i][j]] - moves.size();
                }
            }
        }
        // calculate sum
        int totalScore = 0;
        if (turn == Group.BLACK) {
            // todo
        } else if (turn == Group.RED) {
            // todo
        } else {
            System.err.println("illegal input \"Group\"---at method : evaluate");
            System.exit(1);
        }

        return totalScore;
    }

    // find the deep-th score----------
    public static int bestScore(int[][] boardPiece, int[][] boardGroup, Group turn, int deep) { // recursion return { i1, i2, j1, j2 }
        // end of deep search
        if (deep <= 0) {
            return evaluate(boardPiece, boardGroup, turn);
        }

        ArrayList<int[]> moves = generateAvailableMove(boardPiece, boardGroup, turn);
        int max = Integer.MIN_VALUE;

        for (int[] move : moves) {
            // copy board
            int[][] newBoardPiece = new int[boardPiece.length][boardPiece[0].length];
            int[][] newBoardGroup = new int[boardGroup.length][boardGroup[0].length];
            Group newTurn = turn;
            for (int i = 0; i < boardPiece.length; i++) {
                for (int j = 0; j < boardPiece[i].length; j++) {
                    newBoardPiece[i][j] = boardPiece[i][j];
                    newBoardGroup[i][j] = boardGroup[i][j];
                }
            }

            newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]];
            newBoardPiece[move[0]][move[1]] = Piece.EMPTY.ordinal();
            newBoardGroup[move[2]][move[3]] = boardGroup[move[0]][move[1]];
            newBoardGroup[move[0]][move[1]] = Group.EMPTY.ordinal();

            if (turn == Group.BLACK) {
                newTurn = Group.RED;
            } else if (turn == Group.RED) {
                newTurn = Group.BLACK;
            } else {
                System.err.println("illegal input \"Group\"---at method : bestMove");
                System.exit(1);
            }

            // the opposite choose the best move
            int score = bestScore(newBoardPiece, newBoardGroup, newTurn, deep - 1);

            if (score > max) {
                max = score;
            }
        }
        return max;
    }
    // end of bestScore----------

    // can be called by main----------
    public static int[] bestMove(int[][] boardPiece, int[][] boardGroup, Group turn, int deep) { // recursion return { i1, i2, j1, j2 }
        ArrayList<int[]> moves = generateAvailableMove(boardPiece, boardGroup, turn);
        int max = Integer.MIN_VALUE;
        int[] ansMove = null;

        for (int[] move : moves) {
            // copy board
            int[][] newBoardPiece = new int[boardPiece.length][boardPiece[0].length];
            int[][] newBoardGroup = new int[boardGroup.length][boardGroup[0].length];
            Group newTurn = turn;
            for (int i = 0; i < boardPiece.length; i++) {
                for (int j = 0; j < boardPiece[i].length; j++) {
                    newBoardPiece[i][j] = boardPiece[i][j];
                    newBoardGroup[i][j] = boardGroup[i][j];
                }
            }

            newBoardPiece[move[2]][move[3]] = newBoardPiece[move[0]][move[1]];
            newBoardPiece[move[0]][move[1]] = Piece.EMPTY.ordinal();
            newBoardGroup[move[2]][move[3]] = boardGroup[move[0]][move[1]];
            newBoardGroup[move[0]][move[1]] = Group.EMPTY.ordinal();

            if (turn == Group.BLACK) {
                newTurn = Group.RED;
            } else if (turn == Group.RED) {
                newTurn = Group.BLACK;
            } else {
                System.err.println("illegal input \"Group\"---at method : bestMove");
                System.exit(1);
            }

            // the opposite choose the best move
            int score = bestScore(newBoardPiece, newBoardGroup, newTurn, deep - 1);

            if (score > max) {
                max = score;
                ansMove = move;
            }
        }
        return ansMove;
    }
    //end of bestMove----------

    // main for testing
    public static void main(String[] args) {
        Piece piece = Piece.valueOf("SOLDIER");
        System.out.println(pieceEvaluate[piece.ordinal()]);

        int[][] pieceBoard = null;
        int[][] groupBoard = null;
        Group turn = null;
        int deep = -1;

        int[] nextMove = bestMove(pieceBoard, groupBoard, turn, deep);
        System.out.println("the next move is : " + Arrays.asList(nextMove));
    }
}