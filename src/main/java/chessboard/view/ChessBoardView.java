package chessboard.view;

import chessboard.model.ChessBoardController;
import chessboard.model.pieces.ChessPiece;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChessBoardView extends Stage {

    private ChessBoardController model;

    private ChessPiece selectedPiece;

    public ChessBoardView(ChessBoardController model){
        this.model = model;
    }

    public void initBoard()
    {

        this.setTitle("Hello Chess!");

        BorderPane root = new BorderPane();
        root.setCenter(createBoard());
        this.setScene(new Scene(root, 300, 250));
    }

    public GridPane createBoard()
    {
        GridPane grid = new GridPane();
        //grid.setPadding(new Insets(0, 10, 0, 10));

        grid.getColumnConstraints().setAll(
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build(),
                ColumnConstraintsBuilder.create().percentWidth(100/8.0).build()
        );

        grid.getRowConstraints().setAll(
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build(),
                RowConstraintsBuilder.create().percentHeight(100/8.0).build()
        );

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Text category = new Text("" + 1);
                category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                grid.add(createBoardCell(j,i), i, j, 1,1);
            }
        }
        return grid;
    }

    private Node createBoardCell(final int row, final int column) {
        return model.getCell(row, column).getViewComponent();
    }

}
