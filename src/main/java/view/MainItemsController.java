package view;

import java.io.IOException;
import java.text.ParseException;

public class MainItemsController {

    private Main main = Main.getInstance();

    public void openNewAttributeRangeRule() throws IOException, ParseException {
        main.showNewAttributeRangeRule();
    }


    }


