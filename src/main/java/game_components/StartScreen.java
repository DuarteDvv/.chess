package game_components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import chessServer.ChessServer;

public class StartScreen implements Initializable{

    @FXML
    private Label statusP1;

    @FXML
    private Label statusP2;

    public void initialize(URL a, ResourceBundle b) {
        // Adicione listeners para monitorar alterações nos estados de conexão
        ChessServer.statusP1Property().addListener(new ChangeListener<ChessServer.StatusConexao>() {
            @Override
            public void changed(ObservableValue<? extends ChessServer.StatusConexao> observable, ChessServer.StatusConexao oldValue, ChessServer.StatusConexao newValue) {
                updateStatusLabels();
            }
        });

        ChessServer.statusP2Property().addListener(new ChangeListener<ChessServer.StatusConexao>() {
            @Override
            public void changed(ObservableValue<? extends ChessServer.StatusConexao> observable, ChessServer.StatusConexao oldValue, ChessServer.StatusConexao newValue) {
                updateStatusLabels();
            }
        });

        // Atualize os rótulos inicialmente
        updateStatusLabels();
    }

    private void updateStatusLabels() {
        // Obtém os estados de conexão dos jogadores
        ChessServer.StatusConexao stsP1 = ChessServer.getStatusP1();
        ChessServer.StatusConexao stsP2 = ChessServer.getStatusP2();

        // Atualiza os textos dos labels
        statusP1.setText(stsP1 == ChessServer.StatusConexao.CONECTADO ? "CONNECTED" : "WAITING CONNECTION");
        statusP2.setText(stsP2 == ChessServer.StatusConexao.CONECTADO ? "CONNECTED" : "WAITING CONNECTION");
    }
}
