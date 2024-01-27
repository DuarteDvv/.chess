package game_components;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameScreenControll {

    @FXML
    private GridPane chessBoard;

    public void initialize() {
        // Configurar o tabuleiro
        initializeChessBoard();
    }

    private void initializeChessBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane cell = createCell(row, col);
                chessBoard.add(cell, col, row);
            }
        }
    }

    private StackPane createCell(int row, int col) {
        StackPane cell = new StackPane();
        cell.setMinSize(60, 60);  // Tamanho mínimo para visualização

        // Adicione um manipulador de eventos para clicar nas células
        cell.setOnMouseClicked(event -> handleCellClick(row, col));

        // Adicione estilos ou outras configurações conforme necessário
        cell.setStyle("-fx-background-color: " + determineCellColor(row, col) + ";");

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
