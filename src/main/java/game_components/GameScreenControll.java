package game_components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GameScreenControll {

    @FXML
    private GridPane chessBoard;

    public void initialize() {
        // Configurar o tabuleiro
        initializeChessBoard();
    }

    private void initializeChessBoard() {
        // Arrays para representar as peças em cada linha e coluna do tabuleiro
        String[][] pieces = {
                { "♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖" },
                { "♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙" },
                { "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "" },
                { "", "", "", "", "", "", "", "" },
                { "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟" },
                { "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜" }
        };

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Label cell = createCell(row, col, pieces[row][col]);
                chessBoard.add(cell, col, row);
            }
        }
    }

    private Label createCell(int row, int col, String piece) {
        Label cell = new Label(piece);
        cell.setMinSize(60, 60); // Tamanho mínimo para visualização
        cell.setStyle("-fx-background-color: " + determineCellColor(row, col) + ";");

        // Configurar fonte, centralização e ocupação total do espaço
        cell.setFont(javafx.scene.text.Font.font(45));
        cell.setAlignment(javafx.geometry.Pos.CENTER);
        cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Adicione um manipulador de eventos para clicar nas células
        cell.setOnMouseClicked(event -> handleCellClick(row, col));

        return cell;
    }

    private String determineCellColor(int row, int col) {
        // Lógica para determinar a cor da célula com base na posição no tabuleiro
        return (row + col) % 2 == 0 ? "white" : "lightgray";
    }

    private void handleCellClick(int row, int col) {
        // Lógica para lidar com o clique na célula
        System.out.println("Clicou na célula: " + row + ", " + col);
    }
}
