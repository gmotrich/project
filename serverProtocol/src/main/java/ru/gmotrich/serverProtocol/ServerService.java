package ru.gmotrich.serverProtocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gmotrich.protocol.entity.BlockSelection;
import ru.gmotrich.protocol.entity.Identification;
import ru.gmotrich.protocol.entity.MaskingOfInputs;
import ru.gmotrich.protocol.entity.ResultOfCommandExecution;
import ru.gmotrich.protocol.entity.dataStructures.*;
import ru.gmotrich.protocol.entity.message.Header;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService {

    private static final Logger logger = LoggerFactory.getLogger(ServerService.class);

    public void start() throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            logger.info("Server started. Waiting for client to connect...");

            Socket clientSocket = serverSocket.accept();
            logger.info("Client connected.");

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            Header clientObject1 = (Header) objectInputStream.readObject();
            Object clientObject2 = objectInputStream.readObject();

            logger.info("Message source: " + clientObject1.getSource());

            replyMessage(clientObject1, clientObject2, objectOutputStream);

            clientSocket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void replyMessage(Header header, Object data, ObjectOutputStream objectOutputStream) throws IOException {
        String head = header.toString();
        if (data instanceof Slice slice) {
            String message = head + slice.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof SettingTheMode mode) {
            String message = head + mode.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof Lockout lockout) {
            String message = head + lockout.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof ChannelSetting channel) {
            String message = head + channel.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof BlackBoxSetup blackBox) {
            String message = head + blackBox.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof UnloadingBlackBox unloading) {
            String message = head + unloading.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof BlockSelection blockSelection) {
            String message = head + blockSelection.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof Identification identification) {
            String message = head + identification.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof MaskingOfInputs maskingOfInputs) {
            String message = head + maskingOfInputs.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof ResultOfCommandExecution result) {
            String message = head + result.toString();
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        }
    }
}


