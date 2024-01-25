package chessServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.beans.property.SimpleObjectProperty;

public final class ChessServer {
    private static ServerSocket serverSocket;
    private static Socket player1Socket;
    private static Socket player2Socket;
    private static SimpleObjectProperty<StatusConexao> statusP1 = new SimpleObjectProperty<>(StatusConexao.AGUARDANDO_CONEXAO);
    private static SimpleObjectProperty<StatusConexao> statusP2 = new SimpleObjectProperty<>(StatusConexao.AGUARDANDO_CONEXAO);

    public enum StatusConexao {
        CONECTADO,
        AGUARDANDO_CONEXAO
    }

    public ChessServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForPlayers() {
        try {
            System.out.println("Waiting for player 1...");
            player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected");
            setStatusP1(StatusConexao.CONECTADO);

            Thread threadP1 = new Thread(new PlayerHandler(player1Socket));

            System.out.println("Waiting for player 2...");
            player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected");
            setStatusP2(StatusConexao.CONECTADO);

            Thread threadP2 = new Thread(new PlayerHandler(player2Socket));

            threadP1.start();
            threadP2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SimpleObjectProperty<StatusConexao> statusP1Property() {
        return statusP1;
    }

    public static SimpleObjectProperty<StatusConexao> statusP2Property() {
        return statusP2;
    }

    public static Socket getPlayer1Socket() {
        return player1Socket;
    }

    public static Socket getPlayer2Socket() {
        return player2Socket;
    }

    public static StatusConexao getStatusP1() {
        return statusP1.get();
    }

    public static StatusConexao getStatusP2() {
        return statusP2.get();
    }

    private static void setStatusP1(StatusConexao status) {
        statusP1.set(status);
    }

    private static void setStatusP2(StatusConexao status) {
        statusP2.set(status);
    }

    public static void main(String[] args) {
        ChessServer server = new ChessServer(8080);
        server.waitForPlayers();
    }
}
