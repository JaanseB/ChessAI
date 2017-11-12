package chessboard.model.pieces;

import chessboard.model.position.BoardPosition;

import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece {

    public Knight(int row, int col) {
        super(row, col);
    }

    @Override
    public String pieceText() {
        return "K";
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();

        int[] rows = {-2, -1, 1 ,2};
        int[] cols = {-2, -1, 1 ,2};

        for(int rowIdx = 0; rowIdx < 4; rowIdx++)
        {
            for(int colIdx = 0; colIdx < 4; colIdx++)
            {
                if((Math.abs(rows[rowIdx]) != Math.abs(cols[colIdx]) && inRange(getRow() + rows[rowIdx], getColumn() + cols[colIdx]))){
                    positions.add(new BoardPosition(getRow() + rows[rowIdx], getColumn() + cols[colIdx]));
                }
            }
        }


        return positions;
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.KNIGHT;
    }


}
