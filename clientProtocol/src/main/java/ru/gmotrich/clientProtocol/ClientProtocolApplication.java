package ru.gmotrich.clientProtocol;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.gmotrich.protocol.entity.dataStructures.SettingTheMode;
import ru.gmotrich.protocol.entity.enums.MessageType;
import ru.gmotrich.protocol.entity.enums.NodeType;
import ru.gmotrich.protocol.entity.message.Header;
import ru.gmotrich.protocol.entity.message.Message;

import java.io.IOException;

@SpringBootApplication
public class ClientProtocolApplication {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-client.xml");
        ClientService client = (ClientService) context.getBean("clientService");


        Header header = new Header();
        header.setId(1);
        header.setSource(NodeType.POINT_USER);
        header.setTimeout(0);
        header.setType(MessageType.MESSAGE_SET_MODE);
        header.setDataLength(0);
        header.setUnitId(0);

        SettingTheMode sliceMessage = new SettingTheMode();
        sliceMessage.setMode('v');
        sliceMessage.setTime(10);

        Message message = new Message();
        message.setHeader(header);
        message.setData(sliceMessage);

        try {
            client.sendMessage(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
