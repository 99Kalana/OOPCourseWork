package lk.ijse.dep.service;

public class BoardImpl implements Board{

    private Piece[][] pieces;
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        // Initialize the pieces array with 6 columns and 5 rows
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        // Initialize all the pieces to Piece.Empty
        for (int col=0;col<NUM_OF_COLS;col++){
            for (int row=0;row<NUM_OF_ROWS;row++){
                pieces[col][row]=Piece.EMPTY;
            }
        }

    }



    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        //Start from the bottom of the column and go up
        for (int row=NUM_OF_ROWS-1;row>=0;row--){
            if (pieces[col][row]== Piece.EMPTY){
                // Return the first empty row found
                return row;
            }
        }
        // If no empty spots are found,return -1

        return -1;

    }

    @Override
    public boolean isLegalMove(int col) {
        // Check if the column has at least one empty spot
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        //Check each column for a legal move
        for (int col=0;col<NUM_OF_COLS;col++){
            if (isLegalMove(col)){
                //If a legal move is found, return true
                return true;
            }
        }
        // If no legal moves are found,return false
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        // Find the first available row in the specified column
        int availableRow = findNextAvailableSpot(col);

        // Assuming the move is legal, update the board
        pieces[col][availableRow]=move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        // Update the board with the specified piece
        pieces[col][row]=move;
    }

    @Override
    public Winner findWinner() {
        // Check for horizontal win
        for (int row=0;row<NUM_OF_ROWS;row++){
            for (int col=0;col<NUM_OF_COLS-3;col++){
                if (pieces[col][row]!= Piece.EMPTY && pieces[col][row]==pieces[col+1][row] && pieces[col][row]==pieces[col+2][row]&& pieces[col][row]==pieces[col+3][row] ){
                    return new Winner(pieces[col][row],col,row,col+3,row);
                }
            }
        }

        // Check for vertical win
        for (int col=0;col<NUM_OF_COLS;col++){
            for (int row=0;row<NUM_OF_ROWS-3;row++){
                if (pieces[col][row]!= Piece.EMPTY && pieces[col][row]==pieces[col][row+1] && pieces[col][row]==pieces[col][row+2]&& pieces[col][row]==pieces[col][row+3]){
                    return new Winner(pieces[col][row],col,row,col,row+3);
                }
            }
        }

        // No winner found

        return new Winner(Piece.EMPTY,-1,-1,-1,-1);

    }

}
