package ru.gmotrich.serverProtocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gmotrich.protocol.entity.BlockSelection;
import ru.gmotrich.protocol.entity.Identification;
import ru.gmotrich.protocol.entity.MaskingOfInputs;
import ru.gmotrich.protocol.entity.ResultOfCommandExecution;
import ru.gmotrich.protocol.entity.dataStructures.*;
import ru.gmotrich.protocol.entity.message.Header;
import ru.gmotrich.protocol.entity.message.Message;

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

            Message message = (Message) objectInputStream.readObject();

            Header header = message.getHeader();
            Object clientObject = message.getData();

            logger.info("Message source: " + header.getSource());

            replyMessage(header, clientObject, objectOutputStream);

            clientSocket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void replyMessage(Header header, Object data, ObjectOutputStream objectOutputStream) throws IOException {
        String head = header.toString();
        if (data instanceof Slice slice) {
            String message = head + slice;
            objectOutputStream.writeObject("Slice");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof SettingTheMode mode) {
            String message = head + mode;
            objectOutputStream.writeObject("SettingTheMode");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof Lockout lockout) {
            String message = head + lockout;
            objectOutputStream.writeObject("Lockout");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof ChannelSetting channel) {
            String message = head + channel;
            objectOutputStream.writeObject("ChannelSetting");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof BlackBoxSetup blackBox) {
            String message = head + blackBox;
            objectOutputStream.writeObject("BlackBoxSetup");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof UnloadingBlackBox unloading) {
            String message = head + unloading;
            objectOutputStream.writeObject("UnloadingBlackBox");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof BlockSelection blockSelection) {
            String message = head + blockSelection;
            objectOutputStream.writeObject("BlockSelection");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof Identification identification) {
            String message = head + identification;
            objectOutputStream.writeObject("Identification");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof MaskingOfInputs maskingOfInputs) {
            String message = head + maskingOfInputs;
            objectOutputStream.writeObject("MaskingOfInputs");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        } else if (data instanceof ResultOfCommandExecution result) {
            String message = head + result;
            objectOutputStream.writeObject("ResultOfCommandExecution");
            objectOutputStream.flush();
            logger.info("Sent object to client: " + message);
        }
    }
}


