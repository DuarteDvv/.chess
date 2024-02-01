package chessElements;

import javafx.scene.paint.Color;

public class King extends ChessPiece {

    public King(String pieceText, Player player, int row, int col) {
        super(pieceText, player, row, col);
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