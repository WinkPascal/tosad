package domain.connection;

import controller.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




public class Client  {


    private Socket socket = null;

    public Client(String address, int port, TransportRule transportRule, Controller controller)  throws NullPointerException {
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
                if(controller instanceof AttributeCompareRuleController) {
                    AttributeCompareRuleController attributeCompareRuleController = (AttributeCompareRuleController) controller;
                    attributeCompareRuleController.setGeneratedPreviewArea(test);
                }
                if(controller instanceof AttributeRangeRuleController){
                    AttributeRangeRuleController attributeRangeRuleController = (AttributeRangeRuleController) controller;
                    attributeRangeRuleController.setGeneratedPreviewArea(test);
                }
                if (controller instanceof TupleCompareRuleController){
                    TupleCompareRuleController tupleCompareRuleController = (TupleCompareRuleController) controller;
                    tupleCompareRuleController.setGeneratedPreviewArea(test);
                }
                if(controller instanceof ViewRulesController){
                    ViewRulesController viewRulesController = (ViewRulesController) controller;

                }





            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }


        } catch (IOException i) {
            System.out.println(i);
        }



    }
}
