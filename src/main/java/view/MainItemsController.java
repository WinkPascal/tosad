package view;

import java.io.IOException;
import java.text.ParseException;

public class MainItemsController {

    private Main main = Main.getInstance();

    public void openNewAttributeRangeRule() throws IOException, ParseException {
        main.showNewAttributeRangeRule();
    }
    public void openNewAttributeCompareRule() throws IOException, ParseException {
        main.showNewAttributeCompareRule();
    }
    public void openNewAttributeListRule() throws IOException, ParseException{
        main.showNewAttributeListRule();
    }
    public void openNewAttributeOtherRule() throws IOException, ParseException{
        main.showNewAttributeOtherRule();
    }
    public void openNewTupleCompareRule() throws IOException, ParseException{
        main.showNewTupleCompareRule();
    }
    public void openNewInterEntityCompareRule() throws IOException{
        main.showNewInterEntityCompareRule();
    }
    public void openNewModifyRule() throws IOException{
        main.showNewModifyRule();

    }
    public void openAllRules() throws IOException{
        main.showAllRules();
    }


    }


