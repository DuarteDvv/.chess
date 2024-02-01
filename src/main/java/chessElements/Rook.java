package chessElements;

import javafx.scene.paint.Color;

// Classe para representar a Torre
public class Rook extends ChessPiece {

    public Rook(String pieceText, Player player, int row, int col) {
        super(pieceText, player, row, col);
    }

    @Override
    public boolean move(int toRow, int toCol, ChessPiece[][] board) {
        return true;

    }

    @Override
    public void customize() {
        // Personalize a cor da peça (para o exemplo, preto)
        setTextFill(Color.BLACK);
    }
}