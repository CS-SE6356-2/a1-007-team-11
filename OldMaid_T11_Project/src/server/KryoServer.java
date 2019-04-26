package server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import packets.GamePacket;
import packets.LobbyPacket;
import packets.Packet;

import java.io.IOException;

public class KryoServer{
    public Server server = new Server();

    //default constructor
    KryoServer(){

        server.start();
        try {
            server.bind(54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //register packets
        server.getKryo().register(Packet.class);
        server.getKryo().register(GamePacket.class);
        server.getKryo().register(LobbyPacket.class);

    }

}