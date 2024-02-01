package chessElements;

import javafx.scene.paint.Color;

public class EmptySquare extends ChessPiece {

    public EmptySquare(int row, int col) {
        super("", Player.NONE, row, col);
    }

    @Override
    public boolean move(int toRow, int toCol, ChessPiece[][] board) {
        return true;

    }

    @Override
    public void customize() {
        setTextFill(Color.BLACK);

    }

}
