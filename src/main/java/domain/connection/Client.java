package domain.connection;

import controller.AttributeCompareRuleController;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




public class Client {

    private AttributeCompareRuleController attributeCompareRuleController = AttributeCompareRuleController.getInstance();
    private Socket socket = null;

    public Client(String address, int port, TransportRule transportRule, AttributeCompareRuleController attributeCompareRuleController)  throws NullPointerException {
        this.attributeCompareRuleController = attributeCompareRuleController;
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");


        } catch (IOException u) {
            System.out.println(u);
        }

        String lineIn =null;
        String line = "";



        try {


            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            line = transportRule.toJSONString();
            out.writeUTF(line);
            StringBuilder ruleBuilder = new StringBuilder();


            while((lineIn = in.readLine()) != null){
                ruleBuilder.append(lineIn + "\n");
            }

            try {
                in.close();
                out.close();
                socket.close();
                System.out.println("closing connection");
                String test = ruleBuilder.toString();
                System.out.println(test);
                attributeCompareRuleController.setGeneratedPreviewArea(test);





            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }


        } catch (IOException i) {
            System.out.println(i);
        }



    }
}
