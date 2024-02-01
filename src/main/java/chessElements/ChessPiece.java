package chessElements;

import javafx.scene.control.Label;

// Classe base para representar uma peça de xadrez

public abstract class ChessPiece extends Label {

    protected Player player;
    protected int row;
    protected int col;

    public ChessPiece(String pieceText, Player player, int row, int col) {
        super(pieceText);
        this.player = player;
        this.row = row;
        this.col = col;

        setMinSize(60, 60);
        setFont(javafx.scene.text.Font.font(45));
        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public abstract boolean move(int toRow, int toCol, ChessPiece[][] board);

    public abstract void customize();

    public Player getPlayer() {
        return this.player;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    // Adicione métodos adicionais conforme necessário para movimentação, captura,
    // etc.

    public enum Player {
        PLAYER_1, PLAYER_2, NONE
    }
}

// Adicione classes adicionais para outras peças conforme necessário
