package game_components;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import chessServer.ServerConnection;


import java.io.IOException;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) {
        ChessServerConnectionService connectionService = new ChessServerConnectionService();

        // Configurar o serviço para exibir a janela do aplicativo quando a conexão for bem-sucedida
        connectionService.setOnSucceeded(workerStateEvent -> {
            try {
                scene = new Scene(loadFXML("gameScreen"), 640, 480);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.show();
        });

        // Configurar o serviço para tratar erros de conexão
        connectionService.setOnFailed(workerStateEvent -> {
            exibirMensagemDeErro("Falha ao conectar ao servidor: " + connectionService.getException().getMessage());
            connectionService.reset();
            encerrarAplicacao();
        });

        // Iniciar o serviço
        connectionService.start();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void exibirMensagemDeErro(String mensagem) {
        System.err.println("Erro: " + mensagem);
        // Aqui você pode usar, por exemplo, JavaFX Alert ou outro mecanismo para mostrar mensagens de erro na interface gráfica.
    }

    private void encerrarAplicacao() {
        System.exit(1);
    }

    public static void main(String[] args) {
        launch();
    }
}

class ChessServerConnectionService extends Service<Void> {
    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                ServerConnection connection = new ServerConnection();
                return null;
            }
        };
    }
}
