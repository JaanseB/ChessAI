package chessboard.model;

import chessboard.ChessBoard;
import chessboard.model.listeners.ChessBoardCellListener;
import chessboard.model.pieces.*;
import chessboard.model.player.Player;
import chessboard.model.position.BoardPosition;
import chessboard.model.position.ChessBoardCell;

import java.util.ArrayList;
import java.util.List;

public class ChessBoardController implements ChessBoardCellListener {


    public enum State { NONE, PIECE_SELECTED}

    private static ChessBoardCell[][] m_board;
    private Player[] m_player;
    private int m_playerIdx;

    private State m_state;
    private ChessBoardCell m_selectedCell;

    public ChessBoardController()
    {
        m_state = State.NONE;

        initPlayers();
        initBoard(8,8);

    }

    public void nextPlayer(){
        m_playerIdx = (m_playerIdx + 1) % (m_player.length);
    }

    public void startGame() {
        m_playerIdx = 0;
    }


    private void initPlayers() {

        m_playerIdx = 0;

        m_player = new Player[2];
        m_player[0] = new Player(1);
        m_player[0].setName("Player 1");
        m_player[1] = new Player(2);
        m_player[1].setName("Player 2");
    }

    public void initBoard(int rows, int cols)
    {
        Player currPlayer = null;

        m_board = new ChessBoardCell[8][8];

        for(int i = 0; i < rows; i++){

            if(i == 0)
                currPlayer = m_player[0];
            else if( i == rows - 2)
                currPlayer = m_player[1];

            for(int j = 0; j < cols; j++){

                if(i == 0 || i == rows - 1){
                    if(j == 0 || j == cols - 1)
                    {
                        m_board[i][j] =  new ChessBoardCell(i,j, ChessPiece.Type.ROOK);
                    }else if(j == 1 || j == cols - 2){
                        m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.KNIGHT);
                    }else if(j == 2 || j == cols - 3){
                        m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.BISCHOP);
                    }else if(j == 3){
                        m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.KING);
                    }else if(j == 4){
                        m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.QUEEN);
                    }else{
                        m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.NONE);
                    }
                }else if(i == 1 || i == rows - 2){
                    m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.PAWN);
                }else{
                    m_board[i][j] = new ChessBoardCell(i,j, ChessPiece.Type.NONE);
                }

                if(m_board[i][j].getType() != ChessPiece.Type.NONE)
                {
                    m_board[i][j].setPlayer(currPlayer);
                }

                m_board[i][j].registerListener(this);

            }
        }
    }

    public ChessPiece getPiece(int row, int col){
        return m_board[row][col].getPiece();
    }

    public static ChessBoardCell getCell(int row, int col) {
        return m_board[row][col];
    }

    @Override
    public void select(int row, int col) {

        ChessBoardCell cell = getCell(row, col);

        if(m_state == State.PIECE_SELECTED)
        {
            if(cell.isCandidate()){



                if(cell.getPiece().getType() != ChessPiece.Type.NONE){
                    moveToPosition(row, col, true);
                }else{
                    moveToPosition(row, col, false);
                }
                unselect(m_selectedCell.getPiece().getRow(), m_selectedCell.getPiece().getColumn());
                unselect(cell.getPiece().getRow(), cell.getPiece().getColumn());

            }
            if(cell.isSelected()){
                unselect(cell.getPiece().getRow(), cell.getPiece().getColumn());
            }
        }else if(getPiece(row, col).getPlayer() == m_player[m_playerIdx]) {

            cell.select();

            m_state = State.PIECE_SELECTED;
            m_selectedCell = cell;

            ChessPiece piece = cell.getPiece();
            List<BoardPosition> positions = piece.getMovePositions();

            positions = validatePositions(positions);

            for (BoardPosition position : positions) {
                getCell(position.getRow(), position.getCol()).markCandidate();
            }
        }
    }

    private void unselect(int row, int column) {

        m_selectedCell = null;
        m_state = State.NONE;

        getCell(row, column).unselect();
        resetCandidates();
    }

    private void resetCandidates() {

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(getCell(i,j).getState() == ChessBoardCell.State.CANDIDATE)
                {
                    getCell(i,j).setState(ChessBoardCell.State.NONE);
                }
            }
        }
    }


    private void moveToPosition(int row, int col, boolean capture) {

        int selectedRow, selectedCol;
        ChessPiece selectedPiece = m_selectedCell.getPiece();

        selectedRow =  m_selectedCell.getPiece().getRow();
        selectedCol = m_selectedCell.getPiece().getColumn();

        if(capture)
        {
            m_board[selectedRow][selectedCol].setEmptyPiece();
        }else {
            m_board[selectedRow][selectedCol].setPiece(selectedRow, selectedCol, m_board[row][col].getPiece());
        }
        m_board[row][col].setPiece(row, col, selectedPiece);

        endRound();
    }

    private void endRound() {
         nextPlayer();
    }

    private List<BoardPosition> validatePositions(List<BoardPosition> positions) {

        List<BoardPosition> newPositions = new ArrayList<BoardPosition>();

        for(BoardPosition position : positions)
        {
            ChessPiece piece = getPiece(position.getRow(), position.getCol());
            if(!(piece.getType() != ChessPiece.Type.NONE && piece.getPlayer() == m_player[m_playerIdx]))
            {
                newPositions.add(position);
            }
        }

        return newPositions;

    }
}
