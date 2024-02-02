package chessElements;

import javafx.scene.paint.Color;

// Classe para representar o Peão
public class Pawn extends ChessPiece {

    public Pawn(String pieceText, Player player, int row, int col) {
        super(pieceText, player, row, col);
    }

    @Override
    public boolean move(int toRow, int toCol, ChessPiece[][] board) { // sexta mexer em troca de player
        int rowDifference = toRow - this.row;
        int colDifference = Math.abs(toCol - this.col);

        // Verifica o jogador e direção do movimento
        int direction = (this.player == Player.PLAYER_1) ? 1 : -1;

        // Condição para avançar uma casa para frente
        if (rowDifference == direction && colDifference == 0 && board[toRow][toCol] instanceof EmptySquare) {
            return true;
        }

        // Condição para avançar duas casas no início do jogo
        if (rowDifference == 2 * direction && colDifference == 0 && this.row == (this.player == Player.PLAYER_1 ? 1 : 6)
                && board[toRow][toCol] instanceof EmptySquare
                && board[toRow - direction][toCol] instanceof EmptySquare) {
            return true;
        }

        // Condição para capturar peça na diagonal
        if (rowDifference == direction && colDifference == 1 &&
                !(board[toRow][toCol] instanceof EmptySquare) && board[toRow][toCol].getPlayer() != this.player) {
            return true;
        }

        return false;
    }

    @Override
    public void customize() {
        // Personalize a cor da peça (para o exemplo, branco)
        setTextFill(Color.BLACK);
    }
}