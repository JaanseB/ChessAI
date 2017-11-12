package chessboard.model.pieces;

import chessboard.model.position.BoardPosition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.util.*;

public class Bishop extends ChessPiece {


    public Bishop(int row, int col)
    {
        super(row, col);
    }

    @Override
    public String pieceText() {
        return "B";
    }

    @Override
    public List<BoardPosition> getMovePositions(){

        List<BoardPosition> positions = new ArrayList<BoardPosition>();
        return getNeighbours(this.getRow(), this.getColumn(), positions);

    }

    private List<BoardPosition> getNeighbours(int row, int column, List<BoardPosition> neighbours) {

        if(inRange(row, column)){
            if(isDiagonal(row - 1, column - 1) && !positionChecked(row - 1, column - 1, neighbours)) {
                neighbours.add(new BoardPosition(row - 1, column - 1));
                getNeighbours(row - 1, column - 1,neighbours);
            }
            if(isDiagonal(row - 1, column + 1) && !positionChecked(row - 1, column + 1, neighbours)) {
                neighbours.add(new BoardPosition(row - 1, column + 1));
                getNeighbours(row - 1, column + 1,neighbours);
            }
            if(isDiagonal(row + 1, column - 1) && !positionChecked(row + 1, column - 1, neighbours)) {
                neighbours.add(new BoardPosition(row + 1, column - 1));
                getNeighbours(row + 1, column - 1,neighbours);
            }
            if(isDiagonal(row + 1, column + 1) && !positionChecked(row + 1, column + 1, neighbours)) {
                neighbours.add(new BoardPosition(row + 1, column + 1));
                getNeighbours(row + 1, column + 1,neighbours);
            }
        }
        return neighbours;
    }

    private boolean isDiagonal(int row, int column) {
        return inRange(row, column) && (Math.abs(this.getColumn() - column) == Math.abs(this.getRow() - row));
    }

    @Override
    public ChessPiece.Type getType() {
        return Type.BISCHOP;
    }



}
