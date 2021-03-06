package domain.connection;

import controller.*;
import domain.definer.Attribute;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




public class Client {


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
            StringBuilder ruleBuilder = new StringBuilder();
            if(controller instanceof AttributeCompareRuleController) {
                AttributeCompareRuleController attributeCompareRuleController = (AttributeCompareRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof AttributeRangeRuleController) {
                AttributeRangeRuleController attributeRangeRuleController = (AttributeRangeRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof AttributeListRuleController) {
                AttributeListRuleController attributeListRuleController = (AttributeListRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof AttributeOtherRuleController) {
                AttributeOtherRuleController attributeOtherRuleController = (AttributeOtherRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof TupleCompareRuleController) {
                TupleCompareRuleController tupleCompareRuleController=(TupleCompareRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof InterEntityCompareRuleController) {
                InterEntityCompareRuleController interEntityCompareRuleController =(InterEntityCompareRuleController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while((lineIn = in.readLine()) != null){
                    ruleBuilder.append(lineIn + "\n");
                }
            }
            if(controller instanceof ViewRulesController){
                ViewRulesController viewRulesController = (ViewRulesController) controller;
                line = transportRule.toJSONString();
                out.writeUTF(line);
                while ((lineIn = in.readLine()) != null){
                    ruleBuilder.append( lineIn );
                }

            }



            try {
                in.close();
                out.close();
                socket.close();
                System.out.println("closing connection");
                System.out.println(ruleBuilder.toString());
                if(controller instanceof AttributeCompareRuleController) {
                    AttributeCompareRuleController attributeCompareRuleController = (AttributeCompareRuleController) controller;
                    attributeCompareRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                }
                if(controller instanceof AttributeRangeRuleController) {
                    AttributeRangeRuleController attributeRangeRuleController = (AttributeRangeRuleController) controller;
                    attributeRangeRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                }
                if(controller instanceof AttributeListRuleController) {
                    AttributeListRuleController attributeListRuleController = (AttributeListRuleController) controller;
                    attributeListRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                }
                if(controller instanceof AttributeOtherRuleController) {
                    AttributeOtherRuleController attributeOtherRuleController = (AttributeOtherRuleController) controller;
                    attributeOtherRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                }
                if(controller instanceof TupleCompareRuleController) {
                    TupleCompareRuleController tupleCompareRuleController = (TupleCompareRuleController) controller;
                    tupleCompareRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                }
                if(controller instanceof InterEntityCompareRuleController) {
                    InterEntityCompareRuleController interEntityCompareRuleController =(InterEntityCompareRuleController) controller;
                    interEntityCompareRuleController.setGeneratedPreviewArea(ruleBuilder.toString());
                    System.out.println(ruleBuilder.toString());
                }
                if(controller instanceof ViewRulesController){
                    ViewRulesController viewRulesController = (ViewRulesController) controller;
                    viewRulesController.showAlert(ruleBuilder.toString());

                }





            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }


        } catch (IOException i) {
            System.out.println(i);
        }



    }
}
