package chessboard.model.pieces;

import chessboard.model.position.BoardPosition;

import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece {


    public King(int row, int col) {
        super(row, col);
    }

    @Override
    public String pieceText() {
        return "Ki";
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();

        for(int row = getRow() - 1; row <= getRow() + 1; row++){
            for(int column = getColumn() - 1; column <= getColumn() + 1; column++){
                if(inRange(row, column)){
                    positions.add(new BoardPosition(row, column));
                }
            }
        }
        return positions;
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.KING;
    }
}
