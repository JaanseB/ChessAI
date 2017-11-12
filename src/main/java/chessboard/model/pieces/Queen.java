package chessboard.model.pieces;

import chessboard.model.position.BoardPosition;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {

    public Queen(int row, int col) {
        super(row, col);
    }

    @Override
    public String pieceText(){
        return "Q";
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.QUEEN;
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();

        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(isDiagonal(row, col) || row == this.getRow() || col == this.getColumn())
                {
                    positions.add(new BoardPosition(row, col));
                }
            }
        }

        return positions;
    }


    private boolean isDiagonal(int row, int column) {
        return inRange(row, column) && (Math.abs(this.getColumn() - column) == Math.abs(this.getRow() - row));
    }
}
