package chessboard.model.pieces;

import chessboard.model.position.BoardPosition;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {


    public Rook(int row, int col) {
        super(row, col);
    }

    @Override
    public String pieceText() {
        return "R";
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.ROOK;
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();

        for(int row = 0; row < 8; row++){
            positions.add(new BoardPosition(row, getColumn()));
        }

        for(int col = 0; col < 8; col++){
            positions.add(new BoardPosition(getRow(), col));
        }

        return positions;
    }

}
