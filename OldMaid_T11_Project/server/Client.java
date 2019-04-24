package server;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import javax.swing.*;
import java.io.IOException;

public class Client{
    Public Client client = new Client();

    Client(){
        client.start();
        //register packets
        client.getKryo().register(Packet.class);
        client.getKryo().register(GamePacket.class);
        client.getKryo().register(LobbyPacket.class);
    }

}