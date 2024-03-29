package game_components;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import chessServer.ServerConnection;

public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) {
        try {
            ServerConnection serverConnection = new ServerConnection();
            scene = new Scene(loadFXML("startScreen"), 640, 480);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            // Tratar a exceção de falha na conexão
            exibirMensagemDeErro("Falha ao conectar ao servidor: " + e.getMessage());
            encerrarAplicacao();
        }
    }

    private void exibirMensagemDeErro(String mensagem) {
        // Implementar a lógica para exibir mensagens de erro ao usuário
        System.err.println("Erro: " + mensagem);
        // Aqui você pode usar, por exemplo, JavaFX Alert ou outro mecanismo para mostrar mensagens de erro na interface gráfica.
    }

    private void encerrarAplicacao() {
        // Implementar a lógica para encerrar a aplicação
        System.exit(1); // Código de saída não nulo indica um encerramento anormal
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
