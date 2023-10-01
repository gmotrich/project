package ru.gmotrich.clientProtocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gmotrich.protocol.entity.message.Header;
import ru.gmotrich.protocol.entity.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    public void sendMessage(Message message) throws IOException {
        try {
            Socket socket = new Socket("172.10.1.2", 8080);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to server: ");

            Object serverObject = objectInputStream.readObject();
            logger.info("Received object from server: " + serverObject);

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
