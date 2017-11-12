package chessboard.model.pieces;

import chessboard.model.ChessBoardController;
import chessboard.model.position.BoardPosition;
import chessboard.model.position.ChessBoardCell;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(int row, int col) {
        super(row, col);
    }

    @Override
    public String pieceText() {
        return "P";
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();

        for(int row = getRow() - 2; row <= getRow() + 2; row++)
        {
           if(inRange(row, getColumn())) {
               positions.add(new BoardPosition(row, getColumn()));
           }
        }

        if(inRange(getRow() - 1, getColumn() - 1) && ChessBoardController.getCell(getRow() - 1, getColumn() - 1).getPiece().getType() != Type.NONE)
        {
            positions.add(new BoardPosition(getRow() - 1, getColumn() - 1));
        }
        if(inRange(getRow() - 1, getColumn() + 1) && ChessBoardController.getCell(getRow() - 1, getColumn() + 1).getPiece().getType() != Type.NONE)
        {
            positions.add(new BoardPosition(getRow()  - 1, getColumn() + 1));
        }
        if(inRange(getRow() + 1, getColumn() - 1) && ChessBoardController.getCell(getRow() + 1, getColumn() - 1).getPiece().getType() != Type.NONE)
        {
            positions.add(new BoardPosition(getRow() + 1, getColumn() - 1));
        }
        if(inRange(getRow() + 1, getColumn() + 1) && ChessBoardController.getCell(getRow() + 1, getColumn() + 1).getPiece().getType() != Type.NONE)
        {
            positions.add(new BoardPosition(getRow() + 1, getColumn() + 1));
        }

        return positions;
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.PAWN;
    }

}
