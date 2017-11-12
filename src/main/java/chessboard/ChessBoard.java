package chessboard;

import chessboard.model.ChessBoardController;
import chessboard.model.listeners.ChessBoardCellListener;
import chessboard.view.ChessBoardView;

public class ChessBoard {

    private ChessBoardController model;
    private ChessBoardView view;

    public ChessBoard(){

        model = new ChessBoardController();
        view = new ChessBoardView(model);
    }

    public void start()
    {
        view.initBoard();
        view.show();
        model.startGame();
    }
}
