package chessServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public final class ServerConnection {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;
    private Socket socket;
    private BufferedReader serverIn;
    private BufferedWriter clientOut;

    public ServerConnection() throws IOException {
        try {
            establishConnection();
        } catch (IOException e) {
            // Tratar a exceção específica para falha na conexão
            System.err.println("Erro ao conectar ao servidor: " + e.getMessage());
            throw e;
        }
    }

    private void establishConnection() throws IOException {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (UnknownHostException e) {
            // Tratar a exceção específica para host desconhecido
            System.err.println("Host desconhecido: " + e.getMessage());
            throw e;
        } catch (IOException e) {
            // Tratar outras exceções de E/S
            System.err.println("Erro de E/S ao conectar ao servidor: " + e.getMessage());
            throw e;
        }
    }

    public void closeConnection() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader getServerIn() {
        return serverIn;
    }

    public BufferedWriter getClientOut() {
        return clientOut;
    }
}
