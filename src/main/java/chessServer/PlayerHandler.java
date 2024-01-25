package chessServer;

import java.net.Socket;

public class PlayerHandler implements Runnable{
    private Socket connection;

    public PlayerHandler(Socket newConnection){
        this.connection = newConnection;

    }

    public void run(){

    }
    
}
