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
    private static StatusConexao statusP1;
    private static StatusConexao statusP2;

    public enum StatusConexao {
        CONECTADO,
        AGUARDANDO_CONEXAO
    }

    public ChessServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            waitForPlayers();
           
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
        threadP1.start();

        // Envia mensagem ao Player 1
        sendToPlayer(player1Socket, "Waiting second player...");

        System.out.println("Waiting for player 2...");
        player2Socket = serverSocket.accept();
        sendToPlayer(player1Socket, "Player 2 connected");
        System.out.println("Player 2 connected");
        setStatusP2(StatusConexao.CONECTADO);

        
        Thread threadP2 = new Thread(new PlayerHandler(player2Socket));
        threadP2.start();

        // Envia mensagem ao Player 1 e Player 2
        sendToPlayers("Game ready... start in 5 seconds");

    } catch (IOException e) {
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

    private static void setStatusP1(StatusConexao status) {
        statusP1 = status;
    }
    
    private static void setStatusP2(StatusConexao status) {
        statusP2 = status;
    }

    public static void main(String[] args) {
        ChessServer server = new ChessServer(8080);
        
    }
}
