package chessServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public final class ChessServer {
    private static ServerSocket serverSocket;
    private static Socket player1Socket;
    private static Socket player2Socket;
    private static StatusConexao statusP1 = StatusConexao.AGUARDANDO_CONEXAO;
    private static StatusConexao statusP2 = StatusConexao.AGUARDANDO_CONEXAO;

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

        // Envia mensagem ao Player 1
        sendToPlayer(player1Socket, "Waiting second player...");

        System.out.println("Waiting for player 2...");
        player2Socket = serverSocket.accept();
        System.out.println("Player 2 connected");
        setStatusP2(StatusConexao.CONECTADO);

        // Envia mensagem ao Player 1 e Player 2
        sendToPlayers("Game ready... start in 5 seconds");
        Thread.sleep(5000);

        Thread threadP1 = new Thread(new PlayerHandler(player1Socket));
        Thread threadP2 = new Thread(new PlayerHandler(player2Socket));

        threadP1.start();
        threadP2.start();

    } catch (IOException e) {
        e.printStackTrace();
    }
    catch (InterruptedException e){
        e.printStackTrace();
    }
}

    // Adicione o método para enviar mensagens aos jogadores
    private void sendToPlayers(String message) {
        sendToPlayer(player1Socket, message);
        sendToPlayer(player2Socket, message);
    }

    private void sendToPlayer(Socket playerSocket, String message) {
        try {
            BufferedWriter playerOut = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
            playerOut.write(message + "\n");
            playerOut.flush();
            playerOut.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Socket getPlayer1Socket() {
        return player1Socket;
    }

    public static Socket getPlayer2Socket() {
        return player2Socket;
    }

    public static StatusConexao getStatusP1() {

        return statusP1;
    }

    public static StatusConexao getStatusP2() {

        return statusP2;
    }

    private synchronized static void setStatusP1(StatusConexao status) {
        statusP1 = status;
    }
    
    private synchronized static void setStatusP2(StatusConexao status) {
        statusP2 = status;
    }

    public static void main(String[] args) {
        ChessServer server = new ChessServer(8080);
        server.waitForPlayers();
    }
}
