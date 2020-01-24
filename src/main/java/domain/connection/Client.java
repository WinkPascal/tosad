package domain.connection;

// A Java program for a Client

import controller.AttributeCompareRuleController;
import java.net.*;
import java.io.*;



public class Client {


    private Socket socket = null;
    private AttributeCompareRuleController attributeCompareRuleController = AttributeCompareRuleController.getInstance();




    public Client(String address, int port, TransportRule transportRule, AttributeCompareRuleController attributeCompareRuleController) throws NullPointerException {
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
                String test = ruleBuilder.toString().substring(1);
                System.out.println(test);
                attributeCompareRuleController.showAlert();





            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }


        } catch (IOException i) {
            System.out.println(i);
        }



    }


}