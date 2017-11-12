package chessboard.model.pieces;

import chessboard.model.player.Player;
import chessboard.model.position.BoardPosition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

public class ChessPiece {

    private boolean selected;
    private BoardPosition position;
    private Player player;


    public enum Type { NONE, BISCHOP, KING, KNIGHT, PAWN, QUEEN, ROOK };

    public ChessPiece(int row, int col)
    {
        position = new BoardPosition(row, col);
    }

    public String pieceText(){
        return "";
    }

    public int getRow() {
        return position.getRow();
    }

    public int getColumn() {
        return position.getCol();
    }

    public void setPosition(BoardPosition position){
        this.position = position;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public List<BoardPosition> getMovePositions() {
        return null;
    }

    public boolean positionChecked(int row, int column, List<BoardPosition> positions) {

        for(BoardPosition position : positions){
            if(position.getRow() == row && position.getCol() == column){
                return true;
            }
        }
        return false;
    }

    public boolean inRange(int row, int column){
        return (row >= 0 && row <= 7 && column >= 0 && column <= 7);
    }

    public Type getType(){
        return Type.NONE;
    }

}
