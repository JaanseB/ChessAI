package chessboard.model.position;

import chessboard.model.listeners.ChessBoardCellListener;
import chessboard.model.pieces.*;
import chessboard.model.player.Player;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardCell {


    private static final Paint[] TEXTCOLORS = {Color.BLACK, Color.BROWN} ;

    public enum State {NONE, SELECTED, CANDIDATE}

    ;


    private ChessPiece piece;
    private int row, col;
    private State state;

    private StackPane viewComponent;
    private Rectangle viewBackground;
    private Text viewText;

    private List<ChessBoardCellListener> listeners = new ArrayList<ChessBoardCellListener>();

    public ChessBoardCell(int row, int column, ChessPiece.Type type) {
        state = State.NONE;
        listeners = new ArrayList();
        CreatePiece(row, column, type);
    }

    public void registerListener(ChessBoardCellListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(ChessBoardCellListener listener) {
        listeners.remove(listener);
    }

    public void CreatePiece(int row, int column, ChessPiece.Type type) {
        this.row = row;
        this.col = column;

        switch (type) {
            case BISCHOP:
                piece = new Bishop(row, column);
                break;
            case KING:
                piece = new King(row, column);
                break;
            case KNIGHT:
                piece = new Knight(row, column);
                break;
            case PAWN:
                piece = new Pawn(row, column);
                break;
            case QUEEN:
                piece = new Queen(row, column);
                break;
            case ROOK:
                piece = new Rook(row, column);
                break;
            default:
                piece = new ChessPiece(row, column);
                break;
        }
    }

    public ChessPiece.Type getType() {
        return piece.getType();
    }

    public void setPlayer(Player player) {
        piece.setPlayer(player);
    }

    public void select() {
        state = State.SELECTED;
        updateViewComponent();
    }

    public void unselect() {

        state = State.NONE;
        updateViewComponent();
    }

    public void markCandidate() {
        state = State.CANDIDATE;
        updateViewComponent();
    }

    public boolean isCandidate() {
        return state == State.CANDIDATE;
    }

    public boolean isSelected() {
        return state == State.SELECTED;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        updateViewComponent();
    }


    public Node getViewComponent() {

        if (viewComponent == null)
            viewComponent = initViewComponent();

        return viewComponent;
    }

    private StackPane initViewComponent() {

        StackPane stack = new StackPane();
        Color backgroundColor, foregroundColor;

        viewBackground = new Rectangle(30.0, 25.0);
        viewBackground.widthProperty().bind(stack.widthProperty());
        viewBackground.heightProperty().bind(stack.heightProperty());

        updateBackground();

        stack.getChildren().addAll(viewBackground);

        viewText = new Text(piece.pieceText());
        updateText();

        stack.getChildren().addAll(viewText);
        stack.setAlignment(viewText, Pos.CENTER);

        stack.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                listeners.forEach(listener -> listener.select(row, col));
            }
        });

        return stack;
    }

    private void updateViewComponent() {
        updateBackground();
        updateText();
    }

    private void updateBackground() {
        Color backgroundColor;
        if ((row % 2 == 0 && col % 2 != 0) || (row % 2 != 0 && col % 2 == 0)) {
            backgroundColor = Color.BEIGE;
        } else {
            backgroundColor = Color.WHITE;
        }

        if (isSelected()) {
            backgroundColor = Color.BLUE;
        } else if (isCandidate()) {
            backgroundColor = Color.GREEN;
        }

        viewBackground.setFill(backgroundColor);
    }

    private void updateText() {
        viewText.setText(getPiece().pieceText());
        viewText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        if (isSelected())
            viewText.setFill(Color.WHITE);
        else if(getPiece().getPlayer() != null)
            viewText.setFill(TEXTCOLORS[getPiece().getPlayer().getID() - 1]);

//        viewText.setStroke(Color.web("#7080A0"));
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(int row, int col, ChessPiece piece) {
        this.piece = piece;

        this.setPosition(row, col);
        updateViewComponent();
    }
    public void setEmptyPiece() {
        this.piece = new ChessPiece(row, col);
        updateViewComponent();
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece.setPosition(new BoardPosition(row, col));
    }
}

