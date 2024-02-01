package game_components;

import chessElements.Bishop;
import chessElements.ChessPiece;
import chessElements.EmptySquare;
import chessElements.King;
import chessElements.Knight;
import chessElements.Pawn;
import chessElements.Queen;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import chessElements.Rook;
import chessElements.ChessPiece.Player;

public class GameScreenControll {

    private ChessPiece[][] board = new ChessPiece[8][8];
    private int selectedPieceRow = -1;
    private int selectedPieceCol = -1;

    @FXML
    private GridPane chessBoard;

    public void initialize() {
        // Configurar o tabuleiro
        initializeChessBoard();
    }

    private void initializeChessBoard() {

        for (int row = 0; row < 8; row++) {
            switch (row) {
                case 0:
                    board[row][0] = new Rook("♖", Player.PLAYER_2, row, 0);
                    board[row][1] = new Knight("♘", Player.PLAYER_2, row, 1);
                    board[row][2] = new Bishop("♗", Player.PLAYER_2, row, 2);
                    board[row][3] = new Queen("♕", Player.PLAYER_2, row, 3);
                    board[row][4] = new King("♔", Player.PLAYER_2, row, 4);
                    board[row][5] = new Bishop("♗", Player.PLAYER_2, row, 5);
                    board[row][6] = new Knight("♘", Player.PLAYER_2, row, 6);
                    board[row][7] = new Rook("♖", Player.PLAYER_2, row, 7);

                    break;

                case 1:
                    for (int col = 0; col < 8; col++) {
                        board[row][col] = new Pawn("♙", Player.PLAYER_2, row, col);
                    }

                    break;

                case 6:
                    for (int col = 0; col < 8; col++) {
                        board[row][col] = new Pawn("♟", Player.PLAYER_1, row, col);
                    }

                    break;

                case 7:

                    board[row][0] = new Rook("♜", Player.PLAYER_1, row, 0);
                    board[row][1] = new Knight("♞", Player.PLAYER_1, row, 1);
                    board[row][2] = new Bishop("♝", Player.PLAYER_1, row, 2);
                    board[row][3] = new King("♚", Player.PLAYER_1, row, 3);
                    board[row][4] = new Queen("♛", Player.PLAYER_1, row, 4);
                    board[row][5] = new Bishop("♝", Player.PLAYER_1, row, 5);
                    board[row][6] = new Knight("♞", Player.PLAYER_1, row, 6);
                    board[row][7] = new Rook("♜", Player.PLAYER_1, row, 7);

                    break;

                default:

                    for (int col = 0; col < 8; col++) {
                        board[row][col] = new EmptySquare(row, col);
                    }

                    break;

            }

        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    chessBoard.add(createCell(row, col, piece), col, row);
                }
            }
        }
    }

    private ChessPiece createCell(int row, int col, ChessPiece piece) {
        piece.setStyle("-fx-background-color: " + determineCellColor(row, col) + ";");
        piece.setOnMouseClicked(event -> handleCellClick(row, col));
        piece.customize(); // Personalize a peça
        return piece;
    }

    private String determineCellColor(int row, int col) {
        // Lógica para determinar a cor da célula com base na posição no tabuleiro
        return (row + col) % 2 == 0 ? "white" : "brown";
    }

    private void handleCellClick(int row, int col) {
        if (selectedPieceRow == -1 && selectedPieceCol == -1) {
            // Primeiro clique (selecionar a peça)
            ChessPiece selectedPiece = board[row][col];
            if (selectedPiece != null) {
                selectedPieceRow = row;
                selectedPieceCol = col;
                selectedPiece.setStyle("-fx-border-color: #00FF00;");
            }

            System.out.println("selecionou: " + selectedPieceRow + " " + selectedPieceCol);
        } else {
            // Segundo clique (mover a peça)
            ChessPiece selectedPiece = board[selectedPieceRow][selectedPieceCol];
            if (selectedPiece != null && selectedPiece.move(row, col, board)) {
                // Movimento válido, atualize o tabuleiro e reinicie o estado de seleção
                board[row][col] = selectedPiece;
                board[selectedPieceRow][selectedPieceCol] = new EmptySquare(selectedPieceRow, selectedPieceCol);
                selectedPieceRow = -1;
                selectedPieceCol = -1;
                // switchPlayer(); // Mude para o próximo jogador após cada movimento
                updateChessBoard();
                // Atualize a exibição do tabuleiro
            } else {
                // Movimento inválido, limpe o estado de seleção
                selectedPieceRow = -1;
                selectedPieceCol = -1;
            }
        }
    }

    private void updateChessBoard() {
        // Atualize a interface gráfica com base no estado atual do tabuleiro
        chessBoard.getChildren().clear();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    chessBoard.add(createCell(row, col, piece), col, row);
                }
            }
        }
    }
}
