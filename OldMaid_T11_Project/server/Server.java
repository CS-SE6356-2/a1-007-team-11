package server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

public class Server{
    public Server server = new Server();

    //default constructor
    Server(){
        server.start();
        server.bind(54555, 54777);

        //register packets
        server.getKryo().register(Packet.class);
        server.getKryo().register(GamePacket.class);
        server.getKryo().register(LobbyPacket.class);
    }

}