package chessElements;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

// Classe base para representar uma peça de xadrez
public abstract class ChessPiece extends Label {

    public ChessPiece(String pieceText) {
        super(pieceText);
        setMinSize(60, 60);
        setFont(javafx.scene.text.Font.font(45));
        setAlignment(javafx.geometry.Pos.CENTER);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    // Método abstrato para personalizar a peça
    public abstract void customize();

    // Adicione métodos adicionais conforme necessário para movimentação, captura,
    // etc.
}

// Classe para representar a Torre
class Rook extends ChessPiece {

    public Rook(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        // Personalize a cor da peça (para o exemplo, preto)
        setTextFill(Color.BLACK);
    }
}

// Classe para representar o Peão
class Pawn extends ChessPiece {

    public Pawn(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        // Personalize a cor da peça (para o exemplo, branco)
        setTextFill(Color.BLACK);
    }
}

class Knight extends ChessPiece {
    public Knight(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        setTextFill(Color.BLACK);

    }
}

class Bishop extends ChessPiece {

    public Bishop(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        setTextFill(Color.BLACK);

    }

}

class King extends ChessPiece {

    public King(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        setTextFill(Color.BLACK);

    }

}

class Queen extends ChessPiece {

    public Queen(String pieceText) {
        super(pieceText);
    }

    @Override
    public void customize() {
        setTextFill(Color.BLACK);

    }

}

// Adicione classes adicionais para outras peças conforme necessário
