package lk.ijse.dep.service;

public class HumanPlayer extends Player {

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {
        // Check if the move is legal
        if (board.isLegalMove(col)){
            //Update the board with the human player's move
            board.updateMove(col,Piece.BLUE);
            //Notify the UI about the move
            board.getBoardUI().update(col,true);

            //Check for a winner
            Winner winner = board.findWinner();
            if (winner.getWinningPiece() != Piece.EMPTY){
                board.getBoardUI().notifyWinner(winner);
            }else{
                //Check for tied game
                if (!board.existLegalMoves()){
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }
        }else{
            // Handle when move is not legal - example like display error message to the user
            System.out.println("Invalid move! Please choose another column ");
        }
    }

}
