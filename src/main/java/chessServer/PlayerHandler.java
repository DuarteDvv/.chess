package chessServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import game_components.App;



public class PlayerHandler implements Runnable {
    private Socket playerSocket;

    public PlayerHandler(Socket playerSocket) {
        this.playerSocket = playerSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader playerIn = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            String message;

            while ((message = playerIn.readLine()) != null) {
                // Processa a mensagem recebida

                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

