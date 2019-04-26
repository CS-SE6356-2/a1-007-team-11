package server;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import packets.GamePacket;
import packets.LobbyPacket;
import packets.Packet;

import javax.swing.*;
import java.io.IOException;


public class KryoClient{
    public Client client = new Client();

    KryoClient(){
        client.start();
        //register packets
        client.getKryo().register(Packet.class);
        client.getKryo().register(GamePacket.class);
        client.getKryo().register(LobbyPacket.class);


    }

}